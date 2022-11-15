package com.example.flight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class cities extends AppCompatActivity {

    public static boolean citySelected;
    ListView city;
    SearchView searchbar;
    ArrayList<String> citynames;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        citySelected=false;
        setContentView(R.layout.activity_cities);
        city=(ListView)findViewById(R.id.citydata);
        searchbar=(SearchView) findViewById(R.id.searchView);
        citynames=new ArrayList<>();
        citynames.add("delhi");
        citynames.add("kolkata");
        citynames.add("amritsar");
        citynames.add("jaipur");
        citynames.add("chandigarh");
        citynames.add("raipur");
        citynames.add("bengaluru");
        citynames.add("chennai");

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,citynames);
        city.setAdapter(adapter);


        city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                firstpage.getCityName=citynames.get(i);
                Intent intent=new Intent();
                intent.putExtra("getCityName",citynames.get(i));
                setResult(RESULT_OK,intent);
                finish();
            }

        });
//        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                s=s.toLowerCase();
//                if(citynames.contains(s))
//                {
//                    adapter.getFilter().filter(s);
//                    firstpage.getCityName=s;
//                    Toast.makeText(trial.this, ""+firstpage.getCityName, Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(trial.this, "No Match found", Toast.LENGTH_SHORT).show();
//                }
//                return false;
//            }
//        });

    }
}