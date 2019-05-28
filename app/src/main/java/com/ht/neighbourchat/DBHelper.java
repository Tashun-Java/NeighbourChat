package com.ht.neighbourchat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ht.neighbourchat.models.Message;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper dbHelper;
    public static final String USER_ID = "uid";
    public static final String USER_NAME = "uname";
    private static final String USER_DATABASE = "UserDB";
    public static final String USER_TABLE = "Users";
    public static String MESSAGE_ID;
    public static String MESSAGE_CONTENT;
    private final Context context;


    @Override
    public void onCreate(SQLiteDatabase db) {
//create tables
        String createUserTable =
                "CREATE TABLE `user` " +
                        "(" +
                        "`uid` VARCHAR PRIMARY KEY, " +
                        "`username` VARCHAR, " +
                        "`lastseen` VARCHAR" +
                        ") ";
        db.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table \Name of tables ");
//        onCreate(db);
    }
//    private static final String DATABASE_CREATE=

//    private DatabaseHelper

    private DBHelper(Context context) {
        super(context, USER_DATABASE, null, 1);
        this.context = context;
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }

    public void isUid(String uId) {
// search the name in the User Database
    }

    public void createUser(String uId) {
        // add an entry to the user table and create a new table to store messages
    }

    public void persistMessage(String uId, Message message) {
        //persist the messge in the table, having the uId name
    }

    public void getMessages(String uId) {
        // get a certain amount of messages
        // get messages model in to a list and return
    }

    public void getMessages(String uId, int number) {
        // get a "number" amount of messages return a list
    }
}

