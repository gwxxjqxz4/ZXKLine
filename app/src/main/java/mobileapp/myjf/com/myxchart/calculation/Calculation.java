package mobileapp.myjf.com.myxchart.calculation;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mobileapp.myjf.com.myxchart.data.entity.Coordinate;
import mobileapp.myjf.com.myxchart.data.entity.KLineCoordinate;
import mobileapp.myjf.com.myxchart.data.entity.KLineDate;
import mobileapp.myjf.com.myxchart.data.entity.TimeLineData;
import mobileapp.myjf.com.myxchart.data.entity.TimeLinePoint;

/**
 * create by gwx
 * 计算工具类，用于将服务器解析到的数据转化为绘制图形用的数据
 */
public class Calculation {

    /**
     * 计算分时线每个点坐标的方法
     *
     * @param yesterdayPrice 昨日收盘价
     * @param oldDates       从服务器解析到的数据
     * @param height         控件高度
     * @param widht          控件宽度
     * @return 计算到的坐标点集合
     */
    public static List<Coordinate> calculationLines(double yesterdayPrice, List<TimeLineData> oldDates, double height, double widht) {

        // 用于记录分时线中的最大价格
        double maxValue = oldDates.get(0).getClose();
        // 用于记录分时线中的最小价格
        double minValue = oldDates.get(0).getClose();
        // 遍历集合求出最大最小价格
        for (int i = 0; i < oldDates.size(); i++) {

            if (oldDates.get(i).getClose() > maxValue) {
                maxValue = oldDates.get(i).getClose();
            }
            if (oldDates.get(i).getClose() < minValue) {
                minValue = oldDates.get(i).getClose();
            }
        }

        // 每分钟在屏幕上的对应宽度
        double unitX = widht / 1440;
        // 所有价格与昨日收盘价的差值中的最大值
        double abs = Math.abs(maxValue - yesterdayPrice) >= Math.abs(yesterdayPrice - minValue) ? Math.abs(maxValue - yesterdayPrice) : Math.abs(yesterdayPrice - minValue);
        // 每单位价格在屏幕上的对应高度
        double unitY = height / (abs * 2 + 20);
        // 记录每分钟价格的集合
        List<TimeLinePoint> points = new ArrayList<>();
        int j = 0;
        // 初始化集合
        for (int i = 361; i <= 1800; i++) {
            if (i > 1440) {
                j = i - 1440;
            } else {
                j = i;
            }
            TimeLinePoint point = new TimeLinePoint();
            point.setIndex(i - 360);
            point.setPrice(0);
            point.setMinute(j);
            points.add(point);

        }

        // 将服务器返回的价格填充到集合中
        for (TimeLineData timeLineData : oldDates) {
            Long timeValue = timeLineData.getOpenTime() * 1000;
            SimpleDateFormat sdr = new SimpleDateFormat("HH-mm");
            String timeString = sdr.format(timeValue);
            String[] timeStrings = timeString.split("-");
            int minute = Integer.parseInt(timeStrings[0]) * 60 + Integer.parseInt(timeStrings[1]);

            if (minute <= 1440 && minute > 360) {
                points.get(minute - 361).setPrice(timeLineData.getClose());
            } else {
                points.get(minute + 1079).setPrice(timeLineData.getClose());
            }


        }
        // 若6点01没有数据则将该点价格设为昨日收盘价
        if (points.get(0).getPrice() == 0) {
            points.get(0).setPrice(yesterdayPrice);
        }

        // 用于记录最后一个有效价格的索引
        int flag = 0;
        // 求出最后一个有效价格的索引
        for (int i = points.size() - 1; i >= 0; i--) {
            if (points.get(i).getPrice() != 0) {
                flag = i;
                break;
            }
        }

        // 补充无数据的点的价格
        for (int i = 1; i <= flag; i++) {
            if ((int) points.get(i).getPrice() == 0) {
                points.get(i).setPrice(points.get(i - 1).getPrice());
            }
        }

        // 用于记录坐标点的集合
        List<Coordinate> coordinates = new ArrayList<>();

        // 将价格和集合索引转为横纵坐标
        for (int i = 0; i < flag; i++) {
            double xValue;
            xValue = i * unitX;
            double yValue = (maxValue - points.get(i).getPrice()) * unitY;
            Coordinate coordinate = new Coordinate(xValue, yValue);
            coordinates.add(coordinate);
        }

        // 返回计算到的坐标点集合
        return coordinates;

    }

    /**
     * 计算K线主图每个坐标点的方法
     *
     * @param kLineDates      从服务器解析到的数据
     * @param numberPerScreen 每屏幕显示的数据条目数量
     * @param height          屏幕高度
     * @param widht           屏幕宽度
     * @return K线主图坐标点集合
     */
    public static List<KLineCoordinate> calculationKLines(List<KLineDate> kLineDates, int numberPerScreen, double height, double widht) {

        // 初始化最大值和最小值
        double maxValue = kLineDates.get(0).getClose();
        double minValue = kLineDates.get(0).getClose();

        // 遍历服务器解析到的数据，求出七种参数中的最大值和最小值
        for (int i = 0; i < numberPerScreen; i++) {
            if (kLineDates.get(i).getOpen() > maxValue) {
                maxValue = kLineDates.get(i).getOpen();
            }
            if (kLineDates.get(i).getClose() > maxValue) {
                maxValue = kLineDates.get(i).getClose();
            }
            if (kLineDates.get(i).getHigh() > maxValue) {
                maxValue = kLineDates.get(i).getHigh();
            }
            if (kLineDates.get(i).getLow() > maxValue) {
                maxValue = kLineDates.get(i).getLow();
            }
            if (kLineDates.get(i).getMa5() > maxValue) {
                maxValue = kLineDates.get(i).getMa5();
            }
            if (kLineDates.get(i).getMa10() > maxValue) {
                maxValue = kLineDates.get(i).getMa10();
            }
            if (kLineDates.get(i).getMa30() > maxValue) {
                maxValue = kLineDates.get(i).getMa30();
            }

            if (kLineDates.get(i).getOpen() < minValue) {
                minValue = kLineDates.get(i).getOpen();
            }
            if (kLineDates.get(i).getClose() < minValue) {
                minValue = kLineDates.get(i).getClose();
            }
            if (kLineDates.get(i).getHigh() < minValue) {
                minValue = kLineDates.get(i).getHigh();
            }
            if (kLineDates.get(i).getLow() < minValue) {
                minValue = kLineDates.get(i).getLow();
            }
            if (kLineDates.get(i).getMa5() < minValue) {
                minValue = kLineDates.get(i).getMa5();
            }
            if (kLineDates.get(i).getMa10() < minValue) {
                minValue = kLineDates.get(i).getMa10();
            }
            if (kLineDates.get(i).getMa30() < minValue) {
                minValue = kLineDates.get(i).getMa30();
            }
        }

        // 求出最大值和最小值的差值
        double difference = maxValue - minValue;
        // 求出每单位价格对应的屏幕高度
        double unitY = height / difference;
        // 求出每个矩形的宽度
        double unitX = widht / numberPerScreen;
        // K线矩形参数的集合，每个条目为一个矩形，每个矩形中有四个坐标值（上下左右）
        List<KLineCoordinate> coordinates = new ArrayList<>();
        // 遍历服务器解析到的数据并生成K线坐标对象集合
        for (int i = 0; i < numberPerScreen; i++) {
            // 该条服务器数据对应生成一个坐标对象
            KLineCoordinate coordinate = new KLineCoordinate();
            // 计算该坐标上下左右的参数
            double left = widht - ((i + 1) * unitX) + 1;
            double right = widht - (i * unitX) - 1;
            double top;
            double bottom;
            // 计算上下影线的参数值
            double xValue = (left + right) / 2;
            double highValue = (maxValue - kLineDates.get(i).getHigh()) * unitY;
            double lowValue = (maxValue - kLineDates.get(i).getLow()) * unitY;
            // 计算Ma5线、Ma10线、Ma30线的参数值
            double ma5Y = (maxValue - kLineDates.get(i).getMa5()) * unitY;
            double ma10Y = (maxValue - kLineDates.get(i).getMa10()) * unitY;
            double ma30Y = (maxValue - kLineDates.get(i).getMa30()) * unitY;
            // 若开盘价大于收盘价为跌，反之则为涨，根据涨跌计算上下两个参数并设置涨跌状态
            if (kLineDates.get(i).getOpen() > kLineDates.get(i).getClose()) {
                top = (maxValue - kLineDates.get(i).getOpen()) * unitY;
                bottom = (maxValue - kLineDates.get(i).getClose()) * unitY;
                coordinate.setStatus(2);
            } else {
                bottom = (maxValue - kLineDates.get(i).getOpen()) * unitY;
                top = (maxValue - kLineDates.get(i).getClose()) * unitY;
                coordinate.setStatus(1);
            }
            // 将计算到的参数设置给坐标对象
            coordinate.setRectTop(top);
            coordinate.setRectBottom(bottom);
            coordinate.setRectLeft(left);
            coordinate.setRectRight(right);
            coordinate.sethLineTop(new Coordinate(xValue, highValue));
            coordinate.sethLineBottom(new Coordinate(xValue, lowValue));
            coordinate.setMa5Point(new Coordinate(xValue,ma5Y));
            coordinate.setMa10Point(new Coordinate(xValue,ma10Y));
            coordinate.setMa30Point(new Coordinate(xValue,ma30Y));
            // 将设置好的坐标对象添加进集合中
            coordinates.add(coordinate);
        }

        // 返回坐标对象集合
        return coordinates;
    }

    /**
     * 计算MACD图每个坐标点的方法
     *
     * @param kLineDates      从服务器解析到的数据
     * @param numberPerScreen 每屏幕显示的数据条目数量
     * @param height          屏幕高度
     * @param widht           屏幕宽度
     * @return K线主图坐标点集合
     */
    public static List<KLineCoordinate> calculationMACD(List<KLineDate> kLineDates, int numberPerScreen, double height, double widht) {

        // 初始化最大值和最小值
        double maxValue = kLineDates.get(0).getMacd();
        double minValue = kLineDates.get(0).getMacd();

        // 遍历服务器解析到的数据，求出最大值和最小值
        for (int i = 0; i < numberPerScreen; i++) {
            if (kLineDates.get(i).getMacd() > maxValue) {
                maxValue = kLineDates.get(i).getMacd();
            }

            if(kLineDates.get(i).getMacd() < minValue){
                minValue = kLineDates.get(i).getMacd();
            }

            if (kLineDates.get(i).getMacd_dea() > maxValue) {
                maxValue = kLineDates.get(i).getMacd_dea();
            }

            if(kLineDates.get(i).getMacd_dea() < minValue){
                minValue = kLineDates.get(i).getMacd_dea();
            }

            if (kLineDates.get(i).getMacd_dif() > maxValue) {
                maxValue = kLineDates.get(i).getMacd_dif();
            }

            if(kLineDates.get(i).getMacd_dif() < minValue){
                minValue = kLineDates.get(i).getMacd_dif();
            }
        }

        Log.e("value","maxValue = " + maxValue + ",minValue = " + minValue);
        // 求出最大值和最小值的差值
        double difference = maxValue - minValue;
        // 求出每单位价格对应的屏幕高度
        double unitY = height / difference;
        // 求出每个矩形的宽度
        double unitX = widht / numberPerScreen;
        // MACD参数的集合，每个条目为一个矩形，每个矩形中有四个坐标值（上下左右）
        List<KLineCoordinate> coordinates = new ArrayList<>();
        // 遍历服务器解析到的数据并生成MACD坐标对象集合
        for (int i = 0; i < numberPerScreen; i++) {
            // 该条服务器数据对应生成一个坐标对象
            KLineCoordinate coordinate = new KLineCoordinate();
            // 计算该矩形上下左右的参数
            double left = widht - ((i + 1) * unitX) + 1;
            double right = widht - (i * unitX) - 1;
            double top;
            double bottom;
            // 计算MACD_DIF和MACD_DEA的参数
            double xValue = (left + right) / 2;
            double macd_difY = (maxValue - kLineDates.get(i).getMacd_dif()) * unitY;
            double macd_deaY = (maxValue - kLineDates.get(i).getMacd_dea()) * unitY;
            // 记录MACD的正负状态
            if (kLineDates.get(i).getMacd() > 0) {
                top = (difference - kLineDates.get(i).getMacd() + minValue) * unitY;
                bottom = (difference - Math.abs(minValue)) * unitY;
                coordinate.setStatus(1);
            } else {
                bottom = (difference - kLineDates.get(i).getMacd() + minValue) * unitY;
                top = (difference - Math.abs(minValue)) * unitY;
                coordinate.setStatus(2);
            }
            // 将计算到的参数设置给坐标对象
            coordinate.setRectTop(top);
            coordinate.setRectBottom(bottom);
            coordinate.setRectLeft(left);
            coordinate.setRectRight(right);
            coordinate.setMacd_difPoint(new Coordinate(xValue,macd_difY));
            coordinate.setMacd_deaPoint(new Coordinate(xValue,macd_deaY));
            // 将设置好的坐标对象添加进集合中
            coordinates.add(coordinate);
        }

        // 返回坐标对象集合
        return coordinates;

    }

}