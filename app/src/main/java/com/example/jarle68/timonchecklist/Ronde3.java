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

public class Ronde3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ronde3);

        Button terug_naar_overzicht = (Button) findViewById(R.id.stap1_terug3);
        checkBoxes();
        terug_naar_overzicht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ronde3.this, Main2Activity.class));
            }
        });
    }

    public void checkBoxes()
    {
        final CheckBox checkBox5 = findViewById(R.id.checkBox5);
        final CheckBox checkBox9 = findViewById(R.id.checkBox9);
        final CheckBox checkBox10 = findViewById(R.id.checkBox10);
        final CheckBox checkBox12 = findViewById(R.id.checkBox12);
        final CheckBox checkBox11 = findViewById(R.id.checkBox11);


        int[] checked = getFromDatabase();

        if(checked[0] == 1)
        {
            checkBox5.setChecked(true);
        }

        if(checked[1] == 1)
        {
            checkBox9.setChecked(true);
        }

        if(checked[2] == 1)
        {
            checkBox10.setChecked(true);
        }
        if(checked[3] == 1)
        {
            checkBox12.setChecked(true);
        }

        if(checked[4] == 1)
        {
            checkBox11.setChecked(true);
        }


        checkBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox5.isChecked())
                {
                    addToDatabaser11(1, "r3_1");
                }
                else
                    addToDatabaser11(0, "r3_1");


            }
        });

        checkBox9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox9.isChecked())
                {
                    addToDatabaser11(1, "r3_2");
                }
                else
                    addToDatabaser11(0, "r3_2");
            }
        });

        checkBox10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox10.isChecked())
                {
                    addToDatabaser11(1, "r3_3");
                }
                else
                    addToDatabaser11(0, "r3_3");
            }
        });

        checkBox12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox12.isChecked())
                {
                    addToDatabaser11(1, "r3_4");
                }
                else
                    addToDatabaser11(0, "r3_4");
            }
        });


        checkBox11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox11.isChecked())
                {
                    addToDatabaser11(1, "r3_5");
                }
                else
                    addToDatabaser11(0, "r3_5");
            }
        });




    }

    public int[] getFromDatabase()
    {

        int[] checked = new int[5];
        checked[0] = 0;
        checked[1] = 0;
        checked[2] = 0;
        checked[3] = 0;
        checked[4] = 0;
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);
        Cursor rs = dbHelper.query(DatabaseInfo.CheckTables.MOTIVATIE,
                new String[]{"*"}, null, null, null, null,
                null);
        if(rs.moveToNext())
        {
            rs.moveToFirst();
            checked[0]  = rs.getInt(rs.getColumnIndex("r3_1"));
            checked[1] = rs.getInt(rs.getColumnIndex("r3_2"));
            checked[2] = rs.getInt(rs.getColumnIndex("r3_3"));
            checked[3] = rs.getInt(rs.getColumnIndex("r3_4"));
            checked[4] = rs.getInt(rs.getColumnIndex("r3_5"));

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
