package com.framgia.downloadfileapp;

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.framgia.downloadfileapp.controllers.DownloadController;

/**
 * Created by framgiavn on 04/03/2015.
 */
public class DownloadCtrTest extends InstrumentationTestCase {

    public void testDownload() throws Exception {

        final Context testContext = getInstrumentation().getTargetContext();
        String sUrl = "http://mp3.zing.vn/bai-hat/Chac-Ai-Do-Se-Ve-Son-Tung-M-TP/ZW6EO0Z8.html";
        String saveTo = "/sdcard/downloader/test.html";
        DownloadController ctr = new DownloadController(testContext);
        ctr.downloadFile(sUrl, saveTo);

    }
}
