package com.example.jarle68.timonchecklist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.content.SharedPreferences;

import database.DatabaseHelper;
import database.DatabaseInfo;

public class Ronde1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ronde1);

        Button terug_naar_overzicht = (Button) findViewById(R.id.stap1_terug);
        checkBoxes();

        terug_naar_overzicht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ronde1.this, Main2Activity.class));
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.contact) {

            Intent intent = new Intent(this,Contact.class);
            this.startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);

    }

    public void checkBoxes()
    {
        final CheckBox checkBox = findViewById(R.id.checkBox);
        final CheckBox checkBox2 = findViewById(R.id.checkBox2);
        final CheckBox checkBox3 = findViewById(R.id.checkBox3);
        final CheckBox checkBox4 = findViewById(R.id.checkBox4);

        int[] checked = getFromDatabase();

        if(checked[0] == 1)
        {
            checkBox.setChecked(true);
        }

        if(checked[1] == 1)
        {
            checkBox2.setChecked(true);
        }

        if(checked[2] == 1)
        {
            checkBox3.setChecked(true);
        }

        if(checked[3] == 1)
        {
            checkBox4.setChecked(true);
        }


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox.isChecked())
                {
                    addToDatabaser11(1, "r1_1");
                }
                else
                    addToDatabaser11(0, "r1_1");


            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox2.isChecked())
                {
                    addToDatabaser11(1, "r1_2");
                }
                else
                    addToDatabaser11(0, "r1_2");

            }
        });

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox3.isChecked())
                {
                    addToDatabaser11(1, "r1_3");
                }
                else
                    addToDatabaser11(0, "r1_3");

            }
        });

        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox4.isChecked())
                {
                    addToDatabaser11(1, "r1_4");
                }
                else
                    addToDatabaser11(0, "r1_4");

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
            checked[0]  = rs.getInt(rs.getColumnIndex("r1_1"));
            checked[1] = rs.getInt(rs.getColumnIndex("r1_2"));
            checked[2] = rs.getInt(rs.getColumnIndex("r1_3"));
            checked[3] = rs.getInt(rs.getColumnIndex("r1_4"));
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
