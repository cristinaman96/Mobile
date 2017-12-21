package com.example.cris.tvseriesapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

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

//        int noOfAs = 0;
//        int noOfBs = 0;
//        int noOfCs = 0;
//        for (int i=0; i<description.length(); i++){
//            if(description.charAt(i) == 'a' || description.charAt(i) == 'A')
//            {
//                noOfAs++;
//            }else
//            if(description.charAt(i) == 'b' || description.charAt(i) == 'B')
//            {
//                noOfBs++;
//            }else
//             if(description.charAt(i) == 'c' || description.charAt(i) == 'C')
//            {
//                 noOfCs++;
//            }
//        }
//
//        PieChart pieChart = (PieChart) findViewById(R.id.chart);
//        pieChart.setUsePercentValues(true);
//
//        ArrayList<PieEntry> values = new ArrayList<>();
//        values.add(new PieEntry(noOfAs,1));
//        values.add(new PieEntry(noOfBs,2));
//        values.add(new PieEntry(noOfCs,3));
//        values.add(new PieEntry(description.length() - noOfAs - noOfBs - noOfCs,4));
//
//        PieDataSet dataSet = new PieDataSet(values, "Description Analyse");
//
//        ArrayList<String> values2 = new ArrayList<>();
//        values2.add("NoOfAs");
//        values2.add("NoOfBs");
//        values2.add("NoOfCs");
//        values2.add("NoOfOtherLetters");
//
//        PieData data = new PieData();
//
//        pieChart.setData(data);
//        Legend legend = pieChart.getLegend();
//        legend.setEnabled(false);

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
//        System.out.println("mare buton de save!!!");
        TextView titleView = (TextView) findViewById(R.id.title);
        TextView descriptionView = (TextView) findViewById(R.id.description);
        String title = titleView.getText().toString();
        String description = descriptionView.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("what", "save");
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("description", description);
        intent.putExtra("image", image);

        setResult(RESULT_OK, intent);
        finish();

    }

    public void delete(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this TV series?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TextView titleView = (TextView) findViewById(R.id.title);
                        TextView descriptionView = (TextView) findViewById(R.id.description);
                        String title = titleView.getText().toString();
                        String description = descriptionView.getText().toString();

                        Intent intent = new Intent();
                        intent.putExtra("what", "delete");
                        intent.putExtra("id", id);
                        intent.putExtra("title", title);
                        intent.putExtra("description", description);
                        intent.putExtra("image", image);

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
                );
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
