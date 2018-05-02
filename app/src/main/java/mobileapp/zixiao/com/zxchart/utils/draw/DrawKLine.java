package mobileapp.zixiao.com.zxchart.utils.draw;

import android.app.Activity;
import android.os.Looper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

import mobileapp.zixiao.com.zxchart.entity.util.KLineData;
import mobileapp.zixiao.com.zxchart.ui.FullScreenActivity;
import mobileapp.zixiao.com.zxchart.ui.onclicklistener.SecondaryClickListener;
import mobileapp.zixiao.com.zxchart.utils.calculation.LocalToView;
import mobileapp.zixiao.com.zxchart.entity.render.KLineRender;
import mobileapp.zixiao.com.zxchart.utils.global.GlobalViewsUtil;
import mobileapp.zixiao.com.zxchart.ui.ontouchlistener.KLineOnTouchListener;
import mobileapp.zixiao.com.zxchart.utils.global.Variable;
import mobileapp.zixiao.com.zxchart.utils.other.RefreshHelper;

/**
 * 绘制图像的工具类，用于绘制K线、副图和分时图
 */
public class DrawKLine {

    public static void drawKLine(final Activity activity, final List<KLineData> kLineDatas, final int type) {

        final int selectedType;
        if (activity instanceof FullScreenActivity) {
            selectedType = Variable.getFullSelectedType();
        } else {
            selectedType = Variable.getNormalSelectedType();
        }

        // 只有在有数据时才去更新界面
        if (kLineDatas != null && kLineDatas.size() > 3) {
            // 若该方法正在子线程运行则不再另建子线程
            if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
                // 获取响应副图类型点击事件的控件
                final LinearLayout kLineLayout = GlobalViewsUtil.getKLineLayout(activity);
                // 根据外部传递的参数计算得出渲染数据对象
                final KLineRender kLineRender = LocalToView.getKLineRender(activity, kLineDatas);
                // 在主线程中进行界面的更新
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 只有当计算结束后用户仍在此页面时才会更新K线视图
                        if (type == selectedType) {
                            // 根据渲染数据对象渲染K线数据
                            RefreshHelper.refreshMainView(activity, kLineRender);
                            // 设置触摸事件监听，完成十字线显示、拖动、双击全屏等事件
                            kLineLayout.setOnTouchListener(new KLineOnTouchListener(activity, kLineRender, kLineDatas));
                            // 设置副图指标点击事件（用于切换MACD、RSI等指标的数据）
                            initSecondaryListener(activity);
                        }
                    }
                });
            } else {
                // 在子线程中进行运算来保证界面流畅
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 获取响应副图类型点击事件的控件
                        final LinearLayout kLineLayout = GlobalViewsUtil.getKLineLayout(activity);
                        // 根据外部传递的参数计算得出渲染数据对象
                        final KLineRender kLineRender = LocalToView.getKLineRender(activity, kLineDatas);
                        // 在主线程中进行界面的更新
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 只有当计算结束后用户仍在此页面时才会更新K线视图
                                if (type == selectedType) {
                                    // 根据渲染数据对象渲染K线数据
                                    RefreshHelper.refreshMainView(activity, kLineRender);
                                    // 设置触摸事件监听，完成十字线显示、拖动、双击全屏等事件
                                    kLineLayout.setOnTouchListener(new KLineOnTouchListener(activity, kLineRender, kLineDatas));
                                    // 设置副图指标点击事件（用于切换MACD、RSI等指标的数据）
                                    initSecondaryListener(activity);
                                }
                            }
                        });
                    }
                }).start();
            }
        }
    }


    public static void initSecondaryListener(Activity activity) {
        List<RelativeLayout> views = GlobalViewsUtil.getTypes(activity);
        View.OnClickListener secondaryListener = new SecondaryClickListener(activity);
        for (int i = 0; i <= 3; i++) {
            views.get(i).setOnClickListener(secondaryListener);
        }
    }
}
