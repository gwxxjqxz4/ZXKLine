package mobileapp.myjf.com.myxchart.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import mobileapp.myjf.com.myxchart.R;
import mobileapp.myjf.com.myxchart.calculation.PXUtils;
import mobileapp.myjf.com.myxchart.render.TimeLineLayout;

/**
 * create by gwx
 * K线界面，该界面用于展示K线图与分时线图
 */
public class LineActivity extends Activity {

    // K线图与分时线图的容器，用于请求网络数据并传递给K线图和分时线图，以及设置对应参数等
    TimeLineLayout drawLineLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
//
//        LinearLayout lineSdkLayout = new LinearLayout(this);
//        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
//        int screenHeight = wm.getDefaultDisplay().getHeight();
//        int height = screenHeight - PXUtils.dip2px(this,50 + 120 + 30 + 40);
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)lineSdkLayout.getLayoutParams();
//        layoutParams.setMargins();

    }



}
