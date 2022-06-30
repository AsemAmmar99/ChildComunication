package com.inventure.childcomunication.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.inventure.childcomunication.R;
import com.inventure.childcomunication.helpers.MySharedPreference;

public class Splash extends AppCompatActivity {
    ImageView imageView;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.image);

        MySharedPreference.init(this);

        mediaPlayer = MediaPlayer.create(this, R.raw.splash);
        mediaPlayer.start();


        Glide.with(this).load(R.drawable.gif_splash).into(imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if ("".equals(MySharedPreference.getGender())) {
                    mediaPlayer.stop();
                    mediaPlayer = null;
                    Intent intent = new Intent(Splash.this, SelectColor.class);
                    startActivity(intent);
                    finish();
                } else {
                    mediaPlayer.stop();
                    mediaPlayer = null;
                    Intent intent = new Intent(Splash.this, Home.class);
                    startActivity(intent);
                    finish();
                }


            }
        }, 4000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }
}
