package com.srb9181.srbrxandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.srb9181.srbrxandroid.R;

public class Flag extends AppCompatActivity {
    ImageView imageView ;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);

        String url = getIntent().getExtras().getString("url");
        imageView = (ImageView) findViewById(R.id.thumbnail);
        Glide.with(this).load(url).into(imageView);

        imageView.setOnClickListener(v -> {
            supportFinishAfterTransition();
            onBackPressed();
        });

    }


}
