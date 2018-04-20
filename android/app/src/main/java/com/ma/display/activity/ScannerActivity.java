package com.ma.display.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.google.zxing.Result;
import com.ma.display.base.App;
import com.ma.display.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by MA on 10/01/2018.
 */

public class ScannerActivity extends App implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    public static final String RESULT = "RESULT";
    private SwitchCompat aFlash;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        aFlash = findViewById(R.id.switch_light);
        ViewGroup contentFrame = findViewById(R.id.content_frame);

        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);

        aFlash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mScannerView.setFlash(b);
            }
        });
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
    public void handleResult(Result result) {
        String qrcode = result.getText();

        Intent intent = getIntent();
        intent.putExtra(RESULT, qrcode);

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
}
