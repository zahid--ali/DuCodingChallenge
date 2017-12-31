package com.challenge.du.ui.adapters;

import android.content.Context;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.challenge.du.R;
import com.challenge.du.controllers.AppController;
import com.challenge.du.models.ShopModel;
import com.challenge.du.utils.LocaleHelper;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by k.zahid on 12/28/17.
 */

public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.ViewHolder> {
    Context context;
    List<ShopModel> shopList;
    LayoutInflater inflater;
    DecimalFormat df;

    public ShopsAdapter(Context context, List<ShopModel> shopList) {
        this.shopList = shopList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        df = new DecimalFormat("###.##");

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.shop_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(shopList.get(position));
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_shop_title)
        TextView tvShopTitle;
        @BindView(R.id.tv_distance)
        TextView tvDistance;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindData(ShopModel branch) {

            if (LocaleHelper.getLanguage(AppController.context()).equals("ar")) {
                tvShopTitle.setText(branch.getTitleArabic());
            } else {
                tvShopTitle.setText(branch.getTitle());
            }
            float[] results = new float[1];
            Location.distanceBetween(branch.getLatitude(), branch.getLongitude(), AppController.USER_LOCATION_LAT, AppController.USER_LOCATION_LONG, results);

            tvDistance.setText(String.format(context.getString(R.string.kilometer), String.valueOf(df.format(results[0] / 1000))));
        }

    }
}
