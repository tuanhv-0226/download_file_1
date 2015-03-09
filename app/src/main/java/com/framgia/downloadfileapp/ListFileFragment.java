package com.framgia.downloadfileapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.framgia.downloadfileapp.adapters.FileAdapter;
import com.framgia.downloadfileapp.models.ListFiles;

/**
 * Created by framgiavn on 05/03/2015.
 */
public class ListFileFragment extends Fragment implements
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private static final String TAG = "ListFileFragment";

    private FileAdapter mFileAdapter;
    private ListFiles mListFiles;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListFiles = new ListFiles();
        mFileAdapter = new FileAdapter(getActivity(), mListFiles);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_listfile, container, false);
        final ListView mListView = (ListView) view.findViewById(R.id.listItemDownload);
        //mListView.setAdapter(mFileAdapter);
        mListView.setOnItemClickListener(this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}
