package mobileapp.myjf.com.myxchart.net.network;/**
 * Created by nethanhan on 2017/4/1.
 */

import mobileapp.myjf.com.myxchart.entity.util.CommonEntity;
import mobileapp.myjf.com.myxchart.entity.originaldata.KLineOriginal;
import mobileapp.myjf.com.myxchart.entity.originaldata.TimeLineRemote;
import mobileapp.myjf.com.myxchart.entity.originaldata.TimeLineOriginal;
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
    @GET
    Observable<KLineOriginal> getKLineData(@Url String url);

    @FormUrlEncoded
    @POST("MarketInfo/Product/ProductTimeShare_SR")
    Observable<CommonEntity<TimeLineOriginal<TimeLineRemote>>> addTimeLineData(
            @Field("OrganizationCode") String organizationCode,
            @Field("ProductCode") String productCode,
            @Field("Token") String token,
            @Field("OpenTime") long opentime
    );

    @FormUrlEncoded
    @POST("MarketInfo/Product/ProductTimeShare_SR")
    Observable<CommonEntity<TimeLineOriginal<TimeLineRemote>>> getTimeLineData(
            @Field("OrganizationCode") String organizationCode,
            @Field("ProductCode") String productCode,
            @Field("Token") String token
    );

    @FormUrlEncoded
    @POST("Product/GetLatestKLineData")
    Observable<CommonEntity<KLineOriginal>> addKLineData(
            @Field("OrganizationCode") String organizationCode,
            @Field("ProductCode") String productCode,
            @Field("Token") String token,
            @Field("OpenTime") long opentime,
            @Field("KType") String type
    );

}
