package com.example.restaurantfoodreservationapplication.ui.quanly_mon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantfoodreservationapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

/*
public class QUANLY_MONFragment extends Fragment {
    RecyclerView recyclerView_quanly_mon;
    QUANLY_MONAdapter adapter;
    FloatingActionButton fab_add_mon;

    private QUANLY_MONViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = new ViewModelProvider(this).get(QUANLY_MONViewModel.class);
        View root = inflater.inflate(R.layout.fragment_quanly_mon, container, false);

        recyclerView_quanly_mon = (RecyclerView) findViewById(R.id.recyclerview_quanly_mon);

        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });


        return root;

    }
}
*/

public class QUANLY_MONFragment extends AppCompatActivity
{
    RecyclerView recyclerView_quanly_mon;
    QUANLY_MONAdapter adapter;
    FloatingActionButton fab_add_mon;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fragment_quanly_mon);
        setTitle("Search here...");

        recyclerView_quanly_mon = (RecyclerView) findViewById(R.id.recyclerview_quanly_mon);
        recyclerView_quanly_mon.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<QUANLY_MONModel> options = new FirebaseRecyclerOptions.Builder<QUANLY_MONModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("quanlymon"),QUANLY_MONModel.class).build();

        adapter = new QUANLY_MONAdapter(options);
        recyclerView_quanly_mon.setAdapter(adapter);

        fab_add_mon = (FloatingActionButton) findViewById(R.id.floatingActionButton_Add_Mon);
        fab_add_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),adddata_mon.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("students").orderByChild("course").startAt(s).endAt(s+"\uf8ff"), model.class)
                        .build();

        adapter=new myadapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }














}