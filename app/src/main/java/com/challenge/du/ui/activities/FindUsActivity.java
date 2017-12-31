package com.challenge.du.ui.activities;

import android.content.res.AssetManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.challenge.du.R;
import com.challenge.du.ui.fragments.ShopListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.nlopez.smartlocation.OnLocationUpdatedListener;

public class FindUsActivity extends AppCompatActivity implements OnLocationUpdatedListener {


    @BindView(R.id.container)
    ViewPager mViewPager;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;


    private SectionsPagerAdapter mSectionsPagerAdapter;

    Fragment shopListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_us);
        ButterKnife.bind(this);
        tvTitle.setText(getIntent().getStringExtra("Title"));
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        changeTabsFont();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TabLayout.Tab tabSelected, tabSelectedUnSelected1, tabSelectedUnSelected2;
                if (position == 0) {
                    tabSelected = tabLayout.getTabAt(position);
                    tabSelectedUnSelected1 = tabLayout.getTabAt(1);
                    tabSelectedUnSelected2 = tabLayout.getTabAt(2);
//                    checkLocationPermission();
                } else if (position == 1) {
                    tabSelected = tabLayout.getTabAt(position);
                    tabSelectedUnSelected1 = tabLayout.getTabAt(0);
                    tabSelectedUnSelected2 = tabLayout.getTabAt(2);
                } else {
                    tabSelected = tabLayout.getTabAt(position);
                    tabSelectedUnSelected1 = tabLayout.getTabAt(0);
                    tabSelectedUnSelected2 = tabLayout.getTabAt(1);
                }
                TextView tabTitle;
                View view = tabSelected.getCustomView();
                tabTitle = view.findViewById(R.id.tab_title);
                tabTitle.setBackground(ContextCompat.getDrawable(FindUsActivity.this, R.drawable.tab_header_border_selected));
                tabTitle.setTextColor(ContextCompat.getColor(FindUsActivity.this, android.R.color.white));
                tabSelected.setCustomView(view);

                view = tabSelectedUnSelected1.getCustomView();
                tabTitle = view.findViewById(R.id.tab_title);
                tabTitle.setBackground(ContextCompat.getDrawable(FindUsActivity.this, R.drawable.tab_header_border_unselected));
                tabTitle.setTextColor(ContextCompat.getColor(FindUsActivity.this, R.color.colorPrimary));
                tabSelectedUnSelected1.setCustomView(view);

                view = tabSelectedUnSelected2.getCustomView();
                tabTitle = view.findViewById(R.id.tab_title);
                tabTitle.setBackground(ContextCompat.getDrawable(FindUsActivity.this, R.drawable.tab_header_border_unselected));
                tabTitle.setTextColor(ContextCompat.getColor(FindUsActivity.this, R.color.colorPrimary));
                tabSelectedUnSelected2.setCustomView(view);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onLocationUpdated(Location location) {

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        onBackPressed();
    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_find_us, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (shopListFragment == null)
                        shopListFragment = new ShopListFragment();
                    return shopListFragment;
                case 1:
                    return PlaceholderFragment.newInstance(position + 1);
                case 2:
                    return PlaceholderFragment.newInstance(position + 1);

            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.title_tab_shop);
                case 1:
                    return getString(R.string.title_tab_wifi_uae);
                case 2:
                    return getString(R.string.title_tab_payment_machines);
            }
            return null;
        }
    }

    private void changeTabsFont() {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        AssetManager mgr = getAssets();
        for (int j = 0; j < tabsCount; j++) {
            TabLayout.Tab tab = tabLayout.getTabAt(j);
            RelativeLayout relativeLayout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.custom_tab_layout_header, tabLayout, false);

            TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.tab_title);
            tabTextView.setTextSize(18);
            tabTextView.setText(tab.getText());
            tab.setCustomView(relativeLayout);


        }
    }


}
