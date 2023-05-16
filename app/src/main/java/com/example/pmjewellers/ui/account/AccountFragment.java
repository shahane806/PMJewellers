package com.example.pmjewellers.ui.account;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.session.MediaController;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.example.pmjewellers.MainActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.pmjewellers.R;
import com.example.pmjewellers.ui.bag.BagModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
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
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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

    EditText name,address,mobile,email;
    Button save;
    ProfileDetails profileDetails;
    ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    StorageReference storageReference;
    ImageView profileImage;
    Uri image;
    String t;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_account, container, false);
        name = view.findViewById(R.id.ProfileName);
        address = view.findViewById(R.id.ProfileAddress);
        mobile = view.findViewById(R.id.mobileNumber);
        email = view.findViewById(R.id.ProfileEmailId);
        profileImage = view.findViewById(R.id.ProfilePicture);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        BagModel bagModel = new BagModel();
        profileDetails = new ProfileDetails();



           Query query = databaseReference.child(bagModel.getUsername());
           query.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                  try {
                      name.setText(snapshot.child("Name").getValue().toString());
                      address.setText(snapshot.child("Address").getValue().toString());
                      mobile.setText(snapshot.child("Mobile").getValue().toString());
                      email.setText(snapshot.child("Email").getValue().toString());
                      String uri = snapshot.child("ProfilePicture").getValue().toString();
                      Glide.with(getContext()).load(uri).circleCrop().into(profileImage);
                  }
                  catch (Exception e){
                      e.printStackTrace();
                  }

               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {
                   Toast.makeText(getContext(), "Nothing set", Toast.LENGTH_SHORT).show();
               }
           });


        try {
            profileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    try {
                        Intent i = new Intent();
                        i.setType("image/*");
                        i.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(i,100);

                    }
                    catch ( Exception e){
                        e.printStackTrace();
                    }


                }
            });

            save = view.findViewById(R.id.save);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    profileDetails.setName(name.getText().toString());
                    profileDetails.setAddress(address.getText().toString());
                    profileDetails.setMobile(mobile.getText().toString());
                    profileDetails.setEmail(email.getText().toString());

                    uploadImage();
                    storageReference = FirebaseStorage.getInstance().getReference(profileDetails.getName());
                    storageReference.child(profileDetails.getName()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
//                            Toast.makeText(getContext(), uri.toString(), Toast.LENGTH_SHORT).show();
//                            Log.d(TAG,"Onsuccess : "+uri);
                            if(uri == null){
                                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                t = uri.toString();
                                Glide.with(getContext()).load(uri).centerCrop().into(profileImage);
                                Toast.makeText(getContext(), t, Toast.LENGTH_SHORT).show();
                                profileDetails.setProfilePicture(t);
                                setUserDetailsToFirebase();
                            }

                        }

                }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Upload Failed, Save Again...", Toast.LENGTH_SHORT).show();
                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                        }
                    });




                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            try {
                image = data.getData();
                Toast.makeText(getContext(), image.toString()+"has been Selected", Toast.LENGTH_SHORT).show();

            }
            catch (Exception e){
                e.printStackTrace();
            }



        }
    }
    private void uploadImage() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Uploading Image...");
        progressDialog.show();


        storageReference = FirebaseStorage.getInstance().getReference(profileDetails.getName());
        storageReference.child(profileDetails.getName()).putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getContext(), image.toString()+"has been Selected", Toast.LENGTH_SHORT).show();
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Upload Failed", Toast.LENGTH_SHORT).show();
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }
        });
    }
    public void setUserDetailsToFirebase(){
        BagModel bagModel = new BagModel();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(bagModel.getUsername());
        databaseReference.child("ProfilePicture").setValue(profileDetails.getProfilePicture());
        databaseReference.child("Address").setValue(profileDetails.getAddress());
        databaseReference.child("Mobile").setValue(profileDetails.getMobile());
        databaseReference.child("Name").setValue(profileDetails.getName());
        databaseReference.child("Email").setValue(profileDetails.getEmail());

    }

}
