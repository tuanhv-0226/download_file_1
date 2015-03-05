package com.framgia.downloadfileapp.adapters;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.framgia.downloadfileapp.R;

public class DialogMessage extends BaseDialog {

	public DialogMessage(Context context, String link, View.OnClickListener callBackClickDownload) {
		super(context);
		setContentView(R.layout.dialog_linkdownload);
        String sLinkDownload;
        EditText editLinkDownload;
		TextView tvBtnStartDownload, tvBtnClose;
        editLinkDownload = (EditText) findViewById(R.id.editLinkDownload);
        tvBtnStartDownload = (TextView) findViewById(R.id.tvBtnStart);
		tvBtnClose = (TextView) findViewById(R.id.tvBtnClose);

        sLinkDownload = editLinkDownload.getText().toString();
		tvBtnClose.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogMessage.this.dismiss();
			}
		});
        tvBtnStartDownload.setOnClickListener(callBackClickDownload);
	}

	public DialogMessage(Context context,
			View.OnClickListener callBackClickApply) {
		super(context);
		setContentView(R.layout.dialog_setting);
        String sSaveTo;
        CheckBox cbNetAccess, cbNetConnect;
        EditText editSaveTo;
		TextView tvBtnClose, tvBtnApply;
        cbNetAccess = (CheckBox) findViewById(R.id.chkNetAccess);
        cbNetConnect = (CheckBox) findViewById(R.id.chkNetConnect);
        editSaveTo = (EditText) findViewById(R.id.editSaveTo);
		tvBtnClose = (TextView) findViewById(R.id.tvBtnClose);
		tvBtnApply = (TextView) findViewById(R.id.tvBtnApply);

        cbNetAccess.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                } else {

                }
            }
        });

        cbNetConnect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                } else {

                }
            }
        });

        sSaveTo = editSaveTo.getText().toString();

		tvBtnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogMessage.this.dismiss();
            }
        });
		tvBtnApply.setOnClickListener(callBackClickApply);
	}

}
