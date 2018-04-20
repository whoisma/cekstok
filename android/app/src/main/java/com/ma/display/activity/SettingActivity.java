package com.ma.display.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ma.display.BuildConfig;
import com.ma.display.R;
import com.ma.display.base.App;

/**
 * Created by MA on 10/03/2018.
 */

public class SettingActivity extends App implements View.OnClickListener{

    private ImageView imgVps, imgViola;
    private TextView statusVps, statusViola;
    private boolean isVps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_db);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgViola = findViewById(R.id.img_viola);
        imgVps = findViewById(R.id.img_vps);
        statusViola = findViewById(R.id.txt_viola_status);
        statusVps = findViewById(R.id.txt_vps_status);

        reloadView();
    }

    private void reloadView(){
        if (usersSession.getBaseUrl().equals(BuildConfig.BASE_URL_VPS)){

            isVps = true;
            imgVps.setImageDrawable(getResources().getDrawable(R.mipmap.ic_db_enabled));
            statusVps.setText("Aktif");

            imgViola.setImageDrawable(getResources().getDrawable(R.mipmap.ic_database_disabled));
            statusViola.setText("");

        }else{

            isVps = false;
            imgVps.setImageDrawable(getResources().getDrawable(R.mipmap.ic_database_disabled));
            statusVps.setText("");

            imgViola.setImageDrawable(getResources().getDrawable(R.mipmap.ic_db_enabled));
            statusViola.setText("Aktif");

        }

        findViewById(R.id.btn_viola).setOnClickListener(this);
        findViewById(R.id.btn_vps).setOnClickListener(this);
    }

    public void onBackPressed() {
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_vps:
                usersSession.setBaseUrl(BuildConfig.BASE_URL_VPS);
                reloadView();
                break;

            case R.id.btn_viola:
                usersSession.setBaseUrl(BuildConfig.BASE_URL_VIOLA);
                reloadView();
                break;
        }
    }
}
