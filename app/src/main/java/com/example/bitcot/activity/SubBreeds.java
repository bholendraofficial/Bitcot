package com.example.bitcot.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bitcot.R;
import com.example.bitcot.adapter.SubBreedAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

public class SubBreeds extends AppCompatActivity {
    private SubBreedAdapter subBreedAdapter;
    private ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_breeds);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setupRecyclerView();
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String title = bundle.getString("title");
            Objects.requireNonNull(getSupportActionBar()).setTitle(title);
            String data = bundle.getString("data");
            try {
                JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    arrayList.add(jsonArray.get(i).toString());
                }
                subBreedAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupRecyclerView() {
        RecyclerView subBreedRecyclerview = findViewById(R.id.subBreedRecyclerview);
        subBreedRecyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        subBreedRecyclerview.setLayoutManager(layoutManager);

        subBreedAdapter = new SubBreedAdapter(this, arrayList);
        subBreedRecyclerview.setAdapter(subBreedAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
