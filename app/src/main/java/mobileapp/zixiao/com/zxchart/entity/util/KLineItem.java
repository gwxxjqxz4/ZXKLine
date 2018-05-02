package mobileapp.zixiao.com.zxchart.entity.util;

import android.app.Activity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mobileapp.zixiao.com.zxchart.ui.FullScreenActivity;
import mobileapp.zixiao.com.zxchart.utils.global.Variable;

/**
 * Created by gwx
 * K线图的坐标点类，用于记录和操作一组K线数据的各项参数
 */
public class KLineItem {

    // K线图矩形顶部参数
    private double rectTop;
    // K线图矩形底部参数
    private double rectBottom;
    // K线图矩形左侧边参数
    private double rectLeft;
    // K线图矩形右侧边参数
    private double rectRight;
    // 收盘价对应的纵坐标值，用于定位高亮线
    private double closeY;
    // K线中 涨跌状态   1为涨  2为跌
    // MACD中 1为正  2为负
    private int MacdStatus;
    // 上影线顶点坐标
    private KLinePoint hLineTop;
    // 下影线顶点坐标
    private KLinePoint hLineBottom;
    // Ma5线坐标
    private KLinePoint ma5Point;
    // Ma10线坐标
    private KLinePoint ma10Point;
    // Ma30线坐标
    private KLinePoint ma30Point;
    // 收盘价
    private double price;
    private double openPrice;
    private double highPrice;
    private double lowPrice;
    // 日期
    private long date;
    // 副图种类
    private String[] type;

    // 主图状态  1为涨  2为跌
    private int status;

    // ma线数据
    private double ma5;
    private double ma10;
    private double ma30;

    // macd柱状图纵坐标
    private double macdTop;
    private double macdBottom;
    // macd价格
    private double macdPrice;

    // 副图各参数坐标和价格
    private KLinePoint Macd_difPoint;
    private KLinePoint Macd_deaPoint;
    private KLinePoint Kdj_kPoint;
    private KLinePoint Kdj_dPoint;
    private KLinePoint Kdj_jPoint;
    private KLinePoint Rsi1Point;
    private KLinePoint Rsi2Point;
    private KLinePoint Rsi3Point;
    private KLinePoint Bias1Point;
    private KLinePoint Bias2Point;
    private KLinePoint Bias3Point;

    public double getRectTop() {
        return rectTop;
    }

    public void setRectTop(double rectTop) {
        this.rectTop = rectTop;
    }

    public double getRectBottom() {
        return rectBottom;
    }

    public void setRectBottom(double rectBottom) {
        this.rectBottom = rectBottom;
    }

    public double getRectLeft() {
        return rectLeft;
    }

    public void setRectLeft(double rectLeft) {
        this.rectLeft = rectLeft;
    }

    public double getRectRight() {
        return rectRight;
    }

    public void setRectRight(double rectRight) {
        this.rectRight = rectRight;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public KLinePoint gethLineTop() {
        return hLineTop;
    }

    public void sethLineTop(KLinePoint hLineTop) {
        this.hLineTop = hLineTop;
    }

    public KLinePoint gethLineBottom() {
        return hLineBottom;
    }

    public void sethLineBottom(KLinePoint hLineBottom) {
        this.hLineBottom = hLineBottom;
    }

    public KLinePoint getMa5Point() {
        return ma5Point;
    }

    public void setMa5Point(KLinePoint ma5Point) {
        this.ma5Point = ma5Point;
    }

    public KLinePoint getMa10Point() {
        return ma10Point;
    }

    public void setMa10Point(KLinePoint ma10Point) {
        this.ma10Point = ma10Point;
    }

    public KLinePoint getMa30Point() {
        return ma30Point;
    }

    public void setMa30Point(KLinePoint ma30Point) {
        this.ma30Point = ma30Point;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate(Activity activity) {
        long temp = date * 1000;
        Date date = new Date(temp);
        SimpleDateFormat sdf;
        final int selectedType;
        if (activity instanceof FullScreenActivity) {
            selectedType = Variable.getFullSelectedType();
        } else {
            selectedType = Variable.getNormalSelectedType();
        }
        if (selectedType == 1 || selectedType == 7 || selectedType == 8 || selectedType == 2) {
            sdf = new SimpleDateFormat("MM-dd");
        } else if (selectedType == 5 || selectedType == 6) {
            sdf = new SimpleDateFormat("hh:mm");
        }else{
            sdf = new SimpleDateFormat("yyyy");
        }
        return sdf.format(date);
    }

    public String getDate2(){
        long temp = date * 1000;
        Date date = new Date(temp);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd hh:mm");
        return sdf.format(date);
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
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

    public double getMacdTop() {
        return macdTop;
    }

    public void setMacdTop(double macdTop) {
        this.macdTop = macdTop;
    }

    public double getMacdBottom() {
        return macdBottom;
    }

    public void setMacdBottom(double macdBottom) {
        this.macdBottom = macdBottom;
    }

    public KLinePoint getMacd_difPoint() {
        return Macd_difPoint;
    }

    public void setMacd_difPoint(KLinePoint macd_difPoint) {
        Macd_difPoint = macd_difPoint;
    }

    public KLinePoint getMacd_deaPoint() {
        return Macd_deaPoint;
    }

    public void setMacd_deaPoint(KLinePoint macd_deaPoint) {
        Macd_deaPoint = macd_deaPoint;
    }

    public KLinePoint getKdj_kPoint() {
        return Kdj_kPoint;
    }

    public void setKdj_kPoint(KLinePoint kdj_kPoint) {
        Kdj_kPoint = kdj_kPoint;
    }

    public KLinePoint getKdj_dPoint() {
        return Kdj_dPoint;
    }

    public void setKdj_dPoint(KLinePoint kdj_dPoint) {
        Kdj_dPoint = kdj_dPoint;
    }

    public KLinePoint getKdj_jPoint() {
        return Kdj_jPoint;
    }

    public void setKdj_jPoint(KLinePoint kdj_jPoint) {
        Kdj_jPoint = kdj_jPoint;
    }

    public KLinePoint getRsi1Point() {
        return Rsi1Point;
    }

    public void setRsi1Point(KLinePoint rsi1Point) {
        Rsi1Point = rsi1Point;
    }

    public KLinePoint getRsi2Point() {
        return Rsi2Point;
    }

    public void setRsi2Point(KLinePoint rsi2Point) {
        Rsi2Point = rsi2Point;
    }

    public KLinePoint getRsi3Point() {
        return Rsi3Point;
    }

    public void setRsi3Point(KLinePoint rsi3Point) {
        Rsi3Point = rsi3Point;
    }

    public KLinePoint getBias1Point() {
        return Bias1Point;
    }

    public void setBias1Point(KLinePoint bias1Point) {
        Bias1Point = bias1Point;
    }

    public KLinePoint getBias2Point() {
        return Bias2Point;
    }

    public void setBias2Point(KLinePoint bias2Point) {
        Bias2Point = bias2Point;
    }

    public KLinePoint getBias3Point() {
        return Bias3Point;
    }

    public void setBias3Point(KLinePoint bias3Point) {
        Bias3Point = bias3Point;
    }

    public double getCloseY() {
        return closeY;
    }

    public void setCloseY(double closeY) {
        this.closeY = closeY;
    }

    public double getMacdPrice() {
        return macdPrice;
    }

    public void setMacdPrice(double macdPrice) {
        this.macdPrice = macdPrice;
    }

    public int getMacdStatus() {
        return MacdStatus;
    }

    public void setMacdStatus(int macdStatus) {
        MacdStatus = macdStatus;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public String getOpenPriceString(){
        return new DecimalFormat("#0.0000").format(openPrice);
    }

    public String getClosePriceString(){
        return new DecimalFormat("#0.0000").format(price);
    }

    public String getHighPriceString(){
        return new DecimalFormat("#0.0000").format(highPrice);
    }

    public String getLowPriceString(){
        return new DecimalFormat("#0.0000").format(lowPrice);
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }
}