package mobileapp.myjf.com.myxchart.entity.originaldata;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by gwx on 17/4/12.
 */

@Entity
public class AddKLineRemote {


    @Id
    private Long id;

    // 最低价
    @SerializedName("L")
    private double low;

    // 最高价
    @SerializedName("H")
    private double high;

    // 当前时间
    @SerializedName("T")
    private long time;

    // 开盘价
    @SerializedName("O")
    private double openPrice;

    // 收盘价
    @SerializedName("C")
    private double closePrice;


    // 收盘价
    @SerializedName("Ma5")
    private double ma5;

    // 收盘价
    @SerializedName("Ma10")
    private double ma10;

    // 收盘价
    @SerializedName("Ma30")
    private double ma30;

    // 收盘价
    @SerializedName("MACD")
    private double macd;

    // 收盘价
    @SerializedName("MACD_DIF")
    private double macd_dif;

    // 收盘价
    @SerializedName("MACD_DEA")
    private double macd_dea;

    // 收盘价
    @SerializedName("RSI1")
    private double rsi1;

    // 收盘价
    @SerializedName("RSI2")
    private double rsi2;

    // 收盘价
    @SerializedName("RSI3")
    private double rsi3;

    // 收盘价
    @SerializedName("BIAS1")
    private double bias1;

    // 收盘价
    @SerializedName("BIAS2")
    private double bias2;

    // 收盘价
    @SerializedName("BIAS3")
    private double bias3;

    // 收盘价
    @SerializedName("KDJ_D")
    private double kdj_d;

    // 收盘价
    @SerializedName("KDJ_K")
    private double kdj_k;

    // 收盘价
    @SerializedName("KDJ_J")
    private double kdj_j;

    @Generated(hash = 1915577776)
    public AddKLineRemote(Long id, double low, double high, long time,
            double openPrice, double closePrice, double ma5, double ma10,
            double ma30, double macd, double macd_dif, double macd_dea,
            double rsi1, double rsi2, double rsi3, double bias1, double bias2,
            double bias3, double kdj_d, double kdj_k, double kdj_j) {
        this.id = id;
        this.low = low;
        this.high = high;
        this.time = time;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.ma5 = ma5;
        this.ma10 = ma10;
        this.ma30 = ma30;
        this.macd = macd;
        this.macd_dif = macd_dif;
        this.macd_dea = macd_dea;
        this.rsi1 = rsi1;
        this.rsi2 = rsi2;
        this.rsi3 = rsi3;
        this.bias1 = bias1;
        this.bias2 = bias2;
        this.bias3 = bias3;
        this.kdj_d = kdj_d;
        this.kdj_k = kdj_k;
        this.kdj_j = kdj_j;
    }

    @Generated(hash = 2064096471)
    public AddKLineRemote() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
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

    public double getMa5() {
        return ma5;
    }

    public void setMa5(double ma5) {
        this.ma5 = ma5;
    }

    public double getMa10() {
        return ma10;
    }

    public void setMa10(double ma10) {
        this.ma10 = ma10;
    }

    public double getMa30() {
        return ma30;
    }

    public void setMa30(double ma30) {
        this.ma30 = ma30;
    }

    public double getMacd() {
        return macd;
    }

    public void setMacd(double macd) {
        this.macd = macd;
    }

    public double getMacd_dif() {
        return macd_dif;
    }

    public void setMacd_dif(double macd_dif) {
        this.macd_dif = macd_dif;
    }

    public double getMacd_dea() {
        return macd_dea;
    }

    public void setMacd_dea(double macd_dea) {
        this.macd_dea = macd_dea;
    }

    public double getRsi1() {
        return rsi1;
    }

    public void setRsi1(double rsi1) {
        this.rsi1 = rsi1;
    }

    public double getRsi2() {
        return rsi2;
    }

    public void setRsi2(double rsi2) {
        this.rsi2 = rsi2;
    }

    public double getRsi3() {
        return rsi3;
    }

    public void setRsi3(double rsi3) {
        this.rsi3 = rsi3;
    }

    public double getBias1() {
        return bias1;
    }

    public void setBias1(double bias1) {
        this.bias1 = bias1;
    }

    public double getBias2() {
        return bias2;
    }

    public void setBias2(double bias2) {
        this.bias2 = bias2;
    }

    public double getBias3() {
        return bias3;
    }

    public void setBias3(double bias3) {
        this.bias3 = bias3;
    }

    public double getKdj_d() {
        return kdj_d;
    }

    public void setKdj_d(double kdj_d) {
        this.kdj_d = kdj_d;
    }

    public double getKdj_k() {
        return kdj_k;
    }

    public void setKdj_k(double kdj_k) {
        this.kdj_k = kdj_k;
    }

    public double getKdj_j() {
        return kdj_j;
    }

    public void setKdj_j(double kdj_j) {
        this.kdj_j = kdj_j;
    }

    @Override
    public String toString() {
        return "AddKLineRemote{" +
                "id=" + id +
                ", low=" + low +
                ", high=" + high +
                ", time=" + time +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", ma5=" + ma5 +
                ", ma10=" + ma10 +
                ", ma30=" + ma30 +
                ", macd=" + macd +
                ", macd_dif=" + macd_dif +
                ", macd_dea=" + macd_dea +
                ", rsi1=" + rsi1 +
                ", rsi2=" + rsi2 +
                ", rsi3=" + rsi3 +
                ", bias1=" + bias1 +
                ", bias2=" + bias2 +
                ", bias3=" + bias3 +
                ", kdj_d=" + kdj_d +
                ", kdj_k=" + kdj_k +
                ", kdj_j=" + kdj_j +
                '}';
    }
}
