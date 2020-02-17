package com.inova.quran.ui;

import android.util.Log;

import com.inova.quran.Data.QuranClient;
import com.inova.quran.Data.QuranInterface;
import com.inova.quran.pojo.ListeningResponse;
import com.inova.quran.pojo.SurahModel;
import com.inova.quran.repo.Repo;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alaa Moaataz on 2020-02-17.
 */
public class QuranViewModel extends ViewModel {

    public MutableLiveData<List<SurahModel>> surahMutableLiveData = new MutableLiveData<>();;
    QuranInterface quranInterface;
//    Repo repo  = new Repo(new QuranClient().getApi());

    public void getSurah(){
        QuranClient.getInstance().getSurah()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<SurahModel>>() {
                               @Override
                               public void accept(List<SurahModel> surahModels) throws Exception {
                        if (surahModels != null){
                            Log.d("surah", " SURAH : " + surahModels.size());
                            surahMutableLiveData.postValue(surahModels);
                        }
                        else{
                            Log.d("null","null :( :( :(");
                        }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.d("null",throwable.getMessage());

                               }
                           }
//                        new BiConsumer<List<SurahModel>, Throwable>() {
//                    @Override
//                    public void accept(List<SurahModel> surahModels, Throwable throwable) throws Exception {
//                        if (surahModels != null){
//                            Log.d("surah", " SURAH : " + surahModels.size());
//                            surahMutableLiveData.postValue(surahModels);
//                        }
//                        else{
//                            Log.d("null","null :( :( :(");
//                        }
//                    }
//                }
                );

        //        repo.getSurah()
//                .subscribe(new BiConsumer<ListeningResponse, Throwable>() {
//                    @Override
//                    public void accept(ListeningResponse listeningResponse, Throwable throwable) throws Exception {
//                        if(listeningResponse!= null){
//                            Log.d("zxc","TEST : "+listeningResponse.DataObject.surahs);
//                            surahMutableLiveData.postValue(listeningResponse.getDataObject().surahs);
//
//                        }
//                        else {
//                            Log.d("zz","zz");
//                        }
//
//                    }
//                });
    }
}
