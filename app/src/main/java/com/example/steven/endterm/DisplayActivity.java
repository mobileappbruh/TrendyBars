package com.example.steven.endterm;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class DisplayActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nameText, addressText, descText;
    Button monBtn, tueBtn, wedBtn, thurBtn, friBtn, satBtn, sunBtn;
    Venue myVenue;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        name = b.getString("name");

        nameText = (EditText)findViewById(R.id.nameText);
        addressText = (EditText)findViewById(R.id.addressText);
        Button[]  btnArr = {(Button)findViewById(R.id.monBtn), (Button)findViewById(R.id.tueBtn),
                (Button)findViewById(R.id.wedBtn), (Button)findViewById(R.id.thurBtn),
                (Button)findViewById(R.id.friBtn), (Button)findViewById(R.id.satBtn),
                (Button)findViewById(R.id.sunBtn)};
        descText = (EditText)findViewById(R.id.descText);

        for (int i=0; i<btnArr.length; i++)
        {
            btnArr[i].setOnClickListener(this);
        }

        try{
            myVenue = readFile();
            nameText.setText(myVenue.getName());
            addressText.setText(myVenue.getAddress());
            int[] countArr = myVenue.getArrGoing();
            for (int i=0; i<btnArr.length; i++){
                btnArr[i].setText(countArr[i]);
            }
            descText.setText(myVenue.getDescription());
        }catch(IOException e) {
            nameText.setText("Error in Database");
            descText.setText("Sorry, there seems to be a problem loading the data.\nPlease try again.");
        }
    }

    private Venue readFile() throws IOException {
        Venue v = null;
        InputStream iS = getResources().getAssets().open("venues.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
        String line = reader.readLine();
        while (line != null) {
            v = processRecord(line);
            if (v.getName().equals(name) )
            {
                break;
            }
            line = reader.readLine();
        }
        iS.close();
        return v;
    }

    public void writeToFile(String text) throws IOException{
        FileOutputStream stream = new FileOutputStream("venues.txt");
        try {
            stream.write("text-to-write".getBytes());
        } finally {
            stream.close();
        }


        //InputStream iS = getResources().getAssets().open("vvv.txt");
        //OutputStream oS = getResources().

        //FileWriter fw = new FileWriter(f, true);
        //BufferedWriter bw = new BufferedWriter(oS);

        //bw.newLine();
        //bw.write(text);
        //bw.close();
    }

    public void onClick(View v)
    {
        if(v==monBtn)
            myVenue.incrementDay(Weekday.MONDAY);
        else if(v==tueBtn)
            myVenue.incrementDay(Weekday.TUESDAY);
        else if(v==wedBtn)
            myVenue.incrementDay(Weekday.WEDNESDAY);
        else if(v==thurBtn)
            myVenue.incrementDay(Weekday.THURSDAY);
        else if(v==friBtn)
            myVenue.incrementDay(Weekday.FRIDAY);
        else if(v==satBtn)
            myVenue.incrementDay(Weekday.SATURDAY);
        else
            myVenue.incrementDay(Weekday.SUNDAY);
    }

    private Venue processRecord(String rec) {
        String[] fieldArray = new String[12];
        int j=0;
        int prevComma=0;
        for (int i=0; i<rec.length(); i++)
        {
            int comma = rec.indexOf(',',i);
            fieldArray[j++] = rec.substring(prevComma + 1, comma);
            prevComma = comma;
            i = comma+1;
        }

        Venue v = new Venue(fieldArray[0], fieldArray[1], fieldArray[2], fieldArray[3], fieldArray[4]);
        /*
        There were problems with this conversion and I caould not find the issue

        try{

            int[] days = new int[7];
            for (int i=0; i<days.length; i++) {
                int x = (int)Integer.parseInt(fieldArray[5+i]);
                days[i] = x;
            }
            v.setDayGoing(days);
        }catch(Exception e){
            System.out.println("###1 "+v.toString());
            return null;
        }
        System.out.println("###2 "+v.toString());
        */
        return v;
    }



}
