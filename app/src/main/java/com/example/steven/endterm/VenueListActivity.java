package com.example.steven.endterm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;

public class VenueListActivity extends AppCompatActivity {

    private static String type = "";
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        type = b.getString("type");
        setContentView(R.layout.activity_venue_list);

        lv = (ListView) findViewById(R.id.venueList);
        //lv.setOnItemClickListener(this);
        lv.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                String value = (String)adapter.getItemAtPosition(position);

                Intent intent = new Intent(VenueListActivity.this, DisplayActivity.class);
                intent.putExtra("name", value);
                startActivity(intent);
            }
        });

        // Setting up list
        List<String> venueList = new ArrayList<String>();
        try
        {
            readFile(venueList);
        } catch(IOException e)
        {
            Toast.makeText(getApplicationContext(),"There is a problem with the database",Toast.LENGTH_LONG);
        }
        // putting the list of venues into the list on screen
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, venueList );

        lv.setAdapter(arrayAdapter);
    }
/*
    public void onItemClick(View v)
    {
        Intent intent = new Intent(this, DisplayActivity.class);
        intent.putExtra("name", v.getId());
        startActivity(intent);
    }
*/

    private void readFile(List<String> lst) throws IOException {
        InputStream iS = getResources().getAssets().open("venues.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
        String line = reader.readLine();
        while (line != null) {
            String[] info = processRecord(line);
            if (info[0].equals(type))
                lst.add(info[1]);
            line = reader.readLine();
        }
        iS.close();
        //fr.close();
    }

    private String[] processRecord(String rec) {
        int comma1 = rec.indexOf(',');
        int comma2 = rec.indexOf(',',comma1+1);
        int comma3 = rec.indexOf(',', comma2+1);
        String[] data = new String[2];
        String type = rec.substring(comma1 + 1, comma2);
        String name =  rec.substring(comma2 + 1, comma3);
        String[] arr = {type, name};
        return arr;
    }
}
