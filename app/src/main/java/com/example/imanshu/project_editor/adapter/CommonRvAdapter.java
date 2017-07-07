package com.example.imanshu.project_editor.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.imanshu.project_editor.R;
import com.example.imanshu.project_editor.activity.Editing_Screen;
import com.example.imanshu.project_editor.constant.AppConstant;

import java.util.ArrayList;

/**
 * Created by mark-42 on 31/5/17.
 */

public class CommonRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AppConstant {

    private Context mContext;
    private ArrayList<Integer> imageIds;
    private int mFragNum;

    /**
     * @param mContext mContext of the call fragment or activity
     * @param imageIds array list of the images to be inflated
     * @param mFragNum number of the fragment calling Adapter
     */

    public CommonRvAdapter(final Context mContext, final ArrayList<Integer> imageIds, final int mFragNum) {
        this.mContext = mContext;
        this.imageIds = imageIds;
        this.mFragNum = mFragNum;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view;
        switch (viewType) {

            case HOME_FRAG:
                view = LayoutInflater.from(mContext).inflate(R.layout.fragment_fragment__home, parent, false);
                return new HomeViewHolder(view);
            case INSTA_FRAG:
                view = LayoutInflater.from(mContext).inflate(R.layout.fragment_fragment__design, parent, false);
                return new InstaViewHolder(view);
//            case FB_FRAG:
//                view = LayoutInflater.from(mContext).inflate(R.layout.grid_fb_rowlayout, parent, false);
//                return new FbViewHolder(view);
//            case TWITTER_FRAG:
//                view = LayoutInflater.from(mContext).inflate(R.layout.grid_twitter_rowlayout, parent, false);
//                return new TwitterViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int id = imageIds.get(holder.getAdapterPosition());
        switch (holder.getItemViewType()) {
            case HOME_FRAG:
                HomeViewHolder homeVh = (HomeViewHolder) holder;
                homeVh.ivHomeTemp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        Intent intent = new Intent(mContext, Editing_Screen.class);
                        mContext.startActivity(intent);
                    }
                });
                homeVh.ivHomeTemp.setImageResource(id);
                break;
            case INSTA_FRAG:
                InstaViewHolder instaVh = (InstaViewHolder) holder;
                instaVh.ivTemplates.setImageResource(id);
                break;
//            case FB_FRAG:
//                FbViewHolder fbVh = (FbViewHolder) holder;
//                fbVh.ivFbTemp.setImageResource(id);
//                break;
//            case TWITTER_FRAG:
//                TwitterViewHolder twitterVh = (TwitterViewHolder) holder;
//                twitterVh.ivTwitterTemp.setImageResource(id);
//                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return imageIds.size();
    }

    @Override
    public int getItemViewType(final int position) {

        switch (mFragNum) {
            case HOME_FRAG:
                return HOME_FRAG;
            case INSTA_FRAG:
                return INSTA_FRAG;
            case FB_FRAG:
                return HOME_FRAG;
            case TWITTER_FRAG:
                return TWITTER_FRAG;
            default:
                return 0;
        }
    }

    /**
     * View holder of insta fragment
     */
    private final class InstaViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivTemplates;

        /**
         * @param itemView object holding view to be inflated
         */
        private InstaViewHolder(final View itemView) {
            super(itemView);
            ivTemplates = (ImageView) itemView.findViewById(R.id.ivInstaGridView);
        }
    }

    /**
     * View holder of Facebook fragment
     */
//    private final class FbViewHolder extends RecyclerView.ViewHolder {
//        private FbImageView ivFbTemp;
//
//        /**
//         * @param itemView object holding view to be inflated
//         */
//        private FbViewHolder(final View itemView) {
//            super(itemView);
//            ivFbTemp = (FbImageView) itemView.findViewById(R.id.ivFbGridView);
//        }
//    }

    /**
     * View holder of Twitter fragment
     */
//    private final class TwitterViewHolder extends RecyclerView.ViewHolder {
//        private TwitterImageView ivTwitterTemp;
//
//        /**
//         * @param itemView object holding view to be inflated
//         */
//        private TwitterViewHolder(final View itemView) {
//            super(itemView);
//            ivTwitterTemp = (TwitterImageView) itemView.findViewById(R.id.ivTwitterGridView);
//        }
//    }

    /**
     * View holder of Twitter fragment
     */
    private final class HomeViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivHomeTemp;

        /**
         * @param itemView object holding view to be inflated
         */
        private HomeViewHolder(final View itemView) {
            super(itemView);
            ivHomeTemp = (ImageView) itemView.findViewById(R.id.ivHomeGridView);
        }
    }


}
