package com.shahrasul.quizapp.ui.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.shahrasul.quizapp.ui.models.QuizResult;

import java.util.List;

@Dao
public interface QuizDao {
    @Query("SELECT * FROM qresult")
    LiveData<List<QuizResult>> getAll();

    @Query("SELECT * FROM qresult WHERE id = :id")
    QuizResult getById(long id);

    @Insert
    void insert(QuizResult historyResultModel);

    @Query("DELETE FROM qresult")
    void deleteAll();

    @Query("DELETE FROM qresult WHERE id = :id")
    void deleteToId(long id);
}

