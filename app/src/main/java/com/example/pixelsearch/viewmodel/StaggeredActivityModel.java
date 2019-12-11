package com.example.pixelsearch.viewmodel;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pixelsearch.R;
import com.example.pixelsearch.adapter.StaggeredRecycleViewAdapter;
import com.example.pixelsearch.model.Hit;
import com.example.pixelsearch.model.PixaData;
import com.example.pixelsearch.model.PixaList;
import com.example.pixelsearch.repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaggeredActivityModel extends ViewModel {

    private MutableLiveData<Boolean> mProgress = null;
    private MutableLiveData<List<Hit>> mImageList = null ;
    private HashMap<String,String> map = new HashMap<>();
    private Repository repository = null ;
    private String query="wallpaper";
    private String key = "13959586-a943d62b3a48911981744db5f";
    private String imageType = "all";
    private Integer page = 0;
    private List<Hit> myhitlist;

    public List<Hit> getMyhitlist() {
        return myhitlist;
    }

    public StaggeredActivityModel() {
        mProgress = new MutableLiveData<>();
        mImageList = new MutableLiveData<>();
        repository = Repository.getInstance();
        myhitlist  = new ArrayList<>();
    }


    public LiveData<Boolean> getProgress() {
        return mProgress ;
    }

    public LiveData<List<Hit>> getImageList() {
        return mImageList;
    }

    public void newQuery(String query) {
        this.query = query;
        this.page = 0;
        myhitlist.clear();
    }

    //Method to make network call to load images from Pixabay
    public void getImages() {
        page++; // increment page number
        mProgress.postValue(true); //Makes progress bar visible
        map.put("key",key);
        map.put("q", query);
        map.put("image_type",imageType);
        map.put("page",page.toString());
        repository.getImages(map).enqueue(new Callback<PixaData>() {
            @Override
            public void onResponse(Call<PixaData> call, Response<PixaData> response) {

                mProgress.postValue(false); //Makes progress bar invisible

                if (response.body() != null && response.body().getHits() != null && response.body().getHits().size() > 0)
                {
                    myhitlist.addAll(response.body().getHits());
                    mImageList.postValue(myhitlist);//Posts received image list back to activity to update adapter
                }
            }

            @Override
            public void onFailure(Call<PixaData> call, Throwable t) {
                mProgress.postValue(false); //Makes progress bar invisible
            }
        });

    }

}
