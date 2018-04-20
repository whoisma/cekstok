package com.ma.display.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ma.display.base.App;
import com.ma.display.R;
import com.ma.display.listener.StockListener;
import com.ma.display.presenter.StockPresenter;
import com.ma.display.response.DefaultResponse;

/**
 * Created by MA on 10/01/2018.
 */

public class StockActivity extends App implements StockListener, View.OnClickListener{

    public static final String UPC = "UPC";
    public static final String NAMA = "NAMA";
    public static final String TOKO = "TOKO";
    public static final String NAMATOKO = "NAMATOKO";
    public static final String HARGA = "HARGA";
    public static final String STOCK = "STOCK";
    public static final String RESULT = "RESULT";
    public static final String SUPPLIER = "SUPPLIER";

    private TextView txtKode, txtNama, txtToko, txtHarga, txtNamaToko, txtSupplier;
    private EditText inputJml, inputLokasi;
    private LinearLayout layout;

    private StockPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stok);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txtKode = findViewById(R.id.txt_kode);
        txtNama = findViewById(R.id.txt_nama);
        txtToko = findViewById(R.id.txt_toko);
        txtHarga = findViewById(R.id.txt_harga);
        txtNamaToko = findViewById(R.id.txt_namatoko);
        txtSupplier = findViewById(R.id.txt_supplier);

        inputJml = findViewById(R.id.input_jml);
        inputLokasi = findViewById(R.id.input_lokasi);

        layout = findViewById(R.id.layout);

        presenter = new StockPresenter(this, usersSession.getBaseUrl());

        Intent intent = getIntent();

        txtKode.setText(intent.getStringExtra(UPC));
        txtNama.setText(intent.getStringExtra(NAMA));
        txtHarga.setText(intent.getStringExtra(HARGA));
        txtToko.setText(intent.getStringExtra(TOKO));
        inputJml.setText(intent.getStringExtra(STOCK));
        txtNamaToko.setText(intent.getStringExtra(NAMATOKO));
        txtSupplier.setText(intent.getStringExtra(SUPPLIER));

        findViewById(R.id.btn_save).setOnClickListener(this);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != android.R.id.home) {

            return super.onOptionsItemSelected(item);
        }

        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_save:
                save();
                break;
        }
    }

    private void save(){
        if (inputJml.getText().toString().isEmpty()){
            Snackbar.make(layout, "Isian Jumlah Stock tidak boleh kosong !", Snackbar.LENGTH_SHORT).show();

            inputJml.requestFocus();

            return;
        }

        if (inputLokasi.getText().toString().isEmpty()){
            Snackbar.make(layout, "Isian Lokasi Area tidak boleh kosong !", Snackbar.LENGTH_SHORT).show();

            inputLokasi.requestFocus();

            return;
        }

        if (inputLokasi.getText().toString().length() > 10){
            Snackbar.make(layout, "Isian Lokasi Area maksimal 10 karakter !", Snackbar.LENGTH_SHORT).show();

            inputLokasi.requestFocus();

            return;
        }

        presenter.save(
                txtKode.getText().toString(),
                txtToko.getText().toString(),
                inputJml.getText().toString(),
                inputLokasi.getText().toString()
        );
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
    public void onSuccessResponse(DefaultResponse response) {
        if (response.isStatus()){

            Snackbar.make(layout, "Simpan Sukses !", Snackbar.LENGTH_SHORT).show();

            Intent intent = getIntent();
            intent.putExtra(RESULT, inputJml.getText().toString());

            setResult(RESULT_OK, intent);
            finish();

        }else{

            Snackbar.make(layout, response.getMsg(), Snackbar.LENGTH_SHORT).setAction("Coba Lagi", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }).show();
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
}
