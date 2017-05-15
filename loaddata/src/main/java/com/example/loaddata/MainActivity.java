package com.example.loaddata;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 网页读取数据显示在界面上
 */

public class MainActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData("http://www.lrcgc.com/lrc-1680-28586/安又琪-最幸福的孩子.lrc");

    }

    private void loadData(String url) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {

                try {
                    InputStream in = new URL(params[0]).openStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in,"utf-8"));
                    String line = null;
                    StringBuilder content = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        content.append(line+"\n");
                    }
                    in.close();

                    return content.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s!=null){
                    tv.setText(s);
                }else {
                    tv.setText("加载失败");
                }
            }
        }.execute(url);
    }


}
