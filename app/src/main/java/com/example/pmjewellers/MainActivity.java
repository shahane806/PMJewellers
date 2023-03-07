//This is main Activity

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
        new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.drawable.warning)
                .setTitle("Warning !!!")
                .setMessage("Are you sure to Exit ?")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);
                    }
                })
                .setNeutralButton("Help", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Press Exit button to close the app, else press Cancel ", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();

    }

}
