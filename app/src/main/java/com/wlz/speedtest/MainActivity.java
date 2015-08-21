package com.wlz.speedtest;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button btn_hourly;
    Button btn_average;
    Button btn_all;
    BenchMark benchMark;
    EditText date_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        benchMark = new BenchMark(getApplicationContext());
        textView = (TextView) findViewById(R.id.view_txt);
        textView.setText("Hello, it is a speedtest benchmark program!");
        btn_hourly = (Button) findViewById(R.id.btn_hourly);
        btn_average = (Button) findViewById(R.id.btn_avrage);
        btn_all = (Button) findViewById(R.id.btn_all);
        btn_hourly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                benchMark.hourly();
            }
        });

        btn_average.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAverage();
            }

        });

        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAllResult();
            }
        });
    }

    public void showAllResult(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_layout)
                .setMessage("Enter date to search result")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RealmResults<BenchMarkObject> results = benchMark.getAllResultsWithDate(date_txt.getText().toString());
                        textView.setText("");
                        textView.append("Hour \t\t Pings \t\t\t Downloads \t\t\t Uploads \n\n");
                        for(int i = 0; i<results.size();i++){
                            textView.append(results.get(i).getHour()+" \t\t "+results.get(i).getPing()+" \t\t "+results.get(i).getDownload_speed()+" \t\t "+results.get(i).getUpload_speed()+"\n");
                        }
                    }
                }).show();
        date_txt = (EditText) alertDialog.findViewById(R.id.date_txt);
    }

    public void showAverage(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_layout)
                .setMessage("Enter date to search result")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText("Average Results \n\n Ping ="
                                + String.valueOf(benchMark.getAveragePing(date_txt.getText().toString()))
                                + "\n Download = " + String.valueOf(benchMark.getAverageDownload(date_txt.getText().toString()))
                                + "\n Upload = " + String.valueOf(benchMark.getAverageUpload(date_txt.getText().toString())));

                    }
                }).show();
        date_txt = (EditText) alertDialog.findViewById(R.id.date_txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            CSVWriter csvWriter = new CSVWriter(getApplicationContext());
            csvWriter.writeCsvFile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
