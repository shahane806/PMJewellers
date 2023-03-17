package com.example.pmjewellers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class splashScreenActivity extends AppCompatActivity {

    FirebaseAuth mauth;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        Thread splashScreen = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
//                    mauth=FirebaseAuth.getInstance();
//                    if(mauth!=null){
//                        GoogleSignInAccount accountinfo = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
//                        String userId = accountinfo.getId().toString();
//                        String userEId=accountinfo.getEmail().toString();
//                        Intent i=new Intent(getApplicationContext(), HomeActivity.class);
//                        i.putExtra("UserId", userId);
//                        i.putExtra("UserEId", userEId);
//                        startActivity(i);
//                    }
//                    {
                        Intent loginActivity = new Intent(splashScreenActivity.this, MainActivity.class);
                        startActivity(loginActivity);
//                    }
                }

            }

        };
        splashScreen.start();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    new AlertDialog.Builder(splashScreenActivity.this)
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
                        Toast.makeText(splashScreenActivity.this, "Press Exit button to close the app, else press Cancel ", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
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