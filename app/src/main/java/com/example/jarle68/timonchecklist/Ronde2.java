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

public class Ronde2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ronde2);

        Button terug_naar_overzicht = (Button) findViewById(R.id.stap1_terug2);

        checkBoxes();
        terug_naar_overzicht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ronde2.this, Main2Activity.class));
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
        final CheckBox checkBox6 = findViewById(R.id.checkBox6);
        final CheckBox checkBox7 = findViewById(R.id.checkBox7);
        final CheckBox checkBox8 = findViewById(R.id.checkBox8);


        int[] checked = getFromDatabase();

        if(checked[0] == 1)
        {
            checkBox6.setChecked(true);
        }

        if(checked[1] == 1)
        {
            checkBox7.setChecked(true);
        }

        if(checked[2] == 1)
        {
            checkBox8.setChecked(true);
        }



        checkBox6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox6.isChecked())
                {
                    addToDatabaser11(1, "r2_1");
                }
                else
                    addToDatabaser11(0, "r2_1");


            }
        });

        checkBox7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkBox7.isChecked())
                {
                    addToDatabaser11(1, "r2_2");
                }
                else
                    addToDatabaser11(0, "r2_2");
            }
        });

        checkBox8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox8.isChecked())
                {
                    addToDatabaser11(1, "r2_3");
                }
                else
                    addToDatabaser11(0, "r2_3");
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
            checked[0]  = rs.getInt(rs.getColumnIndex("r2_1"));
            checked[1] = rs.getInt(rs.getColumnIndex("r2_2"));
            checked[2] = rs.getInt(rs.getColumnIndex("r2_3"));
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
