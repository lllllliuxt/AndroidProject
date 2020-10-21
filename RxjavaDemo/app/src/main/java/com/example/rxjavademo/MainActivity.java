package com.example.rxjavademo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private ImageView image;
    private static final String  PATH1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603187924663&di=0760e99c01109d4927a9b642ee5ae31c&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ffront%2F342%2Fw700h442%2F20190321%2FxqrY-huqrnan7527352.jpg";
    private Button bn1;
    private Button bn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.img);
        bn1 = findViewById(R.id.btn1);
        bn2 = findViewById(R.id.bn2);
        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImageAction(v);
            }
        });

        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxjavaDownloadImageAction(v);
            }
        });
    }

//    传统方式下载
    public void downloadImageAction(View view){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("download.....");
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(PATH1);
                    URLConnection urlConnection = url.openConnection();
                    HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
                    httpURLConnection.setConnectTimeout(5000);
// 此刻开始下载
                    int responseCode = httpURLConnection.getResponseCode();
                    if(HttpURLConnection.HTTP_OK == responseCode){
//                        Log.e("lxt", String.valueOf(responseCode));
                        Bitmap bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                        Message message = handler.obtainMessage();
                        message.obj = bitmap;
                        handler.sendMessage(message);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
// final 防止内存泄漏
    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            progressDialog.dismiss();
            Bitmap bitmap = (Bitmap) msg.obj;
            image.setImageBitmap(bitmap);
            return false;
        }
    });

//    Rxjava 思维
    public void rxjavaDownloadImageAction(View view){
//        起点
//        第二步 分发事件
        Observable.just(PATH1)
//                第三步 事件转换
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws Exception {
                        URL url = new URL(s);
                        URLConnection urlConnection = url.openConnection();
                        HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
                        httpURLConnection.setConnectTimeout(5000);
                        int responseCode = httpURLConnection.getResponseCode();
                        if(HttpURLConnection.HTTP_OK == responseCode){
                            Bitmap bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                            return bitmap;
                        }
                        return null;
                    }
                })
//         给上游分配异步线程
         .subscribeOn(Schedulers.io())
//         给下游分配安卓主线程
          .observeOn(AndroidSchedulers.mainThread())
//        订阅 起点和终点关联起来
        .subscribe(
//                终点
                new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        第一步 订阅事件
                        progressDialog = new ProgressDialog(MainActivity.this);
                        progressDialog.setTitle("Rxjava run.....");
                        progressDialog.show();
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
//                      第四步
                        image.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
//                        最终点 第五步
                        if(progressDialog != null){
                            progressDialog.dismiss();
                        }

                    }
                }
        );
    }
}