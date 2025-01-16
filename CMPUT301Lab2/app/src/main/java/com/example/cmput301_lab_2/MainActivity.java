package com.example.cmput301_lab_2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    //References:
    String selectedText = "";
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addCityB;

    Button deleteCityB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        addCityB = (Button) findViewById(R.id.addCityButton);
        deleteCityB= (Button) findViewById(R.id.deleteCityButton);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        //ArrayAdapters are intermediaries between two different sources. It used to display data on the UI
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        //Button Functionalities

        //addCityButton and confirmButton
        EditText EnterC = findViewById(R.id.DataAcceptBox);
        Button Confirm = findViewById(R.id.confirmButton);

        addCityB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change visibility of
                EnterC.setVisibility(View.VISIBLE);
                Confirm.setVisibility(View.VISIBLE);
            }
        });

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_c = EnterC.getText().toString();
                dataList.add(new_c); //Added the new City
                cityAdapter.notifyDataSetChanged();
                EnterC.setVisibility(View.GONE);
                EnterC.setText("");
                Confirm.setVisibility(View.GONE);
            }
        });

        //deleteCityButton
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedText = dataList.get(position);
        });

        deleteCityB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Listen for chosen city and remove the text chosen
                if (!selectedText.isEmpty()){
                    dataList.remove(selectedText);
                    cityAdapter.notifyDataSetChanged();
                }

            }
        });

    }
}