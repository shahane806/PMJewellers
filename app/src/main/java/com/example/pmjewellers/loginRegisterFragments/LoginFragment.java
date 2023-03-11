package com.example.pmjewellers.loginRegisterFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pmjewellers.MainActivity;
import com.example.pmjewellers.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
    FirebaseAuth login_authentication;
    MainActivity mainActivity;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        RegisterBtn = (Button)view.findViewById(R.id.FragmentLoginRegisterBtn);
        mainActivity = (MainActivity)getActivity();
        login_authentication=FirebaseAuth.getInstance();
        email=(EditText)view.findViewById(R.id.LoginEmail);
        password=(EditText)view.findViewById(R.id.LoginPassword);
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

                login_validation(email.getText().toString(),password.getText().toString());



            }
        });

        return  view;
    }

    private void login_validation(String email, String password) {
        if(email.isEmpty())
        {
            Toast.makeText(getActivity().getApplicationContext(),"ENter EmailId... ",Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.isEmpty())
        {
            Toast.makeText(getActivity().getApplicationContext(),"ENter Password... ",Toast.LENGTH_SHORT).show();
            return;
        }
        login_authentication.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getActivity().getApplicationContext(), "Login Succesful.", Toast.LENGTH_SHORT).show();
                            mainActivity.changeFragment("Dashboard");

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(getActivity().getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                            return ;

                        }
                    }
                });
    }
}