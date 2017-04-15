package mobileapp.myjf.com.myxchart.ui.onclicklistener;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import mobileapp.myjf.com.myxchart.R;
import mobileapp.myjf.com.myxchart.utils.calculation.PXUtils;
import mobileapp.myjf.com.myxchart.utils.global.GlobalViewsUtil;
import mobileapp.myjf.com.myxchart.utils.global.Variable;
import mobileapp.myjf.com.myxchart.utils.other.RefreshHelper;
import mobileapp.myjf.com.myxchart.utils.other.RequestHelper;

/**
 * Created by gwx
 * 图表类型栏的点击事件监听，根据用户点击的图表类型（如分时、日K、60分等）进行相应处理
 */

public class PagerClickListener implements View.OnClickListener {

    // 上下文对象
    private Activity activity;
    // 图表类型标题栏
    private List<TextView> titles;
    // 标题栏指示器
    private List<View> titleIndectors;
    // 分时线布局
    private RelativeLayout timeLineLayout;
    // K线布局
    private LinearLayout kLineLayout;
    // 图表类型选择器滑动控件
    private HorizontalScrollView pagerSelecter;
    // 有无背景框
    private boolean hasBackground = false;

    // 构造方法，获取上个界面传来的上下文以及初始化控件对象
    public PagerClickListener(Activity activity) {
        this.activity = activity;
        titles = GlobalViewsUtil.getTitles(activity);
        titleIndectors = GlobalViewsUtil.getTitleIndectors(activity);
        timeLineLayout = GlobalViewsUtil.getTimeLineLayout(activity);
        kLineLayout = GlobalViewsUtil.getKLineLayout(activity);
        pagerSelecter = GlobalViewsUtil.getPagerSelecter(activity);

    }

    /**
     * 点击事件处理方法
     *
     * @param v 被点击的按钮
     */
    @Override
    public void onClick(View v) {
        if(R.id.tv_fenshi == v.getId()){
            // 界面选择器滚动布局移到相应位置使被选择项居中
            pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 0), 0);
            // 处理其他逻辑
            setTab(0);
        }
        if(R.id.tv_rik == v.getId()){
            // 界面选择器滚动布局移到相应位置使被选择项居中
            pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 0), 0);
            // 处理其他逻辑
            setTab(1);
        }
        if(R.id.tv_60fen == v.getId()){
            // 界面选择器滚动布局移到相应位置使被选择项居中
            pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 0), 0);
            // 处理其他逻辑
            setTab(2);
        }
        if(R.id.tv_zhouk == v.getId()){
            // 界面选择器滚动布局移到相应位置使被选择项居中
            pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 30), 0);
            // 处理其他逻辑
            setTab(3);
        }
        if(R.id.tv_yuek == v.getId()){
            // 界面选择器滚动布局移到相应位置使被选择项居中
            pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 90), 0);
            // 处理其他逻辑
            setTab(4);
        }
        if(R.id.tv_1fen == v.getId()){
            // 界面选择器滚动布局移到相应位置使被选择项居中
            pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 150), 0);
            // 处理其他逻辑
            setTab(5);
        }
        if(R.id.tv_5fen == v.getId()){
            // 界面选择器滚动布局移到相应位置使被选择项居中
            pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 210), 0);
            // 处理其他逻辑
            setTab(6);
        }
        if(R.id.tv_30fen == v.getId()){
            // 界面选择器滚动布局移到相应位置使被选择项居中
            pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 210), 0);
            // 处理其他逻辑
            setTab(7);
        }
        if(R.id.tv_240fen == v.getId()){
            // 界面选择器滚动布局移到相应位置使被选择项居中
            pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 210), 0);
            // 处理其他逻辑
            setTab(8);
        }
    }

    /**
     * 接收到点击事件时的处理方法
     *
     * @param index 被点击的按钮索引
     */
    private void setTab(int index) {
        // 重置界面状态（图表类型栏、副图类型栏、内容）
        clearStatus();
        // 改变被选择项的字体颜色
        titles.get(index).setTextColor(Color.parseColor("#ff4848"));
        // 显示被选择项的指示器
        titleIndectors.get(index).setVisibility(View.VISIBLE);
        // 若被选择项为分时线则隐藏K线布局，显示分时线布局，否则做相反处理
        if (index == 0) {
            // 请求服务器数据并刷新分时线布局
            RequestHelper.getTimeLineDatas(activity);
            // 显示分时线布局
            timeLineLayout.setVisibility(View.VISIBLE);
            // 隐藏K线布局
            kLineLayout.setVisibility(View.INVISIBLE);
        } else if (index <= 8 && index > 0) {
            // 请求K线数据（通过数据库、服务器）
            RequestHelper.getKLineDatas(activity, index);
            // 若第一次点击进入K线界面则渲染K线及副图背景
            if (!hasBackground) {
                RefreshHelper.refreshSecondaryBackground(activity);
                RefreshHelper.refreshMainBackground(activity);
                // 记录渲染状态，不做重复渲染
                hasBackground = true;
            }
            // 隐藏分时线布局
            timeLineLayout.setVisibility(View.INVISIBLE);
            // 显示K线布局
            kLineLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 重置页面状态的方法，使图表类型栏、标题栏处于未选择状态，清理图表内容
     */
    private void clearStatus() {
        // 遍历标题栏设置字体颜色
        for (TextView tv : titles) {
            tv.setTextColor(Color.parseColor("#676767"));
        }
        // 遍历标题栏隐藏所有指示器
        for (View v : titleIndectors) {
            v.setVisibility(View.GONE);
        }

        // 设置开始索引为0（重置滑动位置）
        Variable.setScrollStartPosition(0);
        // 清除主图和副图数据
        RefreshHelper.refreshMainView(activity, null);
        RefreshHelper.refreshSecondaryView(activity, null, 0);

        // 获取副图类型选择控件
        List<RelativeLayout> types = GlobalViewsUtil.getTypes(activity);
        // 遍历副图类型选择控件
        for (RelativeLayout rl : types) {
            // 字体颜色初始化
            ((TextView) rl.getChildAt(0)).setTextColor(Color.parseColor("#8a8a8a"));
            // 文字背景初始化
            rl.setBackgroundColor(Color.parseColor("#00000000"));
            // 设置被选择的副图类型为MACD
            Variable.setSecondaryType(0);
        }
    }

}
