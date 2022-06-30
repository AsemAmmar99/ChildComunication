package com.inventure.childcomunication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.inventure.childcomunication.R;
import com.inventure.childcomunication.helpers.MySharedPreference;
import com.inventure.childcomunication.helpers.MyUtils;
import com.inventure.childcomunication.model.ModelOfMargeArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterOfReyclerOfRepeat extends RecyclerView.Adapter<AdapterOfReyclerOfRepeat.Holder> {



    List<String> namesList ;
    List<Integer>iconList ;
    AdapterRecyclerVoice.OnclickForVoice onclickForVoice ;


    ArrayList<ModelOfMargeArray>margeArrays ;



   boolean inOrOut ;
    public interface OnclickForVoice{

        void click (int pos , View view );



    }

    public void setOnclickForVoiceOfRepeat(AdapterRecyclerVoice.OnclickForVoice onclickForVoice) {
        this.onclickForVoice = onclickForVoice;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voice_recycler, parent,false);


        return new AdapterOfReyclerOfRepeat.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {




        if (margeArrays.get(position).isCheck()) {
            holder.imageView.setImageResource(margeArrays.get(position).getImgIn());

            holder.textView.setText(margeArrays.get(position).getName());
        }else {
            String name = margeArrays.get(position).getName();

            holder.textView.setText(name.substring(0,name.length()-4));
            Glide.with(holder.view.getContext()).load(margeArrays.get(position).getImgOut()).into(holder.imageView);

        }
    }


    @Override
    public int getItemCount() {


           return margeArrays == null?0:margeArrays.size() ;


    }


//    public void setRepeatList(List<String> namesList , List<Integer> iconList,boolean inOrOut) {
//        this.namesList = namesList;
//        this.iconList = iconList;
//        this.inOrOut = inOrOut ;
//    }
    public void setRepeatList(ArrayList iconList) {

        this.margeArrays = iconList;

    }





    public class Holder extends RecyclerView.ViewHolder{

        TextView textView ;
        ImageView imageView ;
        CardView cardView;
        View view ;
        ConstraintLayout constraintLayout ;



        public Holder(@NonNull final View itemView) {
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

        }


    }


}
