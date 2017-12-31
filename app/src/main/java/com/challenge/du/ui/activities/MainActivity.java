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
import com.challenge.du.communication.response.SectionResponse;
import com.challenge.du.controllers.AppController;
import com.challenge.du.models.SectionModel;
import com.challenge.du.ui.adapters.SectionAdapter;
import com.challenge.du.ui.fragments.ProgressDialogFragment;
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
    List<SectionModel> sections;
    @BindView(R.id.gridview)
    GridView gridview;
    SectionAdapter mSectionAdapter;

    Call<SectionResponse> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sections = new ArrayList<>();
        mSectionAdapter = new SectionAdapter(MainActivity.this, sections);
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.grid_item_anim);
        GridLayoutAnimationController controller = new GridLayoutAnimationController(animation, .2f, .2f);
        gridview.setLayoutAnimation(controller);
        gridview.setAdapter(mSectionAdapter);
        gridview.startLayoutAnimation();
        getHomeScreenContent();


    }

    private void getHomeScreenContent() {
        ProgressDialogFragment.show(MainActivity.this);
        call = Api.SERVICE.getHomeScreenContent();
        call.enqueue(new Callback<SectionResponse>() {
            @Override
            public void onResponse(Call<SectionResponse> call, Response<SectionResponse> response) {
                ProgressDialogFragment.hide(MainActivity.this);
                if (response.body() != null && response.body().getSections() != null) {
                    for (SectionModel section : response.body().getSections()) {
                        if (section.getIsActive() == 1 && section.getIsVisible() == 1) {
                            AppController.getRealmInstance().saveSection(section);
                            sections.add(section);
                        }
                    }
                    mSectionAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<SectionResponse> call, Throwable t) {
                ProgressDialogFragment.hide(MainActivity.this);
                if (t instanceof MalformedJsonException) {
                    Toast.makeText(MainActivity.this, "Internal Error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Internet Connection Error", Toast.LENGTH_SHORT).show();
                }

                // In case there is no internet connection get the sections from cache
                for (SectionModel section : AppController.getRealmInstance().getSections()) {
                    sections.add(section);
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
