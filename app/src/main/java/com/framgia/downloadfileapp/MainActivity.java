package com.framgia.downloadfileapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.framgia.downloadfileapp.adapters.DialogMessage;
import com.framgia.downloadfileapp.controllers.DownloadController;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String sUrl = "http://mp3.zing.vn/bai-hat/Chac-Ai-Do-Se-Ve-Son-Tung-M-TP/ZW6EO0Z8.html";
        String saveTo = "/storage/emulated/0";
        DownloadController ctr = new DownloadController(getBaseContext());
        ctr.downloadFile(sUrl, saveTo);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.actDownload:
                clickLinkDownload();
                break;
            case R.id.actMultiChoice:
                break;
            case R.id.actSettings:
                clickSettingDownload();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickLinkDownload() {
        DialogMessage dialogLink = new DialogMessage(this,
                "LinkDownload",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        dialogLink.show();
    }

    public void clickSettingDownload() {
        DialogMessage dialogSetting = new DialogMessage(this,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        dialogSetting.show();
    }
}
