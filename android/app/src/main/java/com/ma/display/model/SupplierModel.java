package com.ma.display.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MA on 14/02/2018.
 */

public class SupplierModel {

    @SerializedName("upc")
    @Expose
    private String upc;

    @SerializedName("nama_supplier")
    @Expose
    private String nama_supplier;

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getNama_supplier() {
        return nama_supplier;
    }

    public void setNama_supplier(String nama_supplier) {
        this.nama_supplier = nama_supplier;
    }
}
