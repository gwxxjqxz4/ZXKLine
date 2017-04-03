package mobileapp.myjf.com.myxchart.data.domain;

import java.util.Date;

import mobileapp.myjf.com.myxchart.data.entity.CommonEntity;
import mobileapp.myjf.com.myxchart.data.entity.TimeLineData;
import mobileapp.myjf.com.myxchart.data.entity.TimeLineList;
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
public class GetTimeLineList extends UseCase {

    private String token;
    private String organizationCode;
    private String productCode;

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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public GetTimeLineList(){

    }


    @Override
    protected Observable<CommonEntity<TimeLineList<TimeLineData>>> buildUseCaseObservable() {
        ApiService service = TimeLineRestClient.getInstance();
        DataStore dataStore = new RemoteDataStore(service);
        Date date1 = new Date(System.currentTimeMillis());
        return dataStore.getTimeLineData(organizationCode, productCode, token);
    }
}
