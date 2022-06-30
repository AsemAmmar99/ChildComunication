package com.inventure.childcomunication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.inventure.childcomunication.helpers.MyDataControl;
import com.inventure.childcomunication.helpers.MySharedPreference;
import com.inventure.childcomunication.helpers.MyUtils;
import com.inventure.childcomunication.R;

import java.io.File;

public class AfterSplash extends AppCompatActivity {



    long backPressedTime ;
    LinearLayout boy , girl ;
    private long enqueue;
    private DownloadManager dm;
    TextView textProgress ;
     LinearLayout header ;
    Button next ;
    String gender = "";
    int res ;


    Handler handler = new Handler();
    int i = 0 ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_splash);
        header = findViewById(R.id.d);
        next = findViewById(R.id.next_btn);

        MySharedPreference.init(this);
       initViews();
       setColor();



   getFirstTimePermition();


    }
    private void setColor(){
        if (MySharedPreference.getColor().equals(MyUtils.PURBLE_COLOR)){
            header.setBackgroundResource(R.drawable.backgroud2);
            next.setBackgroundResource(R.drawable.background_btn);
            res = R.drawable.back_ground_of_cards_start_activity1;



        }else if(MySharedPreference.getColor().equals(MyUtils.YELLOW_COLOR)){

            header.setBackgroundResource(R.drawable.backgroud_yellow);
            next.setBackgroundResource(R.drawable.background_yellow);
            res = R.drawable.back_ground_of_cards_start_activity_yellow;


        }else {

            res = R.drawable.back_ground_of_cards_start_activity_blue;
            header.setBackgroundResource(R.drawable.backgroud_blue);
            next.setBackgroundResource(R.drawable.background_blue);

        }



    }
    private void initViews(){


        boy = findViewById(R.id.boy_item);
        girl = findViewById(R.id.girl_item);





    }
    private void getFirstTimePermition(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

                String[] per = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(per, 1000);

            }else {







            }
        }else {



        }


    }



    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat
                    .requestPermissions(AfterSplash.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                MyDataControl.createFileOfApp(AfterSplash.this);
            } else {
                // Permission Denied
                Toast.makeText(AfterSplash.this, "Permission Denied", Toast.LENGTH_SHORT)
                        .show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }












    public void girl(View view) {

        girl.setBackgroundResource(res);
        boy.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity2);
        gender =MyUtils.GIRL ;

    }

    public void boy(View view) {


        girl.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity2);
        boy.setBackgroundResource(res);
        gender =MyUtils.BOY ;
    }

    public void next(View view) {
        if (gender=="" ){
            Toast.makeText(this, "برجاء اختيار النوع", Toast.LENGTH_SHORT).show();


        }else {
            MySharedPreference.setGender(gender);
            startActivity(new Intent(AfterSplash.this, Home.class));
            finish();

        }
    }
    @Override
    public void onBackPressed() {



        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishAffinity();
        } else {
            Toast.makeText(this, "press again to exit ", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }







}
