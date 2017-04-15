package com.example.myxdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goToKLine(View v){
        Intent intent = new Intent(this,KTLineActivity.class);
        intent.putExtra("token","IOSMOBILECLIENT");
        intent.putExtra("organizationCode","QL");
        intent.putExtra("productCode","QLOIL10T");
        startActivity(intent);

    }
}