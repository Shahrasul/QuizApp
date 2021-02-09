package com.shahrasul.quizapp.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shahrasul.quizapp.QuizApp;
import com.shahrasul.quizapp.ui.models.QuizResult;

import java.util.List;

public class HistoryViewModel extends ViewModel {
    public LiveData<List<QuizResult>> listHistoryMutableLiveData = new MutableLiveData<>();

    public HistoryViewModel() {
        main();
    }

    private void main() {
        listHistoryMutableLiveData = QuizApp.getInstance().getQuizRepository().getAllHistoryResult();
    }

    public void popupMenuDelete(int position) {
        QuizApp.getInstance().getQuizRepository().deleteToId((long)listHistoryMutableLiveData.getValue().get(position).getId());
    }
}
