package com.ma.display.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.telephony.CellInfo;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.ma.display.base.App;
import com.ma.display.R;
import com.ma.display.listener.LoginListener;
import com.ma.display.presenter.LoginPresenter;
import com.ma.display.response.UsersResponse;
import com.ma.display.utils.SimNoInfo;

import java.util.List;

/**
 * Created by MA on 06/01/2018.
 */

public class LoginActivity extends App implements LoginListener, View.OnClickListener{

    private LoginPresenter presenter;
    private EditText inputTelp;
    private RelativeLayout layout, body;
    private Animation animasi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this, usersSession.getBaseUrl());
        layout = findViewById(R.id.content);
        body = findViewById(R.id.body);
        inputTelp = findViewById(R.id.edhp);

        animasi = AnimationUtils.loadAnimation(this, R.anim.from_title_show);
        body.setAnimation(animasi);

        getPhone();

        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_setting).setOnClickListener(this);
    }

    private void checkPhoneInfo(){
        SimNoInfo telephonyInfo = SimNoInfo.getInstance(this);

        String imeiSIM1 = telephonyInfo.getImeiSIM1();
        String imeiSIM2 = telephonyInfo.getImeiSIM2();

        boolean isSIM1Ready = telephonyInfo.isSIM1Ready();
        boolean isSIM2Ready = telephonyInfo.isSIM2Ready();

        boolean isDualSIM = telephonyInfo.isDualSIM();
        Log.i("Dual Info = "," IME1 : " + imeiSIM1 + "\n" +
                " IME2 : " + imeiSIM2 + "\n" +
                " IS DUAL SIM : " + isDualSIM + "\n" +
                " IS SIM1 READY : " + isSIM1Ready + "\n" +
                " IS SIM2 READY : " + isSIM2Ready + "\n");
    }

    private void getPhone() {
        TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {

            Log.e(getClass().getSimpleName(), "Gagal Permission");

            return;
        }

        String mPhoneNumber = tMgr.getLine1Number();

        Log.d(getClass().getSimpleName(),"Telp: " + mPhoneNumber);

        inputTelp.setText(mPhoneNumber);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                if (inputTelp.getText().toString().isEmpty()){
                    Snackbar.make(layout, getResources().getString(R.string.err_phone),
                            Snackbar.LENGTH_SHORT).show();

                    inputTelp.requestFocus();

                    return;
                }

                presenter.doLogin(inputTelp.getText().toString());

                break;

            case R.id.btn_setting:
                startActivity(new Intent(LoginActivity.this, SettingActivity.class));

                break;
        }
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
    public void onLoginSuccess(UsersResponse response) {
        if (response.isStatus()){
            usersSession.setPhone(response.getData().getUser().getNohp());
            usersSession.setLogin(true);

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);

            finish();

            return;
        }

        Snackbar.make(layout, response.getMsg(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFail(String err) {
        showALert(err, new AlertListener() {
            @Override
            public void onOke() {

            }
        });
    }
}
