package com.example.pmjewellers.loginRegisterFragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pmjewellers.AlertHandling;
import com.example.pmjewellers.Firbase.Authentication;
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

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText email,password;
    private FirebaseAuth login_authentication;
    MainActivity mainActivity;
    AlertHandling alert;
    Button facebook,googleLoginButton;

    GoogleSignInClient mGoogleSignInClient;
    private static final int rc_sign_in=1;
    private static final String Tag = "GOOGLEAUTH";


    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    Button RegisterBtn;
    Button LoginBtn;
    ProgressBar loginProgressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        RegisterBtn = (Button)view.findViewById(R.id.FragmentLoginRegisterBtn);
        mainActivity = (MainActivity)getActivity();
        alert = new AlertHandling(getContext());
        loginProgressBar=(ProgressBar)view.findViewById(R.id.loginProgressBar);
        facebook=(Button)view.findViewById(R.id.FacebookLoginBtn);
        googleLoginButton=(Button)view.findViewById(R.id.GoogleLoginBtn);
        login_authentication=FirebaseAuth.getInstance();
        email=(EditText)view.findViewById(R.id.LoginEmail);
        password=(EditText)view.findViewById(R.id.LoginPassword);

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"This Feature Will Available Soon.",Toast.LENGTH_SHORT).show();
            }
        });

// Google Authentication code

        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleAuthentication();
            }
        });


        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.changeFragment("RegisterFragment");
            }
        });


        LoginBtn = (Button)view.findViewById(R.id.FragmentLoginLoginBtn);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Check Login Validation
                loginProgressBar.setVisibility(View.VISIBLE);
                emailAuthentication(email.getText().toString(),password.getText().toString());



            }
        });



        return  view;
    }



    public void googleAuthentication() {
        GoogleSignInOptions gso =new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(getContext(),gso);

        Intent intent=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent,rc_sign_in);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==rc_sign_in)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account=task.getResult(ApiException.class);
                // here we can fetch loged in accounts information
                GoogleSignInAccount accountinfo =GoogleSignIn.getLastSignedInAccount(getContext());
                Intent i=new Intent(getContext(), HomeActivity.class);
                i.putExtra("UserId", accountinfo.getId().toString());
                i.putExtra("UserEId", accountinfo.getEmail().toString());
                startActivity(i);
            }
            catch(ApiException e){
                Toast.makeText(getContext(),"Failed",Toast.LENGTH_LONG).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }




    private void emailAuthentication(String email, String password) {
        if(email.isEmpty())
        {
            alert.emailRequiredDialog();
            loginProgressBar.setVisibility(View.GONE);
            return;
        }

        if(password.isEmpty())
        {
            alert.passwordRequiredDialog();
            loginProgressBar.setVisibility(View.GONE);
            return;
        }
        login_authentication.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            loginProgressBar.setVisibility(View.GONE);
                            //GoogleSignInAccount accountinfo =GoogleSignIn.getLastSignedInAccount(getContext());
                            Intent i=new Intent(getContext(), HomeActivity.class);
                            i.putExtra("UserId", "default");
                            i.putExtra("UserEId", email);
                            startActivity(i);

                        } else {
                            // If sign in fails, display a message to the user.
                            alert.loginFailedDialog();
                            loginProgressBar.setVisibility(View.GONE);
                           // Toast.makeText(getActivity().getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                            return ;

                        }
                    }
                });
    }


}