package com.example.all_in_onecontroller.features.quran.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.all_in_onecontroller.R;
import com.example.all_in_onecontroller.features.quran.model.QuranVerse;

import java.util.ArrayList;
import java.util.List;

public class VerseAdapter extends RecyclerView.Adapter<VerseAdapter.VerseViewHolder> {

    private final List<QuranVerse> verseList = new ArrayList<>();
    private boolean isUthmaniScript = true;

    public void setVerses(List<QuranVerse> list, boolean uthmaniScript) {
        this.isUthmaniScript = uthmaniScript;
        verseList.clear();
        if (list != null) {
            verseList.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void setUthmaniScript(boolean uthmaniScript) {
        this.isUthmaniScript = uthmaniScript;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VerseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_verse, parent, false);
        return new VerseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerseViewHolder holder, int position) {
        QuranVerse verse = verseList.get(position);
        holder.tvVerseNumber.setText(String.valueOf(verse.getVerseNumber()));

        if (isUthmaniScript) {
            holder.tvVerseArabic.setText(verse.getUthmaniText());
            holder.tvVerseScriptBadge.setText("Uthmani Script");
        } else {
            holder.tvVerseArabic.setText(verse.getSimpleText());
            holder.tvVerseScriptBadge.setText("Quran Simple");
        }
    }

    @Override
    public int getItemCount() {
        return verseList.size();
    }

    static class VerseViewHolder extends RecyclerView.ViewHolder {
        TextView tvVerseNumber;
        TextView tvVerseScriptBadge;
        TextView tvVerseArabic;

        VerseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVerseNumber = itemView.findViewById(R.id.tvVerseNumber);
            tvVerseScriptBadge = itemView.findViewById(R.id.tvVerseScriptBadge);
            tvVerseArabic = itemView.findViewById(R.id.tvVerseArabic);
        }
    }
}