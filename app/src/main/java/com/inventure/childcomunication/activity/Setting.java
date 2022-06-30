package com.inventure.childcomunication.activity;

import androidx.annotation.NonNull;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.Toast;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.inventure.childcomunication.R;
import com.inventure.childcomunication.base.BaseActivity;
import com.inventure.childcomunication.helpers.AudioRecorder;
import com.inventure.childcomunication.helpers.MySharedPreference;
import com.inventure.childcomunication.helpers.MyUtils;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Setting extends BaseActivity {

    MaterialDialog materialDialog ;
    private int Request_Camera = 100, Select_Image = 101;
    AudioRecorder record ;
    private RadioGroup rg1 , rg2;
    private RadioButton rb1, rb2 , rbColor1 ,rbColor2,rbColor3 ;
    ImageView memoryImage , selectImage;

    EditText editTextOfName ;
    AlertDialog.Builder alertDialog ;
    Button settingButtonRecord , cancel , save ;
    LinearLayout header ;

    ConstraintLayout card ;


    Boolean checkWhenSave  = false ;


    int counterForRecordOneTime = 0 ;
   private String gender ="" ;
   private String name= "";
    private Bitmap bitmap;
    private String category ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        MySharedPreference.init(this);


        init();
        requsetPermition();
        onClicks();
         initRadioGroup();
         setColor();
         initRadioColor();

    }
    private void setColor(){
        if (MySharedPreference.getColor().equals(MyUtils.PURBLE_COLOR)){
            header.setBackgroundResource(R.drawable.backgroud2);
            settingButtonRecord.setBackgroundResource(R.drawable.background_btn);
            save.setBackgroundResource(R.drawable.background_btn);
            cancel.setBackgroundResource(R.drawable.background_btn);
            card.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity1);
            editTextOfName.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity1);



        }else if(MySharedPreference.getColor().equals(MyUtils.YELLOW_COLOR)){

            header.setBackgroundResource(R.drawable.backgroud_yellow);
            settingButtonRecord.setBackgroundResource(R.drawable.background_yellow);
            save.setBackgroundResource(R.drawable.background_yellow);
            cancel.setBackgroundResource(R.drawable.background_yellow);
            card.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity_yellow);
            editTextOfName.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity_yellow);



        }else {


            header.setBackgroundResource(R.drawable.backgroud_blue);
            settingButtonRecord.setBackgroundResource(R.drawable.background_blue);
            save.setBackgroundResource(R.drawable.background_blue);
            cancel.setBackgroundResource(R.drawable.background_blue);
            card.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity_blue);
            editTextOfName.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity_blue);



        }



    }
    private void initRadioGroup(){


        if (MySharedPreference.getGender().equals(MyUtils.BOY)){

            rb1.setChecked(true);
        }else if (MySharedPreference.getGender().equals(MyUtils.GIRL)){




            rb2.setChecked(true);
        }

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                if(i== R.id.setting_radio_btn_male ){

                    gender = MyUtils.BOY;
                    MySharedPreference.setGender(gender);
                    ToastMassage("تم التغير");



                }else if (i ==R.id.setting_radio_btn_female ) {

                    gender = MyUtils.GIRL;
                    MySharedPreference.setGender(gender);
                    ToastMassage("تم التغير");

                }
            }
        });

    }

    private void init(){

        card = findViewById(R.id.card_cons);
        cancel = findViewById(R.id.cancel_btn);
        save = findViewById(R.id.save_btn);
         header = findViewById(R.id.header_setting);
        settingButtonRecord = findViewById(R.id.setting_button_record); 
        selectImage= findViewById(R.id.choose_image);
        memoryImage = findViewById(R.id.setting_image);
        editTextOfName =findViewById(R.id.edittext_recycler_voice);
        rg1 = findViewById(R.id.setting_radio_group);
        rb1 = findViewById(R.id.setting_radio_btn_male);
        rb2 = findViewById(R.id.setting_radio_btn_female);
        rg2 = findViewById(R.id.change_color) ;

        rbColor1 = findViewById(R.id.blue);
        rbColor2 = findViewById(R.id.yellow);
        rbColor3 = findViewById(R.id.purble);


    }

    private void initRadioColor(){



        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                 if(i == R.id.blue  ){

                    MySharedPreference.setColor(MyUtils.BLUE_COLOR);
                    setColor();



                 }else if(i==R.id.yellow){
                     MySharedPreference.setColor(MyUtils.YELLOW_COLOR);
                     setColor();

                 }else if(i == R.id.purble ){

                     MySharedPreference.setColor(MyUtils.PURBLE_COLOR);
                     setColor();

                }

            }
        });

        if(MySharedPreference.getColor().equals(MyUtils.PURBLE_COLOR)){
            rbColor3.setChecked(true);



        }else if(MySharedPreference.getColor().equals(MyUtils.YELLOW_COLOR)){

            rbColor2.setChecked(true);

        }else {


              rbColor1.setChecked(true);
        }



    }

    @SuppressLint("ClickableViewAccessibility")
    private void onClicks(){
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });


        settingButtonRecord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            if (MyUtils.validateMicAvailability(getApplicationContext(), Setting.this)) {
                                if (counterForRecordOneTime < 1) {
                                    name = editTextOfName.getText().toString().trim();

                                    if ("".equals(name)) {
                                        editTextOfName.setError("ادخال الاسم");

                                    } else {


                                        startٍRecording();
                                        settingButtonRecord.setText("يسجل...");
//
                                    }

                                } else {

                                    Toast.makeText(Setting.this, "لقد قمت بالتسجيل مسبقا", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(Setting.this, "لا يمكن التسجيل في الوقت الحالي", Toast.LENGTH_SHORT).show();
                                break;
                            }


                            return true; // if you want to handle the touch event
                        case MotionEvent.ACTION_UP:
                            // RELEASED
                            if (counterForRecordOneTime < 1) {
                                alertDialog = new AlertDialog.Builder(Setting.this);
                                if (record != null) {
                                    try {
                                        stopRecording();
                                        settingButtonRecord.setText("تسجيل صوت");
                                        alertDialog.setMessage("هل تريد حفظ التسجيل");
                                        alertDialog.setIcon(R.drawable.favorite);
                                        alertDialog.setPositiveButton("حفظ", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                                Toast.makeText(Setting.this, "تم الحفظ", Toast.LENGTH_SHORT).show();
                                                settingButtonRecord.setText("تسجيل صوت");
                                                counterForRecordOneTime++;

                                            }
                                        });

                                        alertDialog.setNegativeButton("ايقاف", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                                deleteCurrentRecord();
                                                settingButtonRecord.setText("تسجيل صوت");

                                            }
                                        });
                                        alertDialog.setCancelable(false);
                                        alertDialog.show();

                                    } catch (Exception e) {
                                        settingButtonRecord.setText("تسجيل صوت");
                                        Toast.makeText(Setting.this, "اضغط مطولا لتسجيل الصوت", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            }
                            return true; // if you want to handle the touch event

                }
                    return false;

            }
        });
//
//
        
        settingButtonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counterForRecordOneTime<1) {
                    Toast.makeText(Setting.this, "اضغط مطولا لتسجيل الصوت", Toast.LENGTH_SHORT).show();
                }

            }
        });








    }
    private void requsetPermition(){

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);

            } else {


            }
        }else {


        }

    }

    private void selectImage() {
        materialDialog = new MaterialDialog.Builder(this)
                .title(R.string.uploadImages)
                .items(R.array.uploadImages)
                .itemsIds(R.array.itemIds)
                .itemsGravity(GravityEnum.END)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        switch (which){
                            case 0:

                                Intent intentgallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intentgallery.setType("image/*");
                                if(intentgallery.resolveActivity(getPackageManager())!=null)
                                    startActivityForResult(intentgallery.createChooser(intentgallery, "Select File"), Select_Image);
                                break;
                            case 1:
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                if(intent.resolveActivity(getPackageManager())!=null)
                                    startActivityForResult(intent, Request_Camera);
                                break;
                            case 2:
                                materialDialog.dismiss();
                                break;
                        }
                    }
                })
                .show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == Request_Camera) {

                bitmap = (Bitmap) data.getExtras().get("data");
                if (bitmap != null)
                    memoryImage.setImageBitmap(bitmap);


            } else if (requestCode == Select_Image) {
                if (data != null) {
                    try {
                        final Uri SelectedImageUri = data.getData();
                        final InputStream stream = getContentResolver().openInputStream(SelectedImageUri);
                        bitmap = BitmapFactory.decodeStream(stream);
                        CropImage.activity(SelectedImageUri)
                                .setAspectRatio(1, 1)
                                .setMinCropWindowSize(500, 500)
                                .start(this);
                        memoryImage.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



        private void saveToInternalStorage(Bitmap bitmapImage){

            File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + MyUtils.DATA_PATH);

            File directory = null;

                directory = folder;

            // Create imageDir
            File mypath=new File(directory,name+".jpg");

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(mypath);
                // Use the compress method on the BitMap object to write image to the OutputStream
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("m",e.getLocalizedMessage()+"");
            } finally {
                try {
                    assert fos != null;
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            directory.getAbsolutePath();
        }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("Home", "Permission Granted");

                } else {
                    Log.d("Home", "Permission Failed");
                    Toast.makeText(this, "يجب ان تسمع للتطبيق الوصول للميكروفون لاضافه عنصر جديد", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(Setting.this, Home.class));

                }
            }
            // Add additional cases for other permissions you may have asked for
        }
    }


    public void stopRecording() {
        record.stop();
    }

    public void startٍRecording() {
        record   = new AudioRecorder(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+File.separator+MyUtils.DATA_PATH+name);
        record.start();
    }

    public void deleteCurrentRecord() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ File.separator+MyUtils.DATA_PATH+name+".mp3");
        file.delete();
    }

    public void cancelAll(View view) {
        if( counterForRecordOneTime==0){
            finish();
            startActivity(new Intent(Setting.this, Home.class));

        }
        else {
            finish();
            startActivity(new Intent(Setting.this, Home.class));
            deleteCurrentRecord();
        }

    }

    public void saveAll(View view) {

        if (!"".equals(gender)){

            MySharedPreference.setGender(gender);
        }

        if(bitmap== null){
            Toast.makeText(this, "يرجي اختيار الصوره ", Toast.LENGTH_SHORT).show();

        }else if(counterForRecordOneTime == 0 ) {
            Toast.makeText(this, "يرجي تسجيل صوت", Toast.LENGTH_SHORT).show();
        }else {
             checkWhenSave = true ;
          saveToInternalStorage(bitmap);
              finish();
            startActivity(new Intent(Setting.this, Home.class));



        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if( counterForRecordOneTime!=0 && !checkWhenSave) {
            deleteCurrentRecord();
            counterForRecordOneTime = 0;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        if( counterForRecordOneTime!=0 && !checkWhenSave) {
            deleteCurrentRecord();
            counterForRecordOneTime = 0;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if( counterForRecordOneTime!=0 && !checkWhenSave) {
            deleteCurrentRecord();
            counterForRecordOneTime = 0;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if( counterForRecordOneTime==0){
            finish();
            startActivity(new Intent(Setting.this, Home.class));

        }
        else {
            finish();
            startActivity(new Intent(Setting.this, Home.class));

                deleteCurrentRecord();

        }


    }

    public void goColor(View view) {
    }
}
