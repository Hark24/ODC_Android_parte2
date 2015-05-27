package com.tektonlabs.odc_android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.bind.DateTypeAdapter;
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
    private AlbumAdapter albumAdapter;

    private ListView lv_albums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupElements();
        setupServices();
        getDataRequest();
    }

    /* Setup Elements */

    private void setupElements(){
        lv_albums = (ListView)findViewById(R.id.lv_albums);
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

    private void getDataRequest(){
        albums=new ArrayList<>();
        services.listAlbums("beatles", new Callback<JsonObject>() {
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
        for(JsonElement responseObject : results){
            JsonObject jsonObject = responseObject.getAsJsonObject();
            Album album = Album.parseAlbum(jsonObject);
            albums.add(album);
        }
        setupAlbumAdapter();
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
    }


    /**************************** LISTVIEW ****************************/
    private void setupAlbumAdapter(){
        albumAdapter = new AlbumAdapter( this,albums );
        lv_albums.setAdapter(albumAdapter);
    }

}
