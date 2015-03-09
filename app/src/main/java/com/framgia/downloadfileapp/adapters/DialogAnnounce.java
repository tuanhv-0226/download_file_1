package com.framgia.downloadfileapp.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.framgia.downloadfileapp.R;

/**
 * Created by framgiavn on 09/03/2015.
 */
public class DialogAnnounce extends BaseDialog {
    private final static String TAG = "DialogAnnounce";

    public DialogAnnounce(Context context, String title, String message) {
        super(context);
        setContentView(R.layout.layout_dialog_announce);
        TextView tvTitle, tvMessage, tvBtnClose;
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvMessage = (TextView) findViewById(R.id.tvDetailMessage);
        tvBtnClose = (TextView) findViewById(R.id.tvButtonClose);
        tvBtnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogAnnounce.this.dismiss();
            }
        });

        tvTitle.setText(title);
        tvMessage.setText(message);
    }
}
