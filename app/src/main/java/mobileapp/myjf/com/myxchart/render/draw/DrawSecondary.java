package mobileapp.myjf.com.myxchart.render.draw;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

import mobileapp.myjf.com.myxchart.data.entity.localdata.KLineLocal;
import mobileapp.myjf.com.myxchart.data.entity.render.KLineRender;
import mobileapp.myjf.com.myxchart.data.entity.util.KLineData;
import mobileapp.myjf.com.myxchart.data.entity.util.KLineItem;
import mobileapp.myjf.com.myxchart.data.global.GlobalViewsUtil;
import mobileapp.myjf.com.myxchart.data.global.Variable;
import mobileapp.myjf.com.myxchart.render.background.KLineBackgroundView;
import mobileapp.myjf.com.myxchart.render.data.KLineView;
import mobileapp.myjf.com.myxchart.render.data.SecondaryView;
import mobileapp.myjf.com.myxchart.ui.layout.KLineMainLayout;
import mobileapp.myjf.com.myxchart.ui.layout.KLineSecondaryLayout;
import mobileapp.myjf.com.myxchart.ui.onclicklistener.SecondaryClickListener;
import mobileapp.myjf.com.myxchart.ui.ontouchlistener.KLineOnTouchListener;
import mobileapp.myjf.com.myxchart.utils.calculation.LocalToView;
import mobileapp.myjf.com.myxchart.utils.uitools.RefreshHelper;

/**
 * Created by gwx
 */

public class DrawSecondary {

    public static void drawSecondary(Activity activity, List<KLineData> kLineDatas) {

        // 获取副图类型
        int secondaryType = Variable.getSecondaryType();
        // 本地数据经过计算得出副图数据
        KLineRender kLineRender = LocalToView.getKLineRender(activity,kLineDatas);
        // 根据所传参数刷新副图数据
        RefreshHelper.refreshSecondaryView(activity,kLineRender,secondaryType);

    }



}
