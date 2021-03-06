package com.tektonlabs.odc_android.utils;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface Services {
    @GET("/search?media=music")
    void listAlbums(@Query("term") String term, @Query("entity") String entity, Callback<JsonObject> callback);
}
