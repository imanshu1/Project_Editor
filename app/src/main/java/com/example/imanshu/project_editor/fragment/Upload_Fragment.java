package com.example.imanshu.project_editor.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.imanshu.project_editor.R;
import com.example.imanshu.project_editor.adapter.GridViewAdapter;

import java.util.ArrayList;

public class Upload_Fragment extends BaseFragment {
    private GridView rvInstaFrag;
    private ArrayList<Integer> list;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_, container, false);
        init(view);
        return view;
    }


    private void init(final View view) {
        rvInstaFrag = (GridView) view.findViewById(R.id.grid_upload);
        list = new ArrayList<>();
        list.add(R.drawable.fb_1);
        list.add(R.drawable.fb_3);
        list.add(R.drawable.fb_3);
        list.add(R.drawable.fb_3);
        list.add(R.drawable.fb_1);
        list.add(R.drawable.fb_3);
        list.add(R.drawable.fb_3);
        list.add(R.drawable.fb_1);
        list.add(R.drawable.fb_3);

        list.add(R.drawable.fb_3);
        list.add(R.drawable.fb_1);
        list.add(R.drawable.fb_3);
        list.add(R.drawable.fb_3);



        Integer[] i=new Integer[]{R.drawable.fb_1,R.drawable.fb_1,R.drawable.fb_1,R.drawable.fb_1};

//
//        RVAdapter adapter = new RVAdapter(getContext(), list);
//        rvInstaFrag.setAdapter(adapter);

        GridViewAdapter adp=new GridViewAdapter((Activity) getContext(),i);
        rvInstaFrag.setAdapter(adp);
       // rvInstaFrag.setLayoutManager(new GridLayoutManager(getContext(), 4));

//        HeaderDecoration headerDecoration = new HeaderDecoration();
//        rvInstaFrag.addItemDecoration(headerDecoration);

        //rvInstaFrag.addItemDecoration(new GridItemDecoration(2, 2, true, 0));
    }


}