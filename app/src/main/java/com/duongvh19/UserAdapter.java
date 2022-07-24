package com.duongvh19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> mListUser;
    private OnItemClickListener mListener;
    private Context mContext;

    public UserAdapter(Context context, List<User> listUser, OnItemClickListener listener) {
        mListUser = listUser;
        mListener = listener;
        mContext = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        if (mListUser != null) {
            User user = mListUser.get(position);
            holder.tvId.setText(user.getId());
            holder.tvName.setText(user.getName());
            Picasso.with(mContext).load("https://live.staticflickr.com//65535//52236851021_725b9c61c0_m.jpg")
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.ivUser);

            holder.bind(user, mListener);
        }
    }

    @Override
    public int getItemCount() {
        if (mListUser != null) {
            return mListUser.size();
        }
        return 0;
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        TextView tvName;
        RelativeLayout item;
        ImageView ivUser;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = (TextView) itemView.findViewById(R.id.tvIdUser);
            tvName = (TextView) itemView.findViewById(R.id.tvNameUser);
            item = itemView.findViewById(R.id.itemView);
            ivUser = itemView.findViewById(R.id.ivUser);
        }

        void bind(User u, OnItemClickListener listener) {
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(u);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(User item);
    }
}
