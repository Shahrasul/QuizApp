package com.shahrasul.quizapp.data.locally;

import androidx.lifecycle.LiveData;

import com.shahrasul.quizapp.QuizApp;
import com.shahrasul.quizapp.ui.models.QuizResult;

import java.util.List;

public class HistoryStorage implements IHistoryClient {
    @Override
    public void insertHistoryResult(QuizResult resultModel) {
        QuizApp.getInstance().getQuizDataBase().quizDao().insert(resultModel);
    }

    @Override
    public LiveData<List<QuizResult>> getAllHistoryResult() {
        return QuizApp.getInstance().getQuizDataBase().quizDao().getAll();
    }

    @Override
    public void clearAll() {
        QuizApp.getInstance().getQuizDataBase().quizDao().deleteAll();
    }

    @Override
    public void deleteToId(Long id) {
        QuizApp.getInstance().getQuizDataBase().quizDao().deleteToId(id);
    }


}

