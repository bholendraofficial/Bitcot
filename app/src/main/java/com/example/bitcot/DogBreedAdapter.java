package com.example.bitcot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DogBreedAdapter extends RecyclerView.Adapter<DogBreedAdapter.MyViewHolder> {
    private Context context;
    private List<DogBreedsModel> dogBreedsModelList;

    DogBreedAdapter(Context context, List<DogBreedsModel> dogBreedsModelList) {
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

    }

    @Override
    public int getItemCount() {
        return dogBreedsModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
