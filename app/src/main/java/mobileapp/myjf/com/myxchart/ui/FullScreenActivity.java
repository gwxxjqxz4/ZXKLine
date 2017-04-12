package mobileapp.myjf.com.myxchart.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import mobileapp.myjf.com.myxchart.R;
import mobileapp.myjf.com.myxchart.ui.onclicklistener.PagerClickListener;
import mobileapp.myjf.com.myxchart.utils.calculation.PXUtils;
import mobileapp.myjf.com.myxchart.utils.global.GlobalViewsUtil;
import mobileapp.myjf.com.myxchart.utils.global.Variable;
import mobileapp.myjf.com.myxchart.utils.other.RefreshHelper;
import mobileapp.myjf.com.myxchart.utils.other.RequestHelper;

/**
 * Created by yuankai on 17/4/12.
 */

public class FullScreenActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Variable.setSelectedType(0);
        // 初始化图表类型点击事件
        initClickListener(this);
        // 初始化分时线布局及内容（进行第一次渲染）
        RequestHelper.getTimeLineDatas(this);
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


}
