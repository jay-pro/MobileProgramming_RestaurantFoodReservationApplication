package com.example.restaurantfoodreservationapplication;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
                                                            //CÁI NÀY CHẮC KHÔNG XÀI NHA MN, MÀ TẠO FRAGMENT NÊN NÓ CÓ
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLyMonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLyMonFragment extends Fragment {

    //Toolbar toolbar;
    //TextView add_btn;
    //int i, count = 0;
    //String dish_url;
    //Bitmap bitmap;
    //HashMap<String,String> hashMap = new HashMap<>();
    //FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    //final String userID=firebaseUser.getUid();
    //EditText code_res, name_res, price_res, purl_res;
    //boolean flagadd = false;
    //DatabaseReference mamon;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuanLyMonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuanLyMonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuanLyMonFragment newInstance(String param1, String param2) {
        QuanLyMonFragment fragment = new QuanLyMonFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_mon, container, false);
    }
}