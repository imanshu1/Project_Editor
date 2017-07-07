package com.example.imanshu.project_editor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imanshu.project_editor.R;

import java.util.ArrayList;


/**
 * Created by Imanshu on 7/3/2017.
 */

public class HLVAdapter extends RecyclerView.Adapter<HLVAdapter.ViewHolder> {


    ArrayList<Integer> alImage;
    Context context;

    public HLVAdapter(Context context, ArrayList<Integer> alImage) {
        super();
        this.context = context;
        this.alImage = alImage;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.inflate_background, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.background_images.setImageResource(alImage.get(i));

    }

    @Override
    public int getItemCount() {
        return alImage.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView background_images;
        public TextView tvSpecies;

        public ViewHolder(View itemView) {
            super(itemView);
            background_images = (ImageView) itemView.findViewById(R.id.background_images);

        }

    }

}
