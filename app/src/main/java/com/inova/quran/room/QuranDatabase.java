package com.inova.quran.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

/**
 * Created by Alaa Moaataz on 2020-02-24.
 */


@Database(entities = {AyahRoomModel.class}, version = 1, exportSchema = false)
public abstract class QuranDatabase extends RoomDatabase {

    private static QuranDatabase INSTANCE;

    public abstract QuranDoa quranDoa();

    static QuranDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (QuranDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), QuranDatabase.class, "movie_db")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final QuranDoa quranDoa;

        public PopulateDbAsync(QuranDatabase instance) {
            quranDoa = instance.quranDoa();
        }

        @Override
        protected Void doInBackground(Void... voids) {


            //update
            //delete
            //\\ //\\
//            quranDoa.insertAyah();

            return null;
        }
    }
}
