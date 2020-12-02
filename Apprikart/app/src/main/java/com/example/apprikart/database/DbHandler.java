package com.example.apprikart.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 3;
    private static final String DB_NAME = "usersdb";
    private static final String Users = "empdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESG = "designation";
    private static final String KEY_MOBNO = "mobileno";
    private static final String KEY_ADD = "address";

    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT NOT NULL,"
                + KEY_DESG + " TEXT NOT NULL,"
                + KEY_MOBNO + " TEXT NOT NULL,"
                + KEY_ADD + " TEXT NOT NULL" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + Users);
        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
   public void insertUserDetails( String name, String designation, String mobileno,String address) {
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_DESG, designation);
        cValues.put(KEY_MOBNO, mobileno);
        cValues.put(KEY_ADD, address);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Users, null, cValues);
        db.close();
    }

    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT * FROM " + Users;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("name", cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("designation", cursor.getString(cursor.getColumnIndex(KEY_DESG)));
            user.put("mobileno", cursor.getString(cursor.getColumnIndex(KEY_MOBNO)));
            user.put("address", cursor.getString(cursor.getColumnIndex(KEY_ADD)));
            userList.add(user);
        }
        return userList;
    }

    // Get User Details based on userid
    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name,designation,mobileno,address FROM " + Users;
        Cursor cursor = db.query(Users, new String[]{KEY_NAME, KEY_DESG, KEY_MOBNO, KEY_ADD}, KEY_ID + "=?", new String[]{String.valueOf(userid)}, null, null, null, null);
        if (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("name", cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("designation", cursor.getString(cursor.getColumnIndex(KEY_DESG)));
            user.put("mobileno", cursor.getString(cursor.getColumnIndex(KEY_MOBNO)));
            user.put("address", cursor.getString(cursor.getColumnIndex(KEY_ADD)));
            userList.add(user);
        }
        return userList;
    }

    // Delete User Details
    public void DeleteUser(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Users, KEY_ID + " = ?", new String[]{String.valueOf(userid)});
        db.close();
    }

    // Update User Details
    public int UpdateUserDetails( String designation,String mobileno,String address, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_DESG, designation);
        cVals.put(KEY_MOBNO, mobileno);
        cVals.put(KEY_ADD, address);
        int count = db.update(Users, cVals, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        return count;
    }
}
