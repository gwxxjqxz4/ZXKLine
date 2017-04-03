package mobileapp.myjf.com.myxchart.data.entity;

/**
 * create by gwx
 * 分时线点对象，用于操作和保存分时线坐标点计算过程中的过程数据
 */
public class TimeLinePoint {

    // 分钟数，从6：01开始，6:01为0，6:02为1，以此类推
    private int minute;
    // 索引
    private int index;
    // 价格
    private double price;

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TimeLinePoint{" +
                "minute=" + minute +
                ", index=" + index +
                ", price=" + price +
                '}';
    }

}
