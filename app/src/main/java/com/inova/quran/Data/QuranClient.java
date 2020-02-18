package com.inova.quran.Data;

import android.util.Log;

import com.inova.quran.pojo.AyahModel;
import com.inova.quran.pojo.DataResponse;
import com.inova.quran.pojo.ListeningResponse;
import com.inova.quran.pojo.SurahModel;
import com.inova.quran.repo.Repo;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
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
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
//                .interceptors().add(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    return onOnIntercept(chain);
//                  }
//                })
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
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
                    Log.d("zxc","SIZE : : "+listeningResponse.data.surahs.size());
                }
                else{
                    Log.d("zxc","zzzz : ");

                }
                return listeningResponse.data.surahs;
            }
        });
    }

//    public Single<ResponseBody> downloading(){
//        return quranInterface.downloadAudio().map(new Function<ResponseBody, ResponseBody>() {
//            @Override
//            public ResponseBody apply(ResponseBody responseBody) throws Exception {
//                return responseBody;
//            }
//        });
//    }

//    public Single<List<AyahModel>> getAyah(){
//        return quranInterface.getData().map(new Function<ListeningResponse, List<AyahModel>>() {
//            @Override
//            public List<AyahModel> apply(ListeningResponse listeningResponse) throws Exception {
//
//                return listeningResponse.data.surahs.get(0).ayahs;
//            }
//        });
//    }

    public QuranInterface getApi() {
        return retrofit.create(QuranInterface.class);
    }

//    private Response onOnIntercept(Interceptor.Chain chain) throws IOException {
//        try {
//            Response response = chain.proceed(chain.request());
//            String content = UtilityMethods.convertResponseToString(response);
//            Log.d(TAG, lastCalledMethodName + " - " + content);
//            return response.newBuilder().body(ResponseBody.create(response.body().contentType(), content)).build();
//        }
//        catch (SocketTimeoutException exception) {
//            exception.printStackTrace();
//            if(listener != null)
//                listener.onConnectionTimeout();
//        }
//
//        return chain.proceed(chain.request());
//    }

}
