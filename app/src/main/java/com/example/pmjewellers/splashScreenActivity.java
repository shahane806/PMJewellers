package com.example.pmjewellers;

import android.content.Intent;
import android.os.Bundle;

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
//
//                        GoogleSignInAccount accountinfo = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
//                        String userId = accountinfo.getId().toString();
//                        String userEId=accountinfo.getEmail().toString();
//                    if(userEId!=null){
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
        AlertHandling alert = new AlertHandling(getApplicationContext());
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