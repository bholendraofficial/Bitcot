package com.example.bitcot.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bitcot.R;
import com.example.bitcot.activity.SubBreeds;
import com.example.bitcot.model.DogBreedsModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DogBreedAdapter extends RecyclerView.Adapter<DogBreedAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<HashMap<String,Object>> dogBreedsModelList;

    public DogBreedAdapter(Context context, ArrayList<HashMap<String,Object>> dogBreedsModelList) {
        this.context = context;
        this.dogBreedsModelList = dogBreedsModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_layout, null, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        String title=dogBreedsModelList.get(i).get("title").toString();
        myViewHolder.tv_title.setText(title);
        myViewHolder.tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONArray jsonArray= (JSONArray) dogBreedsModelList.get(i).get("data");
                if (jsonArray != null&& jsonArray.length()>0)
                {
                    Intent intent = new Intent(context, SubBreeds.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("data", jsonArray.toString());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }else
                {
                    Toast.makeText(context, "No more sub breeds.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dogBreedsModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
