package com.challenge.du.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.challenge.du.R;
import com.challenge.du.models.HomeContentModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by k.zahid on 12/28/17.
 */

public class ContentAdapter extends BaseAdapter {
    List<HomeContentModel> contentList;
    Context context;
    private final LayoutInflater inflater;



    public ContentAdapter(Context context, List<HomeContentModel> contentList) {
        this.context = context;
        this.contentList = contentList;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return contentList.size();
    }

    @Override
    public Object getItem(int position) {
        return contentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contentList.get(position).getSectionId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.home_content_item_view, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.bindData((HomeContentModel) getItem(position));

        return view;
    }


    class ViewHolder {
        @BindView(R.id.tv_tile_title)
        TextView tvTileTitle;
        @BindView(R.id.iv_tile_image)
        ImageView ivTileImage;
        @BindView(R.id.tv_tile_description)
        TextView tvTileDescription;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.fl_root_tile_view)
        public void onViewClicked() {
            //todo open link
        }

        private void bindData(HomeContentModel content) {
            Glide.with(context).load(content.getImagePath()).into(ivTileImage);
            tvTileTitle.setText(content.getTitle());
            tvTileTitle.setTextColor(Color.parseColor(content.getTitleColor()));
            tvTileDescription.setTextColor(Color.parseColor(content.getShortDescColor()));
            tvTileDescription.setText(content.getShortDesc());

        }
    }
}

