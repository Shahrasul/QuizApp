package com.shahrasul.quizapp.ui.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.shahrasul.quizapp.R;
import com.shahrasul.quizapp.databinding.ActivityResultBinding;
import com.shahrasul.quizapp.ui.models.QuizResult;

public class ResultActivity extends AppCompatActivity {

    public static final String RESULT_QUIZ_KEY = "RESULT_QUIZ_KEY";
    private ResultViewModel resultViewModel;
    private ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        init();
        setArg();
        subscribeIsFinished();
    }

    private void subscribeIsFinished() {
        resultViewModel.isFinished.observe(this, this::onFinish);
    }

    private void setArg() {
        binding.setViewModel(resultViewModel);
        if (getIntent() != null) {
            QuizResult quizResult = (QuizResult) getIntent().getSerializableExtra(RESULT_QUIZ_KEY);
            binding.setModel(quizResult);
            resultViewModel.initResult(quizResult);
        }
    }

    private void init() {
        resultViewModel = new ViewModelProvider(this).get(ResultViewModel.class);
    }

    private void onFinish(Boolean isFinish) {
        if (isFinish) finish();
    }

}