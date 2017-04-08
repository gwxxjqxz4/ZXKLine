package mobileapp.myjf.com.myxchart.data.network;/**
 * Created by nethanhan on 2017/4/1.
 */

import mobileapp.myjf.com.myxchart.data.entity.util.CommonEntity;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.KLineOriginal;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.TimeLineRemote;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.TimeLineOriginal;
import rx.Observable;

/**
 * create by gwx at 2017/4/1
 */
public interface DataStore {

    Observable<KLineOriginal> getKLineData(String url);
    Observable<CommonEntity<TimeLineOriginal<TimeLineRemote>>> getTimeLineData(String organizationCode, String productCode, String token);

}
