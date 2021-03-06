package com.c.dompetabata.Api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.webkit.WebView;

import com.c.dompetabata.Helper.utils;
import com.c.dompetabata.Model.Mlogin;
import com.c.dompetabata.Model.data;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

public class Value {

    String credentials;
    String password;
    data data;
    String message;
    public static String BASE_URL = "https://api-mobile-staging.abatapulsa.com/";   //   https://api-mobile.c-software.id/

    public String getMessage() {
        return message;
    }

    public Value() {
    }

    public data getData() {
        return data;
    }

    public Value(String credentials, String password) {
        this.credentials = credentials;
        this.password = password;
    }

    public static String getMacAddress(Context context) {
        String MAC = utils.getMacAddr();//phone if pc use eth0 if phone wlan0
        return MAC;
    }

    public static String getUserAgent(Context context) {

        String ua = new WebView(context).getSettings().getUserAgentString();
        return ua;
    }

    public static String getIPaddress() {

        String IP = utils.getIPAddress(true);
        return IP;
    }


}
