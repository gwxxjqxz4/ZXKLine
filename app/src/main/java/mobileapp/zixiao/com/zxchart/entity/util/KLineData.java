package mobileapp.zixiao.com.zxchart.entity.util;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * create by gwx
 * K线图数据类型，用于解析和操作从服务器请求来的K线图数据
 */
@Entity
public class KLineData {

    // 数据库存取的唯一标识，插入时传null即可
    @Id
    private Long id;
    // 图表类型，如日K、5分等
    private String type;
    // 时间戳
    private long time;
    // 开盘价
    private double open;
    // 最高价
    private double high;
    // 最低价
    private double low;
    // 收盘价
    private double close;
    // 主图线
    private double Ma5;
    private double Ma10;
    private double Ma30;
    // 副图线
    private double Macd_dif;
    private double Macd_dea;
    private double Macd;
    private double Kdj_k;
    private double Kdj_d;
    private double Kdj_j;
    private double Rsi1;
    private double Rsi2;
    private double Rsi3;
    private double Bias1;
    private double Bias2;
    private double Bias3;

    @Generated(hash = 1699596376)
    public KLineData(Long id, String type, long time, double open, double high,
            double low, double close, double Ma5, double Ma10, double Ma30,
            double Macd_dif, double Macd_dea, double Macd, double Kdj_k,
            double Kdj_d, double Kdj_j, double Rsi1, double Rsi2, double Rsi3,
            double Bias1, double Bias2, double Bias3) {
        this.id = id;
        this.type = type;
        this.time = time;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.Ma5 = Ma5;
        this.Ma10 = Ma10;
        this.Ma30 = Ma30;
        this.Macd_dif = Macd_dif;
        this.Macd_dea = Macd_dea;
        this.Macd = Macd;
        this.Kdj_k = Kdj_k;
        this.Kdj_d = Kdj_d;
        this.Kdj_j = Kdj_j;
        this.Rsi1 = Rsi1;
        this.Rsi2 = Rsi2;
        this.Rsi3 = Rsi3;
        this.Bias1 = Bias1;
        this.Bias2 = Bias2;
        this.Bias3 = Bias3;
    }

    @Generated(hash = 1227447334)
    public KLineData() {
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getMa5() {
        return Ma5;
    }

    public void setMa5(double ma5) {
        Ma5 = ma5;
    }

    public double getMa10() {
        return Ma10;
    }

    public void setMa10(double ma10) {
        Ma10 = ma10;
    }

    public double getMa30() {
        return Ma30;
    }

    public void setMa30(double ma30) {
        Ma30 = ma30;
    }

    public double getMacd_dif() {
        return Macd_dif;
    }

    public void setMacd_dif(double macd_dif) {
        Macd_dif = macd_dif;
    }

    public double getMacd_dea() {
        return Macd_dea;
    }

    public void setMacd_dea(double macd_dea) {
        Macd_dea = macd_dea;
    }

    public double getMacd() {
        return Macd;
    }

    public void setMacd(double macd) {
        Macd = macd;
    }

    public double getKdj_k() {
        return Kdj_k;
    }

    public void setKdj_k(double kdj_k) {
        Kdj_k = kdj_k;
    }

    public double getKdj_d() {
        return Kdj_d;
    }

    public void setKdj_d(double kdj_d) {
        Kdj_d = kdj_d;
    }

    public double getKdj_j() {
        return Kdj_j;
    }

    public void setKdj_j(double kdj_j) {
        Kdj_j = kdj_j;
    }

    public double getRsi1() {
        return Rsi1;
    }

    public void setRsi1(double rsi1) {
        Rsi1 = rsi1;
    }

    public double getRsi2() {
        return Rsi2;
    }

    public void setRsi2(double rsi2) {
        Rsi2 = rsi2;
    }

    public double getRsi3() {
        return Rsi3;
    }

    public void setRsi3(double rsi3) {
        Rsi3 = rsi3;
    }

    public double getBias1() {
        return Bias1;
    }

    public void setBias1(double bias1) {
        Bias1 = bias1;
    }

    public double getBias2() {
        return Bias2;
    }

    public void setBias2(double bias2) {
        Bias2 = bias2;
    }

    public double getBias3() {
        return Bias3;
    }

    public void setBias3(double bias3) {
        Bias3 = bias3;
    }

    @Override
    public String toString() {
        return "KLineDate{" +
                "time=" + time +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", Ma5=" + Ma5 +
                ", Ma10=" + Ma10 +
                ", Ma30=" + Ma30 +
                ", Macd_dif=" + Macd_dif +
                ", Macd_dea=" + Macd_dea +
                ", Macd=" + Macd +
                ", Kdj_k=" + Kdj_k +
                ", Kdj_d=" + Kdj_d +
                ", Kdj_j=" + Kdj_j +
                ", Rsi1=" + Rsi1 +
                ", Rsi2=" + Rsi2 +
                ", Rsi3=" + Rsi3 +
                ", Bias1=" + Bias1 +
                ", Bias2=" + Bias2 +
                ", Bias3=" + Bias3 +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}