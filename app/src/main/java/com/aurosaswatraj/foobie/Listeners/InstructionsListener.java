package com.aurosaswatraj.foobie.Listeners;

import com.aurosaswatraj.foobie.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> responses, String message);
    void didError(String message);
}
