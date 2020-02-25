package com.inova.quran.room;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created by Alaa Moaataz on 2020-02-24.
 */
@Dao
public interface QuranDoa {


    @Query("SELECT * FROM Quran")
    LiveData<List<AyahRoomModel>> getAyah();

    @Query("SELECT * FROM Quran WHERE state = :state AND `check` = :check")
    LiveData<List<AyahRoomModel>> getDownloadingAyah(boolean state, boolean check);

//    @Query("UPDATE Quran SET state = :state")
//    AyahRoomModel updateAyah(boolean state);
    @Query("UPDATE Quran SET state = :state WHERE number = :number")
    void updateAyah(boolean state, int number);


    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = AyahRoomModel.class)
    void insertAyah(List<AyahRoomModel> ayahRoomModels);

//    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = AyahRoomModel.class)
//    void insertAyah(AyahRoomModel ayahRoomModels);

    @Query("DELETE FROM Quran WHERE state = :state AND `check` = :check")
    void deleteAyah(boolean state, boolean check);

}
