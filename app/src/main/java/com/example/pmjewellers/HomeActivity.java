package com.example.pmjewellers;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pmjewellers.databinding.ActivityHomeBinding;
import com.example.pmjewellers.loginRegisterFragments.LoginFragment;
import com.example.pmjewellers.ui.bag.BagFragment;
import com.example.pmjewellers.ui.home.HomeFragment;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.auth.User;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    NavigationView navigationView;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView UserId,UserEId;
    static String id = "HomeFragment";

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
            public void swap(){

              try {
                  if(id == "BagFragment"){
                      id = "HomeFragment";
                      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                      fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new HomeFragment());
                      fragmentTransaction.commit();

                      Toast.makeText(HomeActivity.this, id, Toast.LENGTH_LONG).show();

                  }
                  else if (id == "HomeFragment") {
                      id = "BagFragment";
                      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                      fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new BagFragment());
                      fragmentTransaction.commit();

                      Toast.makeText(HomeActivity.this, id, Toast.LENGTH_SHORT).show();


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

    private void setHeaderUser() {

        View headerView = navigationView.getHeaderView(0);
        TextView userId =(TextView) navigationView.getHeaderView(0).findViewById(R.id.UserID);
        TextView userEId =(TextView) navigationView.getHeaderView(0).findViewById(R.id.UserEID);

        Intent intent=getIntent();


        String UserId= intent.getStringExtra("UserId");
        String userEID= intent.getStringExtra("UserEId");

        if(UserId.equals("default"))
        {
            String[] splitStr=userEID.split("@");
            UserId =splitStr[0];
        }

        userId.setText("User ID: "+UserId);
        userEId.setText("Email ID: "+userEID);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_activity, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId()==R.id.)
//        {
//
//        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public void onBackPressed(){

        AlertHandling alert=new AlertHandling(HomeActivity.this);
        alert.exitAppAlertDialog();
    }
}