package com.aurosaswatraj.foobie.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aurosaswatraj.foobie.R;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder> {

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }
}

class RandomRecipeViewHolder extends RecyclerView.ViewHolder{

    CardView random_list_container;
    TextView textView_title,textView_serving,textView_likes,textView_time;
    ImageView imageView_food;


    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);

        random_list_container=itemView.findViewById(R.id.random_list_container);
        textView_title=itemView.findViewById(R.id.textView_title);
        textView_serving=itemView.findViewById(R.id.textView_serving);
        textView_likes=itemView.findViewById(R.id.textView_likes);
        textView_time=itemView.findViewById(R.id.textView_time);
        imageView_food=itemView.findViewById(R.id.imageView_food);
    }
}
