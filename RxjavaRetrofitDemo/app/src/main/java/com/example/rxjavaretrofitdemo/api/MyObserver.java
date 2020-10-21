package com.example.rxjavaretrofitdemo.api;


import android.app.ProgressDialog;
import android.content.Context;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
    //抽象出observer,每次只需要调用接口
public class MyObserver<T> implements Observer<T> {
    private ObserverOnNextListener listener;
    private Context context;
    private ProgressDialog progressDialog;
    public MyObserver(Context context, ObserverOnNextListener listener){
        this.context = context;
        this.listener = listener;
    }
    @Override
    public void onSubscribe(Disposable d) {
        if(progressDialog == null){
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("download...");
            progressDialog.show();
        }
    }

    @Override
    public void onNext(T t) {
        listener.onNext(t);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }
}
