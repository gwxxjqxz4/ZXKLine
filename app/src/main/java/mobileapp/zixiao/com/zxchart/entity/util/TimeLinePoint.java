package mobileapp.zixiao.com.zxchart.entity.util;

/**
 * create by gwx
 * 用于记录和操作一个数据点的横、纵坐标、价格及时间的数据点类
 */
public class TimeLinePoint {

    // 该点横坐标
    public double coordinateX;
    // 该点纵坐标
    public double coordinateY;
    // 该点价格
    public double price;
    // 该点时间
    public String time;
    // 该点价格百分比
    public double percent;


    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
