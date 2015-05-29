package com.tektonlabs.odc_android.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.squareup.picasso.Picasso;
import com.tektonlabs.odc_android.R;

public class SongDetailActivity extends Activity {
    private String artist;
    private String songName;
    private String previewURL;
    private String imageURL;

    private TextView artistName;
    private TextView songname;
    private Button play;
    private Button pause;
    private ImageView imageurl;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);

        getDataMainActivity();
        setupElements();
        setupData();
        setupMediaPlayer();
        setupActions();
    }

    /* Seteando Elementos */
    private void setupElements(){
        play=(Button)findViewById(R.id.play);
        pause=(Button)findViewById(R.id.pause);

        artistName=(TextView)findViewById(R.id.tv_artistname);
        songname=(TextView)findViewById(R.id.tv_songname);
        imageurl=(ImageView)findViewById(R.id.imageURL);
    }

    /* Setup Data*/
    private void setupData(){
        player = new MediaPlayer();
        artistName.setText(artist);
        songname.setText(songName);
        Picasso.with(getApplication()).load(imageURL).into(imageurl);
    }

    /* Preparando media player */
    private void setupMediaPlayer(){
        try {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(previewURL);
            player.prepare();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /* Seteando Acciones a botonoes */
    private void setupActions(){
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
            }
        });
    }

    /* Recuperar Data de la actividad main */
    private void getDataMainActivity(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            artist = extras.getString("ARTIST");
            songName = extras.getString("SONG_NAME");
            previewURL = extras.getString("PREVIEW_URL");
            imageURL = extras.getString("IMAGE");

        }
    }

    /* Back pressed */
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }
}
