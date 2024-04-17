package kr.benefitplus.drawerexam.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import kr.benefitplus.drawerexam.DTO.Token;
import kr.benefitplus.drawerexam.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Session manager to save and fetch data from SharedPreferences
 */
public class SessionManager {

    private final SharedPreferences prefs;
    private static final String TOKEN_TYPE = "TOKEN_TYPE";
    private static final String EXPIRES_IN = "EXPIRES_IN";
    private static final String AUTH_TOKEN = "AUTH_TOKEN";
    private static final String REFRESH_TOKEN = "REFRESH_TOKEN";

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(context.getString(R.string.corp), Context.MODE_PRIVATE);
    }

    public boolean isSession(){
        long expireIn = prefs.getLong(EXPIRES_IN, 0);
        long currentIn = System.currentTimeMillis();

        return expireIn >= currentIn;
    }

    public void saveAuthToken(Token token){
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(TOKEN_TYPE, token.tokenType);
        editor.putLong(EXPIRES_IN, token.expiresIn * 1000 + System.currentTimeMillis());
        editor.putString(AUTH_TOKEN, token.accessToken);
        editor.putString(REFRESH_TOKEN, token.refreshToken);
        editor.apply();
    }

    public String fetchAuthToken(){
        long expireIn = prefs.getLong(EXPIRES_IN, 0);

        // token 유효기간 종료 - refresh token을 이용하여 연장한다.
        if (expireIn < System.currentTimeMillis()) {

        }
        return prefs.getString(AUTH_TOKEN, null);
    }

    public void logout (){
        @SuppressLint("CommitPrefEdits")
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }
}
