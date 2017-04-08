package mobileapp.myjf.com.myxchart.ui.subscriber;

import android.app.Activity;
import android.util.Log;
import android.widget.RelativeLayout;

import mobileapp.myjf.com.myxchart.calculation.LocalToView;
import mobileapp.myjf.com.myxchart.calculation.OriginalToLocal;
import mobileapp.myjf.com.myxchart.data.entity.localdata.TimeLineLocal;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.TimeLineOriginal;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.TimeLineRemote;
import mobileapp.myjf.com.myxchart.data.entity.render.TimeLineRender;
import mobileapp.myjf.com.myxchart.data.entity.util.CommonEntity;
import mobileapp.myjf.com.myxchart.data.interactor.DefaultSubscriber;
import mobileapp.myjf.com.myxchart.ui.layout.TimeLineLayout;
import mobileapp.myjf.com.myxchart.render.data.TimeLineView;
import mobileapp.myjf.com.myxchart.render.highlight.TimeLineHighLightView;
import mobileapp.myjf.com.myxchart.ui.ontouchlistener.TimeLineOnTouchListener;

/**
 * Created by nethanhan on 2017/4/7.
 */

public class GetTimeLineSubscriber extends DefaultSubscriber<CommonEntity<TimeLineOriginal<TimeLineRemote>>> {

    TimeLineRender timeLineRender;
    Activity context;
    TimeLineHighLightView timeLineHighLightView;
    RelativeLayout timeLineLayout;

    public GetTimeLineSubscriber(Activity context,RelativeLayout timeLineLayout){
        this.context = context;
        this.timeLineLayout = timeLineLayout;
    }

    /**
     * 数据解析成功
     *
     * @param timeLineListCommonEntity 解析得到的结果对象
     */
    @Override
    public void onNext(CommonEntity<TimeLineOriginal<TimeLineRemote>> timeLineListCommonEntity) {
        super.onNext(timeLineListCommonEntity);

        Log.e("getTimeLineList","获取解析结果");
        TimeLineOriginal timeLineOriginal = timeLineListCommonEntity.getEntity();
        TimeLineLocal timeLineLocal = OriginalToLocal.getTimeLineLocal(timeLineOriginal);
        timeLineRender = LocalToView.getTimeLineRender(timeLineLocal,timeLineLayout.getWidth(),timeLineLayout.getHeight());

        // 声明显示分时线的控件
        TimeLineView timeLineView = new TimeLineView(context);
        // 将坐标点集合设置给负责显示的控件
        timeLineView.setTimeLineRender(timeLineRender);
        // 将负责显示的控件添加到容器控件（此控件）中
        timeLineLayout.addView(timeLineView);
        Log.e("getTimeLineList","timeLineView宽度" + timeLineView.getWidth());
        timeLineLayout.setOnTouchListener(new TimeLineOnTouchListener(context,timeLineLayout,timeLineHighLightView,timeLineRender));

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        super.onError(e);
    }


}

