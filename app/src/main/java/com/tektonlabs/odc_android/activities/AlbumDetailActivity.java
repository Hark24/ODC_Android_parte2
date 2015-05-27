package com.tektonlabs.odc_android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.tektonlabs.odc_android.R;

/**
 * Created by rubymobile on 5/27/15.
 */
public class AlbumDetailActivity extends Activity {
    private String albumDetailJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        getDataMainActivity();
    }

    /* Recuperar Data de la actividad main */
    private void getDataMainActivity(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            albumDetailJson = extras.getString("ALBUM_DETAIL");
            Log.e("Album DETAIL", albumDetailJson);
        }
    }
}
