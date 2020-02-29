package com.example.bitcot.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bitcot.R;

import org.json.JSONArray;
import org.json.JSONException;

public class SubBreeds extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_breeds);
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
           String data=bundle.getString("data");
            try {
                JSONArray jsonArray=new JSONArray(data);
                for (int i=0;i<jsonArray.length();i++)
                {
                    Toast.makeText(this, ""+jsonArray.get(i), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
