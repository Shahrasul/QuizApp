package com.shahrasul.quizapp.ui.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.shahrasul.quizapp.ui.models.QuizResult;
@Database(entities = {QuizResult.class}, version = 1)
public abstract class QuizDataBase extends RoomDatabase {
    public abstract QuizDao quizDao();
}
