package khaliliyoussef.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import static android.provider.BaseColumns._ID;
import static khaliliyoussef.habittracker.data.HabitContract.HabitEntry.COLUMN_DATE;
import static khaliliyoussef.habittracker.data.HabitContract.HabitEntry.COLUMN_DURATION;
import static khaliliyoussef.habittracker.data.HabitContract.HabitEntry.COLUMN_LOCATION;
import static khaliliyoussef.habittracker.data.HabitContract.HabitEntry.COLUMN_NAME;
import static khaliliyoussef.habittracker.data.HabitContract.HabitEntry.TABLE_NAME;

/**
 *
 * Created by Khalil on 9/26/2017.
 */


class DatabaseAdapter {

    HabitDbHelper habitDbHelper;

    public DatabaseAdapter(Context context) {
        habitDbHelper = new HabitDbHelper(context);
    }

    public long insertData(String location, int duration, String date) {

        SQLiteDatabase sqLiteDatabase = habitDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LOCATION, location);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_DURATION, duration);
        long id = sqLiteDatabase.insert(COLUMN_NAME, null, contentValues);
        return id;
    }

    public Cursor read() {
        SQLiteDatabase sqLiteDatabase = habitDbHelper.getWritableDatabase();
        String[] columns = {COLUMN_NAME, COLUMN_DATE, COLUMN_DURATION, COLUMN_LOCATION};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns, null, null, null, null, null, null);

      return cursor;
    }

    public class HabitDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "habittracker.db";
        private static final int DATABASE_VERSION = 1;


        public HabitDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                // Create a String that contains the SQL statement to create the pets table
                String SQL_CREATE_TABLE_HABITS = "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_DATE + " TEXT NOT NULL, " +
                        COLUMN_DURATION + " INTEGER DEFAULT 0, " +
                        COLUMN_LOCATION + " TEXT NOT NULL)";

                // Execute the SQL statement
                db.execSQL(SQL_CREATE_TABLE_HABITS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("ALTER TABLE IF EXIST" + TABLE_NAME);
            onCreate(db);
        }
    }
}
