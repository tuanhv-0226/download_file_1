package com.framgia.downloadfileapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by framgiavn on 06/03/2015.
 */
public class PreferenceSetting {
    private SharedPreferences mSetting;
    private SharedPreferences.Editor mEditSetting;

    public PreferenceSetting(Context mContext) {
        mSetting = PreferenceManager.getDefaultSharedPreferences(mContext);
        mEditSetting = mSetting.edit();
    }

    public PreferenceSetting(Context mContext, String sName) {
        mSetting = mContext.getSharedPreferences(sName, Context.MODE_PRIVATE);
        mEditSetting = mSetting.edit();
    }

    public void setCheckBoxNetWork(int isChecked) {
        mEditSetting.putInt(Constants.CHECKBOX_NETWORK, isChecked).commit();
    }

    public int getCheckBoxNetWork() {
        return mSetting.getInt(Constants.CHECKBOX_NETWORK, 0);
    }

    public void setCheckBoxAutoResume(int isChecked) {
        mEditSetting.putInt(Constants.CHECKBOX_AUTO_RESUME, isChecked).commit();
    }

    public int getCheckBoxAutoResume() {
        return mSetting.getInt(Constants.CHECKBOX_AUTO_RESUME, 0);
    }

    public void setEditSaveTo(String sSaveTo) {
        mEditSetting.putString(Constants.EDIT_SAVE_TO, sSaveTo).commit();
    }

    public String getEditSaveTo() {
        return mSetting.getString(Constants.EDIT_SAVE_TO, "");
    }

    public void setMaxActive(int maxActive) {
        mEditSetting.putInt(Constants.MAX_ACTIVE, maxActive).commit();
    }

    public int getMaxActive() {
        return mSetting.getInt(Constants.MAX_ACTIVE, 1);
    }

    public void setNewSetting(int isNetwork, int isAuto, String sSaveTo, int maxActive) {
        mEditSetting.putInt(Constants.CHECKBOX_NETWORK, isNetwork);
        mEditSetting.putInt(Constants.CHECKBOX_AUTO_RESUME, isAuto);
        mEditSetting.putString(Constants.EDIT_SAVE_TO, sSaveTo);
        mEditSetting.putInt(Constants.MAX_ACTIVE, maxActive);
        mEditSetting.commit();
    }
}
