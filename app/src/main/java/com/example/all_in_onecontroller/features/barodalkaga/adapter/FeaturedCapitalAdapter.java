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

public class FeaturedCapitalAdapter extends RecyclerView.Adapter<FeaturedCapitalAdapter.ViewHolder> {

    public interface OnCapitalClickListener {
        void onCapitalClick(Region region);
    }

    private final Context context;
    private final OnCapitalClickListener listener;
    private final List<Region> regions = new ArrayList<>();

    public FeaturedCapitalAdapter(Context context, OnCapitalClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setRegions(List<Region> newRegions) {
        this.regions.clear();
        if (newRegions != null) {
            this.regions.addAll(newRegions);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_featured_capital, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Region region = regions.get(position);

        holder.tvFeaturedCapital.setText(region.getCapitalCity());
        holder.tvFeaturedRegion.setText(region.getName());
        holder.tvFeaturedZone.setText(region.getZone());
        holder.tvFeaturedYear.setText("Est. " + region.getEstablishedYear() + " • Explore");

        int heroRes = region.getImageResId(context);
        if (heroRes != 0) {
            holder.ivFeaturedImage.setImageResource(heroRes);
        } else {
            holder.ivFeaturedImage.setImageResource(R.drawable.bg_region_hero_default);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCapitalClick(region);
            }
        });
    }

    @Override
    public int getItemCount() {
        return regions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFeaturedImage;
        TextView tvFeaturedZone;
        TextView tvFeaturedCapital;
        TextView tvFeaturedRegion;
        TextView tvFeaturedYear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFeaturedImage = itemView.findViewById(R.id.ivFeaturedImage);
            tvFeaturedZone = itemView.findViewById(R.id.tvFeaturedZone);
            tvFeaturedCapital = itemView.findViewById(R.id.tvFeaturedCapital);
            tvFeaturedRegion = itemView.findViewById(R.id.tvFeaturedRegion);
            tvFeaturedYear = itemView.findViewById(R.id.tvFeaturedYear);
        }
    }
}

