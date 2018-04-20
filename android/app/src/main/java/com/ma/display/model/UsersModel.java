package com.ma.display.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MA on 08/01/2018.
 */

public class UsersModel {

    @SerializedName("nohp")
    @Expose
    private String nohp;

    @SerializedName("nama")
    @Expose
    private String nama;

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
