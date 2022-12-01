package com.example.sqlite_14sl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonOlvasas;
    private Button buttonRogzitesre;
    private Button buttonModositasra;
    private Button buttonTorlesre;
    private TextView textViewLista;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        buttonOlvasas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor adatok = adatbazis.listaz();
                if (adatok.getCount() == 0) {
                    Toast.makeText(MainActivity.this,
                            "Nincs az adatbázisban bejegyzés", Toast.LENGTH_SHORT).show();
                } else {
                    StringBuilder bobTheBuilder = new StringBuilder();
                    while (adatok.moveToNext()) {
                        bobTheBuilder.append("ID: ").append(adatok.getInt(0));
                        bobTheBuilder.append(System.lineSeparator());
                        bobTheBuilder.append("Vezetéknév: ").append(adatok.getString(1));
                        bobTheBuilder.append(System.lineSeparator());
                        bobTheBuilder.append("Keresztnév: ").append(adatok.getString(2));
                        bobTheBuilder.append(System.lineSeparator());
                        bobTheBuilder.append("Jegy: ").append(adatok.getInt(3));
                        bobTheBuilder.append(System.lineSeparator());
                        bobTheBuilder.append(System.lineSeparator());
                    }
                    textViewLista.setText(bobTheBuilder.toString());
                }
            }
        });
        buttonRogzitesre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RogzitesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init() {
        buttonOlvasas = findViewById(R.id.buttonOlvasas);
        buttonRogzitesre = findViewById(R.id.buttonRogzitesre);
        buttonModositasra = findViewById(R.id.buttonModositasra);
        buttonTorlesre = findViewById(R.id.buttonTorlesre);
        textViewLista = findViewById(R.id.textViewLista);
        adatbazis = new DBHelper(MainActivity.this);
    }
}