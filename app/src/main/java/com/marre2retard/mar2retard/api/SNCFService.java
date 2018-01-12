package com.marre2retard.mar2retard.api;

import com.android.volley.Response;
import com.marre2retard.mar2retard.models.Train;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by gasmi on 05/01/2018.
 */

public interface SNCFService {

    public static String ENDPOINT = "https://f77130b2-f491-4759-9237-71ed784f9e14@api.sncf.com/v1/coverage/sncf/";


    @FormUrlEncoded
    @POST("/application/json")
    public void insertUser(
            @Field("Username") String Username,
            Callback<Response> callback);

    @Headers("Cache-Control: max-age=640000")
    @GET("/{stop_areas}/")
    List<Train> listTrain(@Path("stop_areas") String stop_areas);


    @GET("/{commercial_modes}/")
    void listTrainAsync(@Path("commercial_modes") String commercial_modes, Callback<List<Train>> callback);

    @GET("/search/commercial_modes")
    List<Train> searchTrain(@Query("q") String query);


    @GET("/users/{user}/repos")
    void listReposAsync(@Path("user") String user, Callback<List<Train>> callback);

}
