package com.example.rxjavaretrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.rxjavaretrofitdemo.api.ApiMethods;
import com.example.rxjavaretrofitdemo.api.ApiService;
import com.example.rxjavaretrofitdemo.api.MyObserver;
import com.example.rxjavaretrofitdemo.api.ObserverOnNextListener;
import com.example.rxjavaretrofitdemo.utity.Banner;
import com.example.rxjavaretrofitdemo.utity.GirlImg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private ApiService apiService;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download();
            }
        });
    }

    public void download() {
//        Observer<Banner> observer = new Observer<Banner>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                progressDialog = new ProgressDialog(MainActivity.this);
//                progressDialog.setTitle("download....");
//                progressDialog.show();
//            }
//
//            @Override
//            public void onNext(Banner banner) {
//                Log.d("status", banner.getStatus().toString());
//                List<GirlImg> list = banner.getData();
//                for (GirlImg girlImg : list) {
//                    Log.d("image", girlImg.getImage());
//                    Log.d("title", girlImg.getTitle());
//                    Log.d("url", girlImg.getUrl());
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                if(progressDialog != null){
//                    progressDialog.dismiss();
//                }
//            }
//        };

        ApiMethods.getBanner(new MyObserver<Banner>(this, new ObserverOnNextListener<Banner>() {
            @Override
            public void onNext(Banner banner) {
                Log.d("status", banner.getStatus().toString());
                List<GirlImg> list = banner.getData();
                for (GirlImg girlImg : list) {
                    Log.d("image", girlImg.getImage());
                    Log.d("title", girlImg.getTitle());
                    Log.d("url", girlImg.getUrl());
                }
            }
        }));
    }

    }
