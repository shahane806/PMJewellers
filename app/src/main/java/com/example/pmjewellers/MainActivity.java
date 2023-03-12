

package com.example.pmjewellers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pmjewellers.loginRegisterFragments.LoginFragment;
import com.example.pmjewellers.loginRegisterFragments.RegisterFragment;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

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

}
