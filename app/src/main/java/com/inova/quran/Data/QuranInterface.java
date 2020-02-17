package com.inova.quran.Data;

import com.inova.quran.pojo.DataResponse;
import com.inova.quran.pojo.ListeningResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Alaa Moaataz on 2020-02-17.
 */
public interface QuranInterface {

    @GET("quran-uthmani")
    public Single<ListeningResponse> getData();
}
