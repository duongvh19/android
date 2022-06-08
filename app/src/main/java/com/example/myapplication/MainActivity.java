package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvData;
    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvData = findViewById(R.id.lv_data);

        Student s1 = new Student("John Hill", "Group_A", 25);
        Student s2 = new Student("Cavil Do", "Group_B", 32);
        Student s3 = new Student("Jack Thomas", "Group_C", 27);
        Student s4 = new Student("Tom Holly", "Group_A", 22);
        Student s5 = new Student("Jimmy Duck", "Group_B", 30);
        Student s6 = new Student("Snow White", "Group_C", 26);


        students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        students.add(s6);


        StudentAdapter adapter = new StudentAdapter(MainActivity.this, R.layout.list_item, students);
        lvData.setAdapter(adapter);

        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Click item " + position, Toast.LENGTH_SHORT).show();
            }
        });

        lvData.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Remove item " + position, Toast.LENGTH_SHORT).show();
                students.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }
}