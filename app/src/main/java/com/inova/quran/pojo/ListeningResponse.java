package com.inova.quran.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by Alaa Moaataz on 2020-02-17.
 */

public class ListeningResponse implements Parcelable {
//    private float code;
    private String status;
    public DataResponse data;


    // Getter Methods

//    public float getCode() {
//        return code;
//    }

    public String getStatus() {
        return status;
    }



// Setter Methods

//    public void setCode(float code) {
//        this.code = code;
//    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.status);
        dest.writeParcelable(this.data, flags);
    }

    protected ListeningResponse(Parcel in){
        this.status = in.readString();
        this.data = in.readParcelable(getClass().getClassLoader());
    }

    public static final Parcelable.Creator<ListeningResponse> CREATOR = new Parcelable.Creator<ListeningResponse>(){

        @Override
        public ListeningResponse createFromParcel(Parcel source) {
            return new ListeningResponse(source);
        }

        @Override
        public ListeningResponse[] newArray(int size) {
            return new ListeningResponse[size];
        }
    };

    public static final Comparator<ListeningResponse> BY_NAME_ALPHABETICAL = new Comparator<ListeningResponse>() {
        @Override
        public int compare(ListeningResponse listeningResponse, ListeningResponse t1) {

            return listeningResponse.status.compareTo(t1.status);
        }
    };
}
