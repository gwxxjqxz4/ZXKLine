package mobileapp.myjf.com.myxchart.entity.render;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import mobileapp.myjf.com.myxchart.entity.util.TimeLinePoint;

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

    public String getTopPrice() {
        return new DecimalFormat("#0.0000").format(topPrice);
    }

    public void setTopPrice(double topPrice) {
        this.topPrice = topPrice;
    }

    public String getBottomPrice() {
        return new DecimalFormat("#0.0000").format(bottomPrice);
    }

    public void setBottomPrice(double bottomPrice) {
        this.bottomPrice = bottomPrice;
    }

    public String getAbovePrice() {
        return new DecimalFormat("#0.0000").format(abovePrice);
    }

    public void setAbovePrice(double abovePrice) {
        this.abovePrice = abovePrice;
    }

    public String getUnderPrice() {
        return new DecimalFormat("#0.0000").format(underPrice);
    }

    public void setUnderPrice(double underPrice) {
        this.underPrice = underPrice;
    }

    public String getMidPrice() {
        return new DecimalFormat("#0.0000").format(midPrice);
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
