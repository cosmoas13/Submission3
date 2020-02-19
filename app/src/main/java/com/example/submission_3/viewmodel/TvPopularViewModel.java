package com.example.submission_3.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.submission_3.models.TvPopularData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvPopularViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<TvPopularData>> listTvPopular = new MutableLiveData<>();

    public void setTvPopular() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvPopularData> listTvPopularItem = new ArrayList<>();
        String API_KEY = "c27f2e9e65724a582c04b6e0b434c348";
        String url = "https://api.themoviedb.org/3/tv/popular?api_key=" + API_KEY + "&language=en-US&page=1";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray listTv = responseObject.getJSONArray("results");

                    for (int i = 0; i < listTv.length(); i++) {
                        JSONObject movie = listTv.getJSONObject(i);
                        TvPopularData tvPopularData = new TvPopularData(movie);
                        listTvPopularItem.add(tvPopularData);
                    }
                    listTvPopular.postValue(listTvPopularItem);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Failure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<TvPopularData>> getTvPopular() {
        return listTvPopular;
    }
}