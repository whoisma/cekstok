package com.ma.display.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MA on 12/01/2018.
 */

public class TokoModel {

    @SerializedName("toko")
    @Expose
    private String toko;

    @SerializedName("nama_toko")
    @Expose
    private String nama_toko;

    public String getToko() {
        return toko;
    }

    public void setToko(String toko) {
        this.toko = toko;
    }

    public String getNama_toko() {
        return nama_toko;
    }

    public void setNama_toko(String nama_toko) {
        this.nama_toko = nama_toko;
    }
}
