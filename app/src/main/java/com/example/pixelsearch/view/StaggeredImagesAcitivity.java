package com.example.pixelsearch.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.pixelsearch.R;
import com.example.pixelsearch.adapter.StaggeredRecycleViewAdapter;
import com.example.pixelsearch.databinding.ImagesStaggeredBinding;
import com.example.pixelsearch.model.Hit;
import com.example.pixelsearch.viewmodel.StaggeredActivityModel;

import java.util.ArrayList;
import java.util.List;

public class StaggeredImagesAcitivity extends AppCompatActivity {

    private Boolean isScrolling = false;
    private int scrolledItems,currItems,totalItems;
    private RecyclerView recyclerView ;
    private ImagesStaggeredBinding mBinding ;
    private StaggeredActivityModel mViewModel ;
    private StaggeredGridLayoutManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.images_staggered);

        mBinding = DataBindingUtil.setContentView(this, R.layout.images_staggered);
        mViewModel = ViewModelProviders.of(this).get(StaggeredActivityModel.class);
        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mBinding.recycler.setLayoutManager(manager);

        final StaggeredRecycleViewAdapter adapter = new StaggeredRecycleViewAdapter((ArrayList<Hit>) mViewModel.getMyhitlist(), StaggeredImagesAcitivity.this);
        mViewModel.getProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean)
                    mBinding.progress.setVisibility(View.VISIBLE);
                else
                    mBinding.progress.setVisibility(View.GONE);
            }
        });
        mViewModel.getImageList().observe(this, new Observer<List<Hit>>() {
            @Override
            public void onChanged(List<Hit> hits) {
                adapter.notifyDataSetChanged();
            }
        });


        recyclerView = mBinding.recycler;
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrolledItems = manager.findFirstVisibleItemPositions(null)[0];
                if(isScrolling && (currItems+scrolledItems==totalItems))
                {
                    //fetch next data page
                    isScrolling = false;
                    mViewModel.getImages();

                }
            }
        });
        //To make network call to load images from Pixabay
        mViewModel.getImages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search images");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mViewModel.newQuery(query);
                mViewModel.getImages();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item)
//        return super.onOptionsItemSelected(item);
//    }
}
