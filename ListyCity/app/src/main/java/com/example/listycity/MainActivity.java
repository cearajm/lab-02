package com.example.listycity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText input;
    ImageView enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        input = findViewById(R.id.input);
        enter = findViewById(R.id.add);

        String[] cities = {"edmonton", "vancouver", "moscow", "sydney", "berlin", "vienna", "tokyo", "beijing", "osaka", "new delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);



        // delete from list using a long click
        cityList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String name = dataList.get(position);
                Log.d("city", name);
                removeCity(position);
                return false;
            }
        });

        // add to list by clicking the icon on the right
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = input.getText().toString();
                if (!text.isEmpty()) {
                    addCity(text);
                    input.setText("");
                }
            }
        });

        // add to list with enter key
        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    String text = input.getText().toString();
                    if (!text.isEmpty()) {
                        addCity(text);
                        input.setText("");
                        return true;
                    }
                }
                return false;
            }
        });
    }


    // add and remove from list

    public void addCity(String city) {
        dataList.add(city);
//        cityList.setAdapter(cityAdapter);
        cityAdapter.notifyDataSetChanged();
        }

    public void removeCity(int city) {
        dataList.remove(city);
        cityAdapter.notifyDataSetChanged();
    }

    }