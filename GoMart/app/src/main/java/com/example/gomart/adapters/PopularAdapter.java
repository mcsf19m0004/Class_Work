package com.example.gomart.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gomart.R;
import com.example.gomart.activities.DetailedActivity;
import com.example.gomart.models.NewProoductsModel;
import com.example.gomart.models.PopularModel;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private Context context;
    private List<PopularModel> list;
    public PopularAdapter(Context context, List<PopularModel> list)
    {
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PopularAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.newImg);
        holder.newName.setText(list.get(position).getName());
        holder.newPrice.setText(String.valueOf(list.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailedActivity.class);
                intent.putExtra("detailed", list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView newImg;
        TextView newName, newPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newImg=itemView.findViewById(R.id.all_img);
            newName=itemView.findViewById(R.id.all_product_name);
            newPrice=itemView.findViewById(R.id.all_price);
        }
    }
}
