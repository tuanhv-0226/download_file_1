package com.framgia.downloadfileapp.adapters;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.framgia.downloadfileapp.R;

public class DialogLinkDownload extends BaseDialog {
    private final static String TAG = "DialogLinkDownload";

    protected EditText editLinkDownload;

	public DialogLinkDownload(Context context, View.OnClickListener callBackClickDownload) {
		super(context);
		setContentView(R.layout.dialog_linkdownload);

		TextView tvBtnStartDownload, tvBtnClose;
        editLinkDownload = (EditText) findViewById(R.id.editLinkDownload);
        tvBtnStartDownload = (TextView) findViewById(R.id.tvBtnStart);
		tvBtnClose = (TextView) findViewById(R.id.tvBtnClose);

		tvBtnClose.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogLinkDownload.this.dismiss();
			}
		});
        tvBtnStartDownload.setOnClickListener(callBackClickDownload);
	}

    public void setLinkDownload(String linkDownload) {
        this.editLinkDownload.setText(linkDownload);
    }

    public EditText getLinkDownload() {
        return editLinkDownload;
    }

}
