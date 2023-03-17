package com.example.pmjewellers.ui.feedback;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pmjewellers.HomeActivity;
import com.example.pmjewellers.MainActivity;
import com.example.pmjewellers.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedbackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedbackFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedbackFragment() {
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
    public static FeedbackFragment newInstance(String param1, String param2) {
        FeedbackFragment fragment = new FeedbackFragment();
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
    Button FeedbackUploadBtn,UploadImage;
    EditText FeedbackTitle;
    EditText Feedback;
    FeedbackModel feedbackModel;
    Uri image;
    ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
     StorageReference storageReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_feedback, container, false);
        FeedbackUploadBtn = view.findViewById(R.id.FeedbackUploadBtn);
        FeedbackTitle = view.findViewById(R.id.FeedbackTitle);
        Feedback = view.findViewById(R.id.Feedback);
        UploadImage = view.findViewById(R.id.ImageUploadBtn);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Feedback");
        UploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               selectImage();
            }
        });
        FeedbackUploadBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                feedbackModel = new FeedbackModel();
                //send data to firebase
                //title
                //feedback

                feedbackModel.setFeedback(Feedback.getText().toString());
                feedbackModel.setTitle(FeedbackTitle.getText().toString());

                try {
                    uploadImage();
                    addFeedback();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        });

        return view;
    }

    private void uploadImage() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Uploading Image...");
        progressDialog.show();

        storageReference = FirebaseStorage.getInstance().getReference("FeedbackImages/");
        storageReference.child(feedbackModel.getTitle()).putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
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

    private void selectImage() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(i,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            image = data.getData();

            Toast.makeText(getContext(), image.toString()+"has been Selected", Toast.LENGTH_SHORT).show();

        }
    }

    public void addFeedback(){

                databaseReference.child(feedbackModel.getTitle()).setValue(feedbackModel.getFeedback());

                Toast.makeText(getContext(), "Feedback Send Successfully", Toast.LENGTH_SHORT).show();

    }

}