package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by mjboere on 6-12-2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static SQLiteDatabase mSQLDB;
    private static DatabaseHelper mInstance;
    public static final String dbName = "motivatie.db";
    public static final int dbVersion = 24;

    private DatabaseHelper(Context ctx) {
        super(ctx, dbName, null, dbVersion);
    }


    public static synchronized DatabaseHelper getHelper (Context ctx){
        if (mInstance == null){
            mInstance = new DatabaseHelper(ctx);
            mSQLDB = mInstance.getWritableDatabase();
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE motivatie (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, begeleider TEXT," +
                " email_begeleider TEXT, r1_1  INTEGER, r1_2  INTEGER, r1_3  INTEGER," +
                " r1_4  INTEGER, r2_1  INTEGER, r2_2  INTEGER, r2_3  INTEGER," +
                "r3_1 INTEGER, r3_2 INTEGER, r3_3 INTEGER, r3_4 INTEGER, r3_5 INTEGER," +
                "r4_1 INTEGER, r4_2 INTEGER, r4_3 INTEGER, r4_4 INTEGER," +
                "r5_1 INTEGER, r5_2 INTEGER, r5_3 INTEGER," +
                "r6_1 INTEGER, r6_2 INTEGER, r6_3 INTEGER," +
                "r7_1 INTEGER, r7_2 INTEGER)"

        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseInfo.CheckTables.MOTIVATIE);
        onCreate(db);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version ){
        super(context,name,factory, version);
    }

    public void insert(String table, String nullColumnHack, ContentValues values){
        mSQLDB.insert(table, nullColumnHack, values);
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectArgs, String groupBy, String having, String orderBy){
        return mSQLDB.query(table, columns, selection, selectArgs, groupBy, having, orderBy);
    }

    public void update(String table, ContentValues cv, String where, String[] args )
    {
        mSQLDB.update(table, cv, where, args);
    }

}
