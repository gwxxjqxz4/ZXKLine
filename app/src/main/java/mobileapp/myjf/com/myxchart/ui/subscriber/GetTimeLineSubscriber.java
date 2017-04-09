package mobileapp.myjf.com.myxchart.ui.subscriber;

import android.app.Activity;
import android.widget.RelativeLayout;

import mobileapp.myjf.com.myxchart.utils.calculation.LocalToView;
import mobileapp.myjf.com.myxchart.utils.calculation.OriginalToLocal;
import mobileapp.myjf.com.myxchart.data.entity.localdata.TimeLineLocal;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.TimeLineOriginal;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.TimeLineRemote;
import mobileapp.myjf.com.myxchart.data.entity.render.TimeLineRender;
import mobileapp.myjf.com.myxchart.data.entity.util.CommonEntity;
import mobileapp.myjf.com.myxchart.data.global.GlobalViewsUtil;
import mobileapp.myjf.com.myxchart.data.interactor.DefaultSubscriber;
import mobileapp.myjf.com.myxchart.render.data.TimeLineView;
import mobileapp.myjf.com.myxchart.ui.ontouchlistener.TimeLineOnTouchListener;
import mobileapp.myjf.com.myxchart.utils.uitools.RefreshHelper;

/**
 * Created by gwx
 */

public class GetTimeLineSubscriber extends DefaultSubscriber<CommonEntity<TimeLineOriginal<TimeLineRemote>>> {

    private Activity activity;

    public GetTimeLineSubscriber(Activity activity){
        this.activity = activity;
    }

    /**
     * 数据解析成功
     *
     * @param timeLineListCommonEntity 解析得到的结果对象
     */
    @Override
    public void onNext(CommonEntity<TimeLineOriginal<TimeLineRemote>> timeLineListCommonEntity) {
        super.onNext(timeLineListCommonEntity);

        // 获取服务器原始数据
        TimeLineOriginal timeLineOriginal = timeLineListCommonEntity.getEntity();

        // 声明容纳分时线的布局，用于设置点击事件
        RelativeLayout timeLineLayout = GlobalViewsUtil.getTimeLineLayout(activity);

        // 将原始数据转化成易于保存操作的本地数据  TODO 之后要加缓存  并分离此后的逻辑
        TimeLineLocal timeLineLocal = OriginalToLocal.getTimeLineLocal(timeLineOriginal);
        // 将本地数据转化成渲染用的数据
        TimeLineRender timeLineRender = LocalToView.getTimeLineRender(activity,timeLineLocal);

        // 用最新的数据重绘界面
        RefreshHelper.refreshTimeLineView(activity,timeLineRender);
        // 给界面设置触摸事件（双击全屏和长按显示高亮）
        timeLineLayout.setOnTouchListener(new TimeLineOnTouchListener(activity,timeLineRender));

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        super.onError(e);
    }


}

