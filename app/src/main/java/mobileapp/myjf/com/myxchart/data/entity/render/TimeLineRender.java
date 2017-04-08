package mobileapp.myjf.com.myxchart.data.entity.render;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import mobileapp.myjf.com.myxchart.data.entity.util.TimeLinePoint;

/**
 * 分时线渲染对象，封装了可以直接用于展示的数据
 */
public class TimeLineRender {

    private List<TimeLinePoint> points;
    private double topPrice;
    private double bottomPrice;
    private double abovePrice;
    private double underPrice;
    private double midPrice;

    private double topPercent;
    private double bottomPercent;
    private double abovePercent;
    private double underPercent;
    private double midPercent;

    public TimeLineRender(){
        points = new ArrayList<>();
    }

    public List<TimeLinePoint> getPoints() {
        return points;
    }

    public void setPoints(List<TimeLinePoint> points) {
        this.points = points;
    }

    public double getTopPrice() {
        return topPrice;
    }

    public void setTopPrice(double topPrice) {
        this.topPrice = topPrice;
    }

    public double getBottomPrice() {
        return bottomPrice;
    }

    public void setBottomPrice(double bottomPrice) {
        this.bottomPrice = bottomPrice;
    }

    public double getAbovePrice() {
        return abovePrice;
    }

    public void setAbovePrice(double abovePrice) {
        this.abovePrice = abovePrice;
    }

    public double getUnderPrice() {
        return underPrice;
    }

    public void setUnderPrice(double underPrice) {
        this.underPrice = underPrice;
    }

    public double getMidPrice() {
        return midPrice;
    }

    public void setMidPrice(double midPrice) {
        this.midPrice = midPrice;
    }

    public String getTopPercent() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(topPercent * 100);
    }

    public void setTopPercent(double topPercent) {
        this.topPercent = topPercent;
    }

    public String getBottomPercent() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(bottomPercent * 100);
    }

    public void setBottomPercent(double bottomPercent) {
        this.bottomPercent = bottomPercent;
    }

    public String getAbovePercent() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(abovePercent * 100);
    }

    public void setAbovePercent(double abovePercent) {
        this.abovePercent = abovePercent;
    }

    public String getUnderPercent() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(underPercent * 100);
    }

    public void setUnderPercent(double underPercent) {
        this.underPercent = underPercent;
    }

    public String getMidPercent() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(midPercent * 100);
    }

    public void setMidPercent(double midPercent) {
        this.midPercent = midPercent;
    }
}
