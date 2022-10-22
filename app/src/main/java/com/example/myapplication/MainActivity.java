package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EmployeeAdapter.OnClickListener {
    private RecyclerView rvData;
    private List<Employee> mEmployeeList = new ArrayList<>();
    private EmployeeAdapter mEmployeeAdapter;
    private Button btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvData = findViewById(R.id.rvData);
        rvData.setLayoutManager(new LinearLayoutManager(this));

        setData();
        mEmployeeAdapter = new EmployeeAdapter(getApplicationContext(), mEmployeeList, this);
        rvData.setAdapter(mEmployeeAdapter);

        btnRemove = findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem();
            }
        });

    }

    private void setData() {
        for (int i= 0; i< 20; i++) {
            String id = String.valueOf(i);
            String name = "Employee " + i;
            Employee e = new Employee(id, name);
            mEmployeeList.add(e);
        }
    }

    @Override
    public void onClickListener(Employee e) {
        Intent intent = new Intent(this, EmployeeDetail.class);

        Bundle bundle = new Bundle();
        bundle.putString("id", e.getId());
        bundle.putString("name", e.getName());

        intent.putExtras(bundle);

        startActivity(intent);
    }

    private void removeItem() {
        if (mEmployeeList == null || mEmployeeList.size() == 0) {
            return;
        }
        View rowView = rvData.getLayoutManager().findViewByPosition(0);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),
                android.R.anim.slide_out_right);
        anim.setDuration(300);
        rowView.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mEmployeeList != null && mEmployeeList.size() > 0) {
                    mEmployeeList.remove(0);
                    mEmployeeAdapter.notifyDataSetChanged();
                }

            }
        }, anim.getDuration());

    }
}