package com.inova.quran.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

/**
 * Created by Alaa Moaataz on 2020-02-17.
 */
public class AyahModel implements Parcelable {

   private int number;
   private String text;
   private int numberInSurah;
   private int page;

    public AyahModel(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumberInSurah() {
        return numberInSurah;
    }

    public void setNumberInSurah(int numberInSurah) {
        this.numberInSurah = numberInSurah;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(text);
        dest.writeInt(numberInSurah);
        dest.writeInt(page);
    }

    protected AyahModel(Parcel in){
        this.number = in.readInt();
        this.text = in.readString();
        this.numberInSurah = in.readInt();
        this.page = in.readInt();
    }

    public static final Parcelable.Creator<AyahModel> CREATOR = new Parcelable.Creator<AyahModel>() {
        @Override
        public AyahModel createFromParcel(Parcel source) {
            return new AyahModel(source);
        }

        @Override
        public AyahModel[] newArray(int size) {
            return new AyahModel[size];
        }
    };

    @Override
    public boolean equals(@Nullable Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AyahModel other = (AyahModel) obj;
        if (number != other.number)
            return false;
        return true;
    }
}
