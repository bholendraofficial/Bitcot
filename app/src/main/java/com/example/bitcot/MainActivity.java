package com.example.bitcot;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bitcot.RetrofitAPI.API_Config;
import com.example.bitcot.RetrofitAPI.ApiActions;
import com.example.bitcot.RetrofitAPI.ApiService;
import com.example.bitcot.RetrofitAPI.ConnectToRetrofit;
import com.example.bitcot.RetrofitAPI.RetrofitCallBackListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements RetrofitCallBackListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DogBreedAdapter dogBreedAdapter;
    private List<DogBreedsModel> dogBreedsModelList=new ArrayList<>();


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
    public void retrofitCallBackListener(String result, String action) {

    }
    private  void getListofBreedsAPI()
    {
        try {
            ApiService apiService = API_Config.getAPIClientByGet();
            Call<String> call = apiService.getBreedList();
            new ConnectToRetrofit(this, this, call, ApiActions.ALL_BREEDS_LIST);

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    public void hitapi(View view) {
        getListofBreedsAPI();
    }
}
