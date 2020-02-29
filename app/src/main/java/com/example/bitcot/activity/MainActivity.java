package com.example.bitcot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bitcot.R;
import com.example.bitcot.adapter.DogBreedAdapter;
import com.example.bitcot.networkAPI.API_Config;
import com.example.bitcot.networkAPI.ApiActions;
import com.example.bitcot.networkAPI.ApiService;
import com.example.bitcot.networkAPI.ConnectToRetrofit;
import com.example.bitcot.networkAPI.RetrofitCallBackListener;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements RetrofitCallBackListener {
    private DogBreedAdapter dogBreedAdapter;
    private ArrayList<HashMap<String, Object>> dogBreedsModelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRecyclerView();
        getListofDogBreedsAPI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.random:
                openRandomImageActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openRandomImageActivity() {
        Intent intent=new Intent(this,RandomImage.class);
        startActivity(intent);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dogBreedAdapter = new DogBreedAdapter(this, dogBreedsModelList);
        recyclerView.setAdapter(dogBreedAdapter);
    }

    @Override
    public void retrofitCallBackListener(JsonObject result, String action) throws JSONException {
        if (ApiActions.ALL_BREEDS_LIST.equals(action)) {
            JSONObject jsonObject = new JSONObject(result.getAsJsonObject("message").toString());
            Iterator<String> keys = jsonObject.keys();
            dogBreedsModelList.clear();
            while (keys.hasNext()) {
                String key = keys.next();
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("title", key);
                hashMap.put("data", jsonObject.get(key));
                dogBreedsModelList.add(hashMap);
            }
            if (dogBreedsModelList.size() > 0) {
                dogBreedAdapter.notifyDataSetChanged();
            }


        }
    }

    private void getListofDogBreedsAPI() {
        try {
            ApiService apiService = API_Config.getAPIClientByGet();
            Call<JsonObject> call = apiService.getBreedList();
            new ConnectToRetrofit(this, this, call, ApiActions.ALL_BREEDS_LIST);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
