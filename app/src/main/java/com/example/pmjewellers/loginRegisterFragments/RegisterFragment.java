package com.example.pmjewellers.loginRegisterFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

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
    EditText email,password;
    MainActivity mainActivity;
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

        email=(EditText)view.findViewById(R.id.RegisterEmailId);
        password=(EditText)view.findViewById(R.id.RegisterPassword);
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

                //Check Registration Validation

                registration_validation(email.getText().toString(),password.getText().toString());
//


            }
        });
        return view;
    }

    private void registration_validation(String email,String password) {

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

            register_authentication.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(getActivity().getApplicationContext(),"Registered Succesfully.",Toast.LENGTH_SHORT).show();

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getActivity().getApplicationContext(),"Registered Succesfully.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

    }
}