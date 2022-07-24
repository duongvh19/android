package com.duongvh19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.OnItemClickListener {
    private RecyclerView lvUser;
    private UserAdapter mUserAdapter;
    private List<User> mListUser = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvUser = findViewById(R.id.lvUser);
        lvUser.setLayoutManager(new LinearLayoutManager(this));

        setData();
        mUserAdapter = new UserAdapter(this, mListUser, this);
        lvUser.setAdapter(mUserAdapter);

    }

    private void setData() {

        for (int i = 0; i < 20; i++) {
            String id = String.valueOf(i);
            String name = "User " + i;
            User u = new User(id, name);

            mListUser.add(u);
        }
    }

    @Override
    public void onItemClick(User item) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, UserDetail.class);

        bundle.putString("id", item.getId());
        bundle.putString("name", item.getName());

        intent.putExtras(bundle);
        startActivity(intent);
    }
}