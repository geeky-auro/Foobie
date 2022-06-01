package com.aurosaswatraj.foobie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aurosaswatraj.foobie.Listeners.RecipeDetailsListener;
import com.aurosaswatraj.foobie.Models.RecipeDetailsResponse;
import com.squareup.picasso.Picasso;

public class RecipeDetailsActivity extends AppCompatActivity {

    int id;
    TextView textView_meal_name,textView_meal_source,textView_meal_summary;
    ImageView imageView_meal_image;
    RecyclerView recycler_meal_ingredients;
    RequestManager manager;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        findViews();
        

//        Fetch theintent data from the mainActivity
        id=Integer.parseInt(getIntent().getStringExtra("id"));
        manager=new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsResponse,id);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading Details..!");
        dialog.show();


    }

    private void findViews() {
        textView_meal_name=findViewById(R.id.textView_meal_name);
        textView_meal_source=findViewById(R.id.textView_meal_source);
        textView_meal_summary=findViewById(R.id.textView_meal_summary);
        imageView_meal_image=findViewById(R.id.imageView_meal_image);
        recycler_meal_ingredients=findViewById(R.id.recycler_meal_ingredients);

    }

    private final RecipeDetailsResponse recipeDetailsResponse = new RecipeDetailsResponse() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            textView_meal_name.setText(response.title);
            textView_meal_source.setText(response.sourceName);
            textView_meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(imageView_meal_image);

//            Show Recycler View ingredients..!

            showData(response);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };


}