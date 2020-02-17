package com.inova.quran.pojo;

/**
 * Created by Alaa Moaataz on 2020-02-17.
 */

public class ListeningResponse {
    private float code;
    private String status;
    public DataResponse data;


    // Getter Methods

    public float getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }



// Setter Methods

    public void setCode(float code) {
        this.code = code;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
