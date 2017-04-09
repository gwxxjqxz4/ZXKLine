package mobileapp.myjf.com.myxchart.ui.onclicklistener;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.TooManyListenersException;

import mobileapp.myjf.com.myxchart.R;
import mobileapp.myjf.com.myxchart.data.entity.render.KLineRender;
import mobileapp.myjf.com.myxchart.data.global.Data;
import mobileapp.myjf.com.myxchart.render.draw.DrawSecondary;
import mobileapp.myjf.com.myxchart.ui.ontouchlistener.KLineOnTouchListener;
import mobileapp.myjf.com.myxchart.utils.calculation.PXUtils;
import mobileapp.myjf.com.myxchart.data.global.Cache;
import mobileapp.myjf.com.myxchart.data.domain.GetKLineOriginal;
import mobileapp.myjf.com.myxchart.data.entity.localdata.KLineLocal;
import mobileapp.myjf.com.myxchart.data.global.GlobalViewsUtil;
import mobileapp.myjf.com.myxchart.data.global.Variable;
import mobileapp.myjf.com.myxchart.render.draw.DrawKLine;
import mobileapp.myjf.com.myxchart.ui.subscriber.GetKLineSubscriber;
import mobileapp.myjf.com.myxchart.utils.uitools.RefreshHelper;

/**
 * Created by gwx
 */

public class PagerClickListener implements View.OnClickListener {

    private Activity activity;
    private List<TextView> titles;
    private List<View> titleIndectors;
    private RelativeLayout timeLineLayout;
    private LinearLayout kLineLayout;
    private HorizontalScrollView pagerSelecter;
    private boolean hasBackground = false;

    public PagerClickListener(Activity activity) {

        this.activity = activity;
        titles = GlobalViewsUtil.getTitles(activity);
        titleIndectors = GlobalViewsUtil.getTitleIndectors(activity);
        timeLineLayout = GlobalViewsUtil.getTimeLineLayout(activity);
        kLineLayout = GlobalViewsUtil.getKLineLayout(activity);
        pagerSelecter = GlobalViewsUtil.getPagerSelecter(activity);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_fenshi:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 0), 0);
                setTab(0);
                break;
            case R.id.tv_rik:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 0), 0);
                setTab(1);
                break;
            case R.id.tv_60fen:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 0), 0);
                setTab(2);
                break;
            case R.id.tv_zhouk:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 30), 0);
                setTab(3);
                break;
            case R.id.tv_yuek:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 90), 0);
                setTab(4);
                break;
            case R.id.tv_1fen:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 150), 0);
                setTab(5);
                break;
            case R.id.tv_5fen:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 210), 0);
                setTab(6);
                break;
            case R.id.tv_30fen:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 210), 0);
                setTab(7);
                break;
            case R.id.tv_240fen:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(activity, 210), 0);
                setTab(8);
                break;
        }
    }

    private void clearStatus() {
        for (TextView tv : titles) {
            tv.setTextColor(Color.BLACK);
        }
        for (View v : titleIndectors) {
            v.setVisibility(View.GONE);
        }

        // 设置开始索引为0（重置滑动位置）
        Variable.setScrollStartPosition(0);
        // 清除主图和副图数据
        RefreshHelper.refreshMainView(activity, null);
        RefreshHelper.refreshSecondaryView(activity, null, 0);
    }

    private void setTab(int index) {
        clearStatus();
        clearSecondaryStatus();
        titles.get(index).setTextColor(Color.RED);
        titleIndectors.get(index).setVisibility(View.VISIBLE);
        switch (index) {
            case 0:
                timeLineLayout.setVisibility(View.VISIBLE);
                kLineLayout.setVisibility(View.INVISIBLE);
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                initKLineDatas(index);
                if (hasBackground == false) {
                    RefreshHelper.refreshSecondaryBackground(activity);
                    RefreshHelper.refreshMainBackground(activity);
                    hasBackground = true;
                }
                timeLineLayout.setVisibility(View.INVISIBLE);
                kLineLayout.setVisibility(View.VISIBLE);

                break;
        }

    }

    private void initKLineDatas(int type) {

        Variable.setSelectedType(type);

        Variable.setSelectedType(type);
        String[] types = new String[]{"", "Day", "60", "Week", "Month", "1", "5", "30", "240"};

        // 如果没有本地缓存则直接请求服务器数据并缓存
        if (Cache.getkLineLocals().get(types[type]) == null) {

            GetKLineOriginal getKLineList = new GetKLineOriginal();
            getKLineList.setType(types[type]);
            getKLineList.setSyncOrgCode("QL");
            getKLineList.setProductCode("QLOIL10T");
            getKLineList.execute(new GetKLineSubscriber(activity, type));
            kLineLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity,"开始请求网络",Toast.LENGTH_SHORT).show();
                }
            });

        }
        // 如果有本地缓存则先渲染本地缓存，再向服务器请求网络并缓存
        else {
            KLineLocal kLineLocal = Cache.getkLineLocals().get(types[type]);
            int selectedType = Variable.getSelectedType();
            if (type == selectedType) {
                DrawKLine.drawKLine(activity, kLineLocal);
                DrawSecondary.drawSecondary(activity, kLineLocal);
            }

            GetKLineOriginal getKLineList = new GetKLineOriginal();
            getKLineList.setType(types[type]);
            getKLineList.setSyncOrgCode("QL");
            getKLineList.setProductCode("QLOIL10T");
            getKLineList.execute(new GetKLineSubscriber(activity, type));
            kLineLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity,"开始请求网络",Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    private void clearSecondaryStatus() {

        List<RelativeLayout> types = GlobalViewsUtil.getTypes(activity);

        for (RelativeLayout rl : types) {
            ((TextView) rl.getChildAt(0)).setTextColor(Color.BLACK);
            rl.setBackgroundColor(Color.WHITE);
            Variable.setSecondaryType(0);
        }
    }

}
