package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements GetRawData.OnDownloadComplete {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetRawData getRawData = new GetRawData(this);
        getRawData.execute("https://www.flickr.com/services/feeds/photos_public.gne?format=json&&nojsoncallback=1");
    }

    @Override
    public void onDownloadComplete(String s, DownloadStatus status) {
        if (status == DownloadStatus.OK) {
            Log.d(TAG, "onDownloadComplete: data is: " + s);
        } else {
            Log.e(TAG, "onDownloadComplete: failed with status: " + status);
        }
    }
}