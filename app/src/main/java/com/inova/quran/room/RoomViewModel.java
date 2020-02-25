package com.inova.quran.room;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Alaa Moaataz on 2020-02-24.
 */
public class RoomViewModel extends AndroidViewModel {

    private QuranRepo quranRepo;
    private LiveData<List<AyahRoomModel>> mAllAyahs;



    public RoomViewModel(@NonNull Application application) {
        super(application);
        quranRepo = new QuranRepo(application);
        mAllAyahs = quranRepo.getAllAyahs();
    }

    public LiveData<List<AyahRoomModel>> getmAllAyahs(){
        return mAllAyahs;
    }

    public void insert(List<AyahRoomModel> movieModel){
        quranRepo.insert(movieModel);
    }

    public void update(){
        quranRepo.update();
    }

    public void delete(){
        quranRepo.deleteAyah();
    }


}
