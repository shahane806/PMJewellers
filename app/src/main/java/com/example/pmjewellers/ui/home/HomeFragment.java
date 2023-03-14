package com.example.pmjewellers.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmjewellers.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private StorageReference mstorageReference;

    public HomeFragment() {
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
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private DatabaseReference myref;
    ArrayList<HomeModel> homeModelArrayList,adBannerList,homeModelArrayList2,homeModelArrayList3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    HomeFragmentAdapter adapter;
    HomeFragmentAdapterTwo adapter2;
    HomeFragmentAdapterThree adapter3;
    AdBannerFragmentAdapter bannerFragmentAdapter;

    RecyclerView recyclerView,recyclerView2,recyclerView3,recyclerView4,recyclerView5;

    LinearLayoutManager linearLayoutManager;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view =  inflater.inflate(R.layout.fragment_home, container, false);
          myref = FirebaseDatabase.getInstance().getReference();
        //get Data from firebase

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    GetGoldCategoriesBracelets();
                    GetGoldCategoriesNecklace();
                    GetGoldCategoriesRings();
                    AdBanner();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    GetGoldCategoriesBracelets();
                    GetGoldCategoriesNecklace();
                    GetGoldCategoriesRings();
                    AdBanner();

                }

            }

    };
        thread.start();
        Clear_homeModelArrayList();
        Clear_adBannerList();
        Clear_homeModelArrayListTwo();
        Clear_homeModelArrayListThree();

       return view;
    }

    //***************************************************  WORKING ON CART  *********************//


//    Button addToCart;
//    ArrayList<Cart> cartArrayList;
//    public ArrayList<Cart> AddToCart(){
//        addToCart = view.findViewById(R.id.addToCart);
//
//        addToCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });
//        return ArrayList<Cart>;
//    }
//
    //***************************************************  WORKING ON CART  *********************//

    private void GetGoldCategoriesBracelets(){
        Query query = myref.child("GoldCategories/Bracelets");
        recyclerView= view.findViewById(R.id.homeFragmentRecyclerView);

        adapter = new HomeFragmentAdapter(getActivity().getApplicationContext(),homeModelArrayList);

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
      //  recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),DividerItemDecoration.HORIZONTAL));


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Clear_homeModelArrayList();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        HomeModel homeModel = new HomeModel();

                        homeModel.setProductImage(snapshot.child("ProductImage").getValue().toString());
                        homeModel.setProductName(snapshot.child("ProductName").getValue().toString());
                        homeModel.setProductCategory(snapshot.child("ProductCategory").getValue().toString());
                        homeModel.setProductOffer(snapshot.child("ProductOffer").getValue().toString());
                        homeModel.setProductPrice(snapshot.child("ProductPrice").getValue().toString());

                        homeModelArrayList.add(homeModel);

                }
                adapter = new HomeFragmentAdapter(getContext(),homeModelArrayList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void GetGoldCategoriesRings(){
        Query query = myref.child("GoldCategories/Rings");
        recyclerView4= view.findViewById(R.id.homeFragmentRecyclerView4);

        adapter3 = new HomeFragmentAdapterThree(getActivity().getApplicationContext(),homeModelArrayList3);

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView4.setLayoutManager(linearLayoutManager);
      //  recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),DividerItemDecoration.HORIZONTAL));


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Clear_homeModelArrayList();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    HomeModel homeModel = new HomeModel();

                        homeModel.setProductImage(snapshot.child("ProductImage").getValue().toString());
                        homeModel.setProductName(snapshot.child("ProductName").getValue().toString());
                        homeModel.setProductCategory(snapshot.child("ProductCategory").getValue().toString());
                        homeModel.setProductOffer(snapshot.child("ProductOffer").getValue().toString());
                        homeModel.setProductPrice(snapshot.child("ProductPrice").getValue().toString());

                         homeModelArrayList3.add(homeModel);
                }
                adapter3 = new HomeFragmentAdapterThree(getContext(),homeModelArrayList3);
                recyclerView4.setAdapter(adapter3);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void GetGoldCategoriesNecklace(){
        Query query = myref.child("GoldCategories/Necklace");
        recyclerView3= view.findViewById(R.id.homeFragmentRecyclerView3);

        adapter2 = new HomeFragmentAdapterTwo(getActivity().getApplicationContext(),homeModelArrayList2);

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView3.setLayoutManager(linearLayoutManager);
        //  recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),DividerItemDecoration.HORIZONTAL));


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Clear_homeModelArrayListTwo();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    HomeModel homeModel = new HomeModel();

                    homeModel.setProductImage(snapshot.child("ProductImage").getValue().toString());
                    homeModel.setProductName(snapshot.child("ProductName").getValue().toString());
                    homeModel.setProductCategory(snapshot.child("ProductCategory").getValue().toString());
                    homeModel.setProductOffer(snapshot.child("ProductOffer").getValue().toString());
                    homeModel.setProductPrice(snapshot.child("ProductPrice").getValue().toString());

                    homeModelArrayList2.add(homeModel);
                }
                adapter2 = new HomeFragmentAdapterTwo(getContext(),homeModelArrayList2);
                recyclerView3.setAdapter(adapter2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void AdBanner(){

        Query query = myref.child("AdBanner");
        recyclerView2= view.findViewById(R.id.homeFragmentRecyclerView2);

     bannerFragmentAdapter = new AdBannerFragmentAdapter(getActivity().getApplicationContext(),adBannerList);

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(linearLayoutManager);
        recyclerView2.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),DividerItemDecoration.HORIZONTAL));


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Clear_adBannerList();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    HomeModel homeModel = new HomeModel();
                        homeModel.setProductImage(snapshot.child("image").getValue().toString());
                        homeModel.setProductName(snapshot.child("name").getValue().toString());

                    adBannerList.add(homeModel);
                }
                bannerFragmentAdapter = new AdBannerFragmentAdapter(getContext(),adBannerList);
                recyclerView2.setAdapter(bannerFragmentAdapter);

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
    public void Clear_homeModelArrayListTwo(){
        if(homeModelArrayList2 != null){
            homeModelArrayList2.clear();
        }

        homeModelArrayList2 = new ArrayList<>();

    }
    public void Clear_homeModelArrayListThree(){
        if(homeModelArrayList3 != null){
            homeModelArrayList3.clear();
        }

        homeModelArrayList3 = new ArrayList<>();

    }
    public void Clear_adBannerList(){
        if(adBannerList != null){
            adBannerList.clear();
        }

    adBannerList = new ArrayList<>();

    }

}