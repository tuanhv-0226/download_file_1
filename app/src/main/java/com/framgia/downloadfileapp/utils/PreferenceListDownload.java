package com.framgia.downloadfileapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by framgiavn on 06/03/2015.
 */
public class PreferenceListDownload {

    private SharedPreferences mListDownload;

    public PreferenceListDownload(Context mContext) {
        mListDownload = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public PreferenceListDownload(Context mContext, String sName) {
        mListDownload = mContext.getSharedPreferences(sName, Context.MODE_PRIVATE);
    }

    public void setLinkDownload(String sLinkDownload) {
        mListDownload.edit().putString(Constants.LINK_DOWNLOAD, sLinkDownload).commit();
    }

    public String getLinkDownload() {
        return mListDownload.getString(Constants.LINK_DOWNLOAD, "");
    }
}
