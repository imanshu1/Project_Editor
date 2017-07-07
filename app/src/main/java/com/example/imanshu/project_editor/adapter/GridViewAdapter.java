package com.example.imanshu.project_editor.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.imanshu.project_editor.R;

/**
 * Created by Imanshu on 7/7/2017.
 */

public class GridViewAdapter  extends ArrayAdapter<String> {

    private final Activity context;
    private final Integer[] imgid;

    public GridViewAdapter(Activity context, Integer[]  imgid) {
        super(context, R.layout.inflate_grid);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.imgid=imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.inflate_grid, null,true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        imageView.setImageResource(imgid[position]);
        return rowView;

    };
}