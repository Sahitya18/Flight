package com.example.flight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

public class trial extends AppCompatActivity {

    ListView city;
    SearchView searchbar;
    List<String> citynames;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial);

        city=(ListView)findViewById(R.id.citydata);
        searchbar=(SearchView) findViewById(R.id.searchView);
        citynames.add("delhi");
        citynames.add("kolkata");
        citynames.add("amritsar");
        citynames.add("jaipur");
        citynames.add("chandigarh");
        citynames.add("raipur");
        citynames.add("bengaluru");
        citynames.add("chennai");

        adapter=new ArrayAdapter<>(this,R.layout.activity_trial,citynames);
        city.setAdapter(adapter);

        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                s=s.toLowerCase();
                if(citynames.contains(s))
                {
                    adapter.getFilter().filter(s);
                }else{
                    Toast.makeText(trial.this, "No Match found", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


    }
}