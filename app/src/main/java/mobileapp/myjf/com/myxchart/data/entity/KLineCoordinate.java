package mobileapp.myjf.com.myxchart.data.entity;

/**
 * Created by gwx
 * K线图的坐标点类，用于记录和操作一组K线数据的各项参数
 */
public class KLineCoordinate {

    // K线图矩形顶部参数
    private double rectTop;
    // K线图矩形底部参数
    private double rectBottom;
    // K线图矩形左侧边参数
    private double rectLeft;
    // K线图矩形右侧边参数
    private double rectRight;
    // K线中 涨跌状态   1为涨  2为跌
    // MACD中 1为正  2为负
    private int status;
    // 上影线顶点坐标
    private Coordinate hLineTop;
    // 下影线顶点坐标
    private Coordinate hLineBottom;
    // Ma5线坐标
    private Coordinate ma5Point;
    // Ma10线坐标
    private Coordinate ma10Point;
    // Ma30线坐标
    private Coordinate ma30Point;
    // Macd_dif线坐标点
    private Coordinate macd_difPoint;
    // Macd_dea线坐标点
    private Coordinate macd_deaPoint;

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

    public Coordinate gethLineTop() {
        return hLineTop;
    }

    public void sethLineTop(Coordinate hLineTop) {
        this.hLineTop = hLineTop;
    }

    public Coordinate gethLineBottom() {
        return hLineBottom;
    }

    public void sethLineBottom(Coordinate hLineBottom) {
        this.hLineBottom = hLineBottom;
    }

    public Coordinate getMa5Point() {
        return ma5Point;
    }

    public void setMa5Point(Coordinate ma5Point) {
        this.ma5Point = ma5Point;
    }

    public Coordinate getMa10Point() {
        return ma10Point;
    }

    public void setMa10Point(Coordinate ma10Point) {
        this.ma10Point = ma10Point;
    }

    public Coordinate getMa30Point() {
        return ma30Point;
    }

    public void setMa30Point(Coordinate ma30Point) {
        this.ma30Point = ma30Point;
    }

    public Coordinate getMacd_difPoint() {
        return macd_difPoint;
    }

    public void setMacd_difPoint(Coordinate macd_difPoint) {
        this.macd_difPoint = macd_difPoint;
    }

    public Coordinate getMacd_deaPoint() {
        return macd_deaPoint;
    }

    public void setMacd_deaPoint(Coordinate macd_deaPoint) {
        this.macd_deaPoint = macd_deaPoint;
    }

    @Override
    public String toString() {
        return "KLineCoordinate{" +
                "rectTop=" + rectTop +
                ", rectBottom=" + rectBottom +
                ", rectLeft=" + rectLeft +
                ", rectRight=" + rectRight +
                ", status=" + status +
                ", hLineTop=" + hLineTop +
                ", hLineBottom=" + hLineBottom +
                '}';
    }
}
