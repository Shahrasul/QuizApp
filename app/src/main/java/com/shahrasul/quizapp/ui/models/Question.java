package com.shahrasul.quizapp.ui.models;

import android.text.Html;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("difficulty")
    @Expose
    private String difficulty;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("correct_answer")
    @Expose
    private String correctAnswer;
    @SerializedName("incorrect_answers")
    @Expose
    private List<String> incorrectAnswers = null;

    public String getCategory() {
        return category;
    }

     public void setCategory(String category) {
        this.category = category;
    }

     public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<String> getIncorrectAnswers() {
        for (int i = 0; i < incorrectAnswers.size()-1; i++) {
            incorrectAnswers.set(i, Html.fromHtml(Html.fromHtml(incorrectAnswers.get(i)).toString()).toString());
        }
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public static Question newInstance() {
        ArrayList<String> incorrectAnswers = new ArrayList<>();
        incorrectAnswers.add("a");
        incorrectAnswers.add("b");
        incorrectAnswers.add("d");
        Question question = new Question();
        question.setCategory("Animal");
        question.setDifficulty("hard");
        question.setIncorrectAnswers(incorrectAnswers);
        question.setType("multiple");
        question.setCorrectAnswer("c");
        question.setQuestion("why?");
        question.getIncorrectAnswers().add(question.getCorrectAnswer());
        Collections.shuffle(question.getIncorrectAnswers());
        return question;
    }

}