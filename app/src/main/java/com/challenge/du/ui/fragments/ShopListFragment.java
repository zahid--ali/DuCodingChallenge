package com.challenge.du.ui.fragments;


import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.challenge.du.R;
import com.challenge.du.communication.Api;
import com.challenge.du.communication.response.NearbyBranchesResponse;
import com.challenge.du.controllers.AppController;
import com.challenge.du.models.ShopModel;
import com.challenge.du.ui.adapters.ShopsAdapter;
import com.google.gson.stream.MalformedJsonException;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.providers.LocationGooglePlayServicesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challenge.du.controllers.AppController.context;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopListFragment extends Fragment implements OnLocationUpdatedListener {


    @BindView(R.id.rv_shops)
    RecyclerView rvShops;
    Unbinder unbinder;

    List<ShopModel> shopList;
    ShopsAdapter mShopAdapter;
    private LocationGooglePlayServicesProvider provider;
    Call<NearbyBranchesResponse> call;
    SmartLocation smartLocation;

    public ShopListFragment() {
        // Required empty public constructor
    }

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
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        smartLocation = new SmartLocation.Builder(context()).logging(true).build();
        checkLocationPermission();
    }

    private void getAllBranches() {

        call = Api.SERVICE.getNearByBranches();
        call.enqueue(new Callback<NearbyBranchesResponse>() {
            @Override
            public void onResponse(Call<NearbyBranchesResponse> call, Response<NearbyBranchesResponse> response) {
                ProgressDialogFragment.hide(getActivity());
                if (response.body().getCode() == 0 && response.body() != null && response.body().getBranchesList() != null) {
                    for (int i = 0; i < response.body().getBranchesList().size(); i++) {
                        shopList.add(response.body().getBranchesList().get(i));
                        mShopAdapter.notifyItemInserted(i);
                        AppController.getRealmInstance().saveShops(response.body().getBranchesList().get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<NearbyBranchesResponse> call, Throwable t) {
                ProgressDialogFragment.hide(getActivity());
                if (t instanceof MalformedJsonException) {
                    Toast.makeText(getActivity(), "Internal Error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Internet Connection Error", Toast.LENGTH_SHORT).show();
                }

                // In case there is no internet connection get the sections from cache
                AppController.USER_LOCATION_LAT = smartLocation.location().getLastLocation().getLatitude();
                AppController.USER_LOCATION_LONG = smartLocation.location().getLastLocation().getLongitude();
                shopList.clear();
                List<ShopModel> shops = AppController.getRealmInstance().getShops();
                for (int i = 0; i < shops.size(); i++) {
                    shopList.add(shops.get(i));
                    mShopAdapter.notifyItemInserted(i);
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (call != null)
            call.cancel();
    }

    @Override
    public void onLocationUpdated(Location location) {
        if (location.getLatitude() == 0.0 && location.getLongitude() == 0.0) {
            return;
        } else {
            AppController.USER_LOCATION_LAT = location.getLatitude();
            AppController.USER_LOCATION_LONG = location.getLongitude();
            getAllBranches();
        }
    }

    private void checkLocationPermission() {
        Dexter.withActivity(getActivity()).withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    startLocation();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();
    }

    private void startLocation() {
        ProgressDialogFragment.show(getActivity());
        provider = new LocationGooglePlayServicesProvider();
        provider.setLocationSettingsAlwaysShow(true);
        provider.setCheckLocationSettings(true);
        if (smartLocation.location().state().locationServicesEnabled()) {
            smartLocation.location(provider).start(this);
        }
    }
}
