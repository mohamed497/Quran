package com.inova.quran.pojo;

import java.util.List;

/**
 * Created by Alaa Moaataz on 2020-02-17.
 */
public class SurahModel {

    private int number;
    private String name;
    private String englishName;
    List<AyahModel> ayahs;

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
}
