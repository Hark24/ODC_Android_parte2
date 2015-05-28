package com.tektonlabs.odc_android.activities;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.squareup.picasso.Picasso;
import com.tektonlabs.odc_android.R;


/**
 * Created by rubymobile on 5/27/15.
 */
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
    MediaPlayer player = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        getDataMainActivity();

        play=(Button)findViewById(R.id.play);
        pause=(Button)findViewById(R.id.pause);

        artistName=(TextView)findViewById(R.id.tv_artistname);
        songname=(TextView)findViewById(R.id.tv_songname);
        imageurl=(ImageView)findViewById(R.id.imageURL);

        artistName.setText(artist);
        songname.setText(songName);


        Picasso.with(getApplication()).load(imageURL).into(imageurl);



        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource(previewURL);
                    player.prepare();
                    player.start();

                } catch (Exception e) {
                    // TODO: handle exception
                }

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
}
