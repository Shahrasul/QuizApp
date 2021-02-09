package com.shahrasul.quizapp.ui.interfaces;

import com.shahrasul.quizapp.ui.models.QuizResult;

public interface IHistoryStorage {
    QuizResult getQuizResult(int id);
    int saveQuizResult(QuizResult quizResult);
    void delete(int id);
    void deleteAll();
}
