package com.aurosaswatraj.foobie;

import android.content.Context;

import com.aurosaswatraj.foobie.Listeners.InstructionsListener;
import com.aurosaswatraj.foobie.Listeners.RandomRecipeResponseListener;
import com.aurosaswatraj.foobie.Listeners.RecipeDetailsListener;
import com.aurosaswatraj.foobie.Listeners.SimilarRecipeListener;
import com.aurosaswatraj.foobie.Models.InstructionsResponse;
import com.aurosaswatraj.foobie.Models.RandomRecipeApiResponse;
import com.aurosaswatraj.foobie.Models.RecipeDetailsResponse;
import com.aurosaswatraj.foobie.Models.SimilarRecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
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
/**
 *
 * Retrofit turns your HTTP API into a Java interface.*/

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


//        Synchronously send the request and return its response
        @GET("recipes/random")
//        This is a method
        Call<RandomRecipeApiResponse> callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String Number,
                @Query("tags")List<String> tags
        );

        /**
         * Query parameter appended to the URL
         * Simple Example:
         *    @GET("/friends")
         *    Call<ResponseBody> friends(@Query("page") int page);
         * Calling with foo.friends(1) yields /friends?page=1.
         * */

    }


    private interface CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );

        //        Now Create a method to access this interface..!

//        Refer the Spoonacular API Documentation and Copy the example response...!
//        Convert the response to java code using https://json2csharp.com/code-converters/json-to-pojo
    }



    public void getRandomRecipes(RandomRecipeResponseListener listener,List<String> tags){
//        https://square.github.io/retrofit/
        CallRandomRecipies callRandomRecipies=retrofit.create(CallRandomRecipies.class);
        Call<RandomRecipeApiResponse> call=callRandomRecipies.callRandomRecipe(context.getString(R.string.api_key)
        ,"10",tags);
//        then Enque all
        /**
         * Returns true if this call has been either executed or enqueued.
         * It is an error to execute or enqueue a call more than once.*/
        call.enqueue(new Callback<RandomRecipeApiResponse>() {

            /**
             * "Callback<RandomRecipeApiResponse>()"
             * Communicates responses from a server or offline requests.
             * One and only one method will be invoked in response to a given request.
             * Callback methods are executed using the Retrofit callback executor.
             * When none is specified, the following defaults are used:
             * Android: Callbacks are executed on the application's main (UI) thread.
             * JVM: Callbacks are executed on the background thread which performed the request.
             *
             *
             * */


            @Override
            /**
             *
             * Invoked for a received HTTP response.
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call "Response.isSuccessful()" to determine if the response indicates success.
             *
             * */
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
//            Here Response->An HTTP response.{ Create a synthetic successful response with body as the deserialized body. }
                if (!response.isSuccessful()){
//                   isSuccessful()-> Returns true if code() is in the range [200..300).
                    listener.error(response.message());
                    return;
                }
//                response.body()-> The deserialized response body of a successful response
//               response.message()-> HTTP status message or null if unknown
                listener.didfetch(response.body(), response.message());

                /**
                 * Deserialization is the process of converting JSON responses into classes.
                 * By doing this we can convert JSON strings into strongly types strings that are less error-prone.*/

            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
//        Call this Method From MainActivity to get all data..!
    }

//    Pass listener as the parameter .. Create a listener inside the Listener Package
    public  void getRecipeDetails(RecipeDetailsListener listener, int id){

//        Call the CallRecipeInterface
        CallRecipeDetails callRecipeDetails=retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call= callRecipeDetails.callRecipeDetails(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());

            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });

    }

    public void GetSimilarRecipe(SimilarRecipeListener listener, int id){
        CallSimilarRecipes callSimilarRecipes = retrofit.create(CallSimilarRecipes.class);
        Call<List<SimilarRecipeResponse>> call = callSimilarRecipes.callSimilarRecipe(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<List<SimilarRecipeResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipeResponse>> call, Response<List<SimilarRecipeResponse>> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipeResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void GetInstructions(InstructionsListener listener, int id){
        CallInstructions callInstructions = retrofit.create(CallInstructions.class);
        Call<List<InstructionsResponse>> call = callInstructions.callInstructions(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<List<InstructionsResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionsResponse>> call, Response<List<InstructionsResponse>> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionsResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }


    private interface CallSimilarRecipes{

        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipeResponse>> callSimilarRecipe(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
    private interface CallInstructions{

        @GET("recipes/{id}/analyzedInstructions")
        Call<List<InstructionsResponse>> callInstructions(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }


}
