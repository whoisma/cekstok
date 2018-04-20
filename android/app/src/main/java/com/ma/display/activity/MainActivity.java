package com.ma.display.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.ma.display.R;
import com.ma.display.base.App;
import com.ma.display.model.ProductModel;

public class MainActivity extends App implements View.OnClickListener{

    private static MainActivity mInstance = null;
    private static synchronized MainActivity getmInstance(){
        return mInstance;
    }

    private static final int REQ_SEARCH = 213;
    private static final int REQ_SCANNER = 363;

    private EditText edKode, edToko;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setElevation(0);

        mInstance = this;

        edKode = findViewById(R.id.input_kode);
        edToko = findViewById(R.id.input_toko);
        layout = findViewById(R.id.layout);

        findViewById(R.id.btn_list).setOnClickListener(this);
        findViewById(R.id.btn_scan).setOnClickListener(this);
        findViewById(R.id.btn_cari).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_list:

                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivityForResult(intent, REQ_SEARCH);

                break;

            case R.id.btn_scan:

                Intent scanAcitivity = new Intent(MainActivity.this, ScannerActivity.class);
                startActivityForResult(scanAcitivity, REQ_SCANNER);

                break;

            case R.id.btn_cari:
                cari();

                break;
        }
    }

    private void cari(){
        if (edKode.getText().toString().isEmpty()){
            Snackbar.make(layout, "Isian UPC/Kode barang tidak boleh kosong !", Snackbar.LENGTH_SHORT).show();
            edKode.requestFocus();

            return;
        }

        if (edToko.getText().toString().isEmpty()){
            Snackbar.make(layout, "Isian kode toko tidak boleh kosong !", Snackbar.LENGTH_SHORT).show();
            edToko.requestFocus();

            return;
        }

        Intent intent = new Intent(MainActivity.this, ProductActivity.class);
        intent.putExtra(ProductActivity.UPC, edKode.getText().toString());
        intent.putExtra(ProductActivity.TOKO, edToko.getText().toString());

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQ_SCANNER:
                if (resultCode == RESULT_OK){
                    edKode.setText(data.getStringExtra(ScannerActivity.RESULT));
                    edToko.requestFocus();
                }

                break;

            case REQ_SEARCH:
                if (resultCode == RESULT_OK){
                    String s = data.getStringExtra(ListActivity.RESULT);

                    Gson gson = new Gson();
                    ProductModel model = gson.fromJson(s, ProductModel.class);

                    edKode.setText(model.getUpc());
                    edToko.setText(model.getToko());
                }

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            usersSession.setLogin(false);
            usersSession.setPhone("");

            Intent intent = new Intent(MainActivity.this,
                    LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);
            finish();

            return true;
        }

        if (id == R.id.action_setting){
            startActivity(new Intent(MainActivity.this, SettingActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
