package com.example.pixelsearch.model;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.example.pixelsearch.retrofit.APIClient;
import com.example.pixelsearch.retrofit.GetData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PixaList extends BaseObservable {

    private String key = "13959586-a943d62b3a48911981744db5f";
    private String image_type = "all";
    private MutableLiveData<PixaData> pixaDataMutableList = new MutableLiveData<>();


    public MutableLiveData<PixaData> getPixaDataList() {
        return pixaDataMutableList;
    }

    public void fetchList() {
        Callback<PixaData> callback = new Callback<PixaData>() {
            @Override
            public void onResponse(Call<PixaData> call, Response<PixaData> response) {
                PixaData body = response.body();
                pixaDataMutableList.setValue(body);
            }

            @Override
            public void onFailure(Call<PixaData> call, Throwable t) {
            }
        };

      //  APIClient.getAPI().create(GetData.class).getallphotos(key,"sunflower",image_type);
    }
}
