package com.inventure.childcomunication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.inventure.childcomunication.adapters.AdapterOfReyclerOfRepeat;
import com.inventure.childcomunication.base.BaseActivity;
import com.inventure.childcomunication.helpers.Data;
import com.inventure.childcomunication.helpers.MyDataControl;
import com.inventure.childcomunication.helpers.MySharedPreference;
import com.inventure.childcomunication.helpers.MyUtils;
import com.inventure.childcomunication.adapters.AdapterRecyclerVoice;
import com.inventure.childcomunication.R;
import com.inventure.childcomunication.adapters.AdapterRecyclerCategory;
import com.inventure.childcomunication.model.ModelOfData;
import com.inventure.childcomunication.model.ModelOfMargeArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Home extends BaseActivity {

   long backPressedTime ;

    int c =0 ;
    int [] voiceData ;
    int [] iconeData ;
    String [] namesData ;
    int color ;

    List<String> list;
    Button setting ;
    ImageView timer ;
    TextView header1 ;
    RelativeLayout footer ;
    MediaPlayer player , playerRepeat ;


    RecyclerView.LayoutManager layoutManager ;
    RecyclerView.LayoutManager layoutManager2 ;

    ArrayList<ModelOfMargeArray>margeArrays = new ArrayList<>();
    ArrayList<HashMap<String,String>> imageList =new ArrayList<>() ;
    ArrayList<HashMap<String,String>> namesList = new ArrayList<>() ;

    boolean check = true  ;
    boolean isCheckFavourite ;
    boolean isIWantChecked ;
    AdapterRecyclerVoice adapterRecyclerVoice  ;
    AdapterOfReyclerOfRepeat adapterOfReyclerOfRepeat  ;
    AdapterRecyclerCategory adapterOfTap  ;

    RecyclerView recyclerViewCategory , recycler_repeat ,recyclerContent;

    List<ModelOfData> listData = new ArrayList<>();

    List<Integer>icon = new ArrayList<Integer>();
    List<Integer>voiceOfCategoryTap = new ArrayList<>();

    Handler anim , timerDelay ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initBaseActivity(this);
        MySharedPreference.init(this);


        layoutManager = new GridLayoutManager(this,3);
        layoutManager2 = new GridLayoutManager(this,2);



        playerRepeat = new MediaPlayer();
       player =new MediaPlayer();

      setting = findViewById(R.id.button_of_setting);
      timer= findViewById(R.id.timer);
      timerDelay = new Handler();
      header1 = findViewById(R.id.header_text);
      footer = findViewById(R.id.footer_text);


        setColor();

        initData();
        initRecyclerRepeat();
        initRecyclerContent();
       initRecyclerTap();
        recyclerOnClick();
        getFavourite();
        goToSetting();

        isCheckFavourite =true ;







    }
    private void setColor(){
        if (MySharedPreference.getColor().equals(MyUtils.PURBLE_COLOR)){
            header1.setBackgroundResource(R.color.dark);
            footer.setBackgroundResource(R.color.dark);
            color = R.color.dark;



        }else if(MySharedPreference.getColor().equals(MyUtils.YELLOW_COLOR)){

            header1.setBackgroundResource(R.color.yellow);
            footer.setBackgroundResource(R.color.yellow);
            color = R.color.yellow;


        }else {

            color = R.color.blue;
            header1.setBackgroundResource(R.color.blue);
            footer.setBackgroundResource(R.color.blue);

        }



    }
    private void timerAction(){


        Glide.with(this).load(R.drawable.timer_gif).into(timer);
        timer.setVisibility(View.VISIBLE);

    }
    private void initDataOfContent(int pos){

        voiceData = listData.get(pos).getVoice();
        iconeData = listData.get(pos).getIcon();
        namesData = listData.get(pos).getNames();

        adapterRecyclerVoice.setData(namesData, iconeData, true);
        adapterRecyclerVoice.notifyDataSetChanged();
    }
    private void getFavourite(){

          imageList = MyDataControl.getImage(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+"/ChildCommunication/Data/");
          namesList =  MyDataControl.getPlayList(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+"/ChildCommunication/Data/");
          adapterRecyclerVoice.setData(namesList,imageList,false);
          adapterRecyclerVoice.notifyDataSetChanged();



    }
    private void initData(){


        listData.add(new ModelOfData(null,null,null));

        listData.add(new ModelOfData(Data.mo3zzatNames,Data.mo3zzatIcon,Data.mo3zzatSound));
        listData.add(new ModelOfData(Data.wantName,Data.wantIcon,Data.wantVoic));
        listData.add(new ModelOfData(Data.foodNames,Data.foodIcon,Data.foodSound));
        listData.add(new ModelOfData(Data.drinksNames,Data.drinksIcon,Data.drinksVoice));
        listData.add(new ModelOfData(Data.playName,Data.playIcon,Data.playVoice));
        listData.add(new ModelOfData(Data.bodyName,Data.bodyIcon,Data.bodyVoic));
        listData.add(new ModelOfData(Data.clothesName,Data.clothesIcon,Data.clothesVoic));
        listData.add(new ModelOfData(Data.familyName,Data.familyIcon,Data.familyVoic));
        listData.add(new ModelOfData(Data.actionName,Data.actionIcon,Data.actionVoic));
        listData.add(new ModelOfData(Data.placeName,Data.placeIcon,Data.placeSound));
        listData.add(new ModelOfData(Data.insideHomeNames,Data.insideHomeIcon,Data.insideHomevoice));
        listData.add(new ModelOfData(Data.elecricaldevicesName,Data.elecricaldevicesIcon,Data.elecricaldevicesVoic));
        listData.add(new ModelOfData(Data.animalsName,Data.animalIcon,Data.animalVoic));
        listData.add(new ModelOfData(Data.transportsName,Data.transportsIcon,Data.transportsVoic));
        listData.add(new ModelOfData(Data.musicalName,Data.musicalIcon,Data.musicalVoic));
        listData.add(new ModelOfData(Data.talkingName,Data.talkingIcon,Data.talkingVoic));
        listData.add(new ModelOfData(Data.feelingName,Data.feelingIcon,Data.feelingVoic));
        listData.add(new ModelOfData(null,null,null));
        listData.add(new ModelOfData(Data.coloraName,Data.colorIcon,Data.colorVoic));
        listData.add(new ModelOfData(Data.shapesName,Data.shapesIcon,Data.shapesVoic));
        listData.add(new ModelOfData(Data.iconName,Data.iconIcon,Data.iconVoic));
        listData.add(new ModelOfData(Data.directioneName,Data.directioneIcon,Data.directioneVoic));
        listData.add(new ModelOfData(null,null,null));
        listData.add(new ModelOfData(Data.soundAnimalsName,Data.soundAnimalsIcon,Data.soundAnimalsVoice));
        listData.add(new ModelOfData(Data.birdsNames,Data.birdsIcon,Data.birdsVoice));
        listData.add(new ModelOfData(Data.transpotationName,Data.transpotationIcon,Data.transpotationVoice));
        listData.add(new ModelOfData(Data.toolName,Data.toolIcon,Data.toolVoic));
        listData.add(new ModelOfData(Data.timingName,Data.timingIcon,Data.timingVoic));
        listData.add(new ModelOfData(Data.currencyName,Data.currencyIcon,Data.currencyVoic));















    }
    private void initRecyclerContent(){

        recyclerContent = findViewById(R.id.recycler_container);
        recyclerContent.setLayoutManager(layoutManager);
        adapterRecyclerVoice = new AdapterRecyclerVoice();
    recyclerContent.setAdapter(adapterRecyclerVoice);


    }

    private void initRecyclerRepeat(){
        recycler_repeat = findViewById(R.id.recycler_repeat);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        layoutManager.setReverseLayout(true);
        recycler_repeat.setLayoutManager(layoutManager);
        adapterOfReyclerOfRepeat = new AdapterOfReyclerOfRepeat() ;
        recycler_repeat.setAdapter(adapterOfReyclerOfRepeat);



    }

    private void initRecyclerTap(){

        recyclerViewCategory = findViewById(R.id.text_recycler);
        if(MySharedPreference.getGender().equals(MyUtils.BOY)) {
            for (int i = 0; i < Data.categoryImage.length; i++)
                icon.add(Data.categoryImage[i]);

            for (int i = 0; i < Data.categoryImage.length; i++)
                voiceOfCategoryTap.add(Data.categoryVoice[i]);

               list = new ArrayList<>(Arrays.asList(Data.categorysData));

        }else {

            for (int i = 0; i < Data.categoryImage.length; i++)
                icon.add(Data.categoryImage[i]);

            for (int i = 0; i < Data.categoryImage.length; i++)
                voiceOfCategoryTap.add(Data.categoryVoice1[i]);

            list = new ArrayList<>(Arrays.asList(Data.categorysData1));


        }


        adapterOfTap = new AdapterRecyclerCategory(list,icon,color);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);

        layoutManager.setReverseLayout(true);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setAdapter(adapterOfTap);



    }

     private void deleteDataFromFavorite(final int pos){

        setAlertDialog("هل تريد حذف هذا العنصر","حذف", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                ToastMassage( "تم الحذف");

                MyDataControl.deleteFromFavourite(Environment.getExternalStorageDirectory()+File.separator+"Child Communication/Data/"
                        +namesList.get(pos).get("file_name"),Environment.getExternalStorageDirectory()+File.separator+"Child Communication/Data/"
                        +imageList.get(pos).get("file_name"));
                getFavourite();
            }
        },"الغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();


            }
        });




     }
    private void saveDataToFavorite(final int pos){


        setAlertDialog("هل تريد حفظ هذا العنصر", "حفظ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                ToastMassage("تم الحفظ");
                try {


                   MyDataControl.CopyRAWtoPhone(iconeData[pos],getExternalCacheDir() +
                            File.separator+namesData[pos]+".jpg",Home.this);


                } catch (IOException e) {
                    e.printStackTrace();

                }

                try {
                MyDataControl.CopyRAWtoPhone(voiceData[pos],getExternalCacheDir() +
                            File.separator+namesData[pos]+".mp3",Home.this);


                } catch (IOException e) {
                    e.printStackTrace();

                }

            }
        }, "الغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });







    }


    private void recyclerOnClick(){
        adapterOfTap.setOnClickOfGategory(new AdapterRecyclerCategory.OnClickOfGategory() {
            @Override
            public void click(int pos) {


                if (pos == 21){
                    recyclerContent.setLayoutManager(layoutManager2);

                }else {
                    recyclerContent.setLayoutManager(layoutManager);

                }



                if (pos==0){

                    getFavourite();
                    isCheckFavourite =true ;
                    isIWantChecked = false ;

                }else {
                    if(pos==2){

                        addDataOnRecyclerRepeat(pos,"want");
                        isIWantChecked = true ;


                    }else if(pos == 18){

                        addDataOnRecyclerRepeat(pos,"see");
                        isIWantChecked = true ;

                    }else if(pos == 23) {

                        addDataOnRecyclerRepeat(pos, "ear");
                        isIWantChecked = true;

                    }


                    else {

                        isIWantChecked=false ;
                    }
                    isCheckFavourite = false ;

                    initDataOfContent(pos);

                }


                MyUtils.stopPlaying(player);
                player=MediaPlayer.create(Home.this,voiceOfCategoryTap.get(pos));
                player.start();



            }
        });


        adapterRecyclerVoice.setOnLongClick(new AdapterRecyclerVoice.OnLongClick() {
            @Override
            public void onLong(final int pos) {
              if(isCheckFavourite){

                  deleteDataFromFavorite(pos);



              }else {
                  saveDataToFavorite(pos);
              }




            }
        });



      adapterOfReyclerOfRepeat.setOnclickForVoiceOfRepeat(new AdapterRecyclerVoice.OnclickForVoice() {
          @Override
          public void click(int pos, View view) {


              if (margeArrays.get(pos).isCheck()){
                  MyUtils.stopPlaying(player);
                  player = MediaPlayer.create(Home.this, margeArrays.get(pos).getSoundIn());
                  player.start();

              }else {


                  MyUtils.stopPlaying(player);
                  player =new MediaPlayer() ;
                  try {
                      player.setDataSource(margeArrays.get(pos).getSoundOut());
                      player.prepare();
                      player.start();
                  } catch (IOException e) {

                  }

              }


          }
      });

        adapterRecyclerVoice.setOnclickForVoice(new AdapterRecyclerVoice.OnclickForVoice() {
            @Override
            public void click(final int pos , View view) {




          if (playerRepeat!=null) {
              if (!playerRepeat.isPlaying()) {


                  if(isCheckFavourite){
                      addDataOnRecyclerRepeat(pos,"");
                      MyUtils.stopPlaying(player);
                      player =new MediaPlayer() ;
                      try {
                          player.setDataSource(namesList.get(pos).get("file_path"));
                          player.prepare();
                          player.start();
                      } catch (IOException e) {

                      }

                  }else {
                      if (isIWantChecked){

                       delayCode(pos);


                      }
                      addDataOnRecyclerRepeat(pos,"");

                      MyUtils.stopPlaying(player);
                      player = MediaPlayer.create(Home.this, voiceData[pos]);
                      player.start();

                  }


              }
          }else {

              if(isCheckFavourite){
                  addDataOnRecyclerRepeat(pos,"");
                  MyUtils.stopPlaying(player);
                  player =new MediaPlayer() ;
                  try {
                      player.setDataSource(namesList.get(pos).get("file_path"));
                      player.prepare();
                      player.start();
                  } catch (IOException e) {

                  }

              }else {
                  addDataOnRecyclerRepeat(pos,"");

                  MyUtils.stopPlaying(player);
                  player = MediaPlayer.create(Home.this, voiceData[pos]);
                  player.start();

              }
          }
            }
        });

    }
    private void delayCode(final int pos){
        if(pos==2||pos==5||pos==6||pos==1||pos==4||pos == 0 ) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (pos == 2) {
                        initDataOfContent(4);
                        isIWantChecked = false;
                        recyclerViewCategory.smoothScrollToPosition(4);


                    } else if (pos == 1){

                        initDataOfContent(7);
                        recyclerViewCategory.smoothScrollToPosition(7);
                        Toast.makeText(Home.this, "asdasda", Toast.LENGTH_SHORT).show();
                        isIWantChecked = false;


                    }else if(pos == 4){

                        initDataOfContent(6);
                        recyclerViewCategory.smoothScrollToPosition(6);

                        isIWantChecked = false;


                    }else if(pos == 0 ){



                        initDataOfContent(10);
                        recyclerViewCategory.smoothScrollToPosition(10);

                        isIWantChecked = false;

                    }

                    else if (pos == 5) {
                        initDataOfContent(3);
                        recyclerViewCategory.smoothScrollToPosition(3);

                        isIWantChecked = false;


                    }else if(pos == 6){
                        initDataOfContent(5);
                        recyclerViewCategory.smoothScrollToPosition(5);

                        isIWantChecked = false;



                    }


                }
            }, 900);
        }
    }
    private void addDataOnRecyclerRepeat(int pos , String tag){



        if(margeArrays.size()<3) {
            if (isCheckFavourite&&tag.equals("")) {

                    margeArrays.add(new ModelOfMargeArray(false,imageList.get(pos).get("file_path"),
                            0,imageList.get(pos).get("file_name"),0,namesList.get(pos).get("file_path")));




                    adapterOfReyclerOfRepeat.setRepeatList(margeArrays);
                    adapterOfReyclerOfRepeat.notifyDataSetChanged();
                }
           else if (!tag.equals("") ){
                if(MySharedPreference.getGender().equals(MyUtils.BOY)) {
                    margeArrays.add(new ModelOfMargeArray(true, "", Data.categoryImage[pos], Data.categorysData[pos], Data.categoryVoice[pos], ""));
                }else {

                    margeArrays.add(new ModelOfMargeArray(true, "", Data.categoryImage[pos], Data.categorysData1[pos], Data.categoryVoice1[pos], ""));


                }
                adapterOfReyclerOfRepeat.setRepeatList(margeArrays);
                adapterOfReyclerOfRepeat.notifyDataSetChanged();


            }else if(tag.equals("see")){

                if(MySharedPreference.getGender().equals(MyUtils.BOY)) {
                    margeArrays.add(new ModelOfMargeArray(true, "", Data.categoryImage[pos], Data.categorysData[pos], Data.categoryVoice[pos], ""));
                }else {

                    margeArrays.add(new ModelOfMargeArray(true, "", Data.categoryImage[pos], Data.categorysData1[pos], Data.categoryVoice1[pos], ""));


                }
                adapterOfReyclerOfRepeat.setRepeatList(margeArrays);
                adapterOfReyclerOfRepeat.notifyDataSetChanged();



            }else if(tag.equals("ear")){

                if(MySharedPreference.getGender().equals(MyUtils.BOY)) {
                    margeArrays.add(new ModelOfMargeArray(true, "", Data.categoryImage[pos], Data.categorysData[pos], Data.categoryVoice[pos], ""));
                }else {

                    margeArrays.add(new ModelOfMargeArray(true, "", Data.categoryImage[pos], Data.categorysData1[pos], Data.categoryVoice1[pos], ""));


                }
                adapterOfReyclerOfRepeat.setRepeatList(margeArrays);
                adapterOfReyclerOfRepeat.notifyDataSetChanged();



            }

            else {


                margeArrays.add(new ModelOfMargeArray(true, "", iconeData[pos], namesData[pos], voiceData[pos], ""));

                adapterOfReyclerOfRepeat.setRepeatList(margeArrays);
                adapterOfReyclerOfRepeat.notifyDataSetChanged();
            }



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



    @Override
    public void onStop() {
        super.onStop();

        MyUtils.stopPlaying(player);
       player = null;
       new  VoiceRepeat().cancel(true);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();


        MyUtils.stopPlaying(player);
        player = null;



    }


    public void playVoice(View view) {
       if(playerRepeat.isPlaying()){


       }else {
           anim = new Handler();

           c = 0;
           yarab();


           new VoiceRepeat().execute();
       }


    }
    private void yarab(){
   if(c<recycler_repeat.getChildCount()&&anim!=null) {
      anim.postDelayed(new Runnable() {
           @Override
           public void run() {
               recycler_repeat.setAdapter(adapterOfReyclerOfRepeat);


               recycler_repeat.getViewTreeObserver().addOnPreDrawListener(
                       new ViewTreeObserver.OnPreDrawListener() {

                           @Override
                           public boolean onPreDraw() {
                               recycler_repeat.getViewTreeObserver().removeOnPreDrawListener(this);

                               MyUtils.setAnimation(recycler_repeat.getChildAt(c), getApplicationContext());

                               playAgain();
                               return true;
                           }
                       });
           }
       }, 600);

   }else {

       anim = null;




   }
 }

 private void playAgain(){


       anim.postDelayed(new Runnable() {
            @Override
            public void run() {
                c++;
                yarab();
            }
        },600);
 }



    public void clearRecyclerRepeat(View view) {
       if(!playerRepeat.isPlaying()) {
           if (player != null) {
               check = false;
               MyUtils.stopPlaying(player);
               margeArrays.clear();
               adapterOfReyclerOfRepeat.notifyDataSetChanged();
           }
       }
       player =new MediaPlayer();
    }

    public void deleteOneItem(View view) {



            int size =margeArrays.size() - 1;
            if (!playerRepeat.isPlaying() && size > -1) {
                margeArrays.remove(size);

                adapterOfReyclerOfRepeat.notifyDataSetChanged();
            }

    }

    public void goToSetting() {
        setting.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        timerAction();
                        delayTimer();

                        return true;
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                         timer.setVisibility(View.INVISIBLE);

                        timerDelay.removeCallbacksAndMessages(null);



                        return true;
                }
                return false;
            }
        });





    }
    private void delayTimer(){
        timerDelay.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Home.this, Setting.class));
                finish();
            }
        },1100);



    }

    public class VoiceRepeat extends AsyncTask<Void , Void , Void> {




        @Override
        protected Void doInBackground(Void... voids) {

            int i = 0 ;
            MyUtils.stopPlaying(player);
            player=new MediaPlayer();


              while (i < margeArrays.size()) {

                  if(!margeArrays.get(i).isCheck()) {
                      if (playerRepeat == null) {
                          MyUtils.stopPlaying(playerRepeat);
                          playerRepeat = new MediaPlayer();
                          try {
                              playerRepeat.setDataSource(margeArrays.get(i).getSoundOut());
                              playerRepeat.prepare();
                              playerRepeat.start();
                          } catch (IOException e) {

                          }

                          i++;
                      }
                      if (playerRepeat.isPlaying()) {


                      } else {

                          MyUtils.stopPlaying(playerRepeat);
                          playerRepeat = new MediaPlayer();
                          try {
                              playerRepeat.setDataSource(margeArrays.get(i).getSoundOut());
                              playerRepeat.prepare();
                              playerRepeat.start();
                          } catch (IOException e) {

                          }

                          i++;

                      }
                  }else {

                      if (playerRepeat == null) {
                          MyUtils.stopPlaying(playerRepeat);
                          playerRepeat = MediaPlayer.create(Home.this, margeArrays.get(i).getSoundIn());
                          playerRepeat.start();

                          i++;
                      }
                      if (playerRepeat.isPlaying()) {


                      } else {

                          MyUtils.stopPlaying(playerRepeat);
                          playerRepeat = MediaPlayer.create(Home.this, margeArrays.get(i).getSoundIn());
                          playerRepeat.start();
                          i++;

                      }
                  }
              }




            return null;
        }
    }



}
