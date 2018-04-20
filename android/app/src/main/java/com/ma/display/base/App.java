package com.ma.display.base;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ma.display.R;
import com.ma.display.session.UsersSession;
import com.ma.display.utils.PermissionResultCallback;
import com.ma.display.utils.PermissionUtils;

import java.util.ArrayList;

/**
 * Created by MA on 07/01/2018.
 */

public class App extends AppCompatActivity implements
        ActivityCompat.OnRequestPermissionsResultCallback, PermissionResultCallback {

    private PermissionUtils permissionUtils;
    private ArrayList<String> permissions = new ArrayList();
    protected ProgressDialog progressDialog;
    protected UsersSession usersSession;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getRequestPermission();
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setMessage(getResources().getString(R.string.txt_loading));
        this.progressDialog.setCancelable(false);

        usersSession = new UsersSession(this);
    }

    protected void resetProgress(){
        progressDialog.setMessage(getResources().getString(R.string.txt_loading));
    }

    protected void getRequestPermission() {
        this.permissionUtils = new PermissionUtils(this);

        this.permissions.add(Manifest.permission.INTERNET);
        this.permissions.add(Manifest.permission.READ_PHONE_STATE);
        this.permissions.add(Manifest.permission.READ_SMS);
        this.permissions.add(Manifest.permission.CAMERA);

        this.permissionUtils.check_permission(this.permissions, "Explain here why the app needs permissions", 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        this.permissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void NeverAskAgain(int i) {
        Log.i(getClass().getSimpleName(), "PERMISSION NEVER ASK AGAIN");
    }

    @Override
    public void PartialPermissionGranted(int i, ArrayList<String> arrayList) {
        Log.i(getClass().getSimpleName(), "PERMISSION PARTIALLY GRANTED");
    }

    @Override
    public void PermissionDenied(int i) {
        Log.i(getClass().getSimpleName(), "PERMISSION DENIED");
    }

    @Override
    public void PermissionGranted(int i) {
        Log.i(getClass().getSimpleName(), "PERMISSION GRANTED");
    }

    protected void showALert(String msg, final AlertListener listener){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getResources().getString(R.string.txt_perhatian));
        alert.setMessage(msg);
        alert.setCancelable(false);
        alert.setPositiveButton(getResources().getString(R.string.txt_oke), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onOke();
                dialogInterface.dismiss();
            }
        });
        alert.show();
    }

    protected interface AlertListener{
        void onOke();
    }
}
