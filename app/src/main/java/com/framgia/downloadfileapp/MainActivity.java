package com.framgia.downloadfileapp;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;

import com.framgia.downloadfileapp.adapters.DatabaseAdapter;
import com.framgia.downloadfileapp.adapters.DialogAnnounce;
import com.framgia.downloadfileapp.adapters.DialogLinkDownload;
import com.framgia.downloadfileapp.adapters.DialogSetting;
import com.framgia.downloadfileapp.adapters.FileAdapter;
import com.framgia.downloadfileapp.controllers.DownloadController;
import com.framgia.downloadfileapp.models.FileModel;
import com.framgia.downloadfileapp.models.ListFiles;
import com.framgia.downloadfileapp.utils.Constants;
import com.framgia.downloadfileapp.utils.PreferenceListDownload;
import com.framgia.downloadfileapp.utils.PreferenceSetting;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    private DatabaseAdapter dbAdapter;
    private FileAdapter mFileAdapter;
    private ListFiles mListFiles;

    private DialogAnnounce dialogAnnounce;
    private DialogLinkDownload dialogLink;
    private DialogSetting dialogSetting;
    private PreferenceSetting mSetting;

    private ListView mListView;
    private int posLink = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSetting = new PreferenceSetting(this, Constants.SETTINGS_FILE_NAME);

        dbAdapter = new DatabaseAdapter(this);
        dbAdapter.open();

        mListFiles = new ListFiles();
        autoLoadListDownload();
        mFileAdapter = new FileAdapter(getBaseContext(), mListFiles);

        mListView = (ListView) findViewById(R.id.listFileDownload);
        mListView.setAdapter(mFileAdapter);
        registerForContextMenu(mListView);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                posLink = position;
                return false;
            }
        });
    }

    public void autoLoadListDownload() {
        Cursor mCursor = dbAdapter.getAllLinkDownload();
        if (mCursor.moveToFirst()) {
            do {
                addLinkDownload(mCursor);
            } while (mCursor.moveToNext());
        }
    }

    public void addLinkDownload(Cursor mCursor) {
        int rowId = mCursor.getInt(0);
        String linkDownload = mCursor.getString(1);
        String saveTo = mCursor.getString(2);
        String fileName = mCursor.getString(3);
        int fileStatus = mCursor.getInt(4);
        FileModel fileDownload = new FileModel(rowId, linkDownload, saveTo, fileName, fileStatus);
        mListFiles.addFileDownload(fileDownload);
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbAdapter.close();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.pause:
                break;
            case R.id.resume:
                break;
            case R.id.stop:
                break;
            case R.id.redownload:
                break;
            case R.id.delete:
                deleteLinkDownload();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    //delete link download
    public void deleteLinkDownload() {
        dbAdapter.deleteLinkDownload(mListFiles.getFileDownload(posLink).getRowId());
        mListFiles.removeFileDownload(posLink);
        mFileAdapter.notifyDataSetChanged();
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
        dialogLink = new DialogLinkDownload(this,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText editLinkDownload = dialogLink.getLinkDownload();
                        String linkDownload = editLinkDownload.getText().toString();

                        String sSaveTo = mSetting.getEditSaveTo();
                        if (sSaveTo.length() != 0) {
                            if (URLUtil.isValidUrl(linkDownload)) {
                                downloadFile(linkDownload, sSaveTo);
                            } else {
                                dialogAnnounce = new DialogAnnounce(MainActivity.this,
                                        "CHECK URL",
                                        linkDownload + " not valid!");
                                dialogAnnounce.show();
                            }
                        } else {
                            clickSettingDownload();
                        }

                        dialogLink.dismiss();
                    }
                });
        dialogLink.show();
    }

    public void clickSettingDownload() {
        dialogSetting = new DialogSetting(this,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckBox chkNetAccess = dialogSetting.getChkNetAccess();
                        CheckBox chkNetConnect = dialogSetting.getChkNetConnect();
                        EditText editSaveTo = dialogSetting.getEditSaveTo();
                        SeekBar seekMaxActive = dialogSetting.getSeekMaxActive();
                        String saveTo = editSaveTo.getText().toString();
                        int isNetAccess = 0;
                        int isNetConnect = 0;
                        int seekMax = 1;
                        if (chkNetAccess.isChecked()) {
                            isNetAccess = 1;
                        }
                        if (chkNetConnect.isChecked()) {
                            isNetConnect = 1;
                        }
                        mSetting.setNewSetting(isNetAccess, isNetConnect, saveTo, seekMax);

                        dialogSetting.dismiss();
                    }
                });
        int isCheckedNetwork = mSetting.getCheckBoxNetWork();
        if (isCheckedNetwork == 0) {
            dialogSetting.getChkNetAccess().setChecked(false);
        } else {
            dialogSetting.getChkNetAccess().setChecked(true);
        }
        int isCheckedAutoResume = mSetting.getCheckBoxAutoResume();
        if (isCheckedAutoResume == 0) {
            dialogSetting.getChkNetConnect().setChecked(false);
        } else {
            dialogSetting.getChkNetConnect().setChecked(true);
        }
        dialogSetting.getEditSaveTo().setText(mSetting.getEditSaveTo());

        dialogSetting.show();
    }

    public String guessFileNameUrl(String sUrl) {
        String fileName = sUrl.substring(sUrl.lastIndexOf('/') + 1, sUrl.length());
        if (fileName.length() == 0) {
            fileName = "defaultfile";
        }
        return fileName;
    }

    public void downloadFile(String linkDownload, String saveTo) {
        String fileName = guessFileNameUrl(linkDownload);
        FileModel fileDownload = new FileModel(linkDownload, saveTo, fileName);

        int rowId = dbAdapter.insertLinkDownload(linkDownload, saveTo, fileName);
        fileDownload.setRowId(rowId);
        mListFiles.addFileDownload(fileDownload);
        mFileAdapter.notifyDataSetChanged();

        DownloadController ctr = new DownloadController(getBaseContext(), fileDownload, dbAdapter);
        ctr.downloadFile();

    }
}
