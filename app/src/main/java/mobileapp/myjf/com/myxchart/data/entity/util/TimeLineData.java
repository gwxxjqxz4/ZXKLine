package mobileapp.myjf.com.myxchart.data.entity.util;

/**
 * 分时线数据对象，用于本地缓存分时线每个点的数据
 */
public class TimeLineData {

    private double price;
    private int hour;
    private int minute;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
