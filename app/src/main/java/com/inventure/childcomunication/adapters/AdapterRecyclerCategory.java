package com.inventure.childcomunication.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.inventure.childcomunication.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerCategory extends RecyclerView.Adapter<AdapterRecyclerCategory.Holder> {

  int row_index ;
    List<String> list =new ArrayList<>();
    List<Integer>icon = new ArrayList<>();
     int color ;
     OnClickOfGategory onClickOfGategory ;



    public interface OnClickOfGategory{
        void  click (int pos);


    }



    public void setOnClickOfGategory(OnClickOfGategory onClickOfGategory){

        this.onClickOfGategory = onClickOfGategory;

    }




    public AdapterRecyclerCategory(List<String> list , List<Integer> icon,int color )  {
        this.list = list;
        this.icon=icon ;
        this.color = color ;

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reycler_category,parent,false);

        return new Holder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        holder.textView.setText(list.get(position));

        holder.imageView.setImageResource(icon.get(position));



        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (onClickOfGategory!=null) {
                   onClickOfGategory.click(position);
               }
                row_index=position;
                notifyDataSetChanged();
            }
        });

        if(row_index==position){
            holder.linearLayout.setBackgroundResource(color);
            holder.textView.setTextColor(Color.parseColor("#000000"));


        }
        else
        {
            holder.linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.textView.setTextColor(Color.parseColor("#000000"));

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class Holder  extends RecyclerView.ViewHolder{

        TextView textView ;
        ImageView imageView ;
     public  ConstraintLayout linearLayout ;

        public Holder(@NonNull final View itemView) {
            super(itemView);


            textView = itemView.findViewById(R.id.text_category);
            imageView = itemView.findViewById(R.id.image_item);
            linearLayout = itemView.findViewById(R.id.parent_item);







        }

    }
}
