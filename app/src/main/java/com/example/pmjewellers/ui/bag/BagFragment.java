package com.example.pmjewellers.ui.bag;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pmjewellers.HomeActivity;
import com.example.pmjewellers.MainActivity;
import com.example.pmjewellers.R;
import com.example.pmjewellers.ui.home.AdBannerFragmentAdapter;
import com.example.pmjewellers.ui.home.HomeFragmentAdapter;
import com.example.pmjewellers.ui.home.HomeFragmentAdapterThree;
import com.example.pmjewellers.ui.home.HomeFragmentAdapterTwo;
import com.example.pmjewellers.ui.home.HomeModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BagFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BagFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BagFragment() {
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
    public static BagFragment newInstance(String param1, String param2) {
        BagFragment fragment = new BagFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    DatabaseReference myref;
    ArrayList<BagModel> homeModelArrayList;


    BagFragmentAdapter adapter;

    RecyclerView recyclerView;

    LinearLayoutManager linearLayoutManager;
    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =  inflater.inflate(R.layout.fragment_bag, container, false);
        myref = FirebaseDatabase.getInstance().getReference();
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    ShowBucket();

                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    ShowBucket();
                }
            }
        };
        thread.start();
        Clear_homeModelArrayList();
        Toast.makeText(getContext(), "BagFragment", Toast.LENGTH_SHORT).show();

        return view;
    }

    //***************************************************  WORKING ON CART  *********************//
    public void ShowBucket(){

        BagModel bagModel = new BagModel();
        Query query = myref.child(bagModel.getUsername()+"/Bucket");
        recyclerView= view.findViewById(R.id.BagRecyclerView);
        adapter = new BagFragmentAdapter(getActivity().getApplicationContext(),homeModelArrayList);

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
       //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),DividerItemDecoration.VERTICAL));


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Clear_homeModelArrayList();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        BagModel bagModel = new BagModel();

                   try {
                       bagModel.setProductImage(snapshot.child("ProductImage").getValue().toString());
                       bagModel.setProductName(snapshot.child("ProductName").getValue().toString());
                       bagModel.setProductCategory(snapshot.child("ProductCategory").getValue().toString());
                       bagModel.setProductOffer(snapshot.child("ProductOffers").getValue().toString());
                       bagModel.setProductPrice(snapshot.child("ProductPrice").getValue().toString());
                   }
                   catch (Exception e){
                       e.printStackTrace();
                   }
                    homeModelArrayList.add(bagModel);

                }
                adapter = new BagFragmentAdapter(getContext(),homeModelArrayList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void Clear_homeModelArrayList(){
        if(homeModelArrayList != null){
            homeModelArrayList.clear();
        }

        homeModelArrayList = new ArrayList<>();

    }
    //***************************************************  WORKING ON CART  *********************//
    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}