package com.shahrasul.quizapp.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shahrasul.quizapp.R;
import com.shahrasul.quizapp.data.callback.OnButtonAnswerClick;
import com.shahrasul.quizapp.databinding.QuizItemBinding;
import com.shahrasul.quizapp.ui.models.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizHolder> {

    private List<Question> data = new ArrayList<>();
    private OnAnswerChangeListener listener;

    public void setListener(OnAnswerChangeListener listener) {
        this.listener = listener;
    }

    public void setQuiz(List<Question> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuizAdapter.QuizHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuizHolder(QuizItemBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull QuizAdapter.QuizHolder holder, int position) {
        holder.binding.button1.setBackgroundResource(R.drawable.btn_custom_bg);
        holder.binding.button2.setBackgroundResource(R.drawable.btn_custom_bg);
        holder.binding.button3.setBackgroundResource(R.drawable.btn_custom_bg);
        holder.binding.button4.setBackgroundResource(R.drawable.btn_custom_bg);
        holder.binding.type2Button.setBackgroundResource(R.drawable.btn_custom_bg);
        holder.binding.type2Button1.setBackgroundResource(R.drawable.btn_custom_bg);

        holder.binding.setModel(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class QuizHolder extends RecyclerView.ViewHolder implements OnButtonAnswerClick {
        public QuizItemBinding binding;
        public static final int CORRECT_ANSWER = 1;
        public static final int CORRECT_ANSWER_AND_FINAL_ANSWER = 11;
        public static final int WRONG_ANSWER = 2;
        public static final int WRONG_ANSWER_AND_FINAL_ANSWER = 22;

        public QuizHolder(@NonNull QuizItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setHandlers(this);
        }

        @Override
        public void onClick(View view, int positionAnswer) {
            boolean isCorrectAnswer = false;
            if (binding.getModel().getCorrectAnswer().equals(binding.getModel().getIncorrectAnswers().get(positionAnswer)))
                isCorrectAnswer = true;

            if (isCorrectAnswer) setBackgroundResources(positionAnswer, R.drawable.item_button_2);
            else setBackgroundResources(positionAnswer, R.drawable.btn_red);

            Log.e("listenerIsNull", "onClick: " + listener);

            if (listener != null)
                listener.onAnswerChange(isCorrectAnswer);

        }

        private void setBackgroundResources(int posBtn, int resId) {
            switch (posBtn) {
                case 0:
                    binding.button1.setBackgroundResource(resId);
                    binding.type2Button.setBackgroundResource(resId);
                    break;
                case 1:
                    binding.button2.setBackgroundResource(resId);
                    binding.type2Button1.setBackgroundResource(resId);
                    break;
                case 2:
                    binding.button3.setBackgroundResource(resId);
                    break;
                case 3:
                    binding.button4.setBackgroundResource(resId);
                    break;
            }
        }

    }

    public interface OnAnswerChangeListener {
        void onAnswerChange(boolean isCorrectAns);
    }

}