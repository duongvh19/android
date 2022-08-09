package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GetFlickrJsonData.OnDataAvailable {
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private FlickrRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        GetRawData getRawData = new GetRawData(this);
//        getRawData.execute("https://www.flickr.com/services/feeds/photos_public.gne?format=json&&nojsoncallback=1");

//        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView = findViewById(R.id.rvPhoto);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FlickrRecyclerViewAdapter(this, new ArrayList<Photo>());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: starts");
        GetFlickrJsonData getFlickrJsonData = new GetFlickrJsonData(this,"https://www.flickr.com/services/feeds/photos_public.gne","en-us", true );
//        getFlickrJsonData.executeOnSameThread("android,nougat");
        getFlickrJsonData.execute("android,nougat");
    }

    @Override
    public void onDataAvailable(List<Photo> data, DownloadStatus status) {
        if (status == DownloadStatus.OK) {
            Log.d(TAG, "onDownloadComplete: data is: " + data);
            adapter.loadPhoto(data);
        } else {
            Log.e(TAG, "onDownloadComplete: failed with status: " + status);
        }
    }

//    @Override
//    public void onDownloadComplete(String s, DownloadStatus status) {
//        if (status == DownloadStatus.OK) {
//            Log.d(TAG, "onDownloadComplete: data is: " + s);
//        } else {
//            Log.e(TAG, "onDownloadComplete: failed with status: " + status);
//        }
//    }
}