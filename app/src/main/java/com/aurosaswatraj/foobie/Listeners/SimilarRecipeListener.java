package com.aurosaswatraj.foobie.Listeners;


import com.aurosaswatraj.foobie.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipeListener {
    void didFetch(List<SimilarRecipeResponse> response, String message);
    void didError(String message);
}
