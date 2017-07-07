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
 * Created by Imanshu on 7/6/2017.
 */

public class Share_Adapter extends RecyclerView.Adapter<Share_Adapter.ViewHolder> {



    Context context;

    ArrayList<Integer> alImage;
    String[] nam;

    public Share_Adapter(Context context, ArrayList<Integer> alImage, String[] nam) {
        this.context = context;
        this.alImage = alImage;
        this.nam = nam;
    }

    @Override
    public Share_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.inflate_share, viewGroup, false);
        Share_Adapter.ViewHolder viewHolder = new Share_Adapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Share_Adapter.ViewHolder holder, int position) {
        holder.background_images.setImageResource(alImage.get(position));
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
            background_images = (ImageView) itemView.findViewById(R.id.share_icon);
            tvSpecies=(TextView)itemView.findViewById(R.id.share_text) ;

        }

    }

}