package com.duongvh19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UserDetail extends AppCompatActivity {
    TextView tvId;
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        Bundle bundle = getIntent().getExtras();

        tvId = findViewById(R.id.tvIdDetail);
        tvName = findViewById(R.id.tvNameDetail);

        tvId.setText(bundle.getString("id"));
        tvName.setText(bundle.getString("name"));
    }
}