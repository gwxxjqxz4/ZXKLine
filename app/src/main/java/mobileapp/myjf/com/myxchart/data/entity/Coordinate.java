package mobileapp.myjf.com.myxchart.data.entity;

/**
 * create by gwx
 * 坐标点类，用于记录和操作一个点的横、纵坐标
 */
public class Coordinate {

    // 该点横坐标
    public double xValue;
    // 该点纵坐标
    public double yValue;

    // 构造方法，直接传递横纵坐标来生成该类对象
    public Coordinate(double xValue,double yValue){
        this.xValue = xValue;
        this.yValue = yValue;
    }

}
