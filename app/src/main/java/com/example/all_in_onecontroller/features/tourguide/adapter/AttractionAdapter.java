package com.example.all_in_onecontroller.features.tourguide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.all_in_onecontroller.R;
import com.example.all_in_onecontroller.features.tourguide.model.Attraction;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for displaying tourist attractions in a clean Material CardView list.
 */
public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder> {

    public interface OnAttractionClickListener {
        void onAttractionClick(Attraction attraction);
    }

    private final Context context;
    private final List<Attraction> attractionsList = new ArrayList<>();
    private final OnAttractionClickListener listener;

    public AttractionAdapter(Context context, List<Attraction> list, OnAttractionClickListener listener) {
        this.context = context;
        if (list != null) {
            this.attractionsList.addAll(list);
        }
        this.listener = listener;
    }

    public void updateList(List<Attraction> newList) {
        attractionsList.clear();
        if (newList != null) {
            attractionsList.addAll(newList);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AttractionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_attraction, parent, false);
        return new AttractionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttractionViewHolder holder, int position) {
        Attraction attraction = attractionsList.get(position);

        holder.tvAttractionName.setText(attraction.getName());
        holder.tvAttractionLocation.setText(attraction.getLocation());
        holder.tvAttractionBadge.setText(attraction.getCategory());

        int imgRes = attraction.getImageResource();
        if (imgRes != 0) {
            holder.ivAttractionThumb.setImageResource(imgRes);
        } else {
            holder.ivAttractionThumb.setImageResource(R.drawable.liido_beach);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAttractionClick(attraction);
            }
        });
    }

    @Override
    public int getItemCount() {
        return attractionsList.size();
    }

    public static class AttractionViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAttractionThumb;
        TextView tvAttractionBadge;
        TextView tvAttractionName;
        TextView tvAttractionLocation;

        public AttractionViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAttractionThumb = itemView.findViewById(R.id.ivAttractionThumb);
            tvAttractionBadge = itemView.findViewById(R.id.tvAttractionBadge);
            tvAttractionName = itemView.findViewById(R.id.tvAttractionName);
            tvAttractionLocation = itemView.findViewById(R.id.tvAttractionLocation);
        }
    }
}

