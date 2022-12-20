package com.example.prakt_3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button back = findViewById(R.id.back_btn);
        Button read_prefs = findViewById(R.id.read_prefs);

        back.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, MainActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // or just 'finish()';
            startActivity(myIntent);
            Log.e("MY", "Clicked on back");
        });

        read_prefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_prefs();
            }
        });
    }

    public void check_prefs(){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);

        String saved_text = sharedPreferences.getString(MainActivity.TEXT, null);

        if(saved_text == null || saved_text.equals("")){
            Toast.makeText(this, "Preferences are empty", Toast.LENGTH_SHORT).show();
        }
    }


}