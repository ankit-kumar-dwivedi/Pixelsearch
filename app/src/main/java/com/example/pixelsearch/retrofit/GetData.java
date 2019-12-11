package com.example.pixelsearch.retrofit;

import com.example.pixelsearch.model.PixaData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface GetData {
    @GET("api/")
    Call<PixaData> getallphotos(
            @QueryMap Map<String, String> param
    );
}
