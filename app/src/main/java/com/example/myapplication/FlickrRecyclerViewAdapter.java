package com.example.myapplication;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrRecyclerViewAdapter.FlickrImageViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private List<Photo> mPhotoList;
    private Context mContext;

    public FlickrRecyclerViewAdapter(Context context, List<Photo> photoList) {
        this.mContext = context;
        this.mPhotoList = photoList;
    }

    @NonNull
    @Override
    public FlickrImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: new view requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse,parent,false);
        return new FlickrImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlickrImageViewHolder holder, int position) {
        if (mPhotoList == null || mPhotoList.size() == 0) {
            holder.thumbnail.setImageResource(R.drawable.placeholder);
            holder.title.setText(R.string.empty_photo);
        } else {
            Photo photoItem = mPhotoList.get(position);

            Picasso.with(mContext).load(photoItem.getImage())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.thumbnail);
            holder.title.setText(photoItem.getTitle());

        }
    }

     void loadPhoto (List<Photo> photoList) {
        mPhotoList = photoList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ((mPhotoList != null) && (mPhotoList.size() != 0)) ? mPhotoList.size() : 1 ;
    }


    static class FlickrImageViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = "FlickrImageViewHolder";
        ImageView thumbnail = null;
        TextView title = null;


        public FlickrImageViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
