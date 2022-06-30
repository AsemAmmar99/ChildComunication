package com.inventure.childcomunication.adapters;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inventure.childcomunication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.Holder> {

    MediaPlayer player2;
    ArrayList<HashMap<String,String>> list;
    String n = "";
    int i = 0 ;

    List<String >list1 ;

    OnClickOfHomeRecycler onClickOfHomeRecycler ;

    public interface OnClickOfHomeRecycler{


        void click (int pos);


    }


    public void setOnClickOfHomeRecycler(OnClickOfHomeRecycler onClickOfHomeRecycler){

        this.onClickOfHomeRecycler = onClickOfHomeRecycler ;


    }



    public AdapterRecycler(List txt) {
       list1 = txt ;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
         holder.textView.setText(list1.get(position));
//         holder.imageView.setImageResource(R.drawable.);
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class Holder  extends RecyclerView.ViewHolder{
       ImageView imageView ;
       TextView textView ;


        public Holder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_recycler);
            textView = itemView.findViewById(R.id.text_recycler1);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (onClickOfHomeRecycler!= null){

                        onClickOfHomeRecycler.click(getAdapterPosition());

                    }



//

                }
            });

        }

    }

}
