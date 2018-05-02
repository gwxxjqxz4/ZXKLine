package mobileapp.zixiao.com.zxchart.net.domain;

import mobileapp.zixiao.com.zxchart.entity.originaldata.TimeLineOriginal;
import mobileapp.zixiao.com.zxchart.entity.originaldata.TimeLineRemote;
import mobileapp.zixiao.com.zxchart.entity.util.CommonEntity;
import mobileapp.zixiao.com.zxchart.net.interactor.UseCase;
import mobileapp.zixiao.com.zxchart.net.network.ApiService;
import mobileapp.zixiao.com.zxchart.net.network.DataStore;
import mobileapp.zixiao.com.zxchart.net.network.RemoteDataStore;
import mobileapp.zixiao.com.zxchart.net.network.TimeLineRestClient;
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
