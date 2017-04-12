package mobileapp.myjf.com.myxchart.net.domain;

import mobileapp.myjf.com.myxchart.entity.originaldata.TimeLineOriginal;
import mobileapp.myjf.com.myxchart.entity.originaldata.TimeLineRemote;
import mobileapp.myjf.com.myxchart.entity.util.CommonEntity;
import mobileapp.myjf.com.myxchart.net.interactor.UseCase;
import mobileapp.myjf.com.myxchart.net.network.ApiService;
import mobileapp.myjf.com.myxchart.net.network.DataStore;
import mobileapp.myjf.com.myxchart.net.network.RemoteDataStore;
import mobileapp.myjf.com.myxchart.net.network.TimeLineRestClient;
import rx.Observable;

/**
 * create by gwx
 * 请求分时线数据的类
 */
public class GetTimeLineOriginal extends UseCase {

    // 令牌
    private String token;
    // 机构代码
    private String organizationCode;
    // 商品代码
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

    public GetTimeLineOriginal(){

    }


    @Override
    protected Observable<CommonEntity<TimeLineOriginal<TimeLineRemote>>> buildUseCaseObservable() {
        ApiService service = TimeLineRestClient.getInstance();
        DataStore dataStore = new RemoteDataStore(service);
        return dataStore.getTimeLineData(organizationCode, productCode, token);
    }
}
