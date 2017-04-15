package mobileapp.myjf.com.myxchart.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import mobileapp.myjf.com.myxchart.GoodsDetailActivity;
import mobileapp.myjf.com.myxchart.R;
import mobileapp.myjf.com.myxchart.ui.onclicklistener.PagerClickListener;
import mobileapp.myjf.com.myxchart.utils.global.GlobalViewsUtil;
import mobileapp.myjf.com.myxchart.utils.global.Variable;
import mobileapp.myjf.com.myxchart.utils.other.RequestHelper;

/**
 * Created by yuankai on 17/4/12.
 */

public class FullScreenActivity extends AppCompatActivity {

    int requestType = 0;
    Timer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(Variable.getProductName());
        }
        setContentView(R.layout.activity_full_screen);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Variable.setFullSelectedType(0);
        // 初始化图表类型点击事件
        initClickListener(this);
        // 初始化分时线布局及内容（进行第一次渲染）
        if (Variable.getNormalSelectedType() == 0) {
            RequestHelper.getTimeLineDatas(this);
        } else {
            RequestHelper.getKLineDatas(this,Variable.getNormalSelectedType());
            GlobalViewsUtil.getTitles(this).get(Variable.getNormalSelectedType()).performClick();
        }

        timer = new Timer();
        timer.schedule(task, 1000 * 60, 1000 * 60);
    }

    /**
     * 初始化图表类型栏的点击事件
     *
     * @param activity 上下文对象
     */
    public static void initClickListener(Activity activity) {
        // 获取图表类型栏的控件对象
        List<TextView> titles = GlobalViewsUtil.getTitles(activity);
        // 遍历控件对象并设置点击事件
        for (TextView tv : titles) {
            tv.setOnClickListener(new PagerClickListener(activity));
        }

    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        Intent intent = new Intent(this,GoodsDetailActivity.class);
        intent.putExtra("token",Variable.getToken());
        intent.putExtra("organizationCode",Variable.getOrganizationCode());
        intent.putExtra("productCode",Variable.getProductCode());
        intent.putExtra("productName",Variable.getProductName());
        startActivity(intent);
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if (Variable.getFullSelectedType() == 0) {
                RequestHelper.getTimeLineDatas(FullScreenActivity.this);
            } else {
                RequestHelper.getKLineDatas(FullScreenActivity.this,Variable.getFullSelectedType());
            }
        }
    };

}
