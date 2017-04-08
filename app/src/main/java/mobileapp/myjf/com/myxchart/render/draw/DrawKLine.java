package mobileapp.myjf.com.myxchart.render.draw;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import mobileapp.myjf.com.myxchart.R;
import mobileapp.myjf.com.myxchart.calculation.LocalToView;
import mobileapp.myjf.com.myxchart.data.entity.localdata.KLineLocal;
import mobileapp.myjf.com.myxchart.data.entity.render.KLineRender;
import mobileapp.myjf.com.myxchart.data.entity.util.KLineItem;
import mobileapp.myjf.com.myxchart.render.background.KLineBackgroundView;
import mobileapp.myjf.com.myxchart.render.background.SecondaryBackgroundView;
import mobileapp.myjf.com.myxchart.render.data.KLineView;
import mobileapp.myjf.com.myxchart.render.data.MacdView;
import mobileapp.myjf.com.myxchart.render.data.RSIView;
import mobileapp.myjf.com.myxchart.ui.layout.KLineMainLayout;
import mobileapp.myjf.com.myxchart.ui.layout.KLineSecondaryLayout;
import mobileapp.myjf.com.myxchart.ui.onclicklistener.SecondaryClickListener;
import mobileapp.myjf.com.myxchart.ui.ontouchlistener.KLineOnTouchListener;

public class DrawKLine {

    public static void drawKLine(Context context,KLineLocal kLineLocal, LinearLayout kLineLayout, KLineMainLayout kLineMainLayout, KLineSecondaryLayout kLineSecondaryLayout,int secondaryType,int itemNumber,int kLineType) {
        KLineRender kLineRender = LocalToView.getKLineRender(kLineLocal, kLineMainLayout.getWidth(), kLineMainLayout.getHeight(), kLineMainLayout.getHeight(), 0, 35);
        List<KLineItem> kLineItems = kLineRender.getItems();

        // 声明显示K线的控件
        KLineView kLineView = new KLineView(context);
        // 将坐标点集合设置给负责显示的控件
        kLineView.setkLineItems(kLineRender);
        // 将负责显示的控件添加到容器控件（此控件）中
        kLineMainLayout.addView(kLineView);
        KLineBackgroundView kLineBackgroundView = new KLineBackgroundView(context);
        kLineMainLayout.addView(kLineBackgroundView);

        kLineRender = LocalToView.getKLineRender(kLineLocal, kLineSecondaryLayout.getWidth(), kLineSecondaryLayout.getHeight(), kLineSecondaryLayout.getHeight(), 0, 35);
        List<KLineItem> secondaryItems = kLineRender.getItems();
        // 声明显示MACD的控件
        View view;
        // 根据情况设置显示哪个副图
        if (secondaryType == 0) {
            view = new MacdView(context);
            ((MacdView) view).setCoordinates(kLineRender);
        } else {
            view = new RSIView(context);
            if (secondaryType == 1) {
                ((RSIView) view).setType(0);
                ((RSIView) view).setCoordinates(kLineRender);
            } else if (secondaryType == 2) {
                ((RSIView) view).setType(1);
                ((RSIView) view).setCoordinates(kLineRender);
            } else if (secondaryType == 3) {
                ((RSIView) view).setType(2);
                ((RSIView) view).setCoordinates(kLineRender);
            }
        }
        view.invalidate();
        // 将负责显示的控件添加到容器控件（此控件）中
        kLineSecondaryLayout.addView(view);
        SecondaryBackgroundView secondaryBackgroundView = new SecondaryBackgroundView(context);
        kLineSecondaryLayout.addView(secondaryBackgroundView);
        initSecondaryListener(context,kLineSecondaryLayout,kLineLayout,kLineItems,kLineType);
        kLineLayout.setOnTouchListener(new KLineOnTouchListener(context, kLineItems,secondaryItems, kLineMainLayout, kLineView, kLineLocal, itemNumber, kLineSecondaryLayout, view));

    }


    public static void initSecondaryListener(Context context,KLineSecondaryLayout kLineSecondaryLayout, LinearLayout kLineLayout,List<KLineItem> kLineItems,int kLineType){
        List<RelativeLayout> views = new ArrayList<>();
        views.add((RelativeLayout) ((Activity)context).findViewById(R.id.rl_macd));
        views.add((RelativeLayout) ((Activity)context).findViewById(R.id.rl_rsi));
        views.add((RelativeLayout) ((Activity)context).findViewById(R.id.rl_bias));
        views.add((RelativeLayout) ((Activity)context).findViewById(R.id.rl_kdj));

        kLineSecondaryLayout = (KLineSecondaryLayout)((Activity)context).findViewById(R.id.layout_secondary);

        View.OnClickListener secondaryListener = new SecondaryClickListener(views,kLineSecondaryLayout,kLineItems,(Activity) context,kLineLayout,kLineType);
        views.get(0).setOnClickListener(secondaryListener);
        views.get(1).setOnClickListener(secondaryListener);
        views.get(2).setOnClickListener(secondaryListener);
        views.get(3).setOnClickListener(secondaryListener);

    }

}
