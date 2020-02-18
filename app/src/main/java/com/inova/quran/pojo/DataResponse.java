package com.inova.quran.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Alaa Moaataz on 2020-02-17.
 */

public class DataResponse implements Parcelable {
    public ArrayList<SurahModel> surahs = new ArrayList<>();
    public EditionResponse edition;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.surahs);
        dest.writeParcelable(this.edition, flags);

    }

    protected DataResponse(Parcel in) {
        this.surahs = in.createTypedArrayList(SurahModel.CREATOR);
        this.edition = in.readParcelable(getClass().getClassLoader());
    }

    public static final Parcelable.Creator<DataResponse> CREATOR = new Parcelable.Creator<DataResponse>() {
        @Override
        public DataResponse createFromParcel(Parcel source) {
            return new DataResponse(source);
        }

        @Override
        public DataResponse[] newArray(int size) {
            return new DataResponse[0];
        }
    };
}