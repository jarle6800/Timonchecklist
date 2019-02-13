package com.example.jarle68.timonchecklist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import database.DatabaseHelper;
import database.DatabaseInfo;

public class MainActivity extends AppCompatActivity {

    public static final String prefs_name = "prefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button check = (Button) findViewById(R.id.checklists);
        Button web = (Button) findViewById(R.id.sites);

        SharedPreferences settings = getSharedPreferences(prefs_name, 0);
        String naam = settings.getString("key", "");

        TextView t1 = (TextView) findViewById(R.id.main_begeleider);
        t1.setText(naam);

        ProgressBar pb = (ProgressBar) findViewById(R.id.progress);

        int[] proogress = getFromDatabase();
        int count = 0;
        for(int i = 0; i < proogress.length; i++)
        {
            if(proogress[i] == 1)
            {
                count++;
            }
        }
        pb.setProgress(count);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Sites.class));
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

    public int[] getFromDatabase()
    {

        int[] checked = new int[24];
        for(int i = 0; i < 24; i++)
        {
            checked[i] = 0;
        }

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
            checked[4]  = rs.getInt(rs.getColumnIndex("r2_1"));
            checked[5] = rs.getInt(rs.getColumnIndex("r2_2"));
            checked[6] = rs.getInt(rs.getColumnIndex("r2_3"));
            checked[7] = rs.getInt(rs.getColumnIndex("r3_1"));
            checked[8]  = rs.getInt(rs.getColumnIndex("r3_2"));
            checked[9] = rs.getInt(rs.getColumnIndex("r3_3"));
            checked[10] = rs.getInt(rs.getColumnIndex("r3_4"));
            checked[11] = rs.getInt(rs.getColumnIndex("r3_5"));
            checked[12]  = rs.getInt(rs.getColumnIndex("r4_1"));
            checked[13] = rs.getInt(rs.getColumnIndex("r4_2"));
            checked[14] = rs.getInt(rs.getColumnIndex("r4_3"));
            checked[15] = rs.getInt(rs.getColumnIndex("r4_4"));
            checked[16]  = rs.getInt(rs.getColumnIndex("r5_1"));
            checked[17] = rs.getInt(rs.getColumnIndex("r5_2"));
            checked[18] = rs.getInt(rs.getColumnIndex("r5_3"));
            checked[19] = rs.getInt(rs.getColumnIndex("r6_1"));
            checked[20]  = rs.getInt(rs.getColumnIndex("r6_2"));
            checked[21] = rs.getInt(rs.getColumnIndex("r6_3"));
            checked[22] = rs.getInt(rs.getColumnIndex("r7_1"));
            checked[23] = rs.getInt(rs.getColumnIndex("r7_2"));

            return checked;
        }


        return checked;

    }
}
