package com.framgia.downloadfileapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.framgia.downloadfileapp.R;
import com.framgia.downloadfileapp.models.FileModel;
import com.framgia.downloadfileapp.models.ListFiles;

/**
 * Created by framgiavn on 05/03/2015.
 */
public class FileAdapter extends BaseAdapter {

    private final Context mContext;
    private ListFiles mListFiles;

    public FileAdapter(Context context, ListFiles listFiles) {
        mContext = context;
        mListFiles = listFiles;
    }

    @Override
    public int getCount() {
        return mListFiles.getListFiles().size();
    }

    @Override
    public Object getItem(int position) {
        return mListFiles.getListFiles().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vHolder;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item_download, parent, false);
            vHolder = new ViewHolder();
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        vHolder.fileName = (TextView) convertView.findViewById(R.id.fileName);
        vHolder.fileInfo = (TextView) convertView.findViewById(R.id.fileInfo);
        vHolder.fileSpeed = (TextView) convertView.findViewById(R.id.fileSpeed);
        vHolder.fileProgress = (ProgressBar) convertView.findViewById(R.id.fileProgress);

        FileModel fileDownload = mListFiles.getListFiles().get(position);
        vHolder.fileName.setText(fileDownload.getLinkDownload());
        vHolder.fileProgress.setIndeterminate(false);
        vHolder.fileProgress.setMax(100);
        if (fileDownload.getFileStatus() != 0) {
            vHolder.fileProgress.setProgress(100);
        } else {
            vHolder.fileProgress.setProgress(0);
        }

        return convertView;
    }

    private class ViewHolder {
        TextView fileName, fileInfo, fileSpeed;
        ProgressBar fileProgress;
    }
}
