package com.example.pmjewellers.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pmjewellers.R;
import com.example.pmjewellers.ui.bag.BagModel;
import com.example.pmjewellers.ui.bag.ProductDetailedActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
public class HomeFragmentAdapterThree extends RecyclerView.Adapter<HomeFragmentAdapterThree.viewHolder>  {
    Context context;
    ArrayList<HomeModel> homeModelArrayList;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String image,name,category,price,offers;


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
        image = homeModelArrayList.get(position).getProductImage();
        name = homeModelArrayList.get(position).getProductName();
        category = homeModelArrayList.get(position).getProductCategory();
        price = homeModelArrayList.get(position).getProductPrice();
        offers = homeModelArrayList.get(position).getProductOffer();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailedActivity.class);
                Bundle bundle = new Bundle();
//                bundle.putString("ProductImage",image);
//                bundle.putString("ProductName",name);
//                bundle.putString("ProductCategory",category);
//                bundle.putString("ProductPrice",price);
//                bundle.putString("ProductOffers",offers);
//                intent.putExtra("Bundle",bundle);

                bundle.putString("ProductImage",homeModelArrayList.get(position).getProductImage());
                bundle.putString("ProductName",homeModelArrayList.get(position).getProductName());
                bundle.putString("ProductCategory",homeModelArrayList.get(position).getProductCategory());
                bundle.putString("ProductPrice",homeModelArrayList.get(position).getProductPrice());
                bundle.putString("ProductOffers",homeModelArrayList.get(position).getProductOffer());
                intent.putExtra("ProductName",homeModelArrayList.get(position).getProductName());
                intent.putExtra("ProductImage",homeModelArrayList.get(position).getProductImage());
                intent.putExtra("ProductCategory",homeModelArrayList.get(position).getProductCategory());
                intent.putExtra("ProductPrice",homeModelArrayList.get(position).getProductPrice());
                intent.putExtra("ProductOffers",homeModelArrayList.get(position).getProductOffer());

                intent.putExtras(bundle);
                view.getContext().startActivity(intent);

            }
        });
        new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, ProductDetailedActivity.class);
                Bundle bundle=new Bundle();
//                bundle.putSerializable("ArrayList",homeModelArrayList);
//                bundle.putInt("position",position);

                bundle.putString("ProductImage",homeModelArrayList.get(position).getProductImage());
                bundle.putString("ProductName",homeModelArrayList.get(position).getProductName());
                bundle.putString("ProductCategory",homeModelArrayList.get(position).getProductCategory());
                bundle.putString("ProductPrice",homeModelArrayList.get(position).getProductPrice());
                bundle.putString("ProductOffers",homeModelArrayList.get(position).getProductOffer());
                intent.putExtra("ProductName",homeModelArrayList.get(position).getProductName());
                intent.putExtra("ProductImage",homeModelArrayList.get(position).getProductImage());
                intent.putExtra("ProductCategory",homeModelArrayList.get(position).getProductCategory());
                intent.putExtra("ProductPrice",homeModelArrayList.get(position).getProductPrice());
                intent.putExtra("ProductOffers",homeModelArrayList.get(position).getProductOffer());
                if(homeModelArrayList.get(position).getProductName()==null) {
                    Toast.makeText(view.getContext(), "Null ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(view.getContext(),homeModelArrayList.get(position).getProductName(), Toast.LENGTH_SHORT).show();
                }
                intent.putExtras(bundle);
//                view.getContext().startActivity(intent);

            }
        };
        holder.itemView.findViewById(R.id.addToCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BagModel bagModel = new BagModel();

                firebaseDatabase = FirebaseDatabase.getInstance();

                databaseReference = firebaseDatabase.getReference("Users/"+bagModel.getUsername()+"/Bucket").child(name);
                databaseReference.child("ProductName").setValue(name);
                databaseReference.child("ProductImage").setValue(image);
                databaseReference.child("ProductCategory").setValue(category);
                databaseReference.child("ProductPrice").setValue(price);
                databaseReference.child("ProductOffers").setValue(offers);

            }
        });
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
