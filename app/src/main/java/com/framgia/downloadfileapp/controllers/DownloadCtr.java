package com.framgia.downloadfileapp.controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.os.PowerManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by framgiavn on 03/03/2015.
 */
public class DownloadCtr {
    private class DownloadTask extends AsyncTask<String, Integer, String> {
        private Context mContext;
        private PowerManager.WakeLock mWakeLock;
        private String mLocationSave;

        public DownloadTask(Context context, String locationSave) {
            mContext = context;
            mLocationSave = locationSave;
        }


        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL urlDownload = new URL(sUrl[0]);
                connection = (HttpURLConnection) urlDownload.openConnection();
                connection.connect();

                //HTTP 200 OK
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                //this will be useful to display download percentage
                //might be -1: if server did not report the length
                int fileLength = connection.getContentLength();

                //download the file
                input = connection.getInputStream();
                output = new FileOutputStream(mLocationSave);

                byte data[] = new byte[4096];
                long total = 0;
                int count;

                while ((count = input.read(data)) != -1) {
                    //allow cancelling
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    //percentage
                    if (fileLength > 0) {
                        publishProgress((int) total * 100 / fileLength);
                    }
                    output.write(data, 0, count);
                }
            } catch (Exception ex) {
                return ex.toString();
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                    if (input != null) {
                        input.close();
                    }
                } catch (IOException ioEx) {
                }

                if (connection != null) {
                    connection.disconnect();
                }
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //take CPU lock
            PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
            mWakeLock.acquire();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mWakeLock.release();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
}
