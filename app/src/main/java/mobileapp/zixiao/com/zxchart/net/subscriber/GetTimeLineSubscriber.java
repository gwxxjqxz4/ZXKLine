package mobileapp.zixiao.com.zxchart.net.subscriber;

import android.app.Activity;

import java.util.List;

import mobileapp.zixiao.com.zxchart.utils.draw.DrawTimeLine;
import mobileapp.zixiao.com.zxchart.entity.originaldata.TimeLineOriginal;
import mobileapp.zixiao.com.zxchart.entity.originaldata.TimeLineRemote;
import mobileapp.zixiao.com.zxchart.entity.util.CommonEntity;
import mobileapp.zixiao.com.zxchart.net.interactor.DefaultSubscriber;
import mobileapp.zixiao.com.zxchart.utils.dao.TimeLineManager;

/**
 * Created by gwx
 */

public class GetTimeLineSubscriber extends DefaultSubscriber<CommonEntity<TimeLineOriginal<TimeLineRemote>>> {

    private Activity activity;

    public GetTimeLineSubscriber(Activity activity) {
        this.activity = activity;
    }

    /**
     * 数据解析成功
     *
     * @param timeLineListCommonEntity 解析得到的结果对象
     */
    @Override
    public void onNext(final CommonEntity<TimeLineOriginal<TimeLineRemote>> timeLineListCommonEntity) {
        super.onNext(timeLineListCommonEntity);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取服务器原始数据
                TimeLineOriginal timeLineOriginal = timeLineListCommonEntity.getEntity();
                // 将服务器的原始数据缓存到数据库中
                TimeLineManager.writeTimeLineRemotes(activity, timeLineOriginal);
                // 从数据库中读取数据
                List<TimeLineRemote> timeLineRemotes = TimeLineManager.queryTimeLineRemotes(activity);
                // 绘制K线图及副图（绘制方法中包含了页面切换监测，决定是否显示新数据）
                DrawTimeLine.drawTimeLine(timeLineOriginal, activity, timeLineRemotes, 0);
            }
        }).start();


    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        super.onError(e);
    }


}

