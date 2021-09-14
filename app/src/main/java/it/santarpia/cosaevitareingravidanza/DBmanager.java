package it.santarpia.cosaevitareingravidanza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.CursorAdapter;

public class DBmanager {
   private DBhelper dbhelper;

    /**
     * Constructor
     * @param context context
     */
    public DBmanager(Context context) {
        dbhelper = new DBhelper(context);
    }

    /**
     * Returns user's settings
     * @return user's settings
     */
   public User findSettings() {
       Cursor crs = null;

       try {
           SQLiteDatabase db = dbhelper.getReadableDatabase();
           crs = db.query(DBstring.TBL_USER, null, null, null, null, null, null, null);

           if(crs != null && crs.moveToNext()) {
               User user = new User();
               user.setName(crs.getString(crs.getColumnIndex(DBstring.F_USER_NAME)));
               user.setToxoplasmosi(crs.getInt(crs.getColumnIndex(DBstring.F_USER_TOXOPLASMOSI)));

               Log.d("kiwi", "Find user: " + user);

               return user;
           }

           Log.d("kiwi", "User not found");

       } catch(SQLiteException sqle) {
           sqle.printStackTrace();
       }

       return null;
   }

    /**
     * Save user's settings
     */
   public void setSettings(User user) {
       SQLiteDatabase db = dbhelper.getReadableDatabase();

       ContentValues cv = new ContentValues();
       cv.put(DBstring.F_USER_NAME, user.getName());
       cv.put(DBstring.F_USER_TOXOPLASMOSI, user.isToxoplasmosi());

       try {
           db.insert(DBstring.TBL_USER, null, cv);

//           Log.d("kiwi", "Toxoplasmosis antibodies setted to: " + b);

       } catch (SQLiteException sqle) {
           sqle.printStackTrace();
       }
   }

    /**
     * Search for all lists of food
     * @return
     */
   public Cursor findFoods() {
       Cursor crs = null;

       try {
           SQLiteDatabase db = dbhelper.getReadableDatabase();
           crs = db.query(DBstring.TBL_FOOD, null, null, null, null, null, null, null);

           if(crs != null && crs.moveToNext())
               Log.d("kiwi", "Foods found");

               return crs;
       } catch(SQLiteException sqle) {
           sqle.printStackTrace();
       }

       Log.d("kiwi", "No foods found");

       return null;
   }

    /**
     * Find last num food searched
     * @param num number of foods to search
     * @return last num foods searched
     */
    public Cursor findLastFoodsSearch(int num) {
        Cursor crs = null;

        try {
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            crs = db.query(DBstring.TBL_FOOD, null,
                    DBstring.F_FOOD_TIME + "!=0", null, null, null,
                    DBstring.F_FOOD_TIME + " DESC", String.valueOf(num));

            if(crs != null && crs.moveToNext())
                Log.d("kiwi", "Foods found");

            return crs;
        } catch(SQLiteException sqle) {
            sqle.printStackTrace();
        }

        Log.d("kiwi", "No foods found");

        return null;
    }

    /**
     * Find food by name
     * @param name name of the food
     * @return food found, null otherwhise
     */
    public Cursor findFoodByName(String name) {
        Cursor crs = null;

        try {
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            crs = db.query(DBstring.TBL_FOOD, null,
                    DBstring.F_FOOD_NAME + " LIKE ? COLLATE NOCASE", new String[]{"%" + name + "%"},
                    null, null, null, null);

            if(crs != null && crs.moveToNext()) {
                do {
                    Log.d("kiwi", "Food found");

                    int id = crs.getInt(crs.getColumnIndex(DBstring.F_FOOD_ID));
                    long time = System.currentTimeMillis();

                    updateFoodTime(id, time);
                } while (crs.moveToNext());
            }

            crs.moveToFirst();

            return crs;
        } catch(SQLiteException sqle) {
            sqle.printStackTrace();
        }

        Log.d("kiwi", "No food found");

        return null;
    }

    /**
     * Find food by id
     * @param id id of the food
     * @return food found, null otherwhise
     */
    public Food findFoodById(String id) {
        Cursor crs = null;
        Food food = null;

        try {
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            crs = db.query(DBstring.TBL_FOOD, null,
                    DBstring.F_FOOD_ID + " = ?", new String[]{id},
                    null, null, null, null);

            if(crs != null && crs.moveToNext()) {
                Log.d("kiwi", "Food found");

                String name = crs.getString(crs.getColumnIndex(DBstring.F_FOOD_NAME));
                String desc = crs.getString(crs.getColumnIndex(DBstring.F_FOOD_DESCRIPTION));
                String cat = crs.getString(crs.getColumnIndex(DBstring.F_FOOD_CATEGORY));
                int list = crs.getInt(crs.getColumnIndex(DBstring.F_FOOD_LISTERIOSI));
                int toxo = crs.getInt(crs.getColumnIndex(DBstring.F_FOOD_TOXOPLASMOSI));
                int salm = crs.getInt(crs.getColumnIndex(DBstring.F_FOOD_SALMONELLOSI));
                int safe = crs.getInt(crs.getColumnIndex(DBstring.F_FOOD_SAFE));

                food = new Food(name, desc, list, toxo, salm, safe, cat);

                return food;
            }
        } catch(SQLiteException sqle) {
            sqle.printStackTrace();
        }

        Log.d("kiwi", "No food found");

        return null;
    }

    /**
     * Set toxoplasmosi's info
     * @param b 1 indicates the presence of toxoplasmosis antibodies, 0 otherwise
     */
   public void setToxoplasmosi(int b) {
       SQLiteDatabase db = dbhelper.getReadableDatabase();

       ContentValues cv = new ContentValues();
       cv.put(DBstring.F_USER_NAME, "Valeria");
       cv.put(DBstring.F_USER_TOXOPLASMOSI, b);

       try {
           db.insert(DBstring.TBL_USER, null, cv);

           Log.d("kiwi", "Toxoplasmosis antibodies setted to: " + b);

       } catch (SQLiteException sqle) {
           sqle.printStackTrace();
       }
   }

   public void insertFood(Food food) {
       SQLiteDatabase db = dbhelper.getReadableDatabase();

       ContentValues cv = new ContentValues();
       cv.put(DBstring.F_FOOD_NAME, food.getName());
       cv.put(DBstring.F_FOOD_DESCRIPTION, food.getDescription());
       cv.put(DBstring.F_FOOD_TOXOPLASMOSI, food.getToxoplasmosi());
       cv.put(DBstring.F_FOOD_LISTERIOSI, food.getListeriosi());
       cv.put(DBstring.F_FOOD_SALMONELLOSI, food.getSalmonellosi());
       cv.put(DBstring.F_FOOD_SAFE, food.getSafe());
       cv.put(DBstring.F_FOOD_CATEGORY, food.getCategory());

       try {
           db.insert(DBstring.TBL_FOOD, null, cv);

           Log.d("kiwi", "Foods updated with: " + food);

       } catch (SQLiteException sqle) {
           sqle.printStackTrace();
       }
   }

    /**
     * Return the version savaed in database
     * @return version of the database, 0 if error occurs
     */
   public int getVersion() {
       Cursor crs = null;

       try {
           SQLiteDatabase db = dbhelper.getReadableDatabase();
           crs = db.query(DBstring.TBL_USER, new String[]{DBstring.F_VERSION}, null, null, null, null, null, null);
           Log.d("kiwi", "Foods found");


           if(crs != null && crs.moveToNext()) {
               int version = crs.getInt(crs.getColumnIndex(DBstring.F_VERSION));
               Log.d("kiwi", "Version found: " + version);

               return version;
           }
       } catch(SQLiteException sqle) {
           sqle.printStackTrace();
       }

       return 0;
   }

   public void updateVersion() {
       try {
           SQLiteDatabase db = dbhelper.getReadableDatabase();
           db.execSQL("UPDATE " + DBstring.TBL_USER + " SET "
                   + DBstring.F_VERSION + "=" + DBstring.F_VERSION + "+1");
       } catch (SQLiteException sqle) {
           sqle.printStackTrace();
       }
   }

    /**
     * Update food searching time
     * @param id id of the food to upgrade
     * @param time time to set
     */
    private void updateFoodTime(int id, long time) {
        Log.d("Kiwi", "Upgrading time of food: " + id + " - " + time);
        SQLiteDatabase db = dbhelper.getReadableDatabase();

        try {
            db.execSQL("UPDATE " + DBstring.TBL_FOOD + " SET "
                    + DBstring.F_FOOD_TIME + "=" + time + " WHERE " + DBstring.F_FOOD_ID + "=" + id);
        } catch (SQLiteException sqle) {
            sqle.printStackTrace();
        }
    }
}
