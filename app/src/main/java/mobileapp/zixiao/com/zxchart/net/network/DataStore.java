package mobileapp.zixiao.com.zxchart.net.network;/**
 * Created by nethanhan on 2017/4/1.
 */

import mobileapp.zixiao.com.zxchart.entity.util.CommonEntity;
import mobileapp.zixiao.com.zxchart.entity.originaldata.KLineOriginal;
import mobileapp.zixiao.com.zxchart.entity.originaldata.TimeLineRemote;
import mobileapp.zixiao.com.zxchart.entity.originaldata.TimeLineOriginal;
import rx.Observable;

/**
 * create by gwx at 2017/4/1
 */
public interface DataStore {

    Observable<KLineOriginal> getKLineData(String url);

    Observable<CommonEntity<double[][]>> addKLineData(String organizationCode, String productCode, String token, long openTime, String type);

    Observable<CommonEntity<TimeLineOriginal<TimeLineRemote>>> addTimeLineData(String organizationCode, String productCode, String token, long openTime);

    Observable<CommonEntity<TimeLineOriginal<TimeLineRemote>>> getTimeLineData(String organizationCode, String productCode, String token);

}
