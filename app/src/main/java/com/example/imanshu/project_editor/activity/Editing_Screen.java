package com.example.imanshu.project_editor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imanshu.project_editor.R;
import com.example.imanshu.project_editor.adapter.PagerAdapter;
import com.example.imanshu.project_editor.adapter.Share_Adapter;
import com.example.imanshu.project_editor.fragment.Elements_Fragment;
import com.example.imanshu.project_editor.fragment.Layout_Fragment;
import com.example.imanshu.project_editor.fragment.Text_Fragment;
import com.example.imanshu.project_editor.fragment.Upload_Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Editing_Screen extends BaseActivity implements ViewPager.OnPageChangeListener {

    ArrayList<Integer> alImage;
    String[] nam;

    RecyclerView rec_v = null;

    @Bind(R.id.editing_screen_return)
    ImageView editing_screen_return;

    @Bind(R.id.text_share)
    TextView text_share;

    private ViewPager viewpager_edit;
    private List<Fragment> fragmentList1;

    @Bind(R.id.tvText)
    TextView  tvText;

    @Bind(R.id.tvElements)
    TextView tvElements;

    @Bind(R.id.tvLayouts)
    TextView tvLayouts;

    @Bind(R.id.tvPages)
    TextView tvPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing__screen);

        ButterKnife.bind(this);

        init();

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), fragmentList1);
        viewpager_edit.setAdapter(adapter);
        viewpager_edit.addOnPageChangeListener(this);
        viewpager_edit.setCurrentItem(0);
       // viewpager_edit.setPagingEnabled(false);

        text_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alImage = new ArrayList<>(Arrays.asList(R.drawable.pexels_photo
                        , R.drawable.pexels_three
                        , R.drawable.pexels_two
                        , R.drawable.pexels_three
                        , R.drawable.pexels_two
                        , R.drawable.pexels_photo
                        , R.drawable.pexels_three
                        , R.drawable.pexels_two
                        , R.drawable.pexels_three));

                alImage = new ArrayList<>(Arrays.asList(R.drawable.list_item,
                        R.drawable.pexels_two,
                        R.drawable.pexels_three,
                        R.drawable.list_item,
                        R.drawable.pexels_photo,
                        R.drawable.list_item,
                        R.drawable.list_item,
                        R.drawable.list_item,
                        R.drawable.list_item));

                nam = new String[]{"instagram",
                        "facebook", "twitter", "instagram", "facebook", "twitter", "instagram", "facebook", "twitter"};

                rec_v.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

                Share_Adapter adapter1 = new Share_Adapter(Editing_Screen.this, alImage, nam);
                rec_v.setAdapter(adapter1);

                AlertDialog.Builder builder1 = new AlertDialog.Builder(Editing_Screen.this);
                builder1.setView(rec_v);
                builder1.setCancelable(false);
                builder1.setPositiveButton("ok", null);
                AlertDialog dialog = builder1.create();
                dialog.show();
            }
        });

    }

    private void init() {

        fragmentList1 = new ArrayList<>();

        tvText=(TextView)findViewById(R.id.tvText);
        tvElements=(TextView)findViewById(R.id.tvElements);
        tvLayouts=(TextView)findViewById(R.id.tvLayouts);
        tvPages=(TextView)findViewById(R.id.tvPages);

        fragmentList1.add(new Text_Fragment());
        fragmentList1.add(new Elements_Fragment());
        fragmentList1.add(new Layout_Fragment());
        fragmentList1.add(new Upload_Fragment());

        viewpager_edit=(ViewPager)findViewById(R.id.viewpager_edit);

        text_share = (TextView) findViewById(R.id.text_share);
        rec_v = new RecyclerView(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.tvText)
    public void setTvText(){
        viewpager_edit.setCurrentItem(0);
    }

    @OnClick(R.id.tvElements)
    public void setTvElements(){
        viewpager_edit.setCurrentItem(1);
    }

    @OnClick(R.id.tvLayouts)
    public void setTvLayouts(){
        viewpager_edit.setCurrentItem(2);
    }

    @OnClick(R.id.tvPages)
    public void setTvPages(){
        viewpager_edit.setCurrentItem(3);
    }

    @OnClick(R.id.editing_screen_return)
    public void setEditing_screen_return() {
        Intent intent = new Intent(Editing_Screen.this, Home.class);
        startActivity(intent);
    }
}
