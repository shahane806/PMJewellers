package com.example.pmjewellers;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.pmjewellers.ui.account.AccountFragment;
import com.example.pmjewellers.ui.bag.BagFragment;
import com.example.pmjewellers.ui.feedback.FeedbackFragment;
import com.example.pmjewellers.ui.home.HomeFragment;
import com.example.pmjewellers.ui.logout.LogoutFragment;
import com.example.pmjewellers.ui.review.ReviewFragment;
import com.example.pmjewellers.ui.settings.SettingsFragment;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    NavigationView navigationView;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView UserId,UserEId;
    static String id = "HomeFragment";
    MenuItem menuItem;
    BagFragment bagFragment = new BagFragment();
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
                    Toast.makeText(HomeActivity.this, lastFragment, Toast.LENGTH_SHORT).show();

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new HomeFragment());
                    fragmentTransaction.commit();

                    Toast.makeText(HomeActivity.this, id, Toast.LENGTH_LONG).show();

                }
                else if(lastFragment.equals("Settings")){
                    id="HomeFragment";
                    Toast.makeText(HomeActivity.this, lastFragment, Toast.LENGTH_SHORT).show();

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new SettingsFragment());
                    fragmentTransaction.commit();

                    Toast.makeText(HomeActivity.this, id, Toast.LENGTH_LONG).show();
                } else if (lastFragment.equals("Logout")) {
                    id="HomeFragment";
                    Toast.makeText(HomeActivity.this, lastFragment, Toast.LENGTH_SHORT).show();

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new LogoutFragment());
                    fragmentTransaction.commit();

                    Toast.makeText(HomeActivity.this, id, Toast.LENGTH_LONG).show();
                } else if (lastFragment.equals("Feedback")) {
                    id="HomeFragment";
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new FeedbackFragment());
                    fragmentTransaction.commit();

                    Toast.makeText(HomeActivity.this, id, Toast.LENGTH_LONG).show();
                }else if (lastFragment.equals("Reviews")) {
                    id="HomeFragment";
                    Toast.makeText(HomeActivity.this, lastFragment, Toast.LENGTH_SHORT).show();

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new ReviewFragment());
                    fragmentTransaction.commit();

                    Toast.makeText(HomeActivity.this, id, Toast.LENGTH_LONG).show();
                }else if (lastFragment.equals("Account")) {
                    id="HomeFragment";
                    Toast.makeText(HomeActivity.this, lastFragment, Toast.LENGTH_SHORT).show();

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new AccountFragment());
                    fragmentTransaction.commit();

                    Toast.makeText(HomeActivity.this, id, Toast.LENGTH_LONG).show();
                }


            }
            public void swap(){

              try {
                  if(id == "BagFragment"){
                      String lastFragment= navigationView.getCheckedItem().toString();
                      changeFragment(lastFragment);


                  }
                  else if (id == "HomeFragment") {
                      id = "BagFragment";
                      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                      fragmentTransaction.replace(R.id.nav_host_fragment_content_main, bagFragment);
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

        //////NEED ONE TIME LOGIN TO SAVE THE DATA ////

//        String UserId= intent.getStringExtra("UserId");
//        String userEID= intent.getStringExtra("UserEId");
//
//        if(UserId.equals("default"))
//        {
//            String[] splitStr=userEID.split("@");
//            UserId =splitStr[0];
//        }
        //////NEED ONE TIME LOGIN TO SAVE THE DATA ////
        String UserId = "Hello";
        String userEID = "UserEID";

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
        if(item.getItemId()==R.id.action_logout)
        {
            Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();


        }
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