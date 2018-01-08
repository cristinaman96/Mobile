package com.example.cris.tvseriesapp;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cris.tvseriesapp.model.TVSerie;
import com.example.cris.tvseriesapp.repository.TVSerieRepository;
import com.example.cris.tvseriesapp.utils.Db;
import com.github.mikephil.charting.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TVSerieRepository tvSerieRepository; //= new TVSerieRepository();

    private TVSeriesAdapter tvSeriesAdapter;

    ListView listView;

    private DatabaseReference tvSeriesDatabase;
    private DatabaseReference usersDatabase;
    private FirebaseDatabase database;

    private static Context context;

    private String userRole;
//    public static TVSerieRepository getTvSerieRepository() {return tvSerieRepository;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        tvSerieRepository = new TVSerieRepository(getApplicationContext());

//        setContentView(R.layout.activity_main);


        database = Db.getDatabase();
        tvSeriesDatabase = database.getReference().child("tvseries");
        tvSeriesDatabase.keepSynced(true);
        usersDatabase = database.getReference().child("users");


//        usersDatabase.child("8L1pBsD5NCe6RuitXezKi1RssLx2").setValue("admin");
//        usersDatabase.child("A8k0dU06kOVxAs4jVDf0SXOz1cA2").setValue("user");

        tvSerieRepository = new TVSerieRepository(tvSeriesDatabase);

        context = getApplicationContext();

        usersDatabase.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userRole = dataSnapshot.getValue(String.class);
                System.out.println("the role!!!" +userRole);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        tvSeriesDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Count ", " " + dataSnapshot.getChildrenCount());
                if(dataSnapshot.getChildrenCount() > 0){
                    tvSerieRepository.clear();
                    for (DataSnapshot data : dataSnapshot.getChildren()){
                        TVSerie tvSerie = data.getValue(TVSerie.class);
                        tvSerieRepository.addTVSerie(tvSerie);
                        Log.i("Data: ", tvSerie.toString());
                    }
                    tvSerieRepository.setId(tvSerieRepository.getTvSeriesList().get(tvSerieRepository.getTvSeriesList().size()-1).getTvsid() + 1);
                    System.out.println("!!!!!!!!!1" + tvSerieRepository.getTvSeriesList().toString());
                    tvSeriesAdapter.updateList(tvSerieRepository.getTvSeriesList());
                }
                else {
                    tvSerieRepository.setId(1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error: " + databaseError.getCode());
            }
        });

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
            if (userRole.equals("user")){
                Toast.makeText(this, "Only the admin users can add new TV series!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (userRole.equals("admin")){
                Intent intent = new Intent(MainActivity.this, AddTVSeries.class);
                startActivityForResult(intent, 2);
            }
        });

        Button signOutButton = (Button) findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                if(FirebaseAuth.getInstance().getCurrentUser() == null){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                }
            }
        });
        System.out.println("55555555555555555555555555" +  tvSerieRepository.getTvSeriesList().toString());


        tvSeriesAdapter = new TVSeriesAdapter(this, R.layout.list_item, tvSerieRepository.getTvSeriesList() );
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(tvSeriesAdapter);
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$" +  tvSerieRepository.getTvSeriesList().toString());
        onTVSerieSelected();
    }

    private void onTVSerieSelected() {
        ListView listView = (ListView) findViewById(R.id.listView);
        final List<TVSerie> tvSeries = tvSerieRepository.getTvSeriesList();

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("role", userRole);
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
    public static void showRepositoryUpdate(){
        Toast.makeText(context, "The repository was updated!", Toast.LENGTH_SHORT).show();
    }

//    private void populateList() {
//        TVSeriesAdapter tvSeriesAdapter;
//        tvSeriesAdapter = new TVSeriesAdapter(this, R.layout.list_item, tvSerieRepository.getTvSeriesList());
//
//        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(tvSeriesAdapter);
//    }
}
