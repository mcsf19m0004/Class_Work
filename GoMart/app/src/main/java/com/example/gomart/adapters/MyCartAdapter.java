package com.example.gomart.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomart.R;
import com.example.gomart.models.CategoryModel;
import com.example.gomart.models.MyCartModel;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

     Context context;
     List<MyCartModel> list;

     int totalAmount=0;

    public MyCartAdapter(Context context, List<MyCartModel> list)
    {
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.date.setText(list.get(position).getCurrentDate());
        holder.time.setText(list.get(position).getCurrentTime());
        holder.price.setText(list.get(position).getProductPrice()+"$");
        holder.name.setText(list.get(position).getProductName());
        holder.totalQuantity.setText(list.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(list.get(position).getTotalPrice()));

        //total amount pass to cart activity
        totalAmount=totalAmount+list.get(position).getTotalPrice();
        Intent intent=new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalAmount);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView  name,price,date,time,totalQuantity,totalPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           name=itemView.findViewById(R.id.product_name);
            price=itemView.findViewById(R.id.product_name);
            date=itemView.findViewById(R.id.product_name);
            time=itemView.findViewById(R.id.product_name);
            totalPrice=itemView.findViewById(R.id.product_name);
            totalQuantity=itemView.findViewById(R.id.product_name);



        }
    }
}
