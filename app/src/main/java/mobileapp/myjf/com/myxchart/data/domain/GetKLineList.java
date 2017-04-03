package mobileapp.myjf.com.myxchart.data.domain;

import mobileapp.myjf.com.myxchart.data.entity.KLineList;
import mobileapp.myjf.com.myxchart.data.interactor.UseCase;
import mobileapp.myjf.com.myxchart.data.network.ApiService;
import mobileapp.myjf.com.myxchart.data.network.DataStore;
import mobileapp.myjf.com.myxchart.data.network.KLineRestClient;
import mobileapp.myjf.com.myxchart.data.network.RemoteDataStore;
import rx.Observable;

/**
 * create by gwx
 * 请求K线数据的类
 */
public class GetKLineList extends UseCase {

    private String syncOrgCode;
    private String type;
    private String productCode;

    public String getSyncOrgCode() {
        return syncOrgCode;
    }

    public void setSyncOrgCode(String syncOrgCode) {
        this.syncOrgCode = syncOrgCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public GetKLineList(){

    }

    @Override
    protected Observable<KLineList> buildUseCaseObservable() {
        ApiService service = KLineRestClient.getInstance();
        DataStore dataStore = new RemoteDataStore(service);
        return dataStore.getKLineData(syncOrgCode + "/" + type + "_" + productCode);
    }
}
