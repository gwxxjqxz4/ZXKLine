package mobileapp.myjf.com.myxchart.data.entity.originaldata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gwx
 * 分时线列表数据类型，用于解析和操作从服务器请求的分时线线数据列表
 */
public class TimeLineOriginal<E>  {

    // 收盘时间
    @SerializedName("CloseTime")
    private double CloseTime;

    // 分时数据
    @SerializedName("Entity")
    private List<E> Entity;

    // 昨日收盘价
    @SerializedName("YesterdayPrice")
    private double YesterdayPrice;

    public double getCloseTime() {
        return CloseTime;
    }

    public void setCloseTime(double closeTime) {
        CloseTime = closeTime;
    }

    public List<E> getEntity() {
        return Entity;
    }

    public void setEntity(List<E> entity) {
        Entity = entity;
    }

    public double getYesterdayPrice() {
        return YesterdayPrice;
    }

    public void setYesterdayPrice(double yesterdayPrice) {
        YesterdayPrice = yesterdayPrice;
    }

    public TimeLineOriginal(double closeTime, List<E> entity, double yesterdayPrice) {
        CloseTime = closeTime;
        Entity = entity;
        YesterdayPrice = yesterdayPrice;
    }

    @Override
    public String toString() {
        return "TimeLineOriginal{" +
                "CloseTime=" + CloseTime +
                ", Entity=" + Entity +
                ", YesterdayPrice=" + YesterdayPrice +
                '}';
    }
}
