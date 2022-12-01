package com.example.sqlite_14sl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RogzitesActivity extends AppCompatActivity {

    private EditText editTextVezNev;
    private EditText editTextKerNev;
    private EditText editTextJegy;
    private Button buttonRogzites;
    private Button buttonVisszaRog;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rogzites);
        init();
        buttonRogzites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vezeteknev = editTextVezNev.getText().toString().trim();
                String keresztnev = editTextKerNev.getText().toString().trim();
                String jegyString = editTextJegy.getText().toString().trim();

                if (vezeteknev.isEmpty() ||
                        keresztnev.isEmpty() ||
                        jegyString.isEmpty()) {
                    Toast.makeText(RogzitesActivity.this,
                            "Minden mező kitöltése kötelező", Toast.LENGTH_LONG).show();
                } else {
                    int jegy = Integer.parseInt(jegyString);
                    if (adatbazis.rogzites(vezeteknev, keresztnev, jegy)) {
                        Toast.makeText(RogzitesActivity.this,
                                "Sikeres rögzítés", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RogzitesActivity.this,
                                "Sikertelen rögzítés", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        buttonVisszaRog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RogzitesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init() {
        editTextJegy = findViewById(R.id.editTextJegy);
        editTextKerNev = findViewById(R.id.editTextKerNev);
        editTextVezNev = findViewById(R.id.editTextVeznev);
        buttonRogzites = findViewById(R.id.buttonRogzites);
        buttonVisszaRog = findViewById(R.id.buttonVisszaRog);
        adatbazis = new DBHelper(RogzitesActivity.this);
    }
}