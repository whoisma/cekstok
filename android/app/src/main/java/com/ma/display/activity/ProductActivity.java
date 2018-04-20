package com.ma.display.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ma.display.base.App;
import com.ma.display.R;
import com.ma.display.listener.ProductListener;
import com.ma.display.presenter.ProductPresenter;
import com.ma.display.response.ProductResponse;
import com.ma.display.utils.Helper;

/**
 * Created by MA on 10/01/2018.
 */

public class ProductActivity extends App implements ProductListener{

    public static final String TOKO = "TOKO";
    public static final String UPC = "UPC";
    private static final int REQ_STOK = 698;

    private TextView txtKode, txtNama, txtToko, txtHarga, txtStok, txtNamaToko, txtSupplier;
    private String vToko, vUpc;

    private ProductPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txtKode = findViewById(R.id.txt_kode);
        txtNama = findViewById(R.id.txt_nama);
        txtToko = findViewById(R.id.txt_toko);
        txtNamaToko = findViewById(R.id.txt_namatoko);
        txtHarga = findViewById(R.id.txt_harga);
        txtStok = findViewById(R.id.txt_jml);
        txtSupplier = findViewById(R.id.txt_supplier);

        presenter = new ProductPresenter(this, usersSession.getBaseUrl());

        Intent intent = getIntent();
        vToko = intent.getStringExtra(TOKO);
        vUpc = intent.getStringExtra(UPC);

        presenter.product(vToko, vUpc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            onBackPressed();
            return true;
        }

        if (item.getItemId() ==R.id.action_edit){
            Intent intent = new Intent(ProductActivity.this, StockActivity.class);

            intent.putExtra(StockActivity.UPC, txtKode.getText().toString());
            intent.putExtra(StockActivity.NAMA, txtNama.getText().toString());
            intent.putExtra(StockActivity.NAMATOKO, txtNamaToko.getText().toString());
            intent.putExtra(StockActivity.TOKO, txtToko.getText().toString());
            intent.putExtra(StockActivity.HARGA, txtHarga.getText().toString());
            intent.putExtra(StockActivity.STOCK, txtStok.getText().toString());
            intent.putExtra(StockActivity.SUPPLIER, txtSupplier.getText().toString());

            startActivityForResult(intent, REQ_STOK);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        finish();
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void onSuccessResponse(ProductResponse response) {
        if (response.isStatus()){

            txtKode.setText(response.getData().getUpc());
            txtNama.setText(response.getData().getNama_barang());
            txtNamaToko.setText(response.getData().getTokos().getNama_toko());
            txtToko.setText(response.getData().getToko());
            txtHarga.setText(Helper.rupiah(response.getData().getJual()));
            txtStok.setText(response.getData().getJumlah_stock());

            txtSupplier.setText("-");
            if (response.getData().getSupplier().size() > 0){

                txtSupplier.setText(response.getData().getSupplier().get(0).getNama_supplier());
            }

            getSupportActionBar().setTitle(response.getData().getUpc());
            getSupportActionBar().setSubtitle(response.getData().getNama_barang());

        }else{

            showALert(response.getMsg(), new AlertListener() {
                @Override
                public void onOke() {

                    finish();
                }
            });
        }
    }

    @Override
    public void onFailResponse(String err) {
        showALert(err, new AlertListener() {
            @Override
            public void onOke() {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_STOK && resultCode == RESULT_OK){
            txtStok.setText(data.getStringExtra(StockActivity.RESULT));
        }
    }
}
