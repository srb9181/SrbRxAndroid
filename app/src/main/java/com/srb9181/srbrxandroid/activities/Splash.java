package com.srb9181.srbrxandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.srb9181.srbrxandroid.R;

import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(() -> {
            try {
                sleep(4000);
                startActivity(new Intent(Splash.this,MainActivity.class));
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
