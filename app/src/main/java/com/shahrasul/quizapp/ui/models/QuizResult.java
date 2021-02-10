package com.shahrasul.quizapp.ui.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "QResult")
public class QuizResult implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "difficulty")
    private String difficulty;

    @ColumnInfo(name = "correct_answer")
    private int correctAnswer;

    @SerializedName("created_at")
    private String createdAt;

    //@TypeConverters(QuestionConverter.class)
    //private List<Question> questions;
    @ColumnInfo(name = "result_percentage")
    private String resultPercentage;

    @ColumnInfo(name = "amount_correct_ans")
    private int amountCorrectAns;

    @ColumnInfo(name = "string_date")
    private String stringDate;
    private int procent = 0;

    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getProcent() {
        return procent;
    }

    public void setProcent(int procent) {
        this.procent = procent;
    }

    //public List<Question> getQuestions() {
    //    return questions;
    //}

    //public void setQuestions(List<Question> questions) {
    //    this.questions = questions;
    //}

    public QuizResult(String category, String difficulty, int correctAnswer, String createdAt, int procent) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswer = correctAnswer;
        this.createdAt = createdAt;
        this.procent = procent;
    }

    public void setResultPercentage(String resultPercentage) {
        this.resultPercentage = resultPercentage;
    }

    public String getResultPercentage() {
        return resultPercentage;
    }

    public int getAmountCorrectAns() {
        return amountCorrectAns;
    }

    public void setAmountCorrectAns(int amountCorrectAns) {
        amountCorrectAns = amountCorrectAns;
    }
}
