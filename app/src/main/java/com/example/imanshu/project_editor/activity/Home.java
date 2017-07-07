package com.example.imanshu.project_editor.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imanshu.project_editor.R;
import com.example.imanshu.project_editor.adapter.PagerAdapter;
import com.example.imanshu.project_editor.fragment.Fragment_Design;
import com.example.imanshu.project_editor.fragment.Fragment_Home;
import com.example.imanshu.project_editor.fragment.Fragment_Notification;
import com.example.imanshu.project_editor.fragment.Fragment_Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.graphics.Typeface.createFromAsset;

public class Home extends BaseActivity implements ViewPager.OnPageChangeListener{

    @Bind(R.id.iv_design) ImageView iv_design;

    @Bind(R.id.iv_home) ImageView iv_home;

    @Bind(R.id.iv_notification) ImageView iv_notification;

    @Bind(R.id.iv_profile) ImageView iv_profile;

    ArrayList<Integer> alImage;

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    TextView home_heading;


    private List<Fragment> fragmentList;
    private ViewPager pager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

        ButterKnife.bind(this);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), fragmentList);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(this);

        changeColors(0);


//        CustomListAdapter adop=new CustomListAdapter(Home.this, temp_data, images_data);
//        home_list.setAdapter(adop);

        alImage = new ArrayList<>(Arrays.asList(R.drawable.pexels_photo,
                R.drawable.pexels_three,
                R.drawable.pexels_two,
                R.drawable.pexels_three,
                R.drawable.pexels_two,
                R.drawable.pexels_photo,
                R.drawable.pexels_three,
                R.drawable.pexels_two,
                R.drawable.pexels_three));

//        mRecyclerView.setHasFixedSize(true);
//
//        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//
//        mAdapter = new RVAdapter(Home.this, alImage);
//        mRecyclerView.setAdapter(mAdapter);

        Typeface type = createFromAsset(getAssets(), "fonts/S.ttf");
        home_heading.setTypeface(type);
    }

//    @Override
//    public void onClick(final View v) {
//        super.onClick(v);
//        switch (v.getId()) {
//            case R.id.iv_home:
//                //changeColors(0);
//                pager.setCurrentItem(0);
//                break;
//            case R.id.iv_design:
//               // changeColors(1);
//                pager.setCurrentItem(1);
//                break;
////            case R.id.ivFb:
////                changeColors(2);
////                pager.setCurrentItem(2);
////                break;
////            case R.id.ivTwitter:
////                changeColors(3);
////                pager.setCurrentItem(3);
////                break;
//            default:
//                break;
//        }
//    }


    @OnClick(R.id.iv_design)
    public void setImage_design(){
                changeColors(1);
                pager.setCurrentItem(1);
    }

    @OnClick(R.id.iv_notification)
    public void setIv_notification(){
        pager.setCurrentItem(2);
        changeColors(2);

    }

    @OnClick(R.id.iv_home)
    public void setIv_home(){
        changeColors(0);
                pager.setCurrentItem(0);
    }

    @OnClick(R.id.iv_profile)
    public void setIv_profile(){
        changeColors(3);
              pager.setCurrentItem(3);
    }

    private void changeColors(final int pos) {
        iv_design.setSelected(false);
        iv_home.setSelected(false);
        iv_notification.setSelected(false);
        iv_profile.setSelected(false);
        switch (pos) {
            case 0:
                iv_home.setSelected(true);
                break;
            case 1:
                iv_design.setSelected(true);
                break;
            case 2:
                iv_notification.setSelected(true);
                break;
            case 3:
                iv_profile.setSelected(true);
                break;
            default:
                break;
        }
    }

    private void init(){
        fragmentList = new ArrayList<>();

        iv_design=(ImageView)findViewById(R.id.iv_design);
        iv_home=(ImageView)findViewById(R.id.iv_home);
        iv_notification=(ImageView)findViewById(R.id.iv_notification);
        iv_profile=(ImageView)findViewById(R.id.iv_profile);

       // layout_profile=(LinearLayout)findViewById(R.id.layout_profile);
        home_heading=(TextView)findViewById(R.id.home_heading);
        pager=(ViewPager)findViewById(R.id.pager);

        fragmentList.add(new Fragment_Home());
        fragmentList.add(new Fragment_Design());
        fragmentList.add(new Fragment_Notification());
        fragmentList.add(new Fragment_Profile());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        changeColors(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
