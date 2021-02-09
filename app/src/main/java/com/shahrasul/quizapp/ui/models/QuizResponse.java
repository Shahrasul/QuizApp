package com.shahrasul.quizapp.ui.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class QuizResponse {
    @SerializedName("response_mode")
    @Expose
    private int responseMode;
    @SerializedName("results")
    @Expose
    private ArrayList<Question> results = null;

    public int getResponseMode() {
        return responseMode;
    }

    public void setResponseMode(int responseMode) {
        this.responseMode = responseMode;
    }

    public ArrayList<Question> getResults() {
        return results;
    }

    public void setResults(ArrayList<Question> results) {
        this.results = results;
    }

}
