package com.aurosaswatraj.foobie.Listeners;

import com.aurosaswatraj.foobie.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {

    void didFetch(RecipeDetailsResponse response,String Message);
    void didError(String message);
}
