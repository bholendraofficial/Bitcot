package com.example.bitcot.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bitcot.R;
import com.example.bitcot.adapter.DogBreedAdapter;
import com.example.bitcot.model.DogBreedsModel;
import com.example.bitcot.networkAPI.API_Config;
import com.example.bitcot.networkAPI.ApiActions;
import com.example.bitcot.networkAPI.ApiService;
import com.example.bitcot.networkAPI.ConnectToRetrofit;
import com.example.bitcot.networkAPI.RetrofitCallBackListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements RetrofitCallBackListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DogBreedAdapter dogBreedAdapter;
    private List<DogBreedsModel> dogBreedsModelList = new ArrayList<>();


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

    @Override
    public void retrofitCallBackListener(JsonObject result, String action) {
        if (ApiActions.ALL_BREEDS_LIST.equals(action)) {
            Gson gson = new Gson();
            DogBreedsModel dogBreedsModel = gson.fromJson(result.getAsJsonObject("message"), DogBreedsModel.class);
            dogBreedsModelList.clear();
            dogBreedsModelList.add(dogBreedsModel);
            dogBreedAdapter.notifyDataSetChanged();
        }

        Toast.makeText(this, "" + result.toString(), Toast.LENGTH_SHORT).show();
    }

    private void getListofBreedsAPI() {
        try {
            ApiService apiService = API_Config.getAPIClientByGet();
            Call<JsonObject> call = apiService.getBreedList();
            new ConnectToRetrofit(this, this, call, ApiActions.ALL_BREEDS_LIST);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void hitapi(View view) {
        getListofBreedsAPI();
    }
}
