package mobileapp.myjf.com.myxchart.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mobileapp.myjf.com.myxchart.R;

/**
 * create by gwx
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 按钮K线视图的点击事件，点击进入K线图界面中
     *
     * @param view
     */
    public void goToKLine(View view){
        Intent intent = new Intent(this,KTLineActivity.class);
        startActivity(intent);
    }
}
