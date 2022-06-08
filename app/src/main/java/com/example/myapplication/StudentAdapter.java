package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StudentAdapter extends ArrayAdapter {

    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<Student> students;

    public StudentAdapter(@NonNull Context context, int resource, List<Student> students) {
        super(context, resource);
        this.students = students;
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(layoutResource, parent, false);

        TextView tvName = (TextView) view.findViewById(R.id.tv_Name);
        TextView tvGroup = (TextView) view.findViewById(R.id.tv_Group);
        TextView tvAge = (TextView) view.findViewById(R.id.tv_Age);

        Student currentStudent = students.get(position);

        tvName.setText(currentStudent.getName());
        tvGroup.setText(currentStudent.getGroup());
        tvAge.setText(Integer.toString(currentStudent.getAge()));

        return view;
    }
}
