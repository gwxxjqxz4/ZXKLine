package mobileapp.myjf.com.myxchart.ui.util;

import android.app.Activity;
import android.content.Context;
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
import mobileapp.myjf.com.myxchart.ui.KTLineFragment;
import mobileapp.myjf.com.myxchart.ui.onclicklistener.PagerClickListener;
import mobileapp.myjf.com.myxchart.ui.ontouchlistener.KLineOnTouchListener;

/**
 * Created by nethanhan on 2017/4/7.
 */

public class ClickControler {

    public static List initClickListener(KTLineFragment ktLineFragment,List<TextView> titles, List<View> titleIndectors,Activity context,HorizontalScrollView pagerSelecter,RelativeLayout timeLineLayout, LinearLayout kLineLayout){

        timeLineLayout = (RelativeLayout)context.findViewById(R.id.layout_timeline);
        kLineLayout = (LinearLayout)context.findViewById(R.id.layout_k_line);
        pagerSelecter = (HorizontalScrollView)context.findViewById(R.id.hsv_pager_select);

        titles = new ArrayList<>();
        titleIndectors = new ArrayList<>();
        titles.add((TextView) context.findViewById(R.id.tv_fenshi));
        titles.add((TextView) context.findViewById(R.id.tv_rik));
        titles.add((TextView) context.findViewById(R.id.tv_60fen));
        titles.add((TextView) context.findViewById(R.id.tv_zhouk));
        titles.add((TextView) context.findViewById(R.id.tv_yuek));
        titles.add((TextView) context.findViewById(R.id.tv_1fen));
        titles.add((TextView) context.findViewById(R.id.tv_5fen));
        titles.add((TextView) context.findViewById(R.id.tv_30fen));
        titles.add((TextView) context.findViewById(R.id.tv_240fen));

        titleIndectors.add(context.findViewById(R.id.view_fenshi));
        titleIndectors.add(context.findViewById(R.id.view_rik));
        titleIndectors.add(context.findViewById(R.id.view_60fen));
        titleIndectors.add(context.findViewById(R.id.view_zhouk));
        titleIndectors.add(context.findViewById(R.id.view_yuek));
        titleIndectors.add(context.findViewById(R.id.view_1fen));
        titleIndectors.add(context.findViewById(R.id.view_5fen));
        titleIndectors.add(context.findViewById(R.id.view_30fen));
        titleIndectors.add(context.findViewById(R.id.view_240fen));

        for(TextView tv:titles){
            tv.setOnClickListener(new PagerClickListener(ktLineFragment,titles,titleIndectors,timeLineLayout,kLineLayout,context,pagerSelecter));
        }

        List views = new ArrayList<>();
        views.add(titles);
        views.add(titleIndectors);
        views.add(kLineLayout);
        views.add(timeLineLayout);
        views.add(pagerSelecter);
        return views;

    }

}
