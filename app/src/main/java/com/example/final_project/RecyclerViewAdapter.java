package com.example.final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<ModelClass>list;


    public RecyclerViewAdapter(Context context, ArrayList<ModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    // يتم استدعاءها مرة واحدة فقط لكل عنصر يعرض لأول مرة على الشاشة للانفلات
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    // للربط بين البيانات في المصفوفة في موقع معين وبين الفيو هولدر عشان ما يتكرر
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.full_name.setText(list.get(position).getGAME_FULL_NAME());
        holder.Score.setText(list.get(position).getGAME_SCORE()+"");
        holder.Data_Time.setText(list.get(position).getGAME_TIME());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView full_name,Data_Time,Score;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            full_name=itemView.findViewById(R.id.tv_Custom_full_name);
            Data_Time=itemView.findViewById(R.id.tv_CustomDate);
            Score=itemView.findViewById(R.id.tv_Custom_score);


        }
    }
}