package com.example.pmjewellers.loginRegisterFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pmjewellers.AlertHandling;
import com.example.pmjewellers.MainActivity;
import com.example.pmjewellers.R;
import com.example.pmjewellers.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FirebaseAuth register_authentication;
    EditText userName,email,password,confirmpassword;
    MainActivity mainActivity;
    public static String userId;
    AlertHandling alert;
    ProgressBar progressBar;
    FirebaseDatabase database;
    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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

    Button LoginBtn;
    Button RegisterRegisterBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_register, container, false);
        mainActivity = (MainActivity)getActivity();
        alert=new AlertHandling(getContext());

        progressBar=(ProgressBar)view.findViewById(R.id.RegisterprogressBar);
        userName=(EditText)view.findViewById(R.id.UserName);
        email=(EditText)view.findViewById(R.id.RegisterEmailId);
        password=(EditText)view.findViewById(R.id.RegisterPassword);
        confirmpassword=(EditText)view.findViewById(R.id.ConfirmRegisterPassword);
        register_authentication=FirebaseAuth.getInstance();
        LoginBtn = (Button) view.findViewById(R.id.FragmentRegisterLoginBtn);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.changeFragment("LoginFragment");

            }

        });
        RegisterRegisterBtn = (Button)view.findViewById(R.id.FragmentRegisterRegisterBtn);
        RegisterRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // mainActivity.changeFragment("Dashboard");
                progressBar.setVisibility(View.VISIBLE);
                //Check Registration Validation

                registration_validation(userName.getText().toString(),email.getText().toString(),password.getText().toString(),confirmpassword.getText().toString());



            }
        });
        return view;
    }

    private void registration_validation(String userName,String email,String password,String confirmpassword) {

        if(email.isEmpty())
        {
           alert.emailRequiredDialog();
            progressBar.setVisibility(View.GONE);
            return;
        }
         if(userName.isEmpty())
                {
                   alert.emailRequiredDialog();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

         if(password.isEmpty()||confirmpassword.isEmpty())
        {
            alert.passwordRequiredDialog();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if(password.length()<8){
            Toast.makeText(getContext(), "Password Must be 8 digits", Toast.LENGTH_SHORT).show();
            return;
        }
         
         if(!(password.equals(confirmpassword)))
        {
            alert.passwordConfirmationFailedDialog();
            progressBar.setVisibility(View.GONE);
            return ;
        }
            register_authentication.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                 userId=task.getResult().getUser().getUid().toString();
                                String userEmail=task.getResult().getUser().getEmail().toString();
                                UserInfo user=new UserInfo(userName,userEmail,password,confirmpassword,userId);
                                Toast.makeText(getActivity().getApplicationContext(),"Registered Succesfully.",Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                database.getReference().child("Users.").child(userId).setValue(user);


                            } else {
                                // If sign in fails, display a message to the user.
                               alert.registrationFailedDialog();
                                //Toast.makeText(getActivity().getApplicationContext(),"Registered Succesfully.",Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });

    }


}