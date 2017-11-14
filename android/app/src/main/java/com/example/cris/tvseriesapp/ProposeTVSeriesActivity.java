package com.example.cris.tvseriesapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProposeTVSeriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propose_tvseries);
    }

    public void proposeTVSeries(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.name);

        if (nameEditText.getText().toString().matches("")) {
            Toast.makeText(this, "You did not enter any name!", Toast.LENGTH_SHORT).show();
            return;
        }

        EditText tvSeriesEditText = (EditText) findViewById(R.id.tvSeriesName);
        if (tvSeriesEditText.getText().toString().matches("")) {
            Toast.makeText(this, "You did not enter any TV series title!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"man.cristina96@yahoo.com"});
        intent.putExtra(intent.EXTRA_SUBJECT, "New TV Series");
        intent.putExtra(intent.EXTRA_TEXT, "There is a request to add a new TV series!");
        try {
            startActivity(Intent.createChooser(intent, "Send mail"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ProposeTVSeriesActivity.this, "There has been an error!!", Toast.LENGTH_SHORT);
        }
    }
}
