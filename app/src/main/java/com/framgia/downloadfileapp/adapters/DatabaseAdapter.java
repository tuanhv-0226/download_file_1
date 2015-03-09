package com.framgia.downloadfileapp.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by framgiavn on 08/03/2015.
 */
public class DatabaseAdapter {
    private static final String TAG = "DatabaseAdapter";
    private static final String DATABASE_NAME = "DownloadFileDB";
    private static final String DATABASE_TABLE = "listdownload";
    private static final int DATABASE_VERSION = 1;

    private static final String KEY_ROWID = "_id";
    private static final String KEY_LINKDOWNLOAD = "linkdownload";
    private static final String KEY_SAVETO = "saveto";
    private static final String KEY_FILENAME = "filename";
    private static final String KEY_FILESTATUS = "filestatus";

    private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + " ("
            + KEY_ROWID + " integer primary key autoincrement, "
            + KEY_LINKDOWNLOAD + " text not null, "
            + KEY_SAVETO + " text not null, "
            + KEY_FILENAME + " text not null, "
            + KEY_FILESTATUS + " integer not null);";
    private static final String DATABASE_UPGRADE = "DROP TABLE IF EXISTS listdownload";
    private static final String SELECT_LAST_INSERT = "SELECT last_insert_rowid();";

    private final Context dbContext;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DatabaseAdapter (Context context) {
        this.dbContext = context;
        this.dbHelper = new DatabaseHelper(context);
    }

    //opens the database
    public DatabaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    //closes the database
    public void close() {
        dbHelper.close();
    }

    //insert a file download to the database
    public int insertLinkDownload(String link, String saveTo, String fileName) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_LINKDOWNLOAD, link);
        initialValues.put(KEY_SAVETO, saveTo);
        initialValues.put(KEY_FILENAME, fileName);
        initialValues.put(KEY_FILESTATUS, 0);
        db.insert(DATABASE_TABLE, null, initialValues);
        Cursor mCursor = getRowIdLastInsert();
        return mCursor.getInt(0);
    }

    //retrieves rowId last insert
    public Cursor getRowIdLastInsert() throws SQLException {
        Cursor mCursor = db.rawQuery(SELECT_LAST_INSERT, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //delete a file download
    public boolean deleteLinkDownload(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //retrieves all link download
    public Cursor getAllLinkDownload() {
        return db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_LINKDOWNLOAD, KEY_SAVETO,
                        KEY_FILENAME, KEY_FILESTATUS}, null, null, null, null, null);
    }

    //retrieves a link download
    public Cursor getLinkDownload(long rowId) throws SQLException {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_LINKDOWNLOAD, KEY_SAVETO,
                KEY_FILENAME, KEY_FILESTATUS}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //update a link download
    public boolean updateLinkDownload(long rowId, int fileStatus) {
        ContentValues updateValues = new ContentValues();
        updateValues.put(KEY_FILESTATUS, fileStatus);
        return db.update(DATABASE_TABLE, updateValues, KEY_ROWID + "=" + rowId, null) > 0;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {


        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_UPGRADE);
            onCreate(db);
        }
    }
}
