package com.shahrasul.quizapp.data.locally;

import androidx.lifecycle.LiveData;

import com.shahrasul.quizapp.ui.models.QuizResult;

import java.util.List;

public interface IHistoryClient {
    void insertHistoryResult(QuizResult resultModel);
    LiveData<List<QuizResult>> getAllHistoryResult();
    void clearAll();
    void deleteToId(Long id);
}