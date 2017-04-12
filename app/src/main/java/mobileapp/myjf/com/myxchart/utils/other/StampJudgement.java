package mobileapp.myjf.com.myxchart.utils.other;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by gwx
 */

public class StampJudgement {

    /**
     * 判断是否为当天6点之前的数据，是返回true，不是返回false
     *
     * @param time
     * @return
     */
    public static boolean isYesterdayData(long time) {

        // 获取日期对象
        Calendar cal = Calendar.getInstance();
        // 获取当前时间
        Date date = new Date(System.currentTimeMillis());


        // 设置基准时间为当天6点
        cal.set(Calendar.HOUR_OF_DAY, 6);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 001);
        // 判断当前时间是否在当天6点以前，若是则小于昨天6点的数据清除，若不是则小于今天6点的数据清除
        if(date.getTime() < cal.getTimeInMillis()){
            cal.add(Calendar.DATE,-1);
        }

        // 若参数时间大于6点返回false，否则返回true
        if (time * 1000 > cal.getTimeInMillis()) {
            Log.e("数据库清理判断","返回了false");
            Log.e("更新","完成配置");
            return false;
        } else {
            Log.e("数据库清理判断","返回了true");
            return true;
        }

    }

}
