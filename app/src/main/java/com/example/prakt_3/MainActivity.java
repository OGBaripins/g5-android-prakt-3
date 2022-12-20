package com.example.prakt_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button save_button;
    private Button to_2nd;
    private TextView display_text;
    private EditText text_input;
    private Spinner theme_spinner;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save_button = (Button) findViewById(R.id.read_prefs);
        to_2nd = (Button) findViewById(R.id.to_2nd_button);
        display_text = (TextView) findViewById(R.id.display_text);
        text_input = (EditText) findViewById(R.id.text_input);
        theme_spinner = (Spinner) findViewById(R.id.theme_spinner);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display_text.setText(text_input.getText().toString());
                saveData();
            }
        });

        theme_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                if(item.toString().equals("Dark Theme")){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        loadData();
        updateViews();
    }

    public void goToSecond(View v){
        Intent myIntent = new Intent(this, MainActivity2.class);
        startActivity(myIntent);
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, display_text.getText().toString());
        editor.apply();

//        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, null);
    }

    public void updateViews(){
        display_text.setText(text);
    }
}