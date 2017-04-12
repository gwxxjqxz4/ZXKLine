package mobileapp.myjf.com.myxchart.net.domain;

import mobileapp.myjf.com.myxchart.entity.originaldata.KLineOriginal;
import mobileapp.myjf.com.myxchart.entity.util.CommonEntity;
import mobileapp.myjf.com.myxchart.net.interactor.UseCase;
import mobileapp.myjf.com.myxchart.net.network.ApiService;
import mobileapp.myjf.com.myxchart.net.network.DataStore;
import mobileapp.myjf.com.myxchart.net.network.RemoteDataStore;
import mobileapp.myjf.com.myxchart.net.network.TimeLineRestClient;
import rx.Observable;

/**
 * create by gwx
 * 已有缓存时添加最新K线数据的请求接口
 */
public class AddKLineOriginal extends UseCase {

    // 令牌
    private String token;
    // 机构代码
    private String organizationCode;
    // 商品代码
    private String productCode;
    // 请求用的时间戳
    private long openTime;
    // 商品类型
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AddKLineOriginal() {

    }


    @Override
    protected Observable<CommonEntity<KLineOriginal>> buildUseCaseObservable() {
        ApiService service = TimeLineRestClient.getInstance();
        DataStore dataStore = new RemoteDataStore(service);
        return dataStore.addKLineData(organizationCode, productCode, token, type, openTime);
    }
}
