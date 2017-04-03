package mobileapp.myjf.com.myxchart.data.network;


import mobileapp.myjf.com.myxchart.data.entity.CommonEntity;
import mobileapp.myjf.com.myxchart.data.entity.KLineList;
import mobileapp.myjf.com.myxchart.data.entity.TimeLineData;
import mobileapp.myjf.com.myxchart.data.entity.TimeLineList;
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
    public Observable<CommonEntity<TimeLineList<TimeLineData>>> getTimeLineData(String token, String list_start, String pageid) {
        return apiService.getTimeLineData(token, list_start, pageid);
    }

    @Override
    public Observable<KLineList> getKLineData(String url){
        return apiService.getKLineData(url);
    }


}


