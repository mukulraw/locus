package com.app.locus.assignment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Flipper extends Activity{



    String[] PERMISSIONS = { Manifest.permission.WRITE_EXTERNAL_STORAGE  , Manifest.permission.READ_LOGS };
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private SharedPreferences pref;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_flipper);

        pref = getSharedPreferences("Locus", Context.MODE_PRIVATE);
        edit = pref.edit();

        File filename = new File(Environment.getExternalStorageDirectory()+"/mylog.log");
        try {
            filename.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String cmd = "logcat -d -f"+filename.getAbsolutePath();
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_CODE_ASK_PERMISSIONS);
        }


        boolean is = pref.getBoolean("is" , false);


        if (!is)
        {
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dilaog);
            dialog.setCancelable(false);
            dialog.show();

            Button yes = (Button)dialog.findViewById(R.id.yes);
            Button no = (Button)dialog.findViewById(R.id.no);


            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                    if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){

                        edit.putBoolean("is" , true);
                        edit.apply();
                        dialog.dismiss();

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            int hasLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
                            if(hasLocationPermission!=PackageManager.PERMISSION_GRANTED)
                            {
                                //request permission
                                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION} , 111);
                            }

                        }



                    }else{
                        edit.putBoolean("is" , true);
                        edit.apply();
                        dialog.dismiss();
                        showGPSDisabledAlertToUser();
                    }


                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edit.putBoolean("is" , true);
                    edit.apply();
                    dialog.dismiss();
                }
            });
        }














    }



    private void showGPSDisabledAlertToUser(){
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivityForResult(callGPSSettingIntent,123);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){

                        finish();
                        dialog.cancel();

                    }
                });
        android.support.v7.app.AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 123)
        {



                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    int hasLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
                    if(hasLocationPermission!=PackageManager.PERMISSION_GRANTED)
                    {
                        //request permission
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION} , REQUEST_CODE_ASK_PERMISSIONS);
                    }

                }






        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.d("asdasdasd", "Permission callback called-------");
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS: {

                Map<String, Integer> perms = new HashMap<>();
                // Initialize the map with both permissions
                //perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);

                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE , PackageManager.PERMISSION_GRANTED);
                //perms.put(Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED);
                // Fill with actual results from user
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);
                    // Check for both permissions
                    if (perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED   ) {
                        Log.d("asdasdasd", "sms & location services permission granted");
                        // process the normal flow
                        //else any one or both the permissions are not granted
                    } else {
                        Log.d("asdasdasd", "Some permissions are not granted ask again ");
                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                        // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this , Manifest.permission.WRITE_EXTERNAL_STORAGE) ) {

                            Toast.makeText(getApplicationContext() , "Permissions are required for this app" , Toast.LENGTH_SHORT).show();
                            finish();

                        }
                        //permission is denied (and never ask again is  checked)
                        //shouldShowRequestPermissionRationale will return false
                        else {
                            Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_LONG)
                                    .show();
                            finish();
                            //                            //proceed with logic by disabling the related features or quit the app.
                        }
                    }
                }
                break;
            }

            case 111:
            {



                if(!hasPermissions(this, Manifest.permission.ACCESS_COARSE_LOCATION)){
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_ASK_PERMISSIONS);
                }




            }
        }

    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }

    public void signin(View v)
    {

        Intent i = new Intent(this , SplashActivity.class);
        startActivity(i);


    }

    public void signup(View v)
    {
        Intent i = new Intent(this , Register.class);
        startActivity(i);


    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    }
