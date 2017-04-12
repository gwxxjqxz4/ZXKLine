package mobileapp.myjf.com.myxchart.net.network;/**
 * Created by nethanhan on 2017/4/1.
 */

import mobileapp.myjf.com.myxchart.entity.util.CommonEntity;
import mobileapp.myjf.com.myxchart.entity.originaldata.KLineOriginal;
import mobileapp.myjf.com.myxchart.entity.originaldata.TimeLineRemote;
import mobileapp.myjf.com.myxchart.entity.originaldata.TimeLineOriginal;
import rx.Observable;

/**
 * create by gwx at 2017/4/1
 */
public interface DataStore {

    Observable<KLineOriginal> getKLineData(String url);

    Observable<CommonEntity<KLineOriginal>> addKLineData(String organizationCode, String productCode, String token, String type, long openTime);

    Observable<CommonEntity<TimeLineOriginal<TimeLineRemote>>> addTimeLineData(String organizationCode, String productCode, String token, long openTime);

    Observable<CommonEntity<TimeLineOriginal<TimeLineRemote>>> getTimeLineData(String organizationCode, String productCode, String token);

}
