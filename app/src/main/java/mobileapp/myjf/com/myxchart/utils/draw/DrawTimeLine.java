package mobileapp.myjf.com.myxchart.utils.draw;

import android.app.Activity;
import android.os.Looper;
import android.widget.RelativeLayout;

import java.util.List;

import mobileapp.myjf.com.myxchart.entity.localdata.TimeLineLocal;
import mobileapp.myjf.com.myxchart.entity.originaldata.TimeLineOriginal;
import mobileapp.myjf.com.myxchart.entity.originaldata.TimeLineRemote;
import mobileapp.myjf.com.myxchart.entity.render.TimeLineRender;
import mobileapp.myjf.com.myxchart.ui.FullScreenActivity;
import mobileapp.myjf.com.myxchart.ui.ontouchlistener.TimeLineOnTouchListener;
import mobileapp.myjf.com.myxchart.utils.calculation.LocalToView;
import mobileapp.myjf.com.myxchart.utils.calculation.OriginalToLocal;
import mobileapp.myjf.com.myxchart.utils.global.GlobalViewsUtil;
import mobileapp.myjf.com.myxchart.utils.global.Variable;
import mobileapp.myjf.com.myxchart.utils.other.RefreshHelper;

/**
 * 绘制图像的工具类，用于绘制K线、副图和分时图
 */
public class DrawTimeLine {

    public static void drawTimeLine(final TimeLineOriginal<TimeLineRemote> timeLineOriginal, final Activity activity, final List<TimeLineRemote> timeLineRemotes, final int type) {

        final int selectedType;
        if (activity instanceof FullScreenActivity) {
            selectedType = Variable.getFullSelectedType();
        } else {
            selectedType = Variable.getNormalSelectedType();
        }

        // 只有在有数据时才去更新界面
        if (timeLineRemotes != null && timeLineRemotes.size() > 3) {
            // 若该方法正在子线程运行则不再另建子线程
            if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
                // 获取响应分时线触摸事件的控件
                final RelativeLayout timeLineLayout = GlobalViewsUtil.getTimeLineLayout(activity);
                // 将服务器原始数据填充成适宜展示的1440数据列表
                TimeLineLocal timeLineLocal = OriginalToLocal.getTimeLineLocal(timeLineRemotes, timeLineOriginal, activity);
                // 根据数据列表计算出渲染对象
                final TimeLineRender timeLineRender = LocalToView.getTimeLineRender(activity, timeLineLocal);
                // 在主线程中进行界面的更新
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 只有当计算结束后用户仍在此页面时才会更新分时线视图
                        if (type == selectedType) {
                            // 根据渲染数据对象渲染分时线数据
                            RefreshHelper.refreshTimeLineView(activity, timeLineRender);
                            // 设置触摸事件监听，完成十字线显示、拖动、双击全屏等事件
                            timeLineLayout.setOnTouchListener(new TimeLineOnTouchListener(activity, timeLineRender));
                        }
                    }
                });
            } else {
                // 在子线程中进行运算来保证界面流畅
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 获取响应分时线触摸事件的控件
                        final RelativeLayout timeLineLayout = GlobalViewsUtil.getTimeLineLayout(activity);
                        // 将服务器原始数据填充成适宜展示的1440数据列表
                        TimeLineLocal timeLineLocal = OriginalToLocal.getTimeLineLocal(timeLineRemotes, timeLineOriginal, activity);
                        // 根据数据列表计算出渲染对象
                        final TimeLineRender timeLineRender = LocalToView.getTimeLineRender(activity, timeLineLocal);
                        // 在主线程中进行界面的更新
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 只有当计算结束后用户仍在此页面时才会更新分时线视图
                                if (type == selectedType) {
                                    // 根据渲染数据对象渲染分时线数据
                                    RefreshHelper.refreshTimeLineView(activity, timeLineRender);
                                    // 设置触摸事件监听，完成十字线显示、拖动、双击全屏等事件
                                    timeLineLayout.setOnTouchListener(new TimeLineOnTouchListener(activity, timeLineRender));
                                }
                            }
                        });
                    }
                }).start();
            }
        }
    }

}
