package mobileapp.zixiao.com.zxchart.utils.draw;

import android.app.Activity;
import android.os.Looper;

import java.util.List;

import mobileapp.zixiao.com.zxchart.entity.render.KLineRender;
import mobileapp.zixiao.com.zxchart.entity.util.KLineData;
import mobileapp.zixiao.com.zxchart.ui.FullScreenActivity;
import mobileapp.zixiao.com.zxchart.utils.global.Variable;
import mobileapp.zixiao.com.zxchart.utils.calculation.LocalToView;
import mobileapp.zixiao.com.zxchart.utils.other.RefreshHelper;

/**
 * Created by gwx
 */

public class DrawSecondary {

    public static void drawSecondary(final Activity activity, final List<KLineData> kLineDatas, final int type) {

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
                // 获取副图类型
                final int secondaryType = Variable.getSecondaryType();
                // 根据外部传递的参数计算得出渲染数据对象
                final KLineRender kLineRender = LocalToView.getKLineRender(activity, kLineDatas);
                // 在主线程中进行界面的更新
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 只有当计算结束后用户仍在此页面时才会更新K线视图
                        if (type == selectedType) {
                            // 根据所传参数刷新副图数据
                            RefreshHelper.refreshSecondaryView(activity, kLineRender, secondaryType);
                        }
                    }
                });

            }
            // 在子线程中进行运算来保证界面流畅
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 获取副图类型
                    final int secondaryType = Variable.getSecondaryType();
                    // 根据外部传递的参数计算得出渲染数据对象
                    final KLineRender kLineRender = LocalToView.getKLineRender(activity, kLineDatas);
                    // 在主线程中进行界面的更新
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // 只有当计算结束后用户仍在此页面时才会更新K线视图
                            if (type == selectedType) {
                                // 根据所传参数刷新副图数据
                                RefreshHelper.refreshSecondaryView(activity, kLineRender, secondaryType);
                            }
                        }
                    });
                }
            }).start();
        }
    }
}
