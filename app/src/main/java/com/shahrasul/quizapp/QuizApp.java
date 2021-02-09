package com.shahrasul.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.shahrasul.quizapp.data.IQuizApiClient;
import com.shahrasul.quizapp.data.QuizApiClient;
import com.shahrasul.quizapp.data.locally.IHistoryClient;
import com.shahrasul.quizapp.data.network.QuizRepository;
import com.shahrasul.quizapp.ui.db.QuizDataBase;
import com.shahrasul.quizapp.data.locally.HistoryStorage;

public class QuizApp extends Application {
    public static QuizApiClient quizApiClient;
    private static QuizApp instance;
    private static QuizRepository quizRepository;
    public static QuizDataBase quizDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        quizApiClient = new QuizApiClient();
        IHistoryClient historyClient = (IHistoryClient) new HistoryStorage();
        quizRepository = new QuizRepository(historyClient,quizApiClient);
        quizDataBase = Room.databaseBuilder(
                this,QuizDataBase.class,
                "quiz.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
    public static QuizApp getInstance() {
        return instance;
    }

    public QuizRepository getQuizRepository() {
        return quizRepository;
    }

    public QuizDataBase getQuizDataBase() {
        return quizDataBase;
    }

}
