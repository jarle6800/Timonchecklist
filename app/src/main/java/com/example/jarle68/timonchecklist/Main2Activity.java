package com.example.jarle68.timonchecklist;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import database.DatabaseHelper;
import database.DatabaseInfo;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button terug = (Button) findViewById(R.id.terug);

        set_progress_text();

        terug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, MainActivity.class));
            }
        });
        set_buttons();
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

    private void set_progress_text()
    {
        TextView prog1 = (TextView) findViewById(R.id.prog1);
        TextView prog = findViewById(R.id.prog);
        TextView prog2 = findViewById(R.id.prog2);
        TextView prog3 = findViewById(R.id.prog3);
        TextView prog4 = findViewById(R.id.prog4);
        TextView prog5 = findViewById(R.id.prog5);
        TextView prog6 = findViewById(R.id.prog6);

        int[] temp = getFromDatabase();
        int count1 = 0;
        int count = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;

        for(int i = 0; i < 4; i++)
        {
            if(temp[i] == 1)
            {
                count1++;
            }
        }

        for(int i = 4; i < 7; i++)
        {
            if(temp[i] == 1)
            {
                count++;
            }
        }

        for(int i = 7; i < 12; i++)
        {
            if(temp[i] == 1)
            {
                count2++;
            }
        }

        for(int i = 12; i < 16; i++)
        {
            if(temp[i] == 1)
            {
                count3++;
            }
        }

        for(int i = 16; i < 19; i++)
        {
            if(temp[i] == 1)
            {
                count4++;
            }
        }

        for(int i = 19; i < 22; i++)
        {
            if(temp[i] == 1)
            {
                count5++;
            }
        }

        for(int i = 22; i < 24; i++)
        {
            if(temp[i] == 1)
            {
                count6++;
            }
        }


        prog1.setText("(" + count1 + "/4)");
        prog.setText("(" + count + "/3)");
        prog2.setText("(" + count2 + "/5)");
        prog3.setText("(" + count3 + "/4)");
        prog4.setText("(" + count4 + "/3)");
        prog5.setText("(" + count5 + "/3)");
        prog6.setText("(" + count6 + "/2)");
    }


    private void set_buttons()
    {
        check_begeleider();

        TextView stap1 = findViewById(R.id.stap1);
        TextView stap2 = findViewById(R.id.stap2);
        TextView stap3 = findViewById(R.id.stap3);
        TextView stap4 = findViewById(R.id.stap4);
        TextView stap5 = findViewById(R.id.stap5);
        TextView stap6 = findViewById(R.id.stap6);
        TextView stap7 = findViewById(R.id.stap7);

        stap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Main2Activity.this, Ronde1.class));
            }
        });

        stap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Main2Activity.this, Ronde2.class));
            }
        });

        stap3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Main2Activity.this, Ronde3.class));
            }
        });

        stap4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Main2Activity.this, Ronde4.class));
            }
        });

        stap5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Main2Activity.this, Ronde5.class));
            }
        });

        stap6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Main2Activity.this, Ronde6.class));
            }
        });

        stap7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Main2Activity.this, Ronde7.class));
            }
        });

    }

    private void check_begeleider()
    {

        DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);
        Cursor rs = dbHelper.query(DatabaseInfo.CheckTables.MOTIVATIE,
                new String[]{"*"}, null, null, null, null,
                null);

        if(!(rs.moveToNext()))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
            builder.setMessage("voer AUB eerst een begleider in");

            builder.setPositiveButton("begeleider invoeren", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Main2Activity.this, Sites.class));
                }
            });

            builder.setNegativeButton("terug", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();

        }


    }
}
