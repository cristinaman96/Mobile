package com.example.cris.tvseriesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    int id;
    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        id = getIntent().getIntExtra("id",0);
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
//        System.out.println("description " + description);
        image = getIntent().getIntExtra("image",0);

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


    public void save(View view) {
        System.out.println("mare buton de save!!!");
        TextView titleView = (TextView) findViewById(R.id.title);
        TextView descriptionView = (TextView) findViewById(R.id.description);
        String title = titleView.getText().toString();
        String description = descriptionView.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("description", description);
        intent.putExtra("image", image);

        setResult(RESULT_OK, intent);
        finish();

    }
}
