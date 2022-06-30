package com.inventure.childcomunication.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.core.app.ActivityCompat;

import com.inventure.childcomunication.R;
import com.inventure.childcomunication.activity.Home;

import java.io.File;
import java.io.IOException;

public class MyUtils {

    public static final String MAIN_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + "ChildCommunication/";
    public static final String DATA_PATH = "ChildCommunication/";
    public static String TAG = "";
    public static final String PURBLE_COLOR = "purble";
    public static final String YELLOW_COLOR = "yellow";
    public static final String BLUE_COLOR = "blue";
    public static final String GIRL = "girl";
    public static final String BOY = "boy";
    public static final int TYPE_WIFI = 1;
    public static final int TYPE_MOBILE = 2;
    public static final int TYPE_NOT_CONNECTED = 0;
    public static final int NETWORK_STATUS_NOT_CONNECTED = 0;
    public static final int NETWORK_STATUS_WIFI = 1;
    public static final int NETWORK_STATUS_MOBILE = 2;

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static boolean validateMicAvailability(Context context, Activity activity) {
        boolean available = true;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    new String [] { Manifest.permission.RECORD_AUDIO },
                    1
            );
        }
        AudioRecord recorder =
                new AudioRecord(MediaRecorder.AudioSource.MIC, 44100,
                        AudioFormat.CHANNEL_IN_MONO,
                        AudioFormat.ENCODING_DEFAULT, 44100);
        try{
            if(recorder.getRecordingState() != AudioRecord.RECORDSTATE_STOPPED ){
                available = false;

            }

            recorder.startRecording();
            if(recorder.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING){
                recorder.stop();
                available = false;

            }
            recorder.stop();
        } finally{
            recorder.release();
            recorder = null;
        }

        return available;
    }

    public static int getConnectivityStatusString(Context context) {
        int conn = MyUtils.getConnectivityStatus(context);
        int status = 0;
        if (conn == MyUtils.TYPE_WIFI) {
            status = NETWORK_STATUS_WIFI;
        } else if (conn == MyUtils.TYPE_MOBILE) {
            status = NETWORK_STATUS_MOBILE;
        } else if (conn == MyUtils.TYPE_NOT_CONNECTED) {
            status = NETWORK_STATUS_NOT_CONNECTED;
        }
        return status;
    }


    //read files








    public static void stopPlaying(MediaPlayer mediaPlayer ) {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    public static void setAnimation(View view, Context context){
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.bounce);
        view.setAnimation(animation);

    }
}
