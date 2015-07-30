package com.example.rahulsk.cabamigo;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.security.MessageDigest;
import android.util.Log;
import android.util.Base64;
import android.content.pm.PackageManager.NameNotFoundException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rahul.sk on 30/07/15.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        print_key_hash();
    }

    void print_key_hash()
    {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.rahulsk.cabamigo",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("MyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
