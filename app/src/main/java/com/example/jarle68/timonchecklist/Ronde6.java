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

public class Ronde6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ronde6);
        checkBoxes();
        Button terug_naar_overzicht = (Button) findViewById(R.id.stap1_terug6);

        terug_naar_overzicht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ronde6.this, Main2Activity.class));
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
        final CheckBox checkBox14 = findViewById(R.id.checkBox14);
        final CheckBox checkBox22 = findViewById(R.id.checkBox22);
        final CheckBox checkBox21 = findViewById(R.id.checkBox21);



        int[] checked = getFromDatabase();

        if(checked[0] == 1)
        {
            checkBox14.setChecked(true);
        }

        if(checked[1] == 1)
        {
            checkBox22.setChecked(true);
        }

        if(checked[2] == 1)
        {
            checkBox21.setChecked(true);
        }



        checkBox14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox14.isChecked())
                {
                    addToDatabaser11(1, "r6_1");
                }
                else
                    addToDatabaser11(0, "r6_1");


            }
        });

        checkBox22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox22.isChecked())
                {
                    addToDatabaser11(1, "r6_2");
                }
                else
                    addToDatabaser11(0, "r6_2");
            }
        });

        checkBox21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox21.isChecked())
                {
                    addToDatabaser11(1, "r6_3");
                }
                else
                    addToDatabaser11(0, "r6_3");
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
            checked[0]  = rs.getInt(rs.getColumnIndex("r6_1"));
            checked[1] = rs.getInt(rs.getColumnIndex("r6_2"));
            checked[2] = rs.getInt(rs.getColumnIndex("r6_3"));

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
