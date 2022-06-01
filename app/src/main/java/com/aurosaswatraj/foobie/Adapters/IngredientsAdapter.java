package com.aurosaswatraj.foobie.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aurosaswatraj.foobie.R;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder> {

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class IngredientsViewHolder extends RecyclerView.ViewHolder{
    TextView textView_ingred_quantity,textView_ingred_name;
    ImageView imageView_ingred;
    public IngredientsViewHolder(@NonNull View itemView){
        super(itemView);
        textView_ingred_quantity=itemView.findViewById(R.id.textView_ingred_quantity);
        textView_ingred_name=itemView.findViewById(R.id.textView_ingred_name);
        imageView_ingred=itemView.findViewById(R.id.imageView_ingred);
    }
}
