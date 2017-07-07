package com.example.imanshu.project_editor.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imanshu.project_editor.R;
import com.example.imanshu.project_editor.view.RoundedImageView;

public class Fragment_Profile extends Fragment {

RoundedImageView home_roundedimage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View myInflatedView = inflater.inflate(R.layout.fragment_fragment__profile, container,false);

       // return inflater.inflate(R.layout.fragment_fragment__profile, container, false);

        home_roundedimage=(RoundedImageView)myInflatedView.findViewById(R.id.home_roundedimage);
        home_roundedimage.setImageResource(R.drawable.im);

        return myInflatedView;

    }



}
