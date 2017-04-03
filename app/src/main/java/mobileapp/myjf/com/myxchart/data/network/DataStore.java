package mobileapp.myjf.com.myxchart.data.network;/**
 * Created by nethanhan on 2017/4/1.
 */

import mobileapp.myjf.com.myxchart.data.entity.CommonEntity;
import mobileapp.myjf.com.myxchart.data.entity.KLineList;
import mobileapp.myjf.com.myxchart.data.entity.TimeLineData;
import mobileapp.myjf.com.myxchart.data.entity.TimeLineList;
import rx.Observable;

/**
 * create by gwx at 2017/4/1
 */
public interface DataStore {

    Observable<KLineList> getKLineData(String url);
    Observable<CommonEntity<TimeLineList<TimeLineData>>> getTimeLineData(String organizationCode, String productCode, String token);

}
