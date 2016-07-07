package com.example.yujimomoi.a1daysummerintern;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class FileManager extends Activity {

    private BufferedReader bufferedReader;

    public FileManager() {};

    // ファイルを保存
    public void saveFile(String file, String str) {
        FileOutputStream fileOutputstream = null;

        try {
            fileOutputstream = openFileOutput(file, Context.MODE_PRIVATE);
            fileOutputstream.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // ファイルを読み出し
    public void readFile(String file) {
        FileInputStream fileInputStream;
        bufferedReader = null;

        try {
            fileInputStream = openFileInput(file);

            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream,"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String GetFileOneLine() {
        String str = null;
        return str;
    }

    public String GetFileAll() {
        String str = null;
        return str;
    };
}
