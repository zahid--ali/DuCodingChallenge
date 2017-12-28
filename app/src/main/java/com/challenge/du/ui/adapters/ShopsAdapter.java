package com.challenge.du.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.challenge.du.R;
import com.challenge.du.models.BranchModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by k.zahid on 12/28/17.
 */

public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.ViewHolder> {
    Context context;
    List<BranchModel> shopList;
    LayoutInflater inflater;

    public ShopsAdapter(Context context, List<BranchModel> shopList) {
        this.shopList = shopList;
        this.context = context;
        inflater = LayoutInflater.from(context);

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

        private void bindData(BranchModel branch) {
            tvShopTitle.setText(branch.getTitle());
        }

    }
}
