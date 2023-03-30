package com.example.pmjewellers.ui.bag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.pmjewellers.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProductDetailedActivity extends AppCompatActivity {

    ImageView ProductImage;
    Button addToCart;
     DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    Bundle bundle;
    TextView ProductName,ProductCategory,ProductPrice,ProductOffers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detailed);
        getSupportActionBar().setTitle("Product Details");


        ProductImage = findViewById(R.id.ProductDetailActivityProductImage);
        BagModel bagModel = new BagModel();

        Intent intent = getIntent();
       bundle =  intent.getExtras();
//        if(bundle!=null)
//        {
//                ArrayList<HomeModel> homeModelArrayList = (ArrayList<HomeModel>) bundle.getSerializable("ArrayList");
//                int position = (int) bundle.getInt("position");

//            String productImage=(String) homeModelArrayList.get(position).getProductImage();
//            String productName=(String) homeModelArrayList.get(position).getProductName();
//            String productCategory=(String) homeModelArrayList.get(position).getProductCategory();
//            String productOffers=(String) homeModelArrayList.get(position).getProductOffer();
//            String productPrice=(String) homeModelArrayList.get(position).getProductPrice();

//            String productImage=(String) bundle.getString("ProductImage");
//            String productName=(String) bundle.getString("ProductName");
//            String productCategory=(String) bundle.getString("ProductCategory");
//            String productPrice=(String) bundle.getString("ProductPrice");
//            String productOffers=(String) bundle.getString("ProductOffers");

            String productImage=(String) intent.getStringExtra("ProductImage");
            String productName=(String) intent.getStringExtra("ProductName");
            String productCategory=(String) intent.getStringExtra("ProductCategory");
            String productPrice=(String) intent.getStringExtra("ProductPrice");
            String productOffers=(String) intent.getStringExtra("ProductOffers");

            Toast.makeText(getApplicationContext(), productCategory, Toast.LENGTH_SHORT).show();


            Glide.with(getApplicationContext()).load(productImage).centerCrop().into(ProductImage);
            ProductName = findViewById(R.id.ProductDetailActivityProductName);
            ProductCategory = findViewById(R.id.ProductDetailActivityProductCategory);
            ProductPrice = findViewById(R.id.ProductDetailActivityProductPrice);
            ProductOffers = findViewById(R.id.ProductDetailActivityProductOffers);
            addToCart = findViewById(R.id.ProductDetailActivityaddToCart);

            ProductOffers.setText(productOffers);
            ProductPrice.setText(productPrice);
            ProductCategory.setText(productCategory);
            ProductName.setText(productName);

            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference(bagModel.getUsername()+"/Bucket").child(productName);
                    databaseReference.child("ProductName").setValue(productName);
//                    databaseReference.child("ProductImage").setValue(productImage);
                    databaseReference.child("ProductCategory").setValue(productCategory);
                    databaseReference.child("ProductPrice").setValue(productPrice);
                    databaseReference.child("ProductOffers").setValue(productOffers);
                }
            });

//        }
//        else {
//            Toast.makeText(ProductDetailedActivity.this,"Error", Toast.LENGTH_SHORT).show();
//        }




    }

    @Override
    public void onBackPressed() {



        super.onBackPressed();


    }

    @Override
    protected void onStop() {
        super.onStop();
        bundle.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bundle.clear();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
