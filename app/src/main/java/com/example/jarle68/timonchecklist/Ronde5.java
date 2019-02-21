package com.example.jarle68.timonchecklist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import database.DatabaseHelper;
import database.DatabaseInfo;

public class Ronde5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ronde5);

        Button terug_naar_overzicht = (Button) findViewById(R.id.stap1_terug5);
        checkBoxes();
        terug_naar_overzicht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ronde5.this, Main2Activity.class));
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
        final CheckBox checkBox19 = findViewById(R.id.checkBox19);
        final CheckBox checkBox20 = findViewById(R.id.checkBox20);
        final CheckBox checkBox18 = findViewById(R.id.checkBox18);



        int[] checked = getFromDatabase();

        if(checked[0] == 1)
        {
            checkBox19.setChecked(true);
        }

        if(checked[1] == 1)
        {
            checkBox20.setChecked(true);
        }

        if(checked[2] == 1)
        {
            checkBox18.setChecked(true);
        }



        checkBox19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox19.isChecked())
                {
                    addToDatabaser11(1, "r5_1");
                }
                else
                    addToDatabaser11(0, "r5_1");


            }
        });

        checkBox20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox20.isChecked())
                {
                    addToDatabaser11(1, "r5_2");
                }
                else
                    addToDatabaser11(0, "r5_2");
            }
        });

        checkBox18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox18.isChecked())
                {
                    addToDatabaser11(1, "r5_3");
                }
                else
                    addToDatabaser11(0, "r5_3");
            }
        });





    }

    public int[] getFromDatabase()
    {

        int[] checked = new int[4];
        checked[0] = 0;
        checked[1] = 0;
        checked[2] = 0;
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);
        Cursor rs = dbHelper.query(DatabaseInfo.CheckTables.MOTIVATIE,
                new String[]{"*"}, null, null, null, null,
                null);
        if(rs.moveToNext())
        {
            rs.moveToFirst();
            checked[0]  = rs.getInt(rs.getColumnIndex("r5_1"));
            checked[1] = rs.getInt(rs.getColumnIndex("r5_2"));
            checked[2] = rs.getInt(rs.getColumnIndex("r5_3"));

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
