package com.inventure.childcomunication.helpers;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class AudioRecorder {
    final MediaRecorder recorder = new MediaRecorder();
    public static String path;

    public AudioRecorder(String path) {
        AudioRecorder.path = sanitizePath(path);
    }

    private String sanitizePath(String path) {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        if (!path.contains(".")) {
            path += ".mp3";
        }
        return path;
    }

    public void start(){
//        String state = android.os.Environment.getExternalStorageState();
//        if (!state.equals(android.os.Environment.MEDIA_MOUNTED)) {
//            throw new IOException("SD Card is not mounted.  It is " + state
//                    + ".");
//        }
//
//        // make sure the directory we plan to store the recording in exists
//        File directory = new File(path).getParentFile();
//        if (!directory.exists() && !directory.mkdirs()) {
//            throw new IOException("Path to file could not be created.");
//        }


        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(path);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e("prepareRec", "could not prepare MediaRecorder"+e.toString());
            return;
        } catch (IllegalStateException e) {
            return;
        }
        recorder.start();
    }

    public void stop(){

        recorder.stop();
        recorder.release();
    }

    public void playarcoding(String path) throws IOException {
        MediaPlayer mp = new MediaPlayer();
        mp.setDataSource(path);
        mp.prepare();
        mp.start();
        mp.setVolume(10, 10);
    }
}
