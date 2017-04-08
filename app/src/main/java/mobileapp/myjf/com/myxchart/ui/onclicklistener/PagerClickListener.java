package mobileapp.myjf.com.myxchart.ui.onclicklistener;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mobileapp.myjf.com.myxchart.R;
import mobileapp.myjf.com.myxchart.calculation.PXUtils;
import mobileapp.myjf.com.myxchart.data.global.Cache;
import mobileapp.myjf.com.myxchart.data.domain.GetKLineOriginal;
import mobileapp.myjf.com.myxchart.data.entity.localdata.KLineLocal;
import mobileapp.myjf.com.myxchart.data.global.Variable;
import mobileapp.myjf.com.myxchart.render.data.KLineView;
import mobileapp.myjf.com.myxchart.render.draw.DrawKLine;
import mobileapp.myjf.com.myxchart.ui.KTLineFragment;
import mobileapp.myjf.com.myxchart.ui.layout.KLineMainLayout;
import mobileapp.myjf.com.myxchart.ui.layout.KLineSecondaryLayout;
import mobileapp.myjf.com.myxchart.ui.subscriber.GetMainSubscriber;

/**
 * Created by nethanhan on 2017/4/7.
 */

public class PagerClickListener implements View.OnClickListener {

    private List<TextView> titles;
    private List<View> titleIndectors;
    private RelativeLayout timeLineLayout;
    private LinearLayout kLineLayout;
    private Activity context;
    private HorizontalScrollView pagerSelecter;
    private KTLineFragment ktLineFragment;

    public PagerClickListener(KTLineFragment ktLineFragment,List<TextView> titles, List<View> titleIndectors, RelativeLayout timeLineLayout, LinearLayout kLineLayout, Activity context, HorizontalScrollView pagerSelecter) {
        this.titles = titles;
        this.titleIndectors = titleIndectors;
        this.timeLineLayout = timeLineLayout;
        this.kLineLayout = kLineLayout;
        this.context = context;
        this.pagerSelecter = pagerSelecter;
        this.ktLineFragment = ktLineFragment;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_fenshi:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(context, 0), 0);
                setTab(0);
                break;
            case R.id.tv_rik:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(context, 0), 0);
                setTab(1);
                break;
            case R.id.tv_60fen:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(context, 0), 0);
                setTab(2);
                break;
            case R.id.tv_zhouk:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(context, 30), 0);
                setTab(3);
                break;
            case R.id.tv_yuek:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(context, 90), 0);
                setTab(4);
                break;
            case R.id.tv_1fen:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(context, 150), 0);
                setTab(5);
                break;
            case R.id.tv_5fen:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(context, 210), 0);
                setTab(6);
                break;
            case R.id.tv_30fen:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(context, 210), 0);
                setTab(7);
                break;
            case R.id.tv_240fen:
                pagerSelecter.smoothScrollTo(PXUtils.dip2px(context, 210), 0);
                setTab(8);
                break;
        }
    }

    public void clearStatus() {
        for (TextView tv : titles) {
            tv.setTextColor(Color.BLACK);
        }
        for (View v : titleIndectors) {
            v.setVisibility(View.GONE);
        }
    }

    public void setTab(int index) {
        clearStatus();
        clearSecondaryStatus();
        titles.get(index).setTextColor(Color.RED);
        titleIndectors.get(index).setVisibility(View.VISIBLE);
        switch (index) {
            case 0:
                timeLineLayout.setVisibility(View.VISIBLE);
                kLineLayout.setVisibility(View.INVISIBLE);
                break;
            case 1:case 2:case 3:case 4:case 5:case 6:case 7:case 8:
                initKLineDatas(index);
                timeLineLayout.setVisibility(View.INVISIBLE);
                kLineLayout.setVisibility(View.VISIBLE);

                break;
        }

    }

    public void initKLineDatas(int type) {

        String[] types = new String[]{"fenshi","rik","60fen","zhouk","yuek","1fen","5fen","30fen","240fen"};
        String[] requestTypes = new String[]{"","Day","60","Week","Month","1","5","30","240"};

        KLineMainLayout kLineMainLayout = (KLineMainLayout) context.findViewById(R.id.layout_main);
        KLineSecondaryLayout kLineSecondaryLayout = (KLineSecondaryLayout)context.findViewById(R.id.layout_secondary);
        kLineSecondaryLayout.removeAllViews();
        kLineMainLayout.removeAllViews();
        KLineView kLineView = new KLineView(context);

        if (Cache.getkLineLocals().get(types[type]) == null) {


            /**
             * 根据给定请求类型进行网络请求，并在请求结果中添加子控件展示图标
             *
             * @param context
             */
            // 初始化上下文对象
            // 进行K线请求
            GetKLineOriginal getKLineList = new GetKLineOriginal();
            getKLineList.setType(requestTypes[type]);
            getKLineList.setSyncOrgCode("QL");
            getKLineList.setProductCode("QLOIL10T");
            getKLineList.execute(new GetMainSubscriber(context, kLineMainLayout, kLineSecondaryLayout,35,kLineLayout,0,type));

            ktLineFragment.kLineType = type;
        }else{
            KLineLocal kLineLocal= Cache.getkLineLocals().get(types[type]);
            DrawKLine.drawKLine(context,kLineLocal,kLineLayout,kLineMainLayout,kLineSecondaryLayout,0,35,type);

        }

    }

    public void clearSecondaryStatus() {
        List<RelativeLayout> views = new ArrayList<>();
        views.add((RelativeLayout) ((Activity)context).findViewById(R.id.rl_macd));
        views.add((RelativeLayout) ((Activity)context).findViewById(R.id.rl_rsi));
        views.add((RelativeLayout) ((Activity)context).findViewById(R.id.rl_bias));
        views.add((RelativeLayout) ((Activity)context).findViewById(R.id.rl_kdj));

        for (RelativeLayout rl : views) {
            ((TextView) rl.getChildAt(0)).setTextColor(Color.BLACK);
            rl.setBackgroundColor(Color.WHITE);
        }
    }

}
