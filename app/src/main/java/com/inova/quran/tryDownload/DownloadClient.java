package com.inova.quran.tryDownload;

import com.inova.quran.Data.QuranInterface;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alaa Moaataz on 2020-02-18.
 */
public class DownloadClient {

    private static final String BASE_URL = "http://cdn.alquran.cloud/media/audio/";
    public QuranInterface quranInterface;
    private static DownloadClient INSTANCE;
    Retrofit retrofit;

    public DownloadClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
//        quranInterface = retrofit.create(QuranInterface.class);
//        quranInterface = ServiceGenerator

    }

    public static DownloadClient getInstance(){
        if (INSTANCE == null){
            INSTANCE = new DownloadClient();
        }
        return INSTANCE;
    }

    public Single<ResponseBody> downloading(){
        return quranInterface.downloadAudio().map(new Function<ResponseBody, ResponseBody>() {
            @Override
            public ResponseBody apply(ResponseBody responseBody) throws Exception {
                return responseBody;
            }
        });
    }

    public QuranInterface getApi() {
        return retrofit.create(QuranInterface.class);
    }



}
