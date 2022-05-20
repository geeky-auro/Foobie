package com.aurosaswatraj.foobie.Listeners;

import com.aurosaswatraj.foobie.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didfetch(RandomRecipeApiResponse response,String message);
    void error(String Message);
}
