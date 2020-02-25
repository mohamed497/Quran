package com.inova.quran.room;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Alaa Moaataz on 2020-02-24.
 */

@Entity(tableName = "Quran")
public class AyahRoomModel {

    @PrimaryKey
    public int id;
    public int number;
    public String text;
    public int numberInSurah;
    public boolean state;
    public boolean check;
    public boolean checkPause;
    public long progressPercent;
    public String size;

//
//    public AyahRoomModel() {
//    }


    public AyahRoomModel(int id, int number, String text, int numberInSurah, boolean state, boolean check, boolean checkPause, long progressPercent, String size) {
        this.id = id;
        this.number = number;
        this.text = text;
        this.numberInSurah = numberInSurah;
        this.state = state;
        this.check = check;
        this.checkPause = checkPause;
        this.progressPercent = progressPercent;
        this.size = size;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AyahRoomModel other = (AyahRoomModel) obj;
        if (number != other.number)
            return false;
        return true;
    }
}
