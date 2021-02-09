package com.shahrasul.quizapp.data;

import com.shahrasul.quizapp.core.IBaseCallBack;
import com.shahrasul.quizapp.data.callback.IQuizApiCallBack;
import com.shahrasul.quizapp.ui.models.Question;
import com.shahrasul.quizapp.ui.models.TriviaCategories;

import java.util.List;

public interface IQuizApiClient {

    void getQuestions(String amount, String category, String difficulty, IQuizApiCallBack.Questions callBack);

    void getCategory(CategoryCallBack categoryCallBack);

    interface QuestionCallBack extends IBaseCallBack<List<Question>> {
        @Override
        void onSuccess(List<Question> result);

        @Override
        void onFailure(Exception e);
    }

    interface CategoryCallBack extends IBaseCallBack<TriviaCategories> {
        @Override
        void onSuccess(TriviaCategories result);

        @Override
        void onFailure(Exception e);
    }
}
