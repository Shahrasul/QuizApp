package com.shahrasul.quizapp.ui.result;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shahrasul.quizapp.QuizApp;
import com.shahrasul.quizapp.ui.models.QuizResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ResultViewModel extends ViewModel {
    MutableLiveData<Boolean> isFinished = new MutableLiveData<>(false);

    public void onFinishClicked(){
        isFinished.setValue(true);
    }

    public void initResult(QuizResult quizResult) {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        quizResult.setStringDate(formattedDate);
        QuizApp.getInstance().getQuizRepository().insertHistoryResult(quizResult);
    }
}
