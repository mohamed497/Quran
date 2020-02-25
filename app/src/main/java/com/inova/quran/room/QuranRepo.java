package com.inova.quran.room;

import android.app.Application;
import android.os.AsyncTask;

import com.inova.quran.AyahActivity;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Created by Alaa Moaataz on 2020-02-24.
 */
public class QuranRepo {

    private QuranDoa quranDoa;
    private LiveData<List<AyahRoomModel>> mAllQuran;

    public QuranRepo(Application application) {

        QuranDatabase db = QuranDatabase.getDatabase(application);
        quranDoa = db.quranDoa();
        //\\//\\
//        mAllQuran = quranDoa.getAyah();
        mAllQuran = quranDoa.getDownloadingAyah(true,true);
    }

    LiveData<List<AyahRoomModel>> getAllAyahs() {
        return mAllQuran;
    }



    public void deleteAyah()  {
        new deleteAyahAsyncTask(quranDoa).execute();
    }

    public void insert (List<AyahRoomModel> movie) {
        new insertAsyncTask(quranDoa).execute(movie);
    }

    public void update () {
        new updateAyahAsyncTask(quranDoa).execute();
    }

    private class insertAsyncTask extends AsyncTask<List<AyahRoomModel>, Void, Void> {

        private QuranDoa mAsyncTaskDao;

        insertAsyncTask(QuranDoa dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(List<AyahRoomModel>... lists) {
            mAsyncTaskDao.insertAyah(lists[0]);
            return null;
        }
    }

    private static class deleteAyahAsyncTask extends AsyncTask<Void, Void, Void> {
        private QuranDoa mAsyncTaskDao;

        deleteAyahAsyncTask(QuranDoa dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //\\ //\\ //\\ boolean state
//            mAsyncTaskDao.deleteAyah();
            return null;
        }
    }
    private static class updateAyahAsyncTask extends AsyncTask<Void, Void, Void> {
        private QuranDoa mAsyncTaskDao;
        AyahActivity ayahActivity = new AyahActivity();

        updateAyahAsyncTask(QuranDoa dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //\\ //\\ //\\ boolean state
            if (ayahActivity.ayahRoomModels != null)
            mAsyncTaskDao.updateAyah(true,ayahActivity.ayahRoomModels.get(0).number);
            return null;
        }
    }
}
