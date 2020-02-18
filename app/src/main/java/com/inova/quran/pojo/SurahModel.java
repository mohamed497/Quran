package com.inova.quran.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Alaa Moaataz on 2020-02-17.
 */
public class SurahModel implements Parcelable {

    private int number;
    private String name;
    private String englishName;
    public List<AyahModel> ayahs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.number);
        dest.writeString(this.name);
        dest.writeString(this.englishName);
        dest.writeTypedList(this.ayahs);
    }

    protected SurahModel(Parcel in){
        this.number = in.readInt();
        this.name = in.readString();
        this.englishName = in.readString();
        this.ayahs = in.createTypedArrayList(AyahModel.CREATOR);
    }

    public static final Parcelable.Creator<SurahModel> CREATOR = new Parcelable.Creator<SurahModel>() {
        @Override
        public SurahModel createFromParcel(Parcel source) {
            return new SurahModel(source);
        }

        @Override
        public SurahModel[] newArray(int size) {
            return new SurahModel[size];
        }
    };
}
