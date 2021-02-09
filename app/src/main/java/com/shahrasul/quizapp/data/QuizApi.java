package com.shahrasul.quizapp.data;

import com.shahrasul.quizapp.ui.models.QuizResponse;
import com.shahrasul.quizapp.ui.models.TriviaCategories;

import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuizApi {

    @GET("api.php")
    Call<QuizResponse> getQuestions(
      @Query("amount") String amount,
      @Query("category") String category,
      @Query("difficulty") String difficulty
    );

    @GET("api_category.php")
    Call<TriviaCategories> getCategory();
}
