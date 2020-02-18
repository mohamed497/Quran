package com.inova.quran.tryDownload;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Alaa Moaataz on 2020-02-18.
 */
public class DownloadViewModel extends ViewModel {

    public MutableLiveData<ResponseBody> responseBodyMutableLiveData = new MutableLiveData<>();

    public void download(){
        DownloadClient.getInstance().downloading()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        responseBodyMutableLiveData.postValue(responseBody);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.getMessage();
                    }
                });
    }
}
