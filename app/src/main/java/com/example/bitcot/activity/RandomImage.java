package com.example.bitcot.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.bitcot.R;
import com.example.bitcot.networkAPI.API_Config;
import com.example.bitcot.networkAPI.ApiActions;
import com.example.bitcot.networkAPI.ApiService;
import com.example.bitcot.networkAPI.ConnectToRetrofit;
import com.example.bitcot.networkAPI.RetrofitCallBackListener;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import retrofit2.Call;

public class RandomImage extends AppCompatActivity implements RetrofitCallBackListener {
ImageView img_random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_image);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Random Image");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        findIDS();
        getRandomImageAPI();
    }

    private void findIDS() {
        img_random=(ImageView)findViewById(R.id.img_random);
    }

    private  void getRandomImageAPI()
    {
        try {
            ApiService apiService = API_Config.getAPIClientByGet();
            Call<JsonObject> call = apiService.getRandomImage();
            new ConnectToRetrofit(this, this, call, ApiActions.RANDOM_IMAGE);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void retrofitCallBackListener(JsonObject result, String action) throws JSONException {
        if (ApiActions.RANDOM_IMAGE.equals(action)) {
            String imgUrl=result.get("message").toString();
            Picasso.get().load(imgUrl).into(img_random);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
