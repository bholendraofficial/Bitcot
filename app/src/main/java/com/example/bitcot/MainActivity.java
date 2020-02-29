package com.example.bitcot;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DogBreedAdapter dogBreedAdapter;
    private List<DogBreedsModel> dogBreedsModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findIDS();
        setRecyclerView();
    }

    private void findIDS() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dogBreedAdapter = new DogBreedAdapter(this, dogBreedsModelList);
        recyclerView.setAdapter(dogBreedAdapter);
    }

}
