package com.udacity.gradle.builditbigger;

import android.util.Log;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

/**
 * Created by Aditya on 11/14/15.
 */
public class Utilities {
    public static String xlateToDeviceID(String deviceId){
        return getMD5(deviceId);
    }
    private static String getMD5(String inString){
        String md5 = ""; //AdRequest.DEVICE_ID_EMULATOR;
        try{
            MessageDigest digester = MessageDigest.getInstance("MD5");
            digester.update(inString.getBytes());
            md5 = new BigInteger(1,digester.digest()).toString(16).toUpperCase(Locale.ENGLISH);
        }
        catch (Exception e){
            Log.e("Exception:",e.getMessage());
        }
        return  md5;
    }
}
