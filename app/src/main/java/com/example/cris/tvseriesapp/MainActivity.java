package com.example.cris.tvseriesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cris.tvseriesapp.model.TVSerie;
import com.example.cris.tvseriesapp.repository.TVSerieRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static TVSerieRepository tvSerieRepository = new TVSerieRepository();

    public static TVSerieRepository getTvSerieRepository() {return tvSerieRepository;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateList();
        onTVSerieSelected();
    }

    private void onTVSerieSelected() {
        ListView listView = (ListView) findViewById(R.id.listView);
        final List<TVSerie> tvSeries = tvSerieRepository.getTvSeriesList();

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("title", tvSeries.get(position).getTitle());
                intent.putExtra("description", tvSeries.get(position).getDescription());
                intent.putExtra("image", tvSeries.get(position).getImage());
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(listener);

    }

    private void populateList() {
        TVSeriesAdapter tvSeriesAdapter;
        tvSeriesAdapter = new TVSeriesAdapter(this, R.layout.list_item, tvSerieRepository.getTvSeriesList());

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(tvSeriesAdapter);
    }
}
