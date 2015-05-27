package com.tektonlabs.odc_android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.tektonlabs.odc_android.models.Album;
import com.tektonlabs.odc_android.utils.Services;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupServices();
        getDataRequest();
    }

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

    private void getDataRequest(){
        services.listAlbums("beatles", new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jsonObject, Response response) {
                if (response.getReason().equals("OK")) {
                    successRequest(jsonObject);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("ERROR", error.getMessage());
            }
        });
    }

    private void successRequest(JsonObject responseJsonObject){
        JsonArray results = responseJsonObject.get("results").getAsJsonArray();
        for(JsonElement responseObject : results){
            JsonObject jsonObject = responseObject.getAsJsonObject();
            Album album = Album.parseAlbum(jsonObject);
            Log.e("Album", album.toString());
        }
    }

}
