package com.shahrasul.quizapp.data.network;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.shahrasul.quizapp.data.callback.IQuizApiCallBack;
import com.shahrasul.quizapp.data.callback.QuestionsCallBack;
import com.shahrasul.quizapp.data.IQuizApiClient;
import com.shahrasul.quizapp.data.locally.IHistoryClient;
import com.shahrasul.quizapp.ui.models.Question;
import com.shahrasul.quizapp.ui.models.QuizResponse;
import com.shahrasul.quizapp.ui.models.QuizResult;

import java.util.Collections;
import java.util.List;

public class QuizRepository implements IQuizApiClient, IHistoryClient {
    private final IHistoryClient historyStorage;
    private final IQuizApiClient quizApiClient;

    public QuizRepository(IHistoryClient historyStorage, IQuizApiClient quizApiClient) {
        this.historyStorage = historyStorage;
        this.quizApiClient = quizApiClient;
    }

    @Override
    public void getQuestions(String amount, String category, String difficulty, IQuizApiCallBack.Questions callBack) {
        quizApiClient.getQuestions(
                amount.equals("null") ? "10" : amount,
                category.equals("null") ? null : category,
                difficulty.equals("any type") ? null : difficulty,
                new QuestionsCallBack(){
                    @Override
                    public void onSuccess(QuizResponse result) {
                        addCorrectAnsInIncorrect(result.getResults());
                        callBack.onSuccess(result);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        super.onFailure(e);
                        Log.e("fffffff", "onFailure: ", e);
                    }
                });
    }

    @Override
    public void getCategory(CategoryCallBack categoryCallBack) {
        quizApiClient.getCategory(categoryCallBack);

    }




    private void addCorrectAnsInIncorrect(List<Question> questions) {
        for (Question question : questions) {
            question.getIncorrectAnswers().add(question.getCorrectAnswer());
            Collections.shuffle(question.getIncorrectAnswers());
        }
    }

    @Override
    public void insertHistoryResult(QuizResult resultModel) {
        historyStorage.insertHistoryResult(resultModel);
    }

    @Override
    public LiveData<List<QuizResult>> getAllHistoryResult() {
        return historyStorage.getAllHistoryResult();
    }


    @Override
    public void clearAll() {
        historyStorage.clearAll();
    }

    @Override
    public void deleteToId(Long id) {
        historyStorage.deleteToId(id);
    }
}