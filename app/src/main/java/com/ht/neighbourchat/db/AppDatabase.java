
package com.ht.neighbourchat.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ht.neighbourchat.models.Message;
import com.ht.neighbourchat.models.UserDoa;

@Database(entities = {UserDoa.class, Message.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDoa appDoa();
//    public static Logger logger;

    private static AppDatabase INSTANCE;

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                    AppDatabase.class).fallbackToDestructiveMigration().addCallback(
                            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull final SupportSQLiteDatabase db) {
                    super.onCreate(db);

                }
            }).build();
//            logger.info(AppDatabase.class+": AppDatabase Created, in Memory Database");
        }
        return INSTANCE;
    }

    public static AppDatabase getFileDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    "messenger").fallbackToDestructiveMigration().addCallback(
                            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull final SupportSQLiteDatabase db) {
                    super.onCreate(db);

                }
            })
                    .build();
//            logger.info(AppDatabase.class+": AppDatabase Created, in File Database");

        }
        return INSTANCE;
    }

    public static void destroyIntstance() {
        INSTANCE = null;
//        logger.info(AppDatabase.class+": Instance was Destoryed");

    }
}
