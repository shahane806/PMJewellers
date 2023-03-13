package com.example.pmjewellers.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.pmjewellers.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.viewHolder> {
    Context context;
    ArrayList<HomeModel> homeModelArrayList;




    public HomeFragmentAdapter(Context c, ArrayList<HomeModel> h) {
        this.homeModelArrayList = h;
        this.context = c;
    }

    @NonNull
    @Override
    public HomeFragmentAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_home_fragment,parent,false);

        return  new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFragmentAdapter.viewHolder holder, int position) {
      HomeModel model = homeModelArrayList.get(position);
        Toast.makeText(context, homeModelArrayList.get(position).getImage(), Toast.LENGTH_SHORT).show();
      Glide.with(context).load(homeModelArrayList.get(position).getImage()).centerCrop().into(holder.image);
      holder.text.setText(homeModelArrayList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return homeModelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            context  = itemView.getContext();
            image = itemView.findViewById(R.id.homeFragmentImage);
            text = itemView.findViewById(R.id.homeFragmentText);

        }


    }

}
