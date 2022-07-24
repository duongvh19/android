package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;


public class EmployeeDetail extends AppCompatActivity {
    TextView tvId;
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        tvId = findViewById(R.id.tvIdDetail);
        tvName = findViewById(R.id.tvNameDetail);

        Bundle bundle = getIntent().getExtras();

        tvId.setText(bundle.getString("id"));
        tvName.setText(bundle.getString("name"));

    }
}