package com.example.imanshu.project_editor.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.imanshu.project_editor.R;
import com.example.imanshu.project_editor.adapter.CustomListAdapter;

import java.util.ArrayList;


public class Fragment_Notification  extends BaseFragment {

    private ListView home_list;
    private ArrayList<Integer> list;

    String []temp_data={"Notification on review 1 Notification on review 2 Notification on review 2 Notification on review 2",
            "Notification on review 1 Notification on review 2 Notification on review 2 Notification on review 2",
            "Notification on review 1 Notification on review 2 Notification on review 2 Notification on review 2",
            "Notification on review 1 Notification on review 2 Notification on reviewfication on review 1 Notification on review 2 Notification on review 2 Notification on review 2",
            "Notification on review 1 Notification on review 2 Notification on review 2 Notification on review 2Notification on review 1 Notification on review 2 Notification on review 2 Notification on review 2",
            "Notification on review 1 Notification on review 2 Notification on review 2 Notification on review 2",
            "Notification on review 1 Notification on review 2 Notification on review 2 Notification on review 2",
            "Notification on review 1 Notification on review 2 Notification on review 2 Notification on review 2Notification on review 1 Notification on review 2 Notification on review 2 Notification on review 2"};

    Integer []images_data={R.drawable.pexels_photo,
            R.drawable.pexels_three,
            R.drawable.pexels_two,
            R.drawable.pexels_three,
            R.drawable.pexels_two,
            R.drawable.pexels_photo,
            R.drawable.pexels_three,
            R.drawable.pexels_two};



    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__notification, container, false);
        init(view);
        return view;
    }

    /**
     * Initialize all the views
     *
     * @param view object of the inflated view
     */
    private void init(final View view) {
        home_list = (ListView) view.findViewById(R.id.home_list);
        list = new ArrayList<>();
        list.add(R.drawable.thumbnail_large);
        list.add(R.drawable.fb_1);
        list.add(R.drawable.thumbnail_large);
        list.add(R.drawable.fb_1);
        list.add(R.drawable.thumbnail_large);
        list.add(R.drawable.fb_1);
        list.add(R.drawable.thumbnail_large);
        list.add(R.drawable.fb_1);
        CustomListAdapter adapter = new CustomListAdapter((Activity) getContext(),temp_data,images_data);
        home_list.setAdapter(adapter);
       // home_list.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        // rvInstaFrag.addItemDecoration(new GridItemDecoration(2, 1, true, 0));
    }

}

