package com.ht.neighbourchat.db;

import android.content.Context;

public class DataHandler {
    private static AppDatabase appDatabase;
    private static DataHandler dataHandler;


//    @Override
//    public void onCreate() {
//        super.onCreate();
//        appDatabase = AppDatabase.getFileDatabase(this);
//        dataHandler = this;
//    }

//    @Override
//    public void onCreate( Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        appDatabase = AppDatabase.getFileDatabase(this.getApplicationContext());
//        dataHandler = this;
//    }
    private DataHandler (Context context){
        appDatabase=AppDatabase.getFileDatabase(context.getApplicationContext());
    }

    public static AppDatabase getAppDatabase(Context context) {
        getDataHandlerInstance(context);
        return appDatabase;
    }

    private static void getDataHandlerInstance(Context context) {
        if(dataHandler==null){
            dataHandler=new DataHandler(context);
        }
    }
}
