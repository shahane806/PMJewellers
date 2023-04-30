

package com.example.pmjewellers;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.pmjewellers.loginRegisterFragments.LoginFragment;
import com.example.pmjewellers.loginRegisterFragments.RegisterFragment;

public class MainActivity extends AppCompatActivity{

    public UserInfo user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

//        FirebaseAuth mauth = (FirebaseAuth) getSystemService(getContext());

        LoginFragment();

    }
    public void changeFragment(String id){
        if(id == "LoginFragment")
        {
            LoginFragment();
        } else if (id == "RegisterFragment") {
            RegisterFragment();
        }
        else if(id == "Dashboard"){
            Dashboard();
        }
    }
    public void Dashboard(){
        Intent Dashboard = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(Dashboard);
    }
    public void LoginFragment(){
        LoginFragment loginRegisterFragment = new LoginFragment();
        FragmentTransaction loginRegisterFragmentTransaction = getSupportFragmentManager().beginTransaction();
        loginRegisterFragmentTransaction.replace(R.id.LoginRegister,loginRegisterFragment);

        loginRegisterFragmentTransaction.commit();
    }
    public void RegisterFragment(){
        RegisterFragment loginRegisterFragment = new RegisterFragment();
        FragmentTransaction loginRegisterFragmentTransaction = getSupportFragmentManager().beginTransaction();
        loginRegisterFragmentTransaction.replace(R.id.LoginRegister,loginRegisterFragment);
        loginRegisterFragmentTransaction.commit();
    }

    @Override
    public void onBackPressed(){
        AlertHandling alert=new AlertHandling(MainActivity.this);
        alert.exitAppAlertDialog();

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
