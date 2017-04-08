package mobileapp.myjf.com.myxchart.data.network;/**
 * Created by nethanhan on 2017/4/1.
 */

import mobileapp.myjf.com.myxchart.data.entity.util.CommonEntity;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.KLineOriginal;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.TimeLineRemote;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.TimeLineOriginal;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * create by gwx at 2017/4/1
 */
public interface ApiService {

    @Headers("Referer:iapp.xingxingguijinshu.com")
    // @GET("QL/Day_QLOIL10T")
    @GET
    Observable<KLineOriginal> getKLineData(@Url String url);

    @FormUrlEncoded
    @POST("MarketInfo/Product/ProductTimeShare_SR")
    Observable<CommonEntity<TimeLineOriginal<TimeLineRemote>>> getTimeLineData(
            @Field("OrganizationCode") String organizationCode,
            @Field("ProductCode") String productCode,
            @Field("Token") String token
    );

}
