package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tvResult);

        DownLoadData downLoadData = new DownLoadData();
        downLoadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml");
    }

    private class DownLoadData extends AsyncTask<String,Void,String> {
        private static final String TAG = "DownLoadData";

        @Override
        protected String doInBackground(String... strings) {
            String rssFeed = "";
            rssFeed = downLoadURL (strings[0]);
            return rssFeed;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e(TAG, "onPostExecute: " + s);
            tvResult.setText(s);
        }

        private String downLoadURL (String urlPath) {
            StringBuilder xmlResult = new StringBuilder();

            try {
                URL url = new URL(urlPath);
                try {
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    int response = connection.getResponseCode();
                    Log.e(TAG, "downLoadURL: responseCode " + response);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String line="";
                    while ((line = bufferedReader.readLine()) != null) {
                        xmlResult.append(line + "\n");
                    }
                    bufferedReader.close();
                    return xmlResult.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}