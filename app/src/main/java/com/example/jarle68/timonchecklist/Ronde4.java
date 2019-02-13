package com.example.jarle68.timonchecklist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import database.DatabaseHelper;
import database.DatabaseInfo;

public class Ronde4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ronde4);

        Button terug_naar_overzicht = (Button) findViewById(R.id.stap1_terug4);
        checkBoxes();
        terug_naar_overzicht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ronde4.this, Main2Activity.class));
            }
        });
    }

    public void checkBoxes()
    {
        final CheckBox checkBox16 = findViewById(R.id.checkBox16);
        final CheckBox checkBox17 = findViewById(R.id.checkBox17);
        final CheckBox checkBox13 = findViewById(R.id.checkBox13);
        final CheckBox checkBox15 = findViewById(R.id.checkBox15);



        int[] checked = getFromDatabase();

        if(checked[0] == 1)
        {
            checkBox16.setChecked(true);
        }

        if(checked[1] == 1)
        {
            checkBox17.setChecked(true);
        }

        if(checked[2] == 1)
        {
            checkBox13.setChecked(true);
        }
        if(checked[3] == 1)
        {
            checkBox15.setChecked(true);
        }



        checkBox16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox16.isChecked())
                {
                    addToDatabaser11(1, "r4_1");
                }
                else
                    addToDatabaser11(0, "r4_1");


            }
        });

        checkBox17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox17.isChecked())
                {
                    addToDatabaser11(1, "r4_2");
                }
                else
                    addToDatabaser11(0, "r4_2");
            }
        });

        checkBox13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox13.isChecked())
                {
                    addToDatabaser11(1, "r4_3");
                }
                else
                    addToDatabaser11(0, "r4_3");
            }
        });

        checkBox15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox15.isChecked())
                {
                    addToDatabaser11(1, "r4_4");
                }
                else
                    addToDatabaser11(0, "r4_4");
            }
        });





    }

    public int[] getFromDatabase()
    {

        int[] checked = new int[4];
        checked[0] = 0;
        checked[1] = 0;
        checked[2] = 0;
        checked[3] = 0;
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);
        Cursor rs = dbHelper.query(DatabaseInfo.CheckTables.MOTIVATIE,
                new String[]{"*"}, null, null, null, null,
                null);
        if(rs.moveToNext())
        {
            rs.moveToFirst();
            checked[0]  = rs.getInt(rs.getColumnIndex("r4_1"));
            checked[1] = rs.getInt(rs.getColumnIndex("r4_2"));
            checked[2] = rs.getInt(rs.getColumnIndex("r4_3"));
            checked[3] = rs.getInt(rs.getColumnIndex("r4_4"));

            return checked;
        }


        return checked;

    }

    private void addToDatabaser11(int checked, String column)
    {
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);
        ContentValues values = new ContentValues();
        values.put(column, checked);
        dbHelper.update(DatabaseInfo.CheckTables.MOTIVATIE, values, "_id=1", null);


    }

}
