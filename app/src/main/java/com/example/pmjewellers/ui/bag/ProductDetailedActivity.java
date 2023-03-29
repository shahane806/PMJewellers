package com.example.pmjewellers.ui.bag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pmjewellers.HomeActivity;
import com.example.pmjewellers.R;
import com.example.pmjewellers.ui.home.HomeModel;
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
       bundle =  intent.getBundleExtra("Bundle");
        if(bundle!=null)
        {

            String userName = (String) bundle.get("UserName");
            String productImage = (String) bundle.get("ProductImage");
            String productName = (String) bundle.get("ProductName");
            String productCategory = (String) bundle.get("ProductCategory");
            String productOffers = (String) bundle.get("ProductOffers");
            String productPrice = (String) bundle.get("ProductPrice");
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
                    databaseReference.child("ProductImage").setValue(productImage);
                    databaseReference.child("ProductCategory").setValue(productCategory);
                    databaseReference.child("ProductPrice").setValue(productPrice);
                    databaseReference.child("ProductOffers").setValue(productOffers);
                    Toast.makeText(ProductDetailedActivity.this, userName+productName+productPrice+productOffers, Toast.LENGTH_SHORT).show();
                }
            });

        }




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
