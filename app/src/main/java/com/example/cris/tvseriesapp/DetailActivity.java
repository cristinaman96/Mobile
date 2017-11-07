package com.example.cris.tvseriesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        int image = getIntent().getIntExtra("image",0);

        showDetails(title, description, image);
    }

    private void showDetails(String title, String description, int image) {
        TextView titleView = (TextView) findViewById(R.id.title);
        titleView.setText(title);
        TextView descriptionView = (TextView) findViewById(R.id.description);
        descriptionView.setText(description);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageResource(image);
    }
}
