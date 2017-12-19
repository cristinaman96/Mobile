package com.example.cris.tvseriesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Cris on 10/19/2017.
 */

public class AddTVSeries extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tvseries);
    }

    public void addTVSeries(View view) {

//        System.out.println("mare buton de add!!!");
        TextView titleView = (TextView) findViewById(R.id.tvseriesTitle);
        TextView descriptionView = (TextView) findViewById(R.id.tvseriesDescription);
        String title = titleView.getText().toString();
        String description = descriptionView.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("title", title);
        intent.putExtra("description", description);

        setResult(RESULT_OK, intent);
        finish();
    }
}
