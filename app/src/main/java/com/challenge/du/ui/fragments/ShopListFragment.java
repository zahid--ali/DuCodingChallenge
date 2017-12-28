package com.challenge.du.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.challenge.du.R;
import com.challenge.du.communication.Api;
import com.challenge.du.communication.response.NearbyBranchesResponse;
import com.challenge.du.models.BranchModel;
import com.challenge.du.ui.adapters.ShopsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopListFragment extends Fragment {


    @BindView(R.id.rv_shops)
    RecyclerView rvShops;
    Unbinder unbinder;

    List<BranchModel> shopList;

    public ShopListFragment() {
        // Required empty public constructor
    }

    ShopsAdapter mShopAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shops, container, false);
        unbinder = ButterKnife.bind(this, view);
        shopList = new ArrayList<>();
        mShopAdapter = new ShopsAdapter(getActivity(), shopList);
        rvShops.setAdapter(mShopAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Api.SERVICE.getNearByBranches().enqueue(new Callback<NearbyBranchesResponse>() {
            @Override
            public void onResponse(Call<NearbyBranchesResponse> call, Response<NearbyBranchesResponse> response) {

                if (response.body().getCode() == 0 && response.body() != null && response.body().getBranchesList() != null) {
                    for (int i = 0; i < response.body().getBranchesList().size(); i++) {
                        shopList.add(response.body().getBranchesList().get(i));
                        mShopAdapter.notifyItemInserted(i);
                    }
                }
            }

            @Override
            public void onFailure(Call<NearbyBranchesResponse> call, Throwable t) {
                Log.d("failure", "false");
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
