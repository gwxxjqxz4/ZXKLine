package mobileapp.myjf.com.myxchart.data.network;


import mobileapp.myjf.com.myxchart.data.entity.util.CommonEntity;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.KLineOriginal;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.TimeLineRemote;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.TimeLineOriginal;
import rx.Observable;

/**
 * 商品信息实现
 */
public class RemoteDataStore implements DataStore {
    // 用户对象
    private final ApiService apiService;

    /**
     * 带参构造
     *
     * @param apiService 用户对象
     */
    public RemoteDataStore(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<CommonEntity<TimeLineOriginal<TimeLineRemote>>> getTimeLineData(String token, String list_start, String pageid) {
        return apiService.getTimeLineData(token, list_start, pageid);
    }

    @Override
    public Observable<KLineOriginal> getKLineData(String url) {
        return apiService.getKLineData(url);
    }


    @Override
    public Observable<CommonEntity<TimeLineOriginal<TimeLineRemote>>> addTimeLineData(String token, String list_start, String pageid, long openTime) {
        return apiService.addTimeLineData(token, list_start, pageid, openTime);
    }

    @Override
    public Observable<KLineOriginal> addKLineData(String token, String list_start, String pageid, String type, long openTime) {
        return apiService.addKLineData(token, list_start, pageid, openTime, token);
    }

}


