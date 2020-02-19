package com.example.submission_3.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.submission_3.ItemClickSupport;
import com.example.submission_3.R;
import com.example.submission_3.adapters.TvAirAdapter;
import com.example.submission_3.adapters.TvPopularAdapter;
import com.example.submission_3.details.DetailTvAirActivity;
import com.example.submission_3.details.DetailTvPopularActivity;
import com.example.submission_3.models.TvAiringData;
import com.example.submission_3.models.TvPopularData;
import com.example.submission_3.viewmodel.TvAiringViewModel;
import com.example.submission_3.viewmodel.TvPopularViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private TvAirAdapter tvAirAdapter;
    private TvPopularAdapter tvPopularAdapter;
    private RecyclerView rvTvPopular;
    private RecyclerView rvTvAir;
    private ProgressBar progressBar;
    private ProgressBar progressBarTvPop;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TvAiringViewModel tvAiringViewModel;
        TvPopularViewModel tvPopularViewModel;

        rvTvAir = view.findViewById(R.id.rv_tv_airing);
        progressBar = view.findViewById(R.id.progressbar_tvair);
        progressBar.setVisibility(View.VISIBLE);

        rvTvPopular = view.findViewById(R.id.rv_tv_popular);
        progressBarTvPop = view.findViewById(R.id.progressbar_populartv);
        progressBarTvPop.setVisibility(View.VISIBLE);

        showRecyclerTvAiring(view);
        showRecyclerTvPopular(view);

        tvAiringViewModel = ViewModelProviders.of(this).get(TvAiringViewModel.class);
        tvAiringViewModel.setTvAir();
        tvAiringViewModel.getTvAiring().observe(this, getTvAring);

        tvPopularViewModel = ViewModelProviders.of(this).get(TvPopularViewModel.class);
        tvPopularViewModel.setTvPopular();
        tvPopularViewModel.getTvPopular().observe(this, getTvPopular);
    }

    private final Observer<ArrayList<TvAiringData>> getTvAring = new Observer<ArrayList<TvAiringData>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvAiringData> tvAiringData) {
            if (tvAiringData != null) {
                tvAirAdapter.setTvAirData(tvAiringData);
                progressBar.setVisibility(View.GONE);
                ItemClickSupport.addTo(rvTvAir).setOnItemClickListener((rvTvAir, position, v) ->
                        showSelectedTvAir(tvAiringData.get(position)));
            }
        }
    };

    private final Observer<ArrayList<TvPopularData>> getTvPopular = new Observer<ArrayList<TvPopularData>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvPopularData> tvPopularData) {
            if (tvPopularData != null) {
                tvPopularAdapter.setTvPopularData(tvPopularData);
                progressBarTvPop.setVisibility(View.GONE);
                ItemClickSupport.addTo(rvTvPopular).setOnItemClickListener((rvTvPopular, position, v) ->
                        showSelectedTvPop(tvPopularData.get(position)));
            }
        }
    };

    private void showSelectedTvAir(TvAiringData itemTvAir) {
        Intent intent = new Intent(getActivity(), DetailTvAirActivity.class);
        intent.putExtra(DetailTvAirActivity.EXTRA_TV, itemTvAir);
        startActivity(intent);
    }

    private void showSelectedTvPop(TvPopularData itemTvPop) {
        Intent mpInten = new Intent(getActivity(), DetailTvPopularActivity.class);
        mpInten.putExtra(DetailTvPopularActivity.EXTRA_TV_POP, itemTvPop);
        startActivity(mpInten);
    }

    private void showRecyclerTvAiring(View view) {
        tvAirAdapter = new TvAirAdapter();
        tvAirAdapter.notifyDataSetChanged();
        rvTvAir.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, view.isInLayout()));
        rvTvAir.setAdapter(tvAirAdapter);
    }

    private void showRecyclerTvPopular(View view) {
        tvPopularAdapter = new TvPopularAdapter();
        tvPopularAdapter.notifyDataSetChanged();
        rvTvPopular.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, view.isInLayout()));
        rvTvPopular.setAdapter(tvPopularAdapter);
    }
}
