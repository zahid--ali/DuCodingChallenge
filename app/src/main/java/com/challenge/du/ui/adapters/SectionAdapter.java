package com.challenge.du.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.challenge.du.R;
import com.challenge.du.controllers.GlideApp;
import com.challenge.du.models.SectionModel;
import com.challenge.du.ui.activities.FindUsActivity;
import com.challenge.du.utils.AppConstants;
import com.thefinestartist.finestwebview.FinestWebView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by k.zahid on 12/28/17.
 */

public class SectionAdapter extends BaseAdapter {
    List<SectionModel> contentList;
    Context context;
    private final LayoutInflater inflater;


    public SectionAdapter(Context context, List<SectionModel> contentList) {
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
        holder.bindData((SectionModel) getItem(position));

        return view;
    }


    class ViewHolder {
        @BindView(R.id.tv_tile_title)
        TextView tvTileTitle;
        @BindView(R.id.iv_tile_image)
        ImageView ivTileImage;
        @BindView(R.id.tv_tile_description)
        TextView tvTileDescription;
        @BindView(R.id.fl_root_tile_view)
        FrameLayout flRootTileView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }


        private void bindData(final SectionModel content) {
            GlideApp.with(context).load(content.getImagePath()).into(ivTileImage);
            tvTileTitle.setText(content.getTitle());
            tvTileTitle.setTextColor(Color.parseColor(content.getTitleColor()));
            tvTileDescription.setTextColor(Color.parseColor(content.getShortDescColor()));
            tvTileDescription.setText(content.getShortDesc());
            flRootTileView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (content.getExternalLink() != null && Patterns.WEB_URL.matcher(content.getExternalLink()).matches())
                        new FinestWebView.Builder(context).show(content.getExternalLink());
                    else if (content.getSectionId() == AppConstants.FIND_US_SECTION_ID) {
                        context.startActivity(new Intent(context, FindUsActivity.class));
                    }
                }
            });
        }
    }
}

