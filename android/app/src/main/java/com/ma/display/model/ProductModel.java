package com.ma.display.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by MA on 08/01/2018.
 */

public class ProductModel {

    @SerializedName("toko")
    @Expose
    private String toko;

    @SerializedName("upc")
    @Expose
    private String upc;

    @SerializedName("nama_barang")
    @Expose
    private String nama_barang;

    @SerializedName("jumlah_stock")
    @Expose
    private String jumlah_stock;

    @SerializedName("jual")
    @Expose
    private String jual;

    @SerializedName("tokos")
    @Expose
    private TokoModel tokos;

    @SerializedName("supplier")
    @Expose
    private List<SupplierModel> supplier;

    public String getToko() {
        return toko;
    }

    public void setToko(String toko) {
        this.toko = toko;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getJumlah_stock() {
        return jumlah_stock;
    }

    public void setJumlah_stock(String jumlah_stock) {
        this.jumlah_stock = jumlah_stock;
    }

    public String getJual() {
        return jual;
    }

    public void setJual(String jual) {
        this.jual = jual;
    }

    public TokoModel getTokos() {
        return tokos;
    }

    public void setTokos(TokoModel tokos) {
        this.tokos = tokos;
    }

    public List<SupplierModel> getSupplier() {
        return supplier;
    }

    public void setSupplier(List<SupplierModel> supplier) {
        this.supplier = supplier;
    }
}
