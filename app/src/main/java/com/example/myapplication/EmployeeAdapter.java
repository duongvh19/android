package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>{

    private Context mContext;
    private List<Employee> mEmployeeList;
    private OnClickListener mListener;

    public EmployeeAdapter(Context context, List<Employee> employeeList, OnClickListener listener) {
        mContext = context;
        mEmployeeList = employeeList;
        mListener = listener;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        if(mEmployeeList != null) {
            Employee e = mEmployeeList.get(position);

            holder.tvId.setText(e.getId());
            holder.tvName.setText(e.getName());

            holder.bind(e, mListener);
        }
    }

    @Override
    public int getItemCount() {
        if (mEmployeeList != null) {
            return mEmployeeList.size();
        }
        return 0;
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itemData;
        TextView tvId;
        TextView tvName;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemData =(LinearLayout) itemView.findViewById(R.id.itemData);
            tvId = (TextView) itemView.findViewById(R.id.tvId);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
        }

        void bind (Employee e, OnClickListener listener) {
            itemData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickListener(e);
                }
            });
        }
    }

    interface OnClickListener {
        void onClickListener (Employee e);
    }
}
