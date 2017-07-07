package com.example.imanshu.project_editor.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imanshu.project_editor.R;
import com.example.imanshu.project_editor.adapter.CommonRvAdapter;

import java.util.ArrayList;


public class Fragment_Design extends BaseFragment {
    private RecyclerView rvInstaFrag;
    private ArrayList<Integer> list;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common, container, false);
        init(view);
        return view;
    }

    /**
     * Initialize all the views
     *
     * @param view object of the inflated view
     */
    private void init(final View view) {
        rvInstaFrag = (RecyclerView) view.findViewById(R.id.rvCommonFragment);
        list = new ArrayList<>();
        list.add(R.drawable.thumbnail_large);
        list.add(R.drawable.second);
        list.add(R.drawable.thumbnail_large);
        list.add(R.drawable.second);
        list.add(R.drawable.thumbnail_large);
        list.add(R.drawable.second);
        list.add(R.drawable.thumbnail_large);
        list.add(R.drawable.second);
        CommonRvAdapter adapter = new CommonRvAdapter(getContext(), list, INSTA_FRAG);
        rvInstaFrag.setAdapter(adapter);
        rvInstaFrag.setLayoutManager(new GridLayoutManager(getContext(), 2));
       // rvInstaFrag.addItemDecoration(new GridItemDecoration(2, 2, true, 0));
    }
}

