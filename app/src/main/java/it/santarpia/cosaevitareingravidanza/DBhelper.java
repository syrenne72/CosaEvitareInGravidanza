package it.santarpia.cosaevitareingravidanza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

class DBhelper extends SQLiteOpenHelper {
    public static final String DBNAME = "ORARIDILAVORO";
    public int version;

    public DBhelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    /**
     * Create database for foods and user's informations
     * @param db database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String q= "CREATE TABLE " + DBstring.TBL_FOOD +
                " ( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBstring.F_FOOD_TOXOPLASMOSI + " INT," +
                DBstring.F_FOOD_LISTERIOSI + " INT," +
                DBstring.F_FOOD_SALMONELLOSI + " INT," +
                DBstring.F_FOOD_SAFE + " INT, " +
                DBstring.F_FOOD_DESCRIPTION + " STRING," +
                DBstring.F_FOOD_CATEGORY + " STRING," +
                DBstring.F_FOOD_NAME + " STRING, " +
                DBstring.F_FOOD_TIME + " LONG)";

        db.execSQL(q);

        q= "CREATE TABLE " + DBstring.TBL_USER +
            "( " + DBstring.F_USER_NAME + " STRING PRIMARY KEY," +
                DBstring.F_VERSION + " INTEGER DEFAULT 1," +
                DBstring.F_USER_TOXOPLASMOSI + " INT)";

        db.execSQL(q);

        Log.i("kiwi", this.getClass().getSimpleName() + ": database created");
    }

//    private static final String DATABASE_ALTER_TEAM_1 = "ALTER TABLE "
//            + DBstring.TBL_FOOD + " ADD COLUMN "
//            + DBstring.F_FOOD_TIME + " INTEGER DEFAULT 0";

    /**
     * Upgrades current database
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("kiwi", "DB updated from version: " + sqLiteDatabase.getVersion());

//        if(sqLiteDatabase.getVersion() < 2)
//            sqLiteDatabase.execSQL(DATABASE_ALTER_TEAM_1);
    }

    public int getVersion() {
        return version;
    }
}

