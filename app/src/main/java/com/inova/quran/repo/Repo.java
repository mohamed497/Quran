package com.inova.quran.repo;

import com.inova.quran.Data.QuranInterface;
import com.inova.quran.pojo.ListeningResponse;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alaa Moaataz on 2020-02-17.
 */
public class Repo {

    private QuranInterface quranInterface;
//    QuranClient quranClient = new QuranClient();

    public Repo(QuranInterface quranInterface) {
        this.quranInterface = quranInterface;
    }

    public Single<ListeningResponse> getSurah(){
        return quranInterface.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
//        return quranInterface.getData().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//        return quranInterface.getData()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
    }
}
