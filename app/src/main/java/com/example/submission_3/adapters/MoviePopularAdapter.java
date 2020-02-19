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
import com.example.submission_3.models.MoviePopularData;

import java.util.ArrayList;

public class MoviePopularAdapter extends RecyclerView.Adapter<MoviePopularAdapter.CardViewViewHolder> {
    private final ArrayList<MoviePopularData> mData = new ArrayList<>();

    public void setMovieData(ArrayList<MoviePopularData> itemData) {
        mData.clear();
        mData.addAll(itemData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviePopularAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePopularAdapter.CardViewViewHolder cardViewViewHolder, int i) {
        cardViewViewHolder.bind(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgMovie;
        final TextView tvName;
        final TextView tvRelease;
        final TextView tvDescription;
        CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvRelease = itemView.findViewById(R.id.tv_item_release);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }

        void bind(MoviePopularData moviePopularData) {
            tvName.setText(moviePopularData.getTitle());
            tvRelease.setText(moviePopularData.getReleaseDate());
            tvDescription.setText(moviePopularData.getOverview());
            Glide.with(itemView).load(moviePopularData.getPosterPath())
                    .into(imgMovie);
        }
    }
}
