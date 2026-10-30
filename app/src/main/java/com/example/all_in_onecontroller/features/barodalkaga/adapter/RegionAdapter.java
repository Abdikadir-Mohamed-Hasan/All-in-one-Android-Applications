package com.example.all_in_onecontroller.features.barodalkaga.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.all_in_onecontroller.R;
import com.example.all_in_onecontroller.features.barodalkaga.model.Region;

import java.util.ArrayList;
import java.util.List;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.RegionViewHolder> {

    public interface OnRegionClickListener {
        void onRegionClick(Region region);
    }

    private final Context context;
    private final OnRegionClickListener listener;
    private final List<Region> regionsList = new ArrayList<>();

    public RegionAdapter(Context context, OnRegionClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setRegions(List<Region> newRegions) {
        regionsList.clear();
        if (newRegions != null) {
            regionsList.addAll(newRegions);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RegionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_region, parent, false);
        return new RegionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionViewHolder holder, int position) {
        Region region = regionsList.get(position);

        holder.tvRegionIndex.setText(String.valueOf(position + 1));
        holder.tvRegionName.setText(region.getName());
        holder.tvRegionCapital.setText("Capital: " + region.getCapitalCity());
        holder.tvRegionZone.setText(region.getZone());
        holder.tvRegionYear.setText(String.valueOf(region.getEstablishedYear()));

        int resId = region.getImageResId(context);
        if (resId != 0) {
            holder.ivRegionThumb.setImageResource(resId);
        } else {
            holder.ivRegionThumb.setImageResource(R.drawable.bg_region_hero_default);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onRegionClick(region);
            }
        });
    }

    @Override
    public int getItemCount() {
        return regionsList.size();
    }

    static class RegionViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRegionThumb;
        TextView tvRegionIndex;
        TextView tvRegionName;
        TextView tvRegionCapital;
        TextView tvRegionZone;
        TextView tvRegionYear;

        public RegionViewHolder(@NonNull View itemView) {
            super(itemView);
            ivRegionThumb = itemView.findViewById(R.id.ivRegionThumb);
            tvRegionIndex = itemView.findViewById(R.id.tvRegionIndex);
            tvRegionName = itemView.findViewById(R.id.tvRegionName);
            tvRegionCapital = itemView.findViewById(R.id.tvRegionCapital);
            tvRegionZone = itemView.findViewById(R.id.tvRegionZone);
            tvRegionYear = itemView.findViewById(R.id.tvRegionYear);
        }
    }
}
