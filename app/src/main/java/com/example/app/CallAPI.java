package com.example.app;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.NetworkOnMainThreadException;
import android.util.JsonReader;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class CallAPI extends AsyncTask<String,Void, String>{

    private String result_data ="";

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL("http://cpslab.jejunu.ac.kr/apis/busstation" );
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(100000);
            conn.setRequestMethod("GET");
            StringBuilder sb = new StringBuilder();
            if( conn.getResponseCode() == HttpURLConnection.HTTP_OK ) {
                InputStream is = conn.getInputStream();
                //InputStreamReader sr = new InputStreamReader(responsebody, "UTF-8");
                //JsonReader jr = new JsonReader(sr);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line;
                while((line=reader.readLine())!=null){
                    sb.append(line);
                }
                result_data = sb.toString();
            }
            else{
                return "Error";
            }

            conn.disconnect();
            return result_data;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
