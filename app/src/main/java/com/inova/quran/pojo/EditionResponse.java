package com.inova.quran.pojo;

/**
 * Created by Alaa Moaataz on 2020-02-17.
 */
public class EditionResponse {
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
}