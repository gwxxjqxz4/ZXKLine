package mobileapp.myjf.com.myxchart.data.entity.localdata;

import java.util.List;

import mobileapp.myjf.com.myxchart.data.entity.util.TimeLineData;

/**
 * 分时线本地对象，用于缓存分时线数据
 */
public class TimeLineLocal {

    private List<TimeLineData> timeLineDatas;
    private double maxPrice;
    private double minPrice;
    private double yesterdayPrice;

    public TimeLineLocal() {
    }

    public List<TimeLineData> getTimeLineDatas() {
        return timeLineDatas;
    }

    public void setTimeLineDatas(List<TimeLineData> timeLineDatas) {
        this.timeLineDatas = timeLineDatas;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getYesterdayPrice() {
        return yesterdayPrice;
    }

    public void setYesterdayPrice(double yesterdayPrice) {
        this.yesterdayPrice = yesterdayPrice;
    }

}
