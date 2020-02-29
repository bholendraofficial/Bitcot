package com.example.bitcot.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bitcot.R;
import com.example.bitcot.networkAPI.API_Config;
import com.example.bitcot.networkAPI.ApiActions;
import com.example.bitcot.networkAPI.ApiService;
import com.example.bitcot.networkAPI.ConnectToRetrofit;
import com.example.bitcot.networkAPI.RetrofitCallBackListener;
import com.google.gson.JsonObject;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.Objects;

import retrofit2.Call;

public class RandomImage extends AppCompatActivity implements RetrofitCallBackListener {
    ImageView img_random;
    ProgressBar image_loader;

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
        img_random = (ImageView) findViewById(R.id.img_random);
        image_loader = (ProgressBar) findViewById(R.id.image_loader);
    }

    private void getRandomImageAPI() {
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
            if (result.get("status").getAsString().equals("success")) {
                String imgUrl = result.get("message").getAsString();
                image_loader.setVisibility(View.VISIBLE);
                Picasso.get().load(imgUrl).error(R.drawable.ic_launcher_background).into(img_random, new Callback() {
                    @Override
                    public void onSuccess() {
                        image_loader.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        image_loader.setVisibility(View.GONE);
                    }
                });
            }

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
