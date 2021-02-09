package com.shahrasul.quizapp.ui.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.shahrasul.quizapp.R;
import com.shahrasul.quizapp.databinding.ActivityQuizBinding;
import com.shahrasul.quizapp.ui.adapter.QuizAdapter;
import com.shahrasul.quizapp.ui.result.ResultActivity;

public class QuizActivity extends AppCompatActivity {

    private QuizViewModel mViewModel;
    private ActivityQuizBinding binding;
    private QuizAdapter adapter;
    private Integer categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);
        init();
        setArg();
        subscribeIsSkip();
        subscribeIsFinish();
        subscribeQuestions();
        setListeners();
        subscribePosition();
        subscribeResultForResultActivity();
        subscribeShowToast();

    }

    private void subscribeShowToast() {
        mViewModel.showToast.observe(this, data -> Toast.makeText(this, data, Toast.LENGTH_SHORT).show());
    }

    private void subscribeResultForResultActivity() {
        mViewModel.result.observeForever(quizResult -> {
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            intent.putExtra(ResultActivity.RESULT_QUIZ_KEY, quizResult);
            startActivity(intent);
            finish();
        });
    }

    private void subscribePosition() {
        mViewModel.position.observe(this, pos -> {
            binding.recyclerview.scrollToPosition(pos);
            binding.progressBarQuestionActivity.setProgress(pos);
        });

    }

    private void setListeners() {
        adapter.setListener(mViewModel);
    }

    private void setArg() {
        binding.recyclerview.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        binding.recyclerview.setLayoutManager(linearLayoutManager);
    }

    private void subscribeIsSkip() {
    }

    private void subscribeQuestions() {
        mViewModel.questions.observe(this, questions -> adapter.setQuiz(questions));
    }

    private void init() {
        String categoryId = getIntent().getStringExtra("categoryId");
        String difficulty = getIntent().getStringExtra("difficulty");
        String amount = getIntent().getStringExtra("amount");
        String categoryName = getIntent().getStringExtra("categoryName");

        int amountInt = amount.equals("null") ? 10 : Integer.parseInt(amount);
        binding.progressBarQuestionActivity.setMax(amountInt);

        mViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        mViewModel.getQuestions(amount, categoryId, difficulty);
        adapter = new QuizAdapter();
        mViewModel.setCategory();
        binding.setViewModel(mViewModel);
        binding.categoryTitle.setText(categoryName);
        LinearSnapHelper snapHelper = new LinearSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                View centerView = findSnapView(layoutManager);
                if (centerView == null)
                    return RecyclerView.NO_POSITION;

                int position = layoutManager.getPosition(centerView);
                int targetPosition = -1;
                if (layoutManager.canScrollHorizontally()) {
                    if (velocityX < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                if (layoutManager.canScrollVertically()) {
                    if (velocityY < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                final int firstItem = 0;
                final int lastItem = layoutManager.getItemCount() - 1;
                targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem));
                return targetPosition;
            }
        };
        snapHelper.attachToRecyclerView(binding.recyclerview);
    }


        private void subscribeIsFinish () {
            mViewModel.isFinish.observe(this, isFinish -> {
                if (isFinish) finish();
            });
        }

}