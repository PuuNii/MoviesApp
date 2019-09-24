package com.example.mymovies.Retrofit;

import android.content.Context;

import com.example.mymovies.MoviesNew.MoviesListner2;
import com.example.mymovies.MoviesNew.MoviesResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCalling
{

    private ApiClient apiClient;
    private ApiInterface apiInterface;
    private Context context;
//    private SessionManager sessionManager;

    public ApiCalling(Context context )
    {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
//        sessionManager = new SessionManager(context);
    }


//    public void login(String username , String password , String lang)
//    {
//        Map<String, String> queryMap = new HashMap<>();
//        queryMap.put("username",username);
//        queryMap.put("password",password);
//
//        // 1 for android
//        queryMap.put("device_type","1");
//        queryMap.put("device_token","macAddress");
//
//
//        Call<LoginResponse> call = apiInterface.login(lang,queryMap);
//        call.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                Log.e("onResponse", response.raw().toString());
//
//                if (response.body().getSuccess()) {
//
//
//                    loginListener.getLoginResponse(ErrorTypeEnum.noError.getValue(),
//                            null, response.body());
//
//
////                        FirebaseMessaging.getInstance().subscribeToTopic("announcements")
////                                .addOnCompleteListener(new OnCompleteListener<Void>() {
////                                    @Override
////                                    public void onComplete(@NonNull Task<Void> task) {
////                                        if (!task.isSuccessful()) {
////                                            Log.e("testFCM", "faile subscribe topic");
////                                        }
////                                        else
////                                        {
////                                            Log.e("testFCM", "Success subscribe topic");
////                                        }
////                                    }
////                                });
//
//                }
//                else if (response.code() == 401) {
//                    // Handle unauthorized
//                    Log.e("onResponse" ,"logout");
//                    Intent i= new Intent(context , ChooseActivity.class);
//
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    context.startActivity(i);
//                }
//                else {
//                    loginListener.getLoginResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
//                            null, response.body());
//                }
//
//            }
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t)
//            {
//                //fail internet connection
//                if (t instanceof IOException)
//                {
//                    Log.e("ApiCheck**" , "no internet connection");
//                    loginListener.getLoginResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
//                            null , null);
//                }
//                //fail conversion issue
//                else {
//                    loginListener.getLoginResponse(ErrorTypeEnum.other.getValue() ,
//                            null , null);
//                }
//            }
//        });
//
//    }



    public void getMovies(final MoviesListner2 moviesListner)
    {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_key","2562a20c7157865fb9a54cab92727a48");


        Call<MoviesResponse> call = apiInterface.getMovies(queryMap);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if(response.isSuccessful())
                {
                    moviesListner.getMoviesListner(true , response.body());
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                moviesListner.getMoviesListner(false , null);
            }
        });
//        call.enqueue(new Callback<MovieResponse>()
//        {
//            @Override
//            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
//                Log.e("onResponse", response.raw().toString());
//
////                if (response.isSuccessful()) {
////
////                    moviesListner.getMovies(true,
////                            "", response.body());
////
////                }
////                else {
////                    moviesListner.getMovies(false ,
////                            "something wrong happen" , null);
////                }
//
//            }
//            @Override
//            public void onFailure(Call<MovieResponse> call, Throwable t)
//            {
////                moviesListner.getMovies(false ,
////                        "something wrong happen" , null);
//            }
//        });

    }


}
