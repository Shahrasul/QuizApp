package com.shahrasul.quizapp.data.callback;

import android.util.Log;

import com.shahrasul.quizapp.ui.models.QuizResponse;

public class QuestionsCallBack implements IQuizApiCallBack.Questions {
    @Override
    public void onSuccess(QuizResponse result) {

    }

    @Override
    public void onFailure(Throwable throwable) {
    }

    @Override
    public void onFailure(Exception e) {

    }
}
