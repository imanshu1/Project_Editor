package com.example.imanshu.project_editor.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imanshu.project_editor.R;
import com.example.imanshu.project_editor.adapter.PagerAdapter;
import com.example.imanshu.project_editor.fragment.Fragment_Design;
import com.example.imanshu.project_editor.fragment.Fragment_Home;
import com.example.imanshu.project_editor.fragment.Fragment_Notification;
import com.example.imanshu.project_editor.fragment.Fragment_Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.graphics.Typeface.createFromAsset;

public class Home extends BaseActivity implements ViewPager.OnPageChangeListener{

    @Bind(R.id.iv_design)
    ImageView iv_design;
    @Bind(R.id.iv_home)
    ImageView iv_home;
    @Bind(R.id.iv_notification)
    ImageView iv_notification;
    @Bind(R.id.iv_profile)
    ImageView iv_profile;
    @Bind(R.id.home_heading)
    TextView home_heading;

    ArrayList<Integer> alImage;

    private List<Fragment> fragmentList;
    private ViewPager pager;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

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


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

        ButterKnife.bind(this);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), fragmentList);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(this);

        changeColors(0);

        ///////////////////////////////////////

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {

        } else {
            Intent intent=new Intent(Home.this,Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }

        alImage = new ArrayList<>(Arrays.asList(R.drawable.pexels_photo,
                R.drawable.pexels_three,
                R.drawable.pexels_two,
                R.drawable.pexels_three,
                R.drawable.pexels_two,
                R.drawable.pexels_photo,
                R.drawable.pexels_three,
                R.drawable.pexels_two,
                R.drawable.pexels_three));

        Typeface type = createFromAsset(getAssets(), "fonts/S.ttf");
        home_heading.setTypeface(type);
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
