package com.example.mymovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.mymovies.MoviesNew.MoviesListner2;
import com.example.mymovies.MoviesNew.MoviesResponse;
import com.example.mymovies.Retrofit.ApiCalling;
import com.example.mymovies.MoviesNew.MoviesListner2;
import com.example.mymovies.MoviesNew.MoviesResponse;
import com.example.mymovies.Retrofit.ApiCalling;

public class Home2Activity extends AppCompatActivity
        implements MoviesListner2 {

    ApiCalling apiCalling;
    RecyclerView adsRv;
    MoviesAdapter moviesAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);



        apiCalling = new ApiCalling(this);
        apiCalling.getMovies((MoviesListner2) this);
    }



    @Override
    public void getMoviesListner(boolean status, MoviesResponse moviesResponse)
    {
        Log.e("status**" , status+"");
        if(status == true)
        {
            adsRv = findViewById(R.id.rv);
            moviesAdapter = new MoviesAdapter(moviesResponse.getResults() , this);
            linearLayoutManager = new LinearLayoutManager(this ,
                    LinearLayoutManager.VERTICAL,false);
            adsRv.setAdapter(moviesAdapter);
            adsRv.setLayoutManager(linearLayoutManager);
        }
    }
}
