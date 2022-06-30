package com.inventure.childcomunication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.inventure.childcomunication.R;
import com.inventure.childcomunication.helpers.MySharedPreference;
import com.inventure.childcomunication.helpers.MyUtils;

public class SelectColor extends AppCompatActivity {
   LinearLayout header ;
   Button next ;
   CardView cardPurble , cardYellow  , cardBlue ;
   String color ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_color);
        cardPurble = findViewById(R.id.purble_card);
        cardBlue = findViewById(R.id.blue_card);
        cardYellow = findViewById(R.id.yellow_card);
        header = findViewById(R.id.header);
        next = findViewById(R.id.next_btn1);
        MySharedPreference.init(this);

        cardPurble.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity1);
        color = MyUtils.PURBLE_COLOR;

    }

    public void purble(View view) {
        cardPurble.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity1);
        cardYellow.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity2);
        cardBlue.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity2);
        header.setBackgroundResource(R.drawable.backgroud2);
        next.setBackgroundResource(R.drawable.background_btn);

        color = MyUtils.PURBLE_COLOR;


    }

    public void blue(View view) {
        cardPurble.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity2);
        cardYellow.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity2);
        cardBlue.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity_blue);
        header.setBackgroundResource(R.drawable.backgroud_blue);
        next.setBackgroundResource(R.drawable.background_blue);
        color = MyUtils.BLUE_COLOR;


    }

    public void yellow(View view) {
        cardPurble.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity2);
        cardYellow.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity_yellow);
        cardBlue.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity2);
        header.setBackgroundResource(R.drawable.backgroud_yellow);
        next.setBackgroundResource(R.drawable.background_yellow);
        color = MyUtils.YELLOW_COLOR;
    }

    public void next(View view) {
        MySharedPreference.setColor(color);
        startActivity(new Intent(SelectColor.this,AfterSplash.class));
        finish();
    }
}
