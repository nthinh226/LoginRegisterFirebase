package com.nhom1.loginregisterfirebase.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class AppStatus {
    private static AppStatus instance = new AppStatus();
    static Context context;
    ConnectivityManager connectivityManager;
    NetworkInfo wifiInfo, mobileInfo;
    boolean connected = false;

    public static AppStatus getInstance(Context ctx) {
        context = ctx.getApplicationContext();
        return instance;
    }
    public boolean isOnline() {
        try {
            connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() &&
                    networkInfo.isConnected();
            return connected;

        } catch (Exception e) {
            System.out.println("CheckConnectivity Exception: " + e.getMessage());
            Log.v("connectivity", e.toString());
        }
        return connected;
    }
    /*
    public static void checkInternet(Context context){
        if (AppStatus.getInstance(context).isOnline()) {
            Toast.makeText(context,"You are online!!!!",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"You are not online!!!!",Toast.LENGTH_SHORT).show();
            Log.v("Home", "############################You are not online!!!!");
        }
    }
    */
    public static boolean checkInternet(Context context){
        if (AppStatus.getInstance(context).isOnline()) {
            return true;
        } else {
            return false;
        }
    }
}
