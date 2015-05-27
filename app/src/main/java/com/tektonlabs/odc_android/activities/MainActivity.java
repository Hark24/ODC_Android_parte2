package com.tektonlabs.odc_android.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.tektonlabs.odc_android.R;
import com.tektonlabs.odc_android.adapters.AlbumAdapter;
import com.tektonlabs.odc_android.models.Album;
import com.tektonlabs.odc_android.utils.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.client.UrlConnectionClient;
import retrofit.converter.GsonConverter;

public class MainActivity extends Activity {

    private Services services;
    private List<Album> albums;

    private ListView lv_albums;
    private EditText et_search;
    private Button btn_search;
    private TextView tv_not_found;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupElements();
        setupServices();
        setupActions();
    }

    /* Setup Elements */

    private void setupElements(){
        lv_albums = (ListView)findViewById(R.id.lv_albums);
        et_search = (EditText)findViewById(R.id.et_search);
        btn_search = (Button)findViewById(R.id.btn_search);
        tv_not_found = (TextView)findViewById(R.id.tv_not_found);

        progress = new ProgressDialog(this);
        progress.setTitle("En progreso");
        progress.setMessage("Buscando ...");
        progress.setIndeterminate(false);
    }

    /* Setup Actions*/
    private void setupActions(){
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_term = et_search.getText().toString();
                removeKeyboard();
                if (!search_term.isEmpty()) {
                    progress.show();
                    getDataRequest(search_term);
                }
            }
        });
    }

    /**************************** RETROFIT ****************************/

    /* Configuracion de Retrofit */

    private void setupServices(){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        UrlConnectionClient urlClient = new UrlConnectionClient();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://itunes.apple.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(urlClient)
                .setConverter(new GsonConverter(gson))
                .build();

        services = restAdapter.create(Services.class);
    }

    /* Realizar el request y obtener la data  */

    private void getDataRequest(String term){
        albums=new ArrayList<>();
        services.listAlbums(term, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jsonObject, Response response) {
                if (response.getReason().equals("OK")) {
                    successRequest(jsonObject);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                failedRequest(error);
            }
        });
    }

    /* Consulta sin errores */

    private void successRequest(JsonObject responseJsonObject){
        JsonArray results = responseJsonObject.get("results").getAsJsonArray();
        int num_results = responseJsonObject.get("resultCount").getAsInt();
        if (num_results != 0){
            tv_not_found.setVisibility(View.GONE);
            lv_albums.setVisibility(View.VISIBLE);
            for(JsonElement responseObject : results){
                JsonObject jsonObject = responseObject.getAsJsonObject();
                Album album = Album.parseAlbum(jsonObject);
                albums.add(album);
            }
            setupAlbumAdapter();
        }
        else{
            tv_not_found.setVisibility(View.VISIBLE);
            lv_albums.setVisibility(View.GONE);
        }
        progress.dismiss();
    }

    /* Mostrando errores */

    private void failedRequest(RetrofitError error){
        String error_message = "ERROR";
        if (error.getResponse() != null) {
            error_message = error.getMessage();
        }
        else{
            if (error.getKind().equals(RetrofitError.Kind.NETWORK)){
                error_message = "Problem with internet connection";
            }
        }
        Toast.makeText(this, error_message, Toast.LENGTH_LONG).show();
        progress.dismiss();
    }

    /**************************** LISTVIEW ****************************/

    /* Setup Adapter a la Lista */

    private void setupAlbumAdapter(){
        AlbumAdapter albumAdapter = new AlbumAdapter(this, albums);
        lv_albums.setAdapter(albumAdapter);
    }

    /* Acción al hacer clic en un elemento */
    public void onItemClick(int mPosition) {
        Album tempValues = albums.get(mPosition);
        Gson gson = new Gson();
        String json = gson.toJson(tempValues);
        openAlbumDetail(json);
    }

    /* Remover teclado */
    private void removeKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        if(imm.isAcceptingText()) {
            imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
        }
    }

    /* Abrir otra actividad */
    private void openAlbumDetail(String albumDetail){
        Intent intent = new Intent(this, AlbumDetailActivity.class);
        intent.putExtra("ALBUM_DETAIL", albumDetail);
        startActivity(intent);
    }

}
