package mobileapp.myjf.com.myxchart.utils.dao;

import android.content.Context;

import java.util.List;

import mobileapp.myjf.com.myxchart.entity.util.KLineData;

/**
 * Created by gwx
 */

public class KLineManager {

    /**
     * 将服务器获取的全部K线数据缓存入数据库
     *
     * @param context               上下文
     * @param kLineDatas            K线数据
     */
    public static void writeKLineDatas(Context context, List<KLineData> kLineDatas) {
        // 将全部数据插入到数据库中
        for (KLineData kLineData : kLineDatas) {
            DBManager.getInstance(context).insertKLineData(kLineData);
        }
    }

    /**
     * 将服务器获取的最新K线数据缓存入数据库
     *
     * @param context               上下文
     * @param kLineDatas      K线数据
     */
    public static void addKLineDatas(Context context,List<KLineData> kLineDatas){

        for(KLineData kLineData:kLineDatas){
            DBManager.getInstance(context).updateKLineData(kLineData);
        }

    }

    /**
     * 通过图表类型查询数据库中缓存的所有该类K线数据
     *
     * @param context           上下文
     */
    public static List<KLineData> queryKLineDatas(Context context,String type){
        List<KLineData> kLineDatas = DBManager.getInstance(context).queryKLineDatas(type);
        return kLineDatas;
    }

    /**
     * 删除数据库中缓存的所有数据
     * @param context           上下文
     */
    public static void deleteAllDatas(Context context){
        DBManager.getInstance(context).deleteAllDatas();
    }

}
