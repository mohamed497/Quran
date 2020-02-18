package com.inova.quran.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alaa Moaataz on 2020-02-17.
 */
public class EditionResponse implements Parcelable {
    private String identifier;
    private String language;
    private String name;
    private String englishName;
    private String format;
    private String type;


    // Getter Methods

    public String getIdentifier() {
        return identifier;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getFormat() {
        return format;
    }

    public String getType() {
        return type;
    }

    // Setter Methods

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.identifier);
        dest.writeString(this.language);
        dest.writeString(this.name);
        dest.writeString(this.englishName);
        dest.writeString(this.format);
        dest.writeString(this.type);
    }

    protected EditionResponse(Parcel in){
        this.identifier = in.readString();
        this.language = in.readString();
        this.name = in.readString();
        this.englishName = in.readString();
        this.format = in.readString();
        this.type = in.readString();
    }


    public static final Parcelable.Creator<EditionResponse> CREATOR = new Parcelable.Creator<EditionResponse>() {
        @Override
        public EditionResponse createFromParcel(Parcel source) {
            return new EditionResponse(source);
        }

        @Override
        public EditionResponse[] newArray(int size) {
            return new EditionResponse[size];
        }
    };
}