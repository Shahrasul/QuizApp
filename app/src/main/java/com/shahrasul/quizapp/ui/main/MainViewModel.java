package com.shahrasul.quizapp.ui.main;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shahrasul.quizapp.QuizApp;
import com.shahrasul.quizapp.data.IQuizApiClient;
import com.shahrasul.quizapp.data.callback.IQuizApiCallBack;
import com.shahrasul.quizapp.ui.models.TriviaCategories;
import com.shahrasul.quizapp.ui.models.TriviaCategory;

public class MainViewModel extends ViewModel implements IQuizApiCallBack.Categories, IQuizApiClient.CategoryCallBack {
    MutableLiveData<TriviaCategories> categories = new MutableLiveData<>();
    MutableLiveData<Integer> progressBarSuccess = new MutableLiveData<>();
    public ObservableField<String> result = new ObservableField<>("0");


    public MainViewModel() {
        updateCategory();
        QuizApp.quizApiClient.getCategory(new IQuizApiClient.CategoryCallBack() {
            @Override
            public void onSuccess(TriviaCategories result) {
                result.getTriviaCategories().add(0, new TriviaCategory(99, "Any Type"));
                categories.setValue(result);
            }

            @Override
            public void onFailure(Exception e) { }
        });
    }
    private void updateCategory() {
        QuizApp.getInstance().getQuizRepository().getCategory(this);
    }

    @Override
    public void onSuccess(TriviaCategories categories) {

    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onFailure(Throwable throwable) {

    }
}
