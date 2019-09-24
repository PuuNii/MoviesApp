package com.example.mymovies.Retrofit;

import com.example.mymovies.MoviesNew.MoviesResponse;
import com.example.mymovies.MoviesNew.MoviesResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by eldsokey on 2017-12-01.
 */

// file of apis
public interface ApiInterface
{

    // username / password / device_type / device_token
//    @POST("login")
//    Call<LoginResponse> login(@Header("lang") String lang,
//                              @QueryMap Map<String, String> queryMap);


    @GET("discover/movie")
    Call<MoviesResponse> getMovies(@QueryMap Map<String, String> queryMap);

}
