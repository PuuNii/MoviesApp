package com.example.mymovies.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.example.mymovies.MoviesAdapter;
import com.example.mymovies.MoviesNew.MoviesListner2;
import com.example.mymovies.MoviesNew.MoviesResponse;
import com.example.mymovies.R;
import com.example.mymovies.Retrofit.ApiCalling;

public class HomeFragment extends Fragment     implements MoviesListner2 {
    ApiCalling apiCalling;
    RecyclerView adsRv;
    MoviesAdapter moviesAdapter;
    LinearLayoutManager linearLayoutManager;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        return root;
    }


    @Override
    public void getMoviesListner(boolean status, MoviesResponse moviesResponse) {
        Log.e("status**" , status+"");

    }
}