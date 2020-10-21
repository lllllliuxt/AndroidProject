package com.example.rxmvp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.rxmvp.mvp.MainPresenter;
import com.example.rxmvp.mvp.MainView;
import com.example.rxmvp.utity.Weather;
import com.example.rxmvp.utity.WeatherInfo;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements MainView {
    private ProgressDialog progressDialog;
    private Button button;
    private TextView text;

    private MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter();
        findViewById();
        onClicked();
    }

    private void onClicked(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.loadData();
            }
        });
    }

    private void findViewById(){
        button = findViewById(R.id.btn);
        text = findViewById(R.id.text);
    }

    @Override
    protected void onResume() {
        mainPresenter.attachView(this);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mainPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void getDataSuccess(WeatherInfo weatherInfo) {
        Weather weather = weatherInfo.getWeather();

        String showData = "城市：" + weather.getCity()
                + "\n风向："+ weather.getWD()
                + "\n风级：" + weather.getWS()
                + "\n发布时间：" + weather.getTime();
        text.setText(showData);
    }

    @Override
    public void getDataFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setTitle("download...");
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }
}