package com.aurosaswatraj.foobie;

import android.content.Context;

import com.aurosaswatraj.foobie.Listeners.RandomRecipeResponseListener;
import com.aurosaswatraj.foobie.Models.RandomRecipeApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
//    Create a Context Ojbect and Retrofit Object
    Context context;
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


//    Constructor for Context
    public RequestManager(Context context) {
        this.context = context;
    }

//    Interface for Random Recipe API call

    private interface CallRandomRecipies{
//        Create a model class for API Response
//        Switch to model package and create classes..!
//   Use this to convert JSON data to Model classes:https://freecodegenerators.com/code-converters/json-to-pojo
//        Note that do not add the root class.>!
//        Models Package contains all the sub classes for the api response.>!


        /**
         * Also Create a Random API response class for (root module class) to get response*/

//        Next
//        Refer the documents for passing parameters
//        Since this call method is a get method provide a annotation as
//        Base the part of the url part after base URL from site:https://spoonacular.com/food-api/docs#Get-Random-Recipes
//        GET: https://api.spoonacular.com/recipes/random

        @GET("recipes/random")
        Call<RandomRecipeApiResponse> callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String Number
        );

    }


    public void getRandomRecipes(RandomRecipeResponseListener listener){
        CallRandomRecipies callRandomRecipies=retrofit.create(CallRandomRecipies.class);
        Call<RandomRecipeApiResponse> call=callRandomRecipies.callRandomRecipe(context.getString(R.string.api_key)
        ,"10");
//        then Enque all
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {

                if (!response.isSuccessful()){
                    listener.error(response.message());
                    return;
                }
                listener.didfetch(response.body(), response.message());

            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
//        Call this Method From MainActivity to get all data..!
    }


}
