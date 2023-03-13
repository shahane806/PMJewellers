package com.example.pmjewellers.Firbase;

import static android.app.PendingIntent.getActivity;
import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pmjewellers.HomeActivity;
import com.example.pmjewellers.MainActivity;
import com.example.pmjewellers.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Authentication extends MainActivity {

    public Context context;
    private static final int rc_sign_in=1;
    private static final String Tag = "GOOGLEAUTH";
    public FirebaseAuth login_authentication;
    public GoogleSignInClient mGoogleSignInClient;
    public Authentication(Context context) {
        this.context = context;
        Toast.makeText( context,context.toString(), Toast.LENGTH_SHORT).show();

    }



}
