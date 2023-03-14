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

import java.util.ArrayList;

public class AdBannerFragmentAdapter extends RecyclerView.Adapter<AdBannerFragmentAdapter.ViewHolder> {
    Context context;
    ArrayList<HomeModel> homeModelArrayList;



    public AdBannerFragmentAdapter(Context c, ArrayList<HomeModel> h) {
        this.homeModelArrayList = h;
        this.context = c;
    }

    @NonNull
    @Override
    public AdBannerFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adbanner,parent,false);
        return new AdBannerFragmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdBannerFragmentAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(homeModelArrayList.get(position).getProductImage()).centerCrop().into(holder.image);
        holder.text.setText(homeModelArrayList.get(position).getProductName());
    }

    @Override
    public int getItemCount() {
        return homeModelArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);


            context  = itemView.getContext();

            image = itemView.findViewById(R.id.AdBannerImage);
            text = itemView.findViewById(R.id.AdBannerText);


        }






    }
}
