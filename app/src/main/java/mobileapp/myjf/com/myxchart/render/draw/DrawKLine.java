package mobileapp.myjf.com.myxchart.render.draw;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.sql.Ref;
import java.util.List;

import mobileapp.myjf.com.myxchart.data.entity.util.KLineData;
import mobileapp.myjf.com.myxchart.ui.onclicklistener.SecondaryClickListener;
import mobileapp.myjf.com.myxchart.utils.calculation.LocalToView;
import mobileapp.myjf.com.myxchart.data.entity.localdata.KLineLocal;
import mobileapp.myjf.com.myxchart.data.entity.render.KLineRender;
import mobileapp.myjf.com.myxchart.data.entity.util.KLineItem;
import mobileapp.myjf.com.myxchart.data.global.GlobalViewsUtil;
import mobileapp.myjf.com.myxchart.render.background.KLineBackgroundView;
import mobileapp.myjf.com.myxchart.render.data.KLineView;
import mobileapp.myjf.com.myxchart.ui.layout.KLineMainLayout;
import mobileapp.myjf.com.myxchart.ui.layout.KLineSecondaryLayout;
import mobileapp.myjf.com.myxchart.ui.ontouchlistener.KLineOnTouchListener;
import mobileapp.myjf.com.myxchart.utils.uitools.RefreshHelper;

public class DrawKLine {

    public static void drawKLine(Activity activity, List<KLineData> kLineDatas) {

        // 获取响应点击事件的控件
        LinearLayout kLineLayout = GlobalViewsUtil.getKLineLayout(activity);
        // 根据本地数据计算得出渲染数据对象
        KLineRender kLineRender = LocalToView.getKLineRender(activity, kLineDatas);
        // 根据渲染数据对象渲染K线数据
        RefreshHelper.refreshMainView(activity, kLineRender);
        // 设置触摸事件监听，完成十字线显示、拖动、双击全屏等事件
        kLineLayout.setOnTouchListener(new KLineOnTouchListener(activity, kLineRender, kLineDatas));
        // 设置副图指标点击事件（用于切换MACD、RSI等指标的数据）
        initSecondaryListener(activity);

    }


    public static void initSecondaryListener(Activity activity) {
        List<RelativeLayout> views = GlobalViewsUtil.getTypes(activity);
        View.OnClickListener secondaryListener = new SecondaryClickListener(activity);
        for(int i = 0;i <= 3;i++){
            views.get(i).setOnClickListener(secondaryListener);
        }
    }

}
