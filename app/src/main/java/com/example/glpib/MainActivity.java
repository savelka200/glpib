package com.example.glpib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void dlpiauth(View view) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // All your networking logic
                // should be here
                // Create URL

                URL glpiEndpoint = null;
                try {
                    glpiEndpoint = new URL("https://it.glpi.borauto.ru/apirest.php/initSession/");
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                HttpsURLConnection myConnection =
                        null;
                try {
                    myConnection = (HttpsURLConnection) glpiEndpoint.openConnection();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                myConnection.setRequestProperty("User-Agent", "glpi-rest");
                myConnection.setRequestProperty("app_token", "uUazFMtj3iji0M7li4CF0Kvs8COKVmY3L6EY2zfT");
                myConnection.setRequestProperty("Authorization", "user_token bC2OedM6NkzIwlue71V1EYZdsIPvA6CAYtAPWNv0");
                myConnection.setRequestProperty("Content-Type", "application/json");
                try {
                    if (myConnection.getResponseCode() == 200){

                    }
                    else{

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }
}