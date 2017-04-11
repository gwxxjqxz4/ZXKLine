package mobileapp.myjf.com.myxchart.data.domain;

import java.util.Date;

import mobileapp.myjf.com.myxchart.data.entity.util.CommonEntity;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.TimeLineRemote;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.TimeLineOriginal;
import mobileapp.myjf.com.myxchart.data.interactor.UseCase;
import mobileapp.myjf.com.myxchart.data.network.ApiService;
import mobileapp.myjf.com.myxchart.data.network.DataStore;
import mobileapp.myjf.com.myxchart.data.network.RemoteDataStore;
import mobileapp.myjf.com.myxchart.data.network.TimeLineRestClient;
import rx.Observable;

/**
 * create by gwx
 * 请求分时线数据的类
 */
public class AddTimeLineOriginal extends UseCase {

    private String token;
    private String organizationCode;
    private String productCode;
    private long openTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public AddTimeLineOriginal(){

    }


    @Override
    protected Observable<CommonEntity<TimeLineOriginal<TimeLineRemote>>> buildUseCaseObservable() {
        ApiService service = TimeLineRestClient.getInstance();
        DataStore dataStore = new RemoteDataStore(service);
        return dataStore.addTimeLineData(organizationCode, productCode, token,openTime);
    }
}
