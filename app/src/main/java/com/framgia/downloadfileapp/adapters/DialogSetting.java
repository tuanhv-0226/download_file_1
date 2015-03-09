package com.framgia.downloadfileapp.adapters;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.framgia.downloadfileapp.R;

/**
 * Created by framgiavn on 06/03/2015.
 */
public class DialogSetting extends BaseDialog {
    private final static String TAG = "DialogSetting";

    protected CheckBox chkNetAccess, chkNetConnect;
    protected EditText editSaveTo;
    protected SeekBar seekMaxActive;

    public DialogSetting(Context context,
                         View.OnClickListener callBackClickApply) {
        super(context);
        setContentView(R.layout.dialog_setting);

        TextView tvBtnClose, tvBtnApply;
        chkNetAccess = (CheckBox) findViewById(R.id.chkNetAccess);
        chkNetConnect = (CheckBox) findViewById(R.id.chkNetConnect);
        editSaveTo = (EditText) findViewById(R.id.editSaveTo);
        seekMaxActive = (SeekBar) findViewById(R.id.seekMaxActive);
        tvBtnClose = (TextView) findViewById(R.id.tvBtnClose);
        tvBtnApply = (TextView) findViewById(R.id.tvBtnApply);

        chkNetAccess.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                } else {

                }
            }
        });

        chkNetConnect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                } else {

                }
            }
        });

        tvBtnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogSetting.this.dismiss();
            }
        });
        tvBtnApply.setOnClickListener(callBackClickApply);
    }

    public CheckBox getChkNetAccess() {
        return chkNetAccess;
    }

    public void setChkNetAccess(boolean isChecked) {
        this.chkNetAccess.setChecked(isChecked);
    }

    public CheckBox getChkNetConnect() {
        return chkNetConnect;
    }

    public void setChkNetConnect(boolean isChecked) {
        this.chkNetConnect.setChecked(isChecked);
    }

    public EditText getEditSaveTo() {
        return editSaveTo;
    }

    public void setEditSaveTo(String editSaveTo) {
        this.editSaveTo.setText(editSaveTo);
    }

    public SeekBar getSeekMaxActive() {
        return seekMaxActive;
    }

    public void setSeekMaxActive(SeekBar seekMaxActive) {
        this.seekMaxActive = seekMaxActive;
    }
}
