package mobileapp.myjf.com.myxchart.utils.dao;

import android.content.Context;

import java.util.List;

import mobileapp.myjf.com.myxchart.data.entity.originaldata.TimeLineOriginal;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.TimeLineRemote;

/**
 * Created by nethanhan on 2017/4/10.
 */

public class TimeLineManager {

    /**
     * 将服务器获取的全部分时线数据缓存入数据库
     *
     * @param context               上下文
     * @param timeLineOriginal      分时线服务器数据
     */
    public static void writeTimeLineRemotes(Context context, TimeLineOriginal<TimeLineRemote> timeLineOriginal) {
        // 获取服务器返回的全部数据
        List<TimeLineRemote> timeLineRemotes = timeLineOriginal.getEntity();
        // 将全部数据插入到数据库中
        for (TimeLineRemote timeLineRemote : timeLineRemotes) {
            DBManager.getInstance(context).insertTimeLineRemote(timeLineRemote);
        }
    }

    /**
     * 将服务器获取的最新分时线数据缓存入数据库
     *
     * @param context               上下文
     * @param timeLineOriginal      分时线数据
     */
    public static void addTimeLineRemotes(Context context,TimeLineOriginal<TimeLineRemote> timeLineOriginal){

        for(TimeLineRemote timeLineRemote:timeLineOriginal.getEntity()){
            DBManager.getInstance(context).updateTimeLineRemote(timeLineRemote);
        }

    }

    /**
     * 查询数据库中缓存的所有分时线数据
     *
     * @param context           上下文
     */
    public static List<TimeLineRemote> queryTimeLineRemotes(Context context){
        List<TimeLineRemote> timeLineRemotes = DBManager.getInstance(context).queryTimeLineRemotes();
        return timeLineRemotes;
    }

    /**
     * 删除数据库中缓存的所有数据
     * @param context           上下文
     */
    public static void deleteAllDatas(Context context){
        DBManager.getInstance(context).deleteAllDatas();
    }

}
