package com.shahrasul.quizapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.shahrasul.quizapp.R;
import com.shahrasul.quizapp.data.callback.ItemSelectedListener;
import com.shahrasul.quizapp.data.callback.SeekBarChangeListener;
import com.shahrasul.quizapp.databinding.MainFragmentBinding;
import com.shahrasul.quizapp.ui.models.TriviaCategory;
import com.shahrasul.quizapp.ui.quiz.QuizActivity;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private MainFragmentBinding binding;
    private Integer categoryId = null;
    private String categoryName;
    private String difficulty;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.inflate(inflater);
        setListener();
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setViewModel(mViewModel);
        binding.btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), QuizActivity.class);
            intent.putExtra("amount", String.valueOf(mViewModel.progressBarSuccess.getValue()));
            intent.putExtra("difficulty", difficulty);
            intent.putExtra("categoryId", String.valueOf(categoryId));
            intent.putExtra("categoryName", categoryName);
            startActivity(intent);
        });
        subscribeCategory();
        subscribeQuestionsAmount();
    }
    private void subscribeQuestionsAmount() {
        mViewModel.progressBarSuccess.observe(requireActivity(), amount -> binding.textQuestAmountValue.setText(String.valueOf(amount)));
    }


    private void setListener() {
        binding.seekBarMain.setOnSeekBarChangeListener(new SeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mViewModel.progressBarSuccess.setValue(i);            }
        });
        binding.spinnerDifficulty.setOnItemSelectedListener(new ItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                difficulty = getResources().getStringArray(R.array.spinner_difficulty_example)[position];
            }
        });
    }


    private void subscribeCategory() {
        mViewModel.categories.observe(requireActivity(), triviaCategories -> {
            List<String> name_category = new ArrayList<>();
            for (TriviaCategory triviaCategory : triviaCategories.getTriviaCategories())
                name_category.add(triviaCategory.getName());

            triviaCategories.getTriviaCategories().add(0, new TriviaCategory(null, "Any Type"));

            ArrayAdapter<String> adapter = new ArrayAdapter<>(binding.getRoot().getContext(), R.layout.support_simple_spinner_dropdown_item, name_category);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerCategory.setAdapter(adapter);
            binding.spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    categoryId = triviaCategories.getTriviaCategories().get(position).getId();
                    categoryName = triviaCategories.getTriviaCategories().get(position).getName();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        });
    }
}