package com.shahrasul.quizapp.ui.quiz;

import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shahrasul.quizapp.QuizApp;
import com.shahrasul.quizapp.data.callback.IQuizApiCallBack;
import com.shahrasul.quizapp.ui.adapter.QuizAdapter;
import com.shahrasul.quizapp.ui.models.Question;
import com.shahrasul.quizapp.ui.models.QuizResponse;
import com.shahrasul.quizapp.ui.models.QuizResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class QuizViewModel extends ViewModel implements QuizAdapter.OnAnswerChangeListener {

    public MutableLiveData<Boolean> isFinish = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> isSkip = new MutableLiveData<>(false);
    public MutableLiveData<List<Question>> questions = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<Integer> position = new MutableLiveData<>(0);
    public MutableLiveData<String> showToast = new MutableLiveData<>();
    MutableLiveData<QuizResult> result = new MutableLiveData<>();
    public String difficulty;
    public String categoryName;
    private int correctAnswerAmount = 0;

    public void getQuestions(String amount, String category, String difficulty) {
        this.difficulty = difficulty;
        QuizApp.getInstance().getQuizRepository().getQuestions(amount, category, difficulty, new IQuizApiCallBack.Questions() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(QuizResponse quizResponse) {
                questions.setValue(quizResponse.getResults());
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void onBackClicked() {
        position.setValue(position.getValue() - 1);
    }

    public void onSkipClicked() {
        position.setValue(position.getValue() + 1);
    }

    public void setCategory() {
        for (int i = 0; i <= 10; i++)
            Objects.requireNonNull(questions.getValue()).add(Question.newInstance());
    }

    @Override
    public void onAnswerChange(boolean isCorrectAns) {
        new CountDownTimer(500, 500){

            @Override
            public void onTick(long l) { }

            @Override
            public void onFinish() {
                position.setValue(position.getValue() + 1);
                if (isCorrectAns) correctAnswerAmount ++;

                if (position.getValue() >= questions.getValue().size()){

                    int questionAmount = Objects.requireNonNull(questions.getValue()).size();
                    double prc =  (((double)correctAnswerAmount) / ((double)questionAmount)) * 100;
                    String dateStr = "04/05/2010";

                    SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
                    Date dateObj = null;
                    try {
                        dateObj = curFormater.parse(dateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");
                    String newDateStr = postFormater.format(dateObj);

                    result.setValue(
                            new QuizResult(
                                    categoryName,
                                    difficulty,
                                    correctAnswerAmount,
                                    newDateStr,
                                    (int) prc
                            )
                    );
                }
            }
        }.start();
    }
    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }
}
