package com.ma.display.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build.VERSION;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PermissionUtils {
    Context context;
    Activity current_activity;
    String dialog_content = "";
    ArrayList<String> listPermissionsNeeded = new ArrayList();
    PermissionResultCallback permissionResultCallback;
    ArrayList<String> permission_list = new ArrayList();
    int req_code;

    private static final String TAG = "Permission";

    public PermissionUtils(Context context) {
        this.context = context;
        this.current_activity = (Activity) context;
        this.permissionResultCallback = (PermissionResultCallback) context;
    }

    public void check_permission(ArrayList<String> permissions, String dialog_content, int request_code) {
        this.permission_list = permissions;
        this.dialog_content = dialog_content;
        this.req_code = request_code;
        if (VERSION.SDK_INT < 23) {
            this.permissionResultCallback.PermissionGranted(request_code);
            Log.i(TAG, "all permissions granted");
            Log.i(TAG, "proceed to callback");
        } else if (checkAndRequestPermissions(permissions, request_code)) {
            this.permissionResultCallback.PermissionGranted(request_code);
            Log.i(TAG, "all permissions granted");
            Log.i(TAG, "proceed to callback");
        }
    }

    private boolean checkAndRequestPermissions(ArrayList<String> permissions, int request_code) {
        if (permissions.size() > 0) {
            this.listPermissionsNeeded = new ArrayList();
            for (int i = 0; i < permissions.size(); i++) {
                if (ContextCompat.checkSelfPermission(this.current_activity, (String) permissions.get(i)) != 0) {
                    this.listPermissionsNeeded.add(permissions.get(i));
                }
            }
            if (!this.listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this.current_activity, (String[]) this.listPermissionsNeeded.toArray(new String[this.listPermissionsNeeded.size()]), request_code);
                return false;
            }
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    int i;
                    Map<String, Integer> perms = new HashMap();
                    for (i = 0; i < permissions.length; i++) {
                        perms.put(permissions[i], Integer.valueOf(grantResults[i]));
                    }
                    final ArrayList<String> pending_permissions = new ArrayList();
                    for (i = 0; i < this.listPermissionsNeeded.size(); i++) {
                        if (((Integer) perms.get(this.listPermissionsNeeded.get(i))).intValue() != 0) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this.current_activity, (String) this.listPermissionsNeeded.get(i))) {
                                pending_permissions.add(this.listPermissionsNeeded.get(i));
                            } else {
                                Log.i(TAG, "Go to settings and enable permissions " + ((String) this.listPermissionsNeeded.get(i)));
                                this.permissionResultCallback.NeverAskAgain(this.req_code);
                                Toast.makeText(this.current_activity, "Silahkan Ke pengaturan dan hidupkan permissions " + ((String) this.listPermissionsNeeded.get(i)), Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }
                    if (pending_permissions.size() > 0) {
                        showMessageOKCancel(this.dialog_content, new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case -2:
                                        Log.i(TAG, "permisson not fully given");
                                        if (PermissionUtils.this.permission_list.size() == pending_permissions.size()) {
                                            PermissionUtils.this.permissionResultCallback.PermissionDenied(PermissionUtils.this.req_code);
                                            return;
                                        } else {
                                            PermissionUtils.this.permissionResultCallback.PartialPermissionGranted(PermissionUtils.this.req_code, pending_permissions);
                                            return;
                                        }
                                    case -1:
                                        PermissionUtils.this.check_permission(PermissionUtils.this.permission_list, PermissionUtils.this.dialog_content, PermissionUtils.this.req_code);
                                        return;
                                    default:
                                        return;
                                }
                            }
                        });
                        return;
                    }
                    Log.i(TAG, "all permissions granted");
                    Log.i(TAG, "proceed to next step");
                    this.permissionResultCallback.PermissionGranted(this.req_code);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void showMessageOKCancel(String message, OnClickListener okListener) {
        new Builder(this.current_activity).setMessage((CharSequence) message).setPositiveButton((CharSequence) "Ok", okListener).setNegativeButton((CharSequence) "Batal", okListener).create().show();
    }
}
