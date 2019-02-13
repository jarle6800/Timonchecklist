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

public class Ronde7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ronde7);

        Button terug_naar_overzicht = (Button) findViewById(R.id.stap1_terug7);
        checkBoxes();
        terug_naar_overzicht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ronde7.this, Main2Activity.class));
            }
        });
    }

    public void checkBoxes()
    {
        final CheckBox checkBox23 = findViewById(R.id.checkBox23);
        final CheckBox checkBox24 = findViewById(R.id.checkBox24);



        int[] checked = getFromDatabase();

        if(checked[0] == 1)
        {
            checkBox23.setChecked(true);
        }

        if(checked[1] == 1)
        {
            checkBox24.setChecked(true);
        }




        checkBox23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox23.isChecked())
                {
                    addToDatabaser11(1, "r7_1");
                }
                else
                    addToDatabaser11(0, "r7_1");


            }
        });

        checkBox24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox24.isChecked())
                {
                    addToDatabaser11(1, "r7_2");
                }
                else
                    addToDatabaser11(0, "r7_2");
            }
        });






    }

    public int[] getFromDatabase()
    {

        int[] checked = new int[4];
        checked[0] = 0;
        checked[1] = 0;
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);
        Cursor rs = dbHelper.query(DatabaseInfo.CheckTables.MOTIVATIE,
                new String[]{"*"}, null, null, null, null,
                null);
        if(rs.moveToNext())
        {
            rs.moveToFirst();
            checked[0]  = rs.getInt(rs.getColumnIndex("r7_1"));
            checked[1] = rs.getInt(rs.getColumnIndex("r7_2"));

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
