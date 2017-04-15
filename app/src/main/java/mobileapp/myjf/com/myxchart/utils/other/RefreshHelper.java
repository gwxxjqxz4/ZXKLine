package mobileapp.myjf.com.myxchart.utils.other;

import android.app.Activity;
import android.widget.RelativeLayout;

import mobileapp.myjf.com.myxchart.R;
import mobileapp.myjf.com.myxchart.entity.render.KLineRender;
import mobileapp.myjf.com.myxchart.entity.render.TimeLineRender;
import mobileapp.myjf.com.myxchart.render.CoverView;
import mobileapp.myjf.com.myxchart.utils.global.GlobalViewsUtil;
import mobileapp.myjf.com.myxchart.render.background.KLineBackgroundView;
import mobileapp.myjf.com.myxchart.render.background.SecondaryBackgroundView;
import mobileapp.myjf.com.myxchart.render.background.TimeLineBackgroundView;
import mobileapp.myjf.com.myxchart.render.data.KLineView;
import mobileapp.myjf.com.myxchart.render.data.SecondaryView;
import mobileapp.myjf.com.myxchart.render.data.TimeLineView;
import mobileapp.myjf.com.myxchart.render.highlight.KLineHighLightView;
import mobileapp.myjf.com.myxchart.render.highlight.SecondaryHighLight;
import mobileapp.myjf.com.myxchart.render.highlight.TimeLineHighLightView;

/**
 * Created by gwx
 * 重新渲染图表用的类，用于渲染背景、数据、高亮线并显示在界面上
 */

public class RefreshHelper {

    public static void refreshTimeLineView(Activity activity, TimeLineRender timeLineRender) {

        RelativeLayout timeLineLayout = GlobalViewsUtil.getTimeLineLayout(activity);
        TimeLineView timeLineView = GlobalViewsUtil.getTimeLineView(activity);

        timeLineLayout.removeView(timeLineView);
        timeLineView.setTimeLineRender(timeLineRender);
        timeLineView.invalidate();
        timeLineLayout.addView(timeLineView);

    }

    public static void refreshTimeLineBackground(Activity activity) {

        RelativeLayout timeLineLayout = GlobalViewsUtil.getTimeLineLayout(activity);
        TimeLineBackgroundView timeLineBackground = GlobalViewsUtil.getTimeLineBackground(activity);

        timeLineLayout.removeView(timeLineBackground);
        timeLineBackground.invalidate();
        timeLineLayout.addView(timeLineBackground);

    }

    public static void refreshTimeLineHighLight(Activity activity, TimeLineRender timeLineRender, float moveX) {

        RelativeLayout timeLineLayout = GlobalViewsUtil.getTimeLineLayout(activity);
        TimeLineHighLightView timeLineHighLightView = GlobalViewsUtil.getTimeLineHighLight(activity);

        timeLineLayout.removeView(timeLineHighLightView);
        timeLineHighLightView.setMoveX(moveX);
        timeLineHighLightView.setTimeLineRender(timeLineRender);
        timeLineHighLightView.invalidate();
        timeLineLayout.addView(timeLineHighLightView);

    }

    public static void refreshCoverView(Activity activity, TimeLineRender timeLineRender, KLineRender kLineRender, float moveX) {

        RelativeLayout coverLayout = (RelativeLayout) activity.findViewById(R.id.rl_cover);
        CoverView coverView = (CoverView) GlobalViewsUtil.getCover(activity);

        coverLayout.removeView(coverView);
        coverView.setMoveX(moveX);
        coverView.setTimeLineRender(timeLineRender);
        coverView.setKLineRender(kLineRender);
        coverView.invalidate();
        coverLayout.addView(coverView);

    }

    public static void refreshMainView(Activity activity, KLineRender kLineRender) {

        RelativeLayout kLineMainLayout = GlobalViewsUtil.getMainLayout(activity);
        KLineView kLineView = GlobalViewsUtil.getKLineView(activity);

        kLineMainLayout.removeView(kLineView);
        kLineView.setKLineRender(kLineRender);
        kLineView.invalidate();
        kLineMainLayout.addView(kLineView);

    }

    public static void refreshMainBackground(Activity activity) {

        RelativeLayout kLineMainLayout = GlobalViewsUtil.getMainLayout(activity);
        KLineBackgroundView kLineBackground = GlobalViewsUtil.getKLineBackground(activity);

        kLineMainLayout.removeView(kLineBackground);
        kLineBackground.invalidate();
        kLineMainLayout.addView(kLineBackground);

    }

    public static void refreshMainHighLight(Activity activity, KLineRender kLineRender, float moveX) {

        RelativeLayout kLineMainLayout = GlobalViewsUtil.getMainLayout(activity);
        KLineHighLightView kLineHighLight = GlobalViewsUtil.getKLineHighLight(activity);

        kLineMainLayout.removeView(kLineHighLight);
        kLineHighLight.setMoveX(moveX);
        kLineHighLight.setKLineRender(kLineRender);
        kLineHighLight.invalidate();
        kLineMainLayout.addView(kLineHighLight);

    }

    public static void refreshSecondaryView(Activity activity, KLineRender secondaryRender, int secondaryType) {

        RelativeLayout kLineMainLayout = GlobalViewsUtil.getSecondaryLayout(activity);
        SecondaryView secondaryView = GlobalViewsUtil.getSecondaryView(activity);

        kLineMainLayout.removeView(secondaryView);
        secondaryView.setSecondaryType(secondaryType);
        secondaryView.setKLineRender(secondaryRender);
        secondaryView.invalidate();
        kLineMainLayout.addView(secondaryView);

    }

    public static void refreshSecondaryBackground(Activity activity) {

        RelativeLayout kLineMainLayout = GlobalViewsUtil.getSecondaryLayout(activity);
        SecondaryBackgroundView secondaryBackground = GlobalViewsUtil.getSecondaryBackground(activity);

        kLineMainLayout.removeView(secondaryBackground);
        secondaryBackground.invalidate();
        kLineMainLayout.addView(secondaryBackground);

    }

    public static void refreshSecondaryHighLight(Activity activity, KLineRender secondaryRender, float moveX) {

        RelativeLayout kLineMainLayout = GlobalViewsUtil.getSecondaryLayout(activity);
        SecondaryHighLight secondaryHighLight = GlobalViewsUtil.getSecondaryHighLight(activity);

        kLineMainLayout.removeView(secondaryHighLight);
        secondaryHighLight.setMoveX(moveX);
        secondaryHighLight.setKLineRender(secondaryRender);
        secondaryHighLight.invalidate();
        kLineMainLayout.addView(secondaryHighLight);

    }

}
