package com.example.pixelsearch.repository;

import com.example.pixelsearch.model.PixaData;
import com.example.pixelsearch.retrofit.APIClient;
import com.example.pixelsearch.retrofit.GetData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Retrofit;

public class Repository {
    private static final Repository ourInstance = new Repository();

    public static Repository getInstance() {
        return ourInstance;
    }

    private Repository() {
    }

    public Call<PixaData> getImages(HashMap<String,String> map) {
       return APIClient.getAPI().create(GetData.class).getallphotos(map);
       //"?" + key + "&q=" + query + "&image_type=" + type + "&per_page=" + page_per);
    }
}
