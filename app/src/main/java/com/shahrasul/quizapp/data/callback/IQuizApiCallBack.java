package com.shahrasul.quizapp.data.callback;

import com.shahrasul.quizapp.core.IBaseCallBack;
import com.shahrasul.quizapp.ui.models.QuizResponse;
import com.shahrasul.quizapp.ui.models.TriviaCategories;

public interface IQuizApiCallBack {
    interface Questions extends IBaseCallBack<QuizResponse> {
        void onSuccess(QuizResponse quizResponse);

        void onFailure(Throwable throwable);
    }

    interface Categories extends IBaseCallBack<TriviaCategories> {
        void onSuccess(TriviaCategories categories);

        void onFailure(Throwable throwable);
    }
}