package com.aurosaswatraj.foobie;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    private interface callRandomRecipies{
//        Create a model class for API Response
    }


}
