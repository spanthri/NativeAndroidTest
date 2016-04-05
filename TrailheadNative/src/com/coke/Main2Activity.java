package com.coke;

import android.*;
import android.app.Activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;

import com.coke.Utils.Utils;

public class Main2Activity extends Activity {
    final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    setPermissions();


    }

    /** Called when the user clicks the login button */
    public void sendLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
    public void unAuthenticatedFlow(View view) {
        Intent intent = new Intent(this, UnAuthenticatedActivity.class);
        startActivity(intent);


    }

    private void  setPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.READ_PHONE_STATE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_PHONE_STATE}, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);


                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        }
        else {
            Utils.setDeviceID(this);

            setContentView(R.layout.activity_main2);
            LinearLayout layout = (LinearLayout) findViewById(R.id.activity2);
            AlphaAnimation animation = new AlphaAnimation(0.0f , 1.0f ) ;
            animation.setFillAfter(true);
            animation.setDuration(1200);
//apply the animation ( fade In ) to your LAyout
            layout.startAnimation(animation);

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_PHONE_STATE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Utils.setDeviceID(this);

                    setContentView(R.layout.activity_main2);
                    LinearLayout layout = (LinearLayout) findViewById(R.id.activity2);
                    AlphaAnimation animation = new AlphaAnimation(0.0f , 1.0f ) ;
                    animation.setFillAfter(true);
                    animation.setDuration(1200);
//apply the animation ( fade In ) to your LAyout
                    layout.startAnimation(animation);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }

                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
