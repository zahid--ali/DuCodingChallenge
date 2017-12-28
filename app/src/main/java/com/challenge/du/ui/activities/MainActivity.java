package com.challenge.du.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import com.challenge.du.R;
import com.challenge.du.communication.Api;
import com.challenge.du.communication.response.HomeScreenResponse;
import com.challenge.du.models.HomeContentModel;
import com.challenge.du.ui.adapters.ContentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    double latitude, longitude;
    List<HomeContentModel> contentList;
    @BindView(R.id.gridview)
    GridView gridview;
    ContentAdapter mContentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        contentList = new ArrayList<>();
        mContentAdapter = new ContentAdapter(MainActivity.this, contentList);
        gridview.setAdapter(mContentAdapter);
        Api.SERVICE.getHomeScreenContent().enqueue(new Callback<HomeScreenResponse>() {
            @Override
            public void onResponse(Call<HomeScreenResponse> call, Response<HomeScreenResponse> response) {
                if (response.body() != null && response.body().getContentList() != null) {
                    for (HomeContentModel content : response.body().getContentList()) {
                        if (content.getIsActive() == 1 && content.getIsVisible() == 1)
                            contentList.add(content);
                    }
                    mContentAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<HomeScreenResponse> call, Throwable t) {
                Log.d("error", "er");
            }
        });

//        Api.SERVICE.getNearByBranches().enqueue(new Callback<NearbyBranchesResponse>() {
//            @Override
//            public void onResponse(Call<NearbyBranchesResponse> call, Response<NearbyBranchesResponse> response) {
//                Log.d("success", "true");
//            }
//
//            @Override
//            public void onFailure(Call<NearbyBranchesResponse> call, Throwable t) {
//                Log.d("failure", "false");
//            }
//        });
    }
}
