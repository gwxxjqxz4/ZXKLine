package mobileapp.myjf.com.myxchart.net.domain;

import mobileapp.myjf.com.myxchart.entity.originaldata.KLineOriginal;
import mobileapp.myjf.com.myxchart.net.interactor.UseCase;
import mobileapp.myjf.com.myxchart.net.network.ApiService;
import mobileapp.myjf.com.myxchart.net.network.DataStore;
import mobileapp.myjf.com.myxchart.net.network.KLineRestClient;
import mobileapp.myjf.com.myxchart.net.network.RemoteDataStore;
import rx.Observable;

/**
 * create by gwx
 * 请求K线数据的类
 */
public class GetKLineOriginal extends UseCase {

    // 机构代码
    private String syncOrgCode;
    // 图表类型
    private String type;
    // 商品代码
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

    public GetKLineOriginal(){

    }

    @Override
    protected Observable<KLineOriginal> buildUseCaseObservable() {
        ApiService service = KLineRestClient.getInstance();
        DataStore dataStore = new RemoteDataStore(service);
        return dataStore.getKLineData(syncOrgCode + "/" + type + "_" + productCode);
    }
}
