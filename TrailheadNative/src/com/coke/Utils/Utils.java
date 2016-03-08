package com.coke.Utils;

/**
 * Created by spanthri on 07/03/16.
 */

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;



import java.util.UUID;



public final class Utils {
    private static String deviceID = null;

    public static String getDeviceID() {

        return deviceID;
    }

    public static void setDeviceID(Activity activity) {


            final TelephonyManager tm = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);

            final String tmDevice, tmSerial, androidId;
            tmDevice = "" + tm.getDeviceId();
            System.out.println(" tmDevice " + tmDevice);
            tmSerial = "" + tm.getSimSerialNumber();
            androidId = "" + android.provider.Settings.Secure.getString(

                    activity.getContentResolver(), android

                            .provider.Settings.Secure.ANDROID_ID);

            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            deviceID = deviceUuid.toString();
            System.out.println(" deviceID " + deviceID);

        Utils.deviceID = deviceID;
    }










}
