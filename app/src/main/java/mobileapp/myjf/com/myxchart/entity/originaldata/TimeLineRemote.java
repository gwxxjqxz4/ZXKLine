package mobileapp.myjf.com.myxchart.entity.originaldata;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

import org.greenrobot.greendao.annotation.Generated;

/**
 * create by gwx
 * 分时线数据类型，用于解析和操作从服务器请求来的分时线数据
 */
@Entity
public class TimeLineRemote implements Serializable {


    @Id
    private Long id;

    @Transient
    // 最低价
    @SerializedName("Low")
    private double low;

    @Transient
    // 最高价
    @SerializedName("High")
    private double high;

    // 当前时间
    @SerializedName("OpenTime")
    private long openTime;

    @Transient
    // 开盘价
    @SerializedName("OpenPrice")
    private double openPrice;

    @Transient
    // 收盘价
    @SerializedName("ClosePrice")
    private double closePrice;

    @Transient
    // 均价
    @SerializedName("AveragePrice")
    private double averagePrice;

    // 当前价
    @SerializedName("Close")
    private double close;

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public TimeLineRemote(double low, double high, long openTime, double openPrice, double closePrice, double averagePrice, double close) {
        this.low = low;
        this.high = high;
        this.openTime = openTime;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.averagePrice = averagePrice;
        this.close = close;
    }

    @Generated(hash = 1340737684)
    public TimeLineRemote(Long id, long openTime, double close) {
        this.id = id;
        this.openTime = openTime;
        this.close = close;
    }

    @Generated(hash = 983661749)
    public TimeLineRemote() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TimeLineRemote{" +
                "low=" + low +
                ", high=" + high +
                ", openTime=" + openTime +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", averagePrice=" + averagePrice +
                ", close=" + close +
                '}';
    }
}
