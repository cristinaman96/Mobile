package com.example.cris.tvseriesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Cris on 10/19/2017.
 */

public class AddTVSeries extends AppCompatActivity{

    NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker numberPicker, int i, int i1) {
            Toast.makeText(AddTVSeries.this, "select number " + numberPicker.getValue(),Toast.LENGTH_SHORT);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tvseries);

        NumberPicker np = findViewById(R.id.ratingPicker);
        np.setMinValue(1);
        np.setMaxValue(10);
        np.setOnValueChangedListener(onValueChangeListener);

    }

    public void addTVSeries(View view) {

//        System.out.println("mare buton de add!!!");
        TextView titleView = (TextView) findViewById(R.id.tvseriesTitle);
        TextView descriptionView = (TextView) findViewById(R.id.tvseriesDescription);
        String title = titleView.getText().toString();
        String description = descriptionView.getText().toString();
        NumberPicker ratingPicker = findViewById(R.id.ratingPicker);
        Integer rating = ratingPicker.getValue();

//        int rating =

        Intent intent = new Intent();
        intent.putExtra("title", title);
        intent.putExtra("description", description);
        intent.putExtra("rating", rating);

        setResult(RESULT_OK, intent);
        finish();
    }
}
