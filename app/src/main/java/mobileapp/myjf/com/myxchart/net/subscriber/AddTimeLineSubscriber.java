package mobileapp.myjf.com.myxchart.net.subscriber;

import android.app.Activity;
import android.util.Log;

import java.util.Date;
import java.util.List;

import mobileapp.myjf.com.myxchart.entity.originaldata.TimeLineOriginal;
import mobileapp.myjf.com.myxchart.entity.originaldata.TimeLineRemote;
import mobileapp.myjf.com.myxchart.entity.util.CommonEntity;
import mobileapp.myjf.com.myxchart.net.interactor.DefaultSubscriber;
import mobileapp.myjf.com.myxchart.utils.dao.TimeLineManager;
import mobileapp.myjf.com.myxchart.utils.draw.DrawTimeLine;

/**
 * Created by gwx
 */

public class AddTimeLineSubscriber extends DefaultSubscriber<CommonEntity<TimeLineOriginal<TimeLineRemote>>> {

    private Activity activity;

    public AddTimeLineSubscriber(Activity activity) {
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
                long date1 = new Date(System.currentTimeMillis()).getTime();
                Log.e("性能优化","收到服务器回复的时间：" + date1);
                // 获取服务器原始数据
                TimeLineOriginal timeLineOriginal = timeLineListCommonEntity.getEntity();
                long date2 = new Date(System.currentTimeMillis()).getTime();
                Log.e("性能优化","开始写入数据库的时间：" + date2);
                // 将服务器的原始数据缓存到数据库中
                TimeLineManager.addTimeLineRemotes(activity, timeLineOriginal);
                long date3 = new Date(System.currentTimeMillis()).getTime();
                Log.e("性能优化","写入完成的时间：" + date3);
                // 从数据库中读取数据
                List<TimeLineRemote> timeLineRemotes = TimeLineManager.queryTimeLineRemotes(activity);
                // 绘制K线图及副图（绘制方法中包含了页面切换监测，决定是否显示新数据）
                long date4 = new Date(System.currentTimeMillis()).getTime();
                Log.e("性能优化","查询完成完成的时间：" + date4);
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

