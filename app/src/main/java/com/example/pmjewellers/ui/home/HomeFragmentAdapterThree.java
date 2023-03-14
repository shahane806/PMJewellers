package com.example.pmjewellers.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.pmjewellers.R;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
public class HomeFragmentAdapterThree extends RecyclerView.Adapter<HomeFragmentAdapterThree.viewHolder>  {
    Context context;
    ArrayList<HomeModel> homeModelArrayList;



    public HomeFragmentAdapterThree(Context c, ArrayList<HomeModel> h) {
        this.homeModelArrayList = h;
        this.context = c;
    }



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_home_fragment,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Glide.with(context).load(homeModelArrayList.get(position).getProductImage()).centerCrop().into(holder.ProductImage);
        holder.ProductName.setText(homeModelArrayList.get(position).getProductName());
        holder.ProductCategory.setText(homeModelArrayList.get(position).getProductCategory());
        holder.ProductPrice.setText(homeModelArrayList.get(position).getProductPrice());
        holder.ProductOffers.setText(homeModelArrayList.get(position).getProductOffer());
    }







    @Override
    public int getItemCount() {
        return homeModelArrayList.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView ProductImage;
        TextView ProductName,ProductCategory,ProductPrice,ProductOffers;

        public viewHolder(@NonNull View itemView) {

            super(itemView);


            context  = itemView.getContext();

            ProductImage = itemView.findViewById(R.id.ProductImage);
            ProductName = itemView.findViewById(R.id.ProductName);
            ProductCategory = itemView.findViewById(R.id.ProductCategory);
            ProductPrice = itemView.findViewById(R.id.ProductPrice);
            ProductOffers = itemView.findViewById(R.id.ProductOffers);

        }






    }

}
