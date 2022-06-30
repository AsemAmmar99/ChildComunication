package com.inventure.childcomunication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.inventure.childcomunication.R;
import com.inventure.childcomunication.helpers.MyDataControl;
import com.inventure.childcomunication.helpers.MySharedPreference;
import com.inventure.childcomunication.helpers.MyUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;

public class AdapterRecyclerVoice  extends RecyclerView.Adapter<AdapterRecyclerVoice.Holder> {


    String names [] ;
    int icon [] ;
    OnclickForVoice onclickForVoice ;
    OnLongClick onLongClick ;
    private Context context;

    boolean inOrOut;
    ArrayList<HashMap<String,String>> imageList  ;
    ArrayList<HashMap<String,String>> namesList  ;




    public interface OnclickForVoice{

        void click (int pos , View view );




    }
    public interface OnLongClick{
        void onLong (int pos);

    }

    public void setOnclickForVoice(OnclickForVoice onclickForVoice) {
        this.onclickForVoice = onclickForVoice;
    }


    public void setOnLongClick(OnLongClick onLongClick) {
        this.onLongClick = onLongClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View  v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voice_recycler, parent,false);

    context = parent.getContext();

        return new Holder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {



        if (inOrOut) {
            holder.imageView.setImageResource(icon[position]);

            holder.textView.setText(names[position]);
        }else {

            Glide.with(holder.view.getContext()).load(imageList.get(position).get("file_path")).into(holder.imageView);

            String name = imageList.get(position).get("file_name");

            holder.textView.setText(name.substring(0,name.length()-4));

        }



    }

    @Override
    public int getItemCount() {

        if (inOrOut) {


            return icon == null?0:icon.length ;




        }else {
            if (namesList.size() != imageList.size()){
                MyDataControl.deleteRecursive(new File( MyUtils.MAIN_PATH));
                MyDataControl.createFileOfApp(context);
                return 0 ;

            }else {
                return namesList == null?0:namesList.size() ;
            }


        }



    }

    public void setData(String[] names ,int[] icon ,boolean inOrOut) {
        this.names = names;
        this.icon = icon;
        this. inOrOut = inOrOut ;
    }
    public void setData(ArrayList namesList ,ArrayList imageList,boolean inOrOut) {
        this.namesList = namesList;
        this.imageList = imageList;
        this. inOrOut = inOrOut ;
    }



    public class Holder extends RecyclerView.ViewHolder{

      TextView textView ;
      ImageView imageView ;
      CardView cardView;
      View view ;
      ConstraintLayout constraintLayout;



        public Holder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            textView = itemView.findViewById(R.id.text_recycler_voice);
            imageView=  itemView.findViewById(R.id.image_recycler_voice);
            cardView = itemView.findViewById(R.id.card_voice_recycler);
            constraintLayout = itemView.findViewById(R.id.parent11);
            if(MySharedPreference.getColor().equals(MyUtils.PURBLE_COLOR)){
              constraintLayout.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity1);




            }else if(MySharedPreference.getColor().equals(MyUtils.YELLOW_COLOR)){
                constraintLayout.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity_yellow);


            }else {

            constraintLayout.setBackgroundResource(R.drawable.back_ground_of_cards_start_activity_blue);

            }


            itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {



                    if (onclickForVoice!=null){

                        onclickForVoice.click(getAdapterPosition(), view );


                    }


                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                   onLongClick.onLong(getAdapterPosition());
                    return false;
                }
            });

        }


    }


}
