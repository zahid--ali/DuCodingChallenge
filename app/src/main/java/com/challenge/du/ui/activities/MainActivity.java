package com.challenge.du.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.challenge.du.R;
import com.challenge.du.communication.Api;
import com.challenge.du.communication.response.NearbyBranchesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Api.SERVICE.getHomeScreenContent().enqueue(new Callback<HomeScreenResponse>() {
//            @Override
//            public void onResponse(Call<HomeScreenResponse> call, Response<HomeScreenResponse> response) {
//                Log.d("hello", "hi");
//            }
//
//            @Override
//            public void onFailure(Call<HomeScreenResponse> call, Throwable t) {
//                Log.d("error", "er");
//            }
//        });

        Api.SERVICE.getNearByBranches().enqueue(new Callback<NearbyBranchesResponse>() {
            @Override
            public void onResponse(Call<NearbyBranchesResponse> call, Response<NearbyBranchesResponse> response) {
                Log.d("success", "true");
            }

            @Override
            public void onFailure(Call<NearbyBranchesResponse> call, Throwable t) {
                Log.d("failure", "false");
            }
        });
    }
}
