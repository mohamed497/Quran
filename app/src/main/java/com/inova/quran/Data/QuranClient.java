package com.inova.quran.Data;

import android.util.Log;

import com.inova.quran.pojo.DataResponse;
import com.inova.quran.pojo.ListeningResponse;
import com.inova.quran.pojo.SurahModel;
import com.inova.quran.repo.Repo;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alaa Moaataz on 2020-02-17.
 */
public class QuranClient {
    private static final String BASE_URL = "http://api.alquran.cloud/v1/quran/";
    public  QuranInterface quranInterface;
    private static QuranClient INSTANCE;
    Retrofit retrofit;
//    Repo repo = new Repo(quranInterface);
    public QuranClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        quranInterface = retrofit.create(QuranInterface.class);
    }

    public static QuranClient getInstance(){
        if(INSTANCE == null)
            INSTANCE = new QuranClient();
        return INSTANCE;
    }


//    public Single<List<SurahModel>> getSurah(){
//        return quranInterface.getData()
//                .map(new Function<DataResponse, List<SurahModel>>() {
//                    @Override
//                    public List<SurahModel> apply(DataResponse dataResponse) throws Exception {
//                        return dataResponse.surahs;
//                    }
//                }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

    public Single<List<SurahModel>> getSurah(){
        return quranInterface.getData().map(new Function<ListeningResponse, List<SurahModel>>() {
            @Override
            public List<SurahModel> apply(ListeningResponse listeningResponse) throws Exception {
                if(listeningResponse != null){
//                    return listeningResponse.DataObject.surahs;
                    Log.d("zxc","zz : ");
                }
                else{
                    Log.d("zxc","zzzz : ");

                }
                return listeningResponse.data.surahs;
            }
        });
    }

    public QuranInterface getApi() {
        return retrofit.create(QuranInterface.class);
    }

}
