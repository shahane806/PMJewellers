package com.example.pmjewellers.ui.bag;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pmjewellers.R;
import com.example.pmjewellers.ui.account.ProfileDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class BagFragmentAdapter extends RecyclerView.Adapter<BagFragmentAdapter.viewHolder>  {
    Context context;
    ArrayList<BagModel> homeModelArrayList;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String image,name,category,price,offers;
      float sum = 0;
     static String TotalCost;
    BagModel    bagModel = new BagModel();
    public BagFragmentAdapter(Context c, ArrayList<BagModel> h) {
        this.homeModelArrayList = h;
        this.context = c;
    }
    BagFragmentAdapter(){}




    @NonNull
    @Override

    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_bag_fragment,parent,false);

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



        try{
            String cost = "0";
            setTotalCost(cost);
            cost = String.valueOf(Float.parseFloat(cost)+Float.parseFloat(getTotalCost(position)));

            sum += Float.parseFloat(getTotalCost(position));

            setSum(sum);

            Toast.makeText(context, String.valueOf(sum), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }




        Button remove ,buy,checkout;
        buy = holder.itemView.findViewById(R.id.Buy);
        remove = holder.itemView.findViewById(R.id.removeFromCart);
        checkout = holder.itemView.findViewById(R.id.Checkout);

        ProfileDetails profileDetails = new ProfileDetails();
        BagModel b  = new BagModel();

        buy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                firebaseDatabase = FirebaseDatabase.getInstance();
                final String[] mobile = new String[1];
                databaseReference = firebaseDatabase.getReference("/Users/"+bagModel.getUsername()+"/UserInfo/");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       mobile[0] = snapshot.child("Mobile").getValue().toString();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                try {
                    sendSMS("+91"+mobile[0],"Prashant Manurkar Jewellers \n<<<Payment receipt>>>\nPayment Id: "+b.getUsername()+Math.random()*200+"\nProduct Name : "+homeModelArrayList.get(position).getProductName()+"\n"+
                            "Product Category : "+homeModelArrayList.get(position).getProductCategory()+"\n"+
                            "Product Price  : "+homeModelArrayList.get(position).getProductPrice()+"\n"+

                            "Total Price :"+homeModelArrayList.get(position).getProductPrice()+"\n"+"Thank you\n" );
                    Toast.makeText(context.getApplicationContext(), "Receipt Generated.", Toast.LENGTH_SHORT).show();


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {

                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        Query applesQuery = ref.child("Users/"+bagModel.getUsername()).child("/Bucket/").child(homeModelArrayList.get(position).getProductName());
                        if(homeModelArrayList.size()==1)
                        {
                           ref.child("Users/"+bagModel.getUsername()).child("/Bucket/").getRef().removeValue();
                        }
                        else{
                        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(homeModelArrayList.size()==1)
                                {
                                    dataSnapshot.getRef().removeValue();
                                }
                                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                    if(appleSnapshot.getRef().removeValue().isSuccessful())
                                    {
                                        Toast.makeText(context, "Item Removed Successfully.", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }


                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });}

                    }

                });
//            }
//        });


    }


    private void sendSMS(String phoneNumber,String msg){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber,null,msg,null,null);
    }


    @Override
    public int getItemCount() {
        return homeModelArrayList.size();
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public float getSum() {
        return sum;
    }

    public String getTotalCost(int position) {

        this.TotalCost = String.valueOf(Float.parseFloat(TotalCost)+Float.parseFloat(homeModelArrayList.get(position).getProductPrice()));
        return TotalCost;
    }

    public void setTotalCost(String totalCost) {
        TotalCost = totalCost;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setOffers(String offers) {
        this.offers = offers;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getOffers() {
        return offers;
    }

    public String getCategory() {
        return category;
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView ProductImage;
        TextView ProductName,ProductCategory,ProductPrice,ProductOffers,TotalItems;

        public viewHolder(@NonNull View itemView) {

            super(itemView);


            context  = itemView.getContext();
            TotalItems = itemView.findViewById(R.id.TotalItems);

            ProductImage = itemView.findViewById(R.id.ProductImage);
            ProductName = itemView.findViewById(R.id.ProductName);
            ProductCategory = itemView.findViewById(R.id.ProductCategory);
            ProductPrice = itemView.findViewById(R.id.ProductPrice);
            ProductOffers = itemView.findViewById(R.id.ProductOffers);

        }






    }

}
