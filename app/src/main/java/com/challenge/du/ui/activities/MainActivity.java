package com.challenge.du.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.widget.GridView;
import android.widget.Toast;

import com.challenge.du.R;
import com.challenge.du.communication.Api;
import com.challenge.du.communication.response.HomeScreenResponse;
import com.challenge.du.controllers.AppController;
import com.challenge.du.models.SectionModel;
import com.challenge.du.ui.adapters.SectionAdapter;
import com.google.gson.stream.MalformedJsonException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    double latitude, longitude;
    List<SectionModel> contentList;
    @BindView(R.id.gridview)
    GridView gridview;
    SectionAdapter mSectionAdapter;

    Call<HomeScreenResponse> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        contentList = new ArrayList<>();
        mSectionAdapter = new SectionAdapter(MainActivity.this, contentList);
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.grid_item_anim);
        GridLayoutAnimationController controller = new GridLayoutAnimationController(animation, .2f, .2f);
        gridview.setLayoutAnimation(controller);
        gridview.setAdapter(mSectionAdapter);
        gridview.startLayoutAnimation();
        getHomeScreenContent();


    }

    private void getHomeScreenContent() {
        call = Api.SERVICE.getHomeScreenContent();
        call.enqueue(new Callback<HomeScreenResponse>() {
            @Override
            public void onResponse(Call<HomeScreenResponse> call, Response<HomeScreenResponse> response) {
                if (response.body() != null && response.body().getContentList() != null) {
                    for (SectionModel section : response.body().getContentList()) {
                        if (section.getIsActive() == 1 && section.getIsVisible() == 1) {
                            AppController.getRealmInstance().saveSection(section);
                            contentList.add(section);
                        }
                    }
                    mSectionAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<HomeScreenResponse> call, Throwable t) {
                if (t instanceof MalformedJsonException) {
                    Toast.makeText(MainActivity.this, "Internal Error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Internet Connection Error", Toast.LENGTH_SHORT).show();
                }

                // In case there is no internet connection get the sections from cache
                for (SectionModel section : AppController.getRealmInstance().getSections()) {
                    contentList.add(section);
                }
                mSectionAdapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (call != null)
            call.cancel();
    }
}
