package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EmployeeAdapter.OnClickListener {
    private RecyclerView rvData;
    private List<Employee> mEmployeeList = new ArrayList<>();
    private EmployeeAdapter mEmployeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvData = findViewById(R.id.rvData);
        rvData.setLayoutManager(new LinearLayoutManager(this));

        setData();
        mEmployeeAdapter = new EmployeeAdapter(getApplicationContext(), mEmployeeList, this);
        rvData.setAdapter(mEmployeeAdapter);

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
}