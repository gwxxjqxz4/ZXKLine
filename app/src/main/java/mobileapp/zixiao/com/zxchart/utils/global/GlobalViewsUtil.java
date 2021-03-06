package mobileapp.zixiao.com.zxchart.utils.global;

import android.app.Activity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mobileapp.zixiao.com.zxchart.R;
import mobileapp.zixiao.com.zxchart.render.background.KLineBackgroundView;
import mobileapp.zixiao.com.zxchart.render.background.SecondaryBackgroundView;
import mobileapp.zixiao.com.zxchart.render.background.TimeLineBackgroundView;
import mobileapp.zixiao.com.zxchart.render.data.KLineView;
import mobileapp.zixiao.com.zxchart.render.data.SecondaryView;
import mobileapp.zixiao.com.zxchart.render.data.TimeLineView;
import mobileapp.zixiao.com.zxchart.render.highlight.KLineHighLightView;
import mobileapp.zixiao.com.zxchart.render.highlight.SecondaryHighLight;
import mobileapp.zixiao.com.zxchart.render.highlight.TimeLineHighLightView;

/**
 * 全局控件，用来保存本模块中使用到的所有控件
 */
public class GlobalViewsUtil {

    // 获取标题栏可滑动布局，用于滑动标题栏
    public static HorizontalScrollView getPagerSelecter(Activity activity){
        return (HorizontalScrollView)activity.findViewById(R.id.hsv_pager_select);
    }
    // 获取标题栏指示器，用于标识用户正在查看的图表类型
    public static List<View> getTitleIndectors(Activity activity){
        List<View> titleIndectors = new ArrayList<>();

        titleIndectors.add(activity.findViewById(R.id.view_fenshi));
        titleIndectors.add(activity.findViewById(R.id.view_rik));
        titleIndectors.add(activity.findViewById(R.id.view_60fen));
        titleIndectors.add(activity.findViewById(R.id.view_zhouk));
        titleIndectors.add(activity.findViewById(R.id.view_yuek));
        titleIndectors.add(activity.findViewById(R.id.view_1fen));
        titleIndectors.add(activity.findViewById(R.id.view_5fen));
        titleIndectors.add(activity.findViewById(R.id.view_30fen));
        titleIndectors.add(activity.findViewById(R.id.view_240fen));

        return titleIndectors;
    }
    // 获取标题栏，用于选择要查看的布局类型（如分时、日K等）
    public static List<TextView> getTitles(Activity activity){
        List<TextView> titles = new ArrayList<>();
        titles.add((TextView)activity.findViewById(R.id.tv_fenshi));
        titles.add((TextView)activity.findViewById(R.id.tv_rik));
        titles.add((TextView)activity.findViewById(R.id.tv_60fen));
        titles.add((TextView)activity.findViewById(R.id.tv_zhouk));
        titles.add((TextView)activity.findViewById(R.id.tv_yuek));
        titles.add((TextView)activity.findViewById(R.id.tv_1fen));
        titles.add((TextView)activity.findViewById(R.id.tv_5fen));
        titles.add((TextView)activity.findViewById(R.id.tv_30fen));
        titles.add((TextView)activity.findViewById(R.id.tv_240fen));
        return titles;
    }
    // 获取副图标题栏，用于选择要查看的副图类型（如MACD、RSI等）
    public static List<RelativeLayout> getTypes(Activity activity){
        List<RelativeLayout> types = new ArrayList<>();

        types.add((RelativeLayout)activity.findViewById(R.id.rl_macd));
        types.add((RelativeLayout)activity.findViewById(R.id.rl_rsi));
        types.add((RelativeLayout)activity.findViewById(R.id.rl_bias));
        types.add((RelativeLayout)activity.findViewById(R.id.rl_kdj));

        return types;
    }
    // 获取分时线总布局，用于容纳分时线图
    public static RelativeLayout getTimeLineLayout(Activity activity){
        return (RelativeLayout)activity.findViewById(R.id.layout_timeline);
    }
    // 获取K线总布局，用于容纳K线主图和副图
    public static LinearLayout getKLineLayout(Activity activity){
        return (LinearLayout)activity.findViewById(R.id.layout_k_line);
    }
    // 获取K线主图布局，用于容纳K线主图
    public static RelativeLayout getMainLayout(Activity activity){
        return (RelativeLayout) activity.findViewById(R.id.layout_main);
    }
    // 获取K线副图布局，用于容纳K线副图
    public static RelativeLayout getSecondaryLayout(Activity activity){
        return (RelativeLayout) activity.findViewById(R.id.layout_secondary);
    }
    // 获取分时线控件，用于渲染分时线数据
    public static TimeLineView getTimeLineView(Activity activity){
        return (TimeLineView)activity.findViewById(R.id.view_time);
    }
    // 获取分时线背景控件，用于渲染分时线背景
    public static TimeLineBackgroundView getTimeLineBackground(Activity activity){
        return (TimeLineBackgroundView)activity.findViewById(R.id.background_time);
    }
    // 获取分时线高亮控件，用于渲染分时线高亮线
    public static TimeLineHighLightView getTimeLineHighLight(Activity activity){
        return (TimeLineHighLightView)activity.findViewById(R.id.highlight_time);
    }
    // 获取K线控件，用于渲染K线数据
    public static KLineView getKLineView(Activity activity){
        return (KLineView) activity.findViewById(R.id.view_kline);
    }
    // 获取K线背景控件，用于渲染K线背景
    public static KLineBackgroundView getKLineBackground(Activity activity){
        return (KLineBackgroundView)activity.findViewById(R.id.background_kline);
    }
    // 获取K线高亮控件，用于渲染K线高亮线
    public static KLineHighLightView getKLineHighLight(Activity activity){
        return (KLineHighLightView)activity.findViewById(R.id.highlight_kline);
    }
    // 获取K线副图控件，用于渲染K线副图
    public static SecondaryView getSecondaryView(Activity activity){
        return (SecondaryView) activity.findViewById(R.id.view_secondary);
    }
    // 获取K线副图背景控件
    public static SecondaryBackgroundView getSecondaryBackground(Activity activity){
        return (SecondaryBackgroundView)activity.findViewById(R.id.background_secondary);
    }
    // 获取K线副图高亮控件
    public static SecondaryHighLight getSecondaryHighLight(Activity activity){
        return (SecondaryHighLight)activity.findViewById(R.id.highlight_secondary);
    }
    // 获取标题栏遮罩层
    public static View getCover(Activity activity){
        return activity.findViewById(R.id.view_cover);
    }

}
