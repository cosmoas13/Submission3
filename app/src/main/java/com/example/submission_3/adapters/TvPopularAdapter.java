package com.example.submission_3.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submission_3.R;
import com.example.submission_3.models.TvPopularData;

import java.util.ArrayList;

public class TvPopularAdapter extends RecyclerView.Adapter<TvPopularAdapter.CardViewViewHolder> {
    private final ArrayList<TvPopularData> tvPopularData = new ArrayList<>();

    public void setTvPopularData(ArrayList<TvPopularData> itemData) {
        tvPopularData.clear();
        tvPopularData.addAll(itemData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tv_popular, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder cardViewViewHolder, int i) {
        cardViewViewHolder.bind(tvPopularData.get(i));
    }

    @Override
    public int getItemCount() {
        return tvPopularData.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        final ImageView posterTvPop;
        final TextView nameTvPop;
        CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            posterTvPop = itemView.findViewById(R.id.poster_tv_pop);
            nameTvPop = itemView.findViewById(R.id.tv_name_pop);
        }

        void bind(TvPopularData tvPopularData) {
            nameTvPop.setText(tvPopularData.getName());
            Glide.with(itemView).load(tvPopularData.getPosterPath())
                    .into(posterTvPop);
        }
    }
}
