/*
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity_Backup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView numbers = (TextView) findViewById(R.id.numbers);
        TextView family = (TextView) findViewById(R.id.family);
        TextView phrase = (TextView) findViewById(R.id.phrases);
        TextView color = (TextView) findViewById(R.id.colors);

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity_Backup.this, NumbersActivity.class);
                startActivity(i);
            }
        });

        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity_Backup.this, FamilyActivity.class);
                startActivity(i);
            }
        });

        phrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity_Backup.this, PhrasesActivity.class);
                startActivity(i);
            }
        });

        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity_Backup.this, ColorsActivity.class);
                startActivity(i);
            }
        });

    }

}
*/
