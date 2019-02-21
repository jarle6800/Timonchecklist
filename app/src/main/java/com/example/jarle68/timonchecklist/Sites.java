package com.example.jarle68.timonchecklist;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import database.*;

public class Sites extends AppCompatActivity {
    public static final String prefs_name = "prefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites);

        TextView digi = (TextView) findViewById(R.id.site_digid);
        Linkify.addLinks(digi, Linkify.WEB_URLS );
        TextView voorziening = (TextView) findViewById(R.id.textView10);
        Linkify.addLinks(voorziening, Linkify.WEB_URLS );

        SharedPreferences settings = getSharedPreferences(prefs_name, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("key", getFromDatabase());
        editor.commit();

        button_stuff();
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

    private void addToDatabase(String email, String naam)
    {
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);
        ContentValues values = new ContentValues();
        values.put(DatabaseInfo.CheckColumn.Begeleider_email, email);
        values.put(DatabaseInfo.CheckColumn.Begeleider_naam, naam);
        dbHelper.insert(DatabaseInfo.CheckTables.MOTIVATIE
                , null, values);
    }

    public void makeToastMessage(String message)
    {
        message += " opgeslagen.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), message, duration);
        toast.show();
    }

    public String getFromDatabase()
    {


        DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);
        Cursor rs = dbHelper.query(DatabaseInfo.CheckTables.MOTIVATIE,
                new String[]{"*"}, null, null, null, null,
                null);
        if(rs.moveToNext())
        {
            rs.moveToLast();
            String name = (String) rs.getString(rs.getColumnIndex("begeleider"));
            Log.d("deze naam moeten", "name: " + name);
            return name;
        }

        return "name";

    }

    public void button_stuff()
    {

        Button email = (Button) findViewById(R.id.naam_email);
        Button terug = (Button) findViewById(R.id.sites_terug);

        terug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sites.this, MainActivity.class));
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);
                EditText editText2 = (EditText) findViewById(R.id.editText2);

                TextView bevestiging = (TextView) findViewById(R.id.mijnbegeleider);


                String invoer = editText.getText().toString();
                String invoer2 = editText2.getText().toString();
                addToDatabase(invoer, invoer2);
                makeToastMessage(invoer);
                bevestiging.setText(getFromDatabase());
                getFromDatabase();

            }
        });
    }
}
