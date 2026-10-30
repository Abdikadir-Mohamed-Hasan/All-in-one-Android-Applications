package com.example.all_in_onecontroller.features.quran.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.all_in_onecontroller.R;
import com.example.all_in_onecontroller.features.quran.model.Surah;

import java.util.ArrayList;
import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.SurahViewHolder> {

    public interface OnSurahClickListener {
        void onSurahClick(Surah surah);
    }

    private final List<Surah> surahList = new ArrayList<>();
    private final OnSurahClickListener listener;
    private boolean isRecitationMode = false;

    public SurahAdapter(OnSurahClickListener listener) {
        this.listener = listener;
    }

    public void setRecitationMode(boolean isRecitation) {
        this.isRecitationMode = isRecitation;
        notifyDataSetChanged();
    }

    public void setSurahs(List<Surah> list) {
        surahList.clear();
        if (list != null) {
            surahList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SurahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_surah, parent, false);
        return new SurahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SurahViewHolder holder, int position) {
        Surah surah = surahList.get(position);
        holder.tvSurahNumber.setText(String.valueOf(surah.getNumber()));
        holder.tvSurahTransliteration.setText(surah.getTransliteration());
        holder.tvSurahArabic.setText(surah.getArabicName());

        String details = surah.getVersesCount() + " Verses • " + surah.getRevelationType();
        holder.tvSurahDetails.setText(details);

        // "Multiple Dars" badge only applies to Tafsiir lessons, NEVER to pure Quran Recitation (Tilawah)
        if (!isRecitationMode && surah.isHasMultipleDars()) {
            holder.tvDarsBadge.setVisibility(View.VISIBLE);
        } else {
            holder.tvDarsBadge.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSurahClick(surah);
            }
        });
    }

    @Override
    public int getItemCount() {
        return surahList.size();
    }

    static class SurahViewHolder extends RecyclerView.ViewHolder {
        TextView tvSurahNumber;
        TextView tvSurahTransliteration;
        TextView tvSurahDetails;
        TextView tvSurahArabic;
        TextView tvDarsBadge;

        SurahViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSurahNumber = itemView.findViewById(R.id.tvSurahNumber);
            tvSurahTransliteration = itemView.findViewById(R.id.tvSurahTransliteration);
            tvSurahDetails = itemView.findViewById(R.id.tvSurahDetails);
            tvSurahArabic = itemView.findViewById(R.id.tvSurahArabic);
            tvDarsBadge = itemView.findViewById(R.id.tvDarsBadge);
        }
    }
}
