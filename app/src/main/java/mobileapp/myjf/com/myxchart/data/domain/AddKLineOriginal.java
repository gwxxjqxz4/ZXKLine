package mobileapp.myjf.com.myxchart.data.domain;

import java.util.Date;

import mobileapp.myjf.com.myxchart.data.entity.originaldata.KLineOriginal;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.TimeLineOriginal;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.TimeLineRemote;
import mobileapp.myjf.com.myxchart.data.entity.util.CommonEntity;
import mobileapp.myjf.com.myxchart.data.interactor.UseCase;
import mobileapp.myjf.com.myxchart.data.network.ApiService;
import mobileapp.myjf.com.myxchart.data.network.DataStore;
import mobileapp.myjf.com.myxchart.data.network.KLineRestClient;
import mobileapp.myjf.com.myxchart.data.network.RemoteDataStore;
import mobileapp.myjf.com.myxchart.data.network.TimeLineRestClient;
import rx.Observable;

/**
 * create by gwx
 * 请求分时线数据的类
 */
public class AddKLineOriginal extends UseCase {

    private String token;
    private String organizationCode;
    private String productCode;
    private long openTime;
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
    protected Observable<KLineOriginal> buildUseCaseObservable() {
        ApiService service = TimeLineRestClient.getInstance();
        DataStore dataStore = new RemoteDataStore(service);
        return dataStore.addKLineData(organizationCode, productCode, token, type, openTime);
    }
}
