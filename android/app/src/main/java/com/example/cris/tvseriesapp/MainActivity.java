package com.example.cris.tvseriesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.cris.tvseriesapp.model.TVSerie;
import com.example.cris.tvseriesapp.repository.TVSerieRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TVSerieRepository tvSerieRepository; //= new TVSerieRepository();

    private TVSeriesAdapter tvSeriesAdapter;
    ListView listView;

//    public static TVSerieRepository getTvSerieRepository() {return tvSerieRepository;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvSerieRepository = new TVSerieRepository(getApplicationContext());

        setContentView(R.layout.activity_main);

        //send email button
        Button button = (Button) findViewById(R.id.proposeSeries);
        button.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this, ProposeTVSeriesActivity.class);
            startActivity(intent);
        });

        //add a tv series button
        Button addSeriesButton = (Button) findViewById(R.id.addSeries);
        addSeriesButton.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this, AddTVSeries.class);
            startActivityForResult(intent,2);
        });

        tvSeriesAdapter = new TVSeriesAdapter(this, R.layout.list_item, tvSerieRepository.getTvSeriesList() );
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(tvSeriesAdapter);
//        populateList();
        onTVSerieSelected();
    }

    private void onTVSerieSelected() {
        ListView listView = (ListView) findViewById(R.id.listView);
        final List<TVSerie> tvSeries = tvSerieRepository.getTvSeriesList();

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("id", tvSerieRepository.findOne(position).getTvsid());
                intent.putExtra("title", tvSerieRepository.findOne(position).getTitle());
                intent.putExtra("description", tvSerieRepository.findOne(position).getDescription());
                intent.putExtra("rating", tvSerieRepository.findOne(position).getRating());
//                System.out.println("DESCRIPTION" + tvSeries.get(position).getDescription() );
                intent.putExtra("image", tvSerieRepository.findOne(position).getImage());
                startActivityForResult(intent,1);
            }
        };

        listView.setOnItemClickListener(listener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1){
            if (resultCode == RESULT_OK){
                if (data.getStringExtra("what").equals("save")) {
                    int id = data.getIntExtra("id", 0);
                    String title = data.getStringExtra("title");
                    String description = data.getStringExtra("description");
                    int rating = data.getIntExtra("rating", 0);
                    int image = data.getIntExtra("image", 0);
                    TVSerie tvSerie = new TVSerie();
                    tvSerie.setTvsid(id);
                    tvSerie.setTitle(title);
                    tvSerie.setDescription(description);
                    tvSerie.setImage(image);
                    System.out.println("New tvserieee  " + tvSerie);
                    tvSerieRepository.updateTVSeries(tvSerie);
//                    System.out.println(tvSerieRepository.getTvSeriesList());
//                    tvSeriesAdapter.updateList(tvSerieRepository.getTvSeriesList());
//                System.out.println(tvSerieRepository.getTvSeriesList());

                   tvSeriesAdapter.notifyDataSetChanged();
                }
                else
                    if (data.getStringExtra("what").equals("delete")) {
                        int id = data.getIntExtra("id", 0);
                        String title = data.getStringExtra("title");
                        String description = data.getStringExtra("description");
                        int rating = data.getIntExtra("rating", 0);
                        int image = data.getIntExtra("image", 0);
                        TVSerie tvSerie = new TVSerie();
                        tvSerie.setTvsid(id);
                        tvSerie.setTitle(title);
                        tvSerie.setDescription(description);
                        tvSerie.setImage(image);
                        System.out.println("New tvserieee  " + tvSerie);
                        tvSerieRepository.delete(tvSerie);
//                        System.out.println(tvSerieRepository.getTvSeriesList());
                        tvSeriesAdapter.updateList(tvSerieRepository.getTvSeriesList());
//                        tvSeriesAdapter.notifyDataSetChanged();

                    }

                }
        }
        if(requestCode == 2){
            if(resultCode == RESULT_OK){
                String title = data.getStringExtra("title");
                String description = data.getStringExtra("description");
                int rating = data.getIntExtra("rating",0);
                tvSerieRepository.insert(title,description,rating);
                tvSeriesAdapter.updateList(tvSerieRepository.getTvSeriesList());

            }
        }
    }

//    private void populateList() {
//        TVSeriesAdapter tvSeriesAdapter;
//        tvSeriesAdapter = new TVSeriesAdapter(this, R.layout.list_item, tvSerieRepository.getTvSeriesList());
//
//        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(tvSeriesAdapter);
//    }
}
