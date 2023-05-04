package com.example.pmjewellers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pmjewellers.databinding.ActivityHomeBinding;
import com.example.pmjewellers.ui.account.AccountFragment;
import com.example.pmjewellers.ui.bag.BagFragment;
import com.example.pmjewellers.ui.bag.BagModel;
import com.example.pmjewellers.ui.feedback.FeedbackFragment;
import com.example.pmjewellers.ui.home.HomeFragment;
import com.example.pmjewellers.ui.logout.LogoutFragment;
import com.example.pmjewellers.ui.review.ReviewFragment;
import com.example.pmjewellers.ui.settings.SettingsFragment;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    NavigationView navigationView;
    UserInfo user;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView UserId,UserEId;
     static String lastFragment;

    static String id = "HomeFragment";
    MenuItem menuItem;
    BagFragment bagFragment = new BagFragment();
     FirebaseDatabase firebaseDatabase;

     DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navigationView=findViewById(R.id.nav_view);

        setSupportActionBar(binding.appBarMain.toolbar);

        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                swap();
            }
            public void changeFragment(String lastFragment){
                if(lastFragment.equals("Home"))
                {
                    id="HomeFragment";

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new HomeFragment());
                    fragmentTransaction.commit();


                }
                else if(lastFragment.equals("Settings")){
                    id="HomeFragment";


                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new SettingsFragment());
                    fragmentTransaction.commit();

                } else if (lastFragment.equals("Logout")) {
                    id="HomeFragment";
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new LogoutFragment());
                    fragmentTransaction.commit();

                } else if (lastFragment.equals("Feedback")) {
                    id="HomeFragment";
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new FeedbackFragment());
                    fragmentTransaction.commit();
                }else if (lastFragment.equals("Reviews")) {
                    id="HomeFragment";

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new ReviewFragment());
                    fragmentTransaction.commit();

                }else if (lastFragment.equals("Account")) {
                    id="HomeFragment";

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new AccountFragment());
                    fragmentTransaction.commit();

                }


            }
            public void swap(){

              try {
                  if(id == "BagFragment"){
                      lastFragment= navigationView.getCheckedItem().toString();

                      changeFragment(lastFragment);


                  }
                  else if (id == "HomeFragment") {
                      id = "BagFragment";
                      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                      fragmentTransaction.replace(R.id.nav_host_fragment_content_main, bagFragment);
                      fragmentTransaction.commit();

//                      Toast.makeText(HomeActivity.this, id, Toast.LENGTH_SHORT).show();


                  }


              }catch (Exception e){
                  e.printStackTrace();
              }

            }

        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_account, R.id.nav_bag, R.id.nav_feedback,R.id.nav_review, R.id.nav_settings, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

            setHeaderUser();

    }
    BagModel bagModel = new BagModel();

    private void setHeaderUser() {

        View headerView = navigationView.getHeaderView(0);
        TextView userId =(TextView) navigationView.getHeaderView(0).findViewById(R.id.UserID);
        TextView userEId =(TextView) navigationView.getHeaderView(0).findViewById(R.id.UserEID);

        Intent intent=getIntent();

        //////NEED ONE TIME LOGIN TO SAVE THE DATA ////

        String UserId= intent.getStringExtra("UserId");
        String userEID= intent.getStringExtra("UserEId");
        databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Users/"+UserId);


        databaseReference=FirebaseDatabase.getInstance().getReference().child("Users/"+UserId+"/UserInfo");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    user = snapshot.getValue(UserInfo.class);

                    Log.d("UserName : : : : : : ",user.getUserName());
                }
                catch(Exception e) {
                    Log.d("Error : : : : : : ", e.toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Log.d("USER ID IS :::::::::",UserId);
        if(UserId.equals("default"))
        {
            String[] splitStr=userEID.split("@");
            UserId =splitStr[0];
        }
        //////NEED ONE TIME LOGIN TO SAVE THE DATA ////


        userId.setText("User ID: "+UserId);
        userEId.setText("Email ID: "+userEID);

        createUserOnFirebase(UserId);
        BagModel bagModel1 = new BagModel();
        bagModel1.setUsername(UserId);
    }
    public void createUserOnFirebase(String UserId){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(UserId+"/Bucket");

    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public void onBackPressed(){

        if(navigationView.getCheckedItem().toString().equals("Home")){
            AlertHandling alert=new AlertHandling(HomeActivity.this);
            alert.exitAppAlertDialog();
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}