package com.example.all_in_onecontroller.features.quran.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.all_in_onecontroller.R;
import com.example.all_in_onecontroller.features.quran.model.Dars;

import java.util.ArrayList;
import java.util.List;

public class DarsAdapter extends RecyclerView.Adapter<DarsAdapter.DarsViewHolder> {

    public interface OnDarsClickListener {
        void onDarsClick(Dars dars);
    }

    private final List<Dars> darsList = new ArrayList<>();
    private final OnDarsClickListener listener;

    public DarsAdapter(OnDarsClickListener listener) {
        this.listener = listener;
    }

    public void setDarsList(List<Dars> list) {
        darsList.clear();
        if (list != null) {
            darsList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dars, parent, false);
        return new DarsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DarsViewHolder holder, int position) {
        Dars dars = darsList.get(position);
        holder.tvDarsTitle.setText(dars.getTitle());

        String range = "Verses " + dars.getVerseRange() + " • Verified Internet Archive Stream";
        holder.tvDarsRange.setText(range);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDarsClick(dars);
            }
        });
    }

    @Override
    public int getItemCount() {
        return darsList.size();
    }

    static class DarsViewHolder extends RecyclerView.ViewHolder {
        TextView tvDarsTitle;
        TextView tvDarsRange;

        DarsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDarsTitle = itemView.findViewById(R.id.tvDarsTitle);
            tvDarsRange = itemView.findViewById(R.id.tvDarsRange);
        }
    }
}