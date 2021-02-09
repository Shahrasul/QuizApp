package com.shahrasul.quizapp.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shahrasul.quizapp.R;
import com.shahrasul.quizapp.data.callback.OnPopupMenuClickListener;
import com.shahrasul.quizapp.databinding.HistoryItemBinding;
import com.shahrasul.quizapp.ui.models.QuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {
    private List<QuizResult> data = new ArrayList<>();
    private OnPopupMenuClickListener onPopupMenuClick;

    public void setOnPopupMenuClick(OnPopupMenuClickListener onPopupMenuClick) {
        this.onPopupMenuClick = onPopupMenuClick;
    }

    public void addData(List<QuizResult> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public HistoryAdapter.HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryHolder(HistoryItemBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class HistoryHolder extends RecyclerView.ViewHolder {
        private final HistoryItemBinding itemHistory;

        public HistoryHolder(@NonNull HistoryItemBinding binding) {
            super(binding.getRoot());
            itemHistory = binding;
            binding.popUpMenu.setOnClickListener(v -> onPopupMenuClick.onPopupMenuClick(v, getAdapterPosition()));
        }

        @SuppressLint("SetTextI18n")
        public void onBind(QuizResult model) {
            itemHistory.textViewCategory.setText(model.getCategory());
            itemHistory.textViewCorrectAns.setText(model.getCorrectAnswer() + "");
            itemHistory.textViewDifficulty.setText(model.getDifficulty());
            itemHistory.textViewData.setText(model.getStringDate());
        }
    }
}
