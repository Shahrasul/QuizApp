package com.shahrasul.quizapp.data;

import android.util.Log;

import com.google.gson.Gson;
import com.shahrasul.quizapp.data.callback.IQuizApiCallBack;
import com.shahrasul.quizapp.ui.models.QuizResponse;
import com.shahrasul.quizapp.ui.models.TriviaCategories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizApiClient implements IQuizApiClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    QuizApi quizApi = retrofit.create(QuizApi.class);



    @Override
    public void getQuestions(String amount, String category, String difficulty, IQuizApiCallBack.Questions callBack) {
        quizApi.getQuestions(amount, category, difficulty)
                .enqueue(new Callback<QuizResponse>() {
                    @Override
                    public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body() !=null){
                                callBack.onSuccess(response.body());
                                Log.e("getEndPoint", "onResponse: "  + " \npoint = " + call.request().url().toString());
                                Log.d("gdfgfggf", "onSuccess: " + response.body().getResults());
                            }else {
                                callBack.onFailure(new Exception("Response is Empty " + response.code()));
                            }
                        }else {
                            callBack.onFailure(new Exception("Response is Empty " + response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<QuizResponse> call, Throwable t) {
                        t.printStackTrace();
                        Log.e("ololo", "onFailure: ", t);
                        callBack.onFailure(new Exception(t));
                    }
                });
    }

    @Override
    public void getCategory(CategoryCallBack categoryCallBack) {
        quizApi.getCategory().enqueue(new Callback<TriviaCategories>() {
            @Override
            public void onResponse(Call<TriviaCategories> call, Response<TriviaCategories> response) {
                if (response.isSuccessful() && response.body() != null) categoryCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TriviaCategories> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getQuestions(QuestionCallBack questionCallBack, Integer id) {
    }
}
