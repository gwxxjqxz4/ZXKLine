package mobileapp.myjf.com.myxchart.utils.calculation;

import android.app.Activity;
import android.util.Log;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import mobileapp.myjf.com.myxchart.data.entity.localdata.KLineLocal;
import mobileapp.myjf.com.myxchart.data.entity.localdata.TimeLineLocal;
import mobileapp.myjf.com.myxchart.data.entity.render.KLineRender;
import mobileapp.myjf.com.myxchart.data.entity.render.TimeLineRender;
import mobileapp.myjf.com.myxchart.data.entity.util.KLineData;
import mobileapp.myjf.com.myxchart.data.entity.util.KLineItem;
import mobileapp.myjf.com.myxchart.data.entity.util.KLinePoint;
import mobileapp.myjf.com.myxchart.data.entity.util.TimeLineData;
import mobileapp.myjf.com.myxchart.data.entity.util.TimeLinePoint;
import mobileapp.myjf.com.myxchart.data.global.GlobalViewsUtil;
import mobileapp.myjf.com.myxchart.data.global.Variable;
import mobileapp.myjf.com.myxchart.ui.layout.KLineMainLayout;
import mobileapp.myjf.com.myxchart.ui.layout.KLineSecondaryLayout;

/**
 * 对本地缓存进行计算得出渲染数据对象的类
 */
public class LocalToView {

    // K线图一屏显示的数据量，在getKLineRender中初始化
    private static int itemNumber;
    private static Activity activity;

    public static TimeLineRender getTimeLineRender(Activity activity, TimeLineLocal timeLineLocal) {

        RelativeLayout timeLineLayout = GlobalViewsUtil.getTimeLineLayout(activity);
        double viewWidth = timeLineLayout.getWidth() - PXUtils.dip2px(activity,20);
        double viewHeight = timeLineLayout.getHeight();

        // 从本地缓存对象中获取数据
        List<TimeLineData> timeLineDatas = timeLineLocal.getTimeLineDatas();
        // 新建渲染对象，准备存储计算结果用于渲染显示界面
        TimeLineRender timeLineRender = new TimeLineRender();

        // 从本地缓存中获取最小价格、最大价格及昨日收盘价格（基准价格）
        double midPrice = timeLineLocal.getYesterdayPrice();
        double maxPrice = timeLineLocal.getMaxPrice();
        double minPrice = timeLineLocal.getMinPrice();

        // 计算得出分时图Y轴上的价格
        double abs = Math.abs(maxPrice - midPrice) > Math.abs(midPrice - minPrice) ? Math.abs(maxPrice - midPrice) : Math.abs(midPrice - minPrice);
        double topPrice = midPrice + abs;
        double bottomPrice = midPrice - abs;
        double abovePrice = (midPrice + topPrice) / 2;
        double underPrice = (midPrice + bottomPrice) / 2;

        // 计算得出分时图Y轴上的百分比
        double topPercent = (maxPrice - midPrice) / midPrice;
        double bottomPercent = (bottomPrice - midPrice) / midPrice;
        double abovePercent = (abovePrice - midPrice) / midPrice;
        double underPercent = (underPrice - midPrice) / midPrice;

        // 将计算结果设置到渲染对象中
        timeLineRender.setTopPrice(topPrice);
        timeLineRender.setBottomPrice(bottomPrice);
        timeLineRender.setAbovePrice(abovePrice);
        timeLineRender.setUnderPrice(underPrice);
        timeLineRender.setMidPrice(midPrice);
        timeLineRender.setTopPercent(topPercent);
        timeLineRender.setBottomPercent(bottomPercent);
        timeLineRender.setAbovePercent(abovePercent);
        timeLineRender.setUnderPercent(underPercent);
        timeLineRender.setMidPercent(0);

        List<TimeLinePoint> timeLinePoints = new ArrayList<>();
        double height = viewHeight * 19 / 20;
        double width = viewWidth;
        double unitX = width / 1440;
        double unitY = height / (abs * 2 + 2);
        double midY = viewHeight / 2;

        for (int i = 0; i < timeLineDatas.size(); i++) {

            int hour = timeLineDatas.get(i).getHour();
            int minute = timeLineDatas.get(i).getMinute();
            double price = timeLineDatas.get(i).getPrice();
            String hourString;
            String minuteString;

            if (hour < 10) {
                hourString = "0" + hour;
            } else {
                hourString = "" + hour;
            }

            if (minute < 10) {
                minuteString = "0" + minute;
            } else {
                minuteString = "" + minute;
            }

            String time = hourString + ":" + minuteString;
            double percent = (price - midPrice) / midPrice;
            double coordinateX = i * unitX;
            double coordinateY = midY - (price - midPrice) * unitY;

            TimeLinePoint timeLinePoint = new TimeLinePoint();
            timeLinePoint.setPercent(percent);
            timeLinePoint.setPrice(price);
            timeLinePoint.setCoordinateX(coordinateX);
            timeLinePoint.setCoordinateY(coordinateY);
            timeLinePoint.setTime(time);

            if (timeLinePoint.getPrice() != 0) {
                timeLinePoints.add(timeLinePoint);
            }

        }

        timeLineRender.setPoints(timeLinePoints);

        return timeLineRender;
    }

    public static KLineRender getKLineRender(Activity activity, KLineLocal kLineLocal) {

        LocalToView.activity = activity;

        KLineMainLayout kLineMainLayout = GlobalViewsUtil.getMainLayout(activity);
        KLineSecondaryLayout kLineSecondaryLayout = GlobalViewsUtil.getSecondaryLayout(activity);

        int startPosition = Variable.getScrollStartPosition();
        itemNumber = Variable.getItemNumber();

        double viewWidth = kLineMainLayout.getWidth();
        double mainViewHeight = kLineMainLayout.getHeight();
        double secondaryViewHeight = kLineSecondaryLayout.getHeight();

        double width = viewWidth;
        double mainHeight = mainViewHeight * 19 / 20;
        double startMainY = mainHeight / 40;
        double startSecondaryY = secondaryViewHeight / 40;
        double secondaryHeight = secondaryViewHeight * 19 / 20;

        List<KLineData> kLineDatas = kLineLocal.getkLineDatas();
        List<KLineData> viewDatas = new ArrayList<>();
        int lastPosition = 0;
        int firstPosition = 0;
        if (kLineDatas.size() < itemNumber) {
            firstPosition = kLineDatas.size() - 1;
            lastPosition = 0;
        } else {
            lastPosition = kLineDatas.size() - startPosition - itemNumber - 1;
            firstPosition = kLineDatas.size() - startPosition - 1;
        }
        if (firstPosition > kLineDatas.size() - 1) {
            Log.e("排错处理", "firstPosition过大");
            firstPosition = kLineDatas.size() - 1;
            lastPosition = firstPosition - itemNumber;
        }

        for (int i = firstPosition; i > lastPosition; i--) {
            viewDatas.add(kLineDatas.get(i));
        }

        double unitX = width / itemNumber;
        double[] limitValue = getLimitValue(viewDatas);
        double maxValue = limitValue[0];
        double minValue = limitValue[1];
        double midValue = (maxValue + minValue) / 2;
        double aboveValue = (maxValue + midValue) / 2;
        double underValue = (midValue + minValue) / 2;

        // 求出最大值和最小值的差值
        double difference = maxValue - minValue;
        // 求出每单位价格对应的屏幕高度
        double mainUnitY = mainHeight / difference * 0.95f;
        // K线矩形参数的集合，每个条目为一个矩形，每个矩形中有四个坐标值（上下左右）
        List<KLineItem> items = new ArrayList<>();
        // 遍历服务器解析到的数据并生成K线坐标对象集合
        setMainItems(items, viewDatas, width, unitX, maxValue, mainUnitY, startMainY);
        double[] secondaryLimit = setSecondaryItems(items, viewDatas, width, secondaryHeight, unitX, startSecondaryY);


        KLineRender kLineRender = new KLineRender();

        kLineRender.setTopPrice(maxValue);
        kLineRender.setBottomPrice(minValue);
        kLineRender.setMidPrice(midValue);
        kLineRender.setAbovePrice(aboveValue);
        kLineRender.setUnderPrice(underValue);

        kLineRender.setMacdTop(secondaryLimit[0]);
        kLineRender.setMacdBottom(secondaryLimit[1]);
        kLineRender.setRsiTop(secondaryLimit[2]);
        kLineRender.setMacdBottom(secondaryLimit[3]);
        kLineRender.setBiasTop(secondaryLimit[4]);
        kLineRender.setBiasBottom(secondaryLimit[5]);
        kLineRender.setKdjTop(secondaryLimit[6]);
        kLineRender.setKdjBottom(secondaryLimit[7]);

        kLineRender.setItems(items);

        // 返回坐标对象集合
        return kLineRender;

    }

    public static void setMainItems(List<KLineItem> items, List<KLineData> viewDatas, double width, double unitX, double maxValue, double mainUnitY, double startMainY) {
        for (int i = 0; i < viewDatas.size(); i++) {
            // 该条服务器数据对应生成一个坐标对象
            KLineItem item = new KLineItem();
            // 计算该坐标上下左右的参数
            double left = width - ((i + 1) * unitX) + 1;
            double right = width - (i * unitX) - 1;
            double top;
            double bottom;
            // 计算上下影线的参数值
            double xValue = (left + right) / 2;
            double highValue = (maxValue - viewDatas.get(i).getHigh()) * mainUnitY + startMainY;
            double lowValue = (maxValue - viewDatas.get(i).getLow()) * mainUnitY + startMainY;
            // 计算Ma5线、Ma10线、Ma30线的参数值
            double ma5Y = (maxValue - viewDatas.get(i).getMa5()) * mainUnitY + startMainY;
            double ma10Y = (maxValue - viewDatas.get(i).getMa10()) * mainUnitY + startMainY;
            double ma30Y = (maxValue - viewDatas.get(i).getMa30()) * mainUnitY + startMainY;
            // 若开盘价大于收盘价为跌，反之则为涨，根据涨跌计算上下两个参数并设置涨跌状态
            if (viewDatas.get(i).getOpen() > viewDatas.get(i).getClose()) {
                top = (maxValue - viewDatas.get(i).getOpen()) * mainUnitY + startMainY;
                bottom = (maxValue - viewDatas.get(i).getClose()) * mainUnitY + startMainY;
                item.setStatus(2);
            } else {
                bottom = (maxValue - viewDatas.get(i).getOpen()) * mainUnitY + startMainY;
                top = (maxValue - viewDatas.get(i).getClose()) * mainUnitY + startMainY;
                item.setStatus(1);
            }

            double closeY = (maxValue - viewDatas.get(i).getClose()) * mainUnitY + startMainY;

            // 将计算到的参数设置给坐标对象
            item.setCloseY(closeY);
            item.setRectTop(top);
            item.setRectBottom(bottom);
            item.setRectLeft(left);
            item.setRectRight(right);
            item.sethLineTop(new KLinePoint(xValue, highValue, viewDatas.get(i).getHigh()));
            item.sethLineBottom(new KLinePoint(xValue, lowValue, viewDatas.get(i).getLow()));
            item.setMa5Point(new KLinePoint(xValue, ma5Y, viewDatas.get(i).getMa5()));
            item.setMa10Point(new KLinePoint(xValue, ma10Y, viewDatas.get(i).getMa10()));
            item.setMa30Point(new KLinePoint(xValue, ma30Y, viewDatas.get(i).getMa30()));
            item.setDate(viewDatas.get(i).getTime());
            item.setPrice(viewDatas.get(i).getClose());
            // 将设置好的坐标对象添加进集合中
            items.add(item);
        }
    }

    public static double[] getLimitValue(List<KLineData> kLineDatas) {
        double maxValue = kLineDatas.get(3).getClose();
        double minValue = kLineDatas.get(3).getClose();

        int lastPosition;
        if (kLineDatas.size() > itemNumber) {
            lastPosition = itemNumber;
        } else {
            lastPosition = kLineDatas.size();
        }

        // 遍历服务器解析到的数据，求出七种参数中的最大值和最小值
        for (int i = 0; i < lastPosition; i++) {
            if (kLineDatas.get(i).getOpen() > maxValue) {
                maxValue = kLineDatas.get(i).getOpen();
            }
            if (kLineDatas.get(i).getClose() > maxValue) {
                maxValue = kLineDatas.get(i).getClose();
            }
            if (kLineDatas.get(i).getHigh() > maxValue) {
                maxValue = kLineDatas.get(i).getHigh();
            }
            if (kLineDatas.get(i).getLow() > maxValue) {
                maxValue = kLineDatas.get(i).getLow();
            }
            if (kLineDatas.get(i).getMa5() > maxValue) {
                maxValue = kLineDatas.get(i).getMa5();
            }
            if (kLineDatas.get(i).getMa10() > maxValue) {
                maxValue = kLineDatas.get(i).getMa10();
            }
            if (kLineDatas.get(i).getMa30() > maxValue) {
                maxValue = kLineDatas.get(i).getMa30();
            }

            if (kLineDatas.get(i).getOpen() < minValue && kLineDatas.get(i).getOpen() != 0) {
                minValue = kLineDatas.get(i).getOpen();
            }
            if (kLineDatas.get(i).getClose() < minValue && kLineDatas.get(i).getClose() != 0) {
                minValue = kLineDatas.get(i).getClose();
            }
            if (kLineDatas.get(i).getHigh() < minValue && kLineDatas.get(i).getHigh() != 0) {
                minValue = kLineDatas.get(i).getHigh();
            }
            if (kLineDatas.get(i).getLow() < minValue && kLineDatas.get(i).getLow() != 0) {
                minValue = kLineDatas.get(i).getLow();
            }
            if (kLineDatas.get(i).getMa5() < minValue && kLineDatas.get(i).getMa5() != 0) {
                minValue = kLineDatas.get(i).getMa5();
            }
            if (kLineDatas.get(i).getMa10() < minValue && kLineDatas.get(i).getMa10() != 0) {
                minValue = kLineDatas.get(i).getMa10();
            }
            if (kLineDatas.get(i).getMa30() < minValue && kLineDatas.get(i).getMa30() != 0) {
                minValue = kLineDatas.get(i).getMa30();
            }
        }

        double[] limitValue = new double[]{maxValue, minValue};
        return limitValue;

    }

    public static double[] getMacdLimit(List<KLineData> kLineDatas) {
        double maxValue = kLineDatas.get(0).getMacd();
        double minValue = kLineDatas.get(0).getMacd();

        int lastPosition;
        if (kLineDatas.size() > itemNumber) {
            lastPosition = itemNumber;
        } else {
            lastPosition = kLineDatas.size();
        }

        // 遍历服务器解析到的数据，求出最大值和最小值
        for (int i = 0; i < lastPosition; i++) {
            if (kLineDatas.get(i).getMacd() > maxValue) {
                maxValue = kLineDatas.get(i).getMacd();
            }

            if (kLineDatas.get(i).getMacd() < minValue && kLineDatas.get(i).getMacd() != 0) {
                minValue = kLineDatas.get(i).getMacd();
            }

            if (kLineDatas.get(i).getMacd_dea() > maxValue) {
                maxValue = kLineDatas.get(i).getMacd_dea();
            }

            if (kLineDatas.get(i).getMacd_dea() < minValue && kLineDatas.get(i).getMacd_dea() != 0) {
                minValue = kLineDatas.get(i).getMacd_dea();
            }

            if (kLineDatas.get(i).getMacd_dif() > maxValue) {
                maxValue = kLineDatas.get(i).getMacd_dif();
            }

            if (kLineDatas.get(i).getMacd_dif() < minValue && kLineDatas.get(i).getMacd_dif() != 0) {
                minValue = kLineDatas.get(i).getMacd_dif();
            }
        }

        double[] limitValue = new double[]{maxValue, minValue};
        return limitValue;
    }

    public static double[] getRsiLimit(List<KLineData> kLineDatas) {
        double maxValue = kLineDatas.get(0).getRsi1();
        double minValue = kLineDatas.get(0).getRsi1();

        int lastPosition;
        if (kLineDatas.size() > itemNumber) {
            lastPosition = itemNumber;
        } else {
            lastPosition = kLineDatas.size();
        }

        // 遍历服务器解析到的数据，求出最大值和最小值
        for (int i = 0; i < lastPosition; i++) {
            if (kLineDatas.get(i).getRsi1() > maxValue) {
                maxValue = kLineDatas.get(i).getRsi1();
            }

            if (kLineDatas.get(i).getRsi1() < minValue && kLineDatas.get(i).getRsi1() != 0) {
                minValue = kLineDatas.get(i).getRsi1();
            }

            if (kLineDatas.get(i).getRsi2() > maxValue) {
                maxValue = kLineDatas.get(i).getRsi2();
            }

            if (kLineDatas.get(i).getRsi2() < minValue && kLineDatas.get(i).getRsi2() != 0) {
                minValue = kLineDatas.get(i).getRsi2();
            }

            if (kLineDatas.get(i).getRsi3() > maxValue) {
                maxValue = kLineDatas.get(i).getRsi3();
            }

            if (kLineDatas.get(i).getRsi3() < minValue && kLineDatas.get(i).getRsi3() != 0) {
                minValue = kLineDatas.get(i).getRsi3();
            }
        }

        double[] limitValue = new double[]{maxValue, minValue};
        return limitValue;
    }

    public static double[] getBiasLimit(List<KLineData> kLineDatas) {
        double maxValue = kLineDatas.get(0).getBias1();
        double minValue = kLineDatas.get(0).getBias1();

        int lastPosition;
        if (kLineDatas.size() > itemNumber) {
            lastPosition = itemNumber;
        } else {
            lastPosition = kLineDatas.size();
        }

        // 遍历服务器解析到的数据，求出最大值和最小值
        for (int i = 0; i < lastPosition; i++) {
            if (kLineDatas.get(i).getBias1() > maxValue) {
                maxValue = kLineDatas.get(i).getBias1();
            }

            if (kLineDatas.get(i).getBias1() < minValue && kLineDatas.get(i).getBias1() != 0) {
                minValue = kLineDatas.get(i).getBias1();
            }

            if (kLineDatas.get(i).getBias2() > maxValue) {
                maxValue = kLineDatas.get(i).getBias2();
            }

            if (kLineDatas.get(i).getBias2() < minValue && kLineDatas.get(i).getBias2() != 0) {
                minValue = kLineDatas.get(i).getBias2();
            }

            if (kLineDatas.get(i).getBias3() > maxValue) {
                maxValue = kLineDatas.get(i).getBias3();
            }

            if (kLineDatas.get(i).getBias3() < minValue && kLineDatas.get(i).getBias3() != 0) {
                minValue = kLineDatas.get(i).getBias3();
            }
        }

        double[] limitValue = new double[]{maxValue, minValue};
        return limitValue;
    }

    public static double[] getKdjLimit(List<KLineData> kLineDatas) {
        double maxValue = kLineDatas.get(0).getKdj_d();
        double minValue = kLineDatas.get(0).getKdj_d();

        int lastPosition;
        if (kLineDatas.size() > itemNumber) {
            lastPosition = itemNumber;
        } else {
            lastPosition = kLineDatas.size();
        }

        // 遍历服务器解析到的数据，求出最大值和最小值
        for (int i = 0; i < lastPosition; i++) {
            if (kLineDatas.get(i).getKdj_d() > maxValue) {
                maxValue = kLineDatas.get(i).getKdj_d();
            }

            if (kLineDatas.get(i).getKdj_d() < minValue && kLineDatas.get(i).getKdj_d() != 0) {
                minValue = kLineDatas.get(i).getKdj_d();
            }

            if (kLineDatas.get(i).getKdj_j() > maxValue) {
                maxValue = kLineDatas.get(i).getKdj_j();
            }

            if (kLineDatas.get(i).getKdj_j() < minValue && kLineDatas.get(i).getKdj_j() != 0) {
                minValue = kLineDatas.get(i).getKdj_j();
            }

            if (kLineDatas.get(i).getKdj_k() > maxValue) {
                maxValue = kLineDatas.get(i).getKdj_k();
            }

            if (kLineDatas.get(i).getKdj_k() < minValue && kLineDatas.get(i).getKdj_k() != 0) {
                minValue = kLineDatas.get(i).getKdj_k();
            }
        }

        double[] limitValue = new double[]{maxValue, minValue};
        return limitValue;
    }

    public static double[] setSecondaryItems(List<KLineItem> items, List<KLineData> viewDatas, double width, double height, double unitX, double startSecondaryY) {

        double[] macdLimit = getMacdLimit(viewDatas);
        double[] rsiLimit = getRsiLimit(viewDatas);
        double[] biasLimit = getBiasLimit(viewDatas);
        double[] kdjLimit = getKdjLimit(viewDatas);

        double macdMax = macdLimit[0];
        double macdMin = macdLimit[1];
        double rsiMax = rsiLimit[0];
        double rsiMin = rsiLimit[1];
        double biasMax = biasLimit[0];
        double biasMin = biasLimit[1];
        double kdjMax = kdjLimit[0];
        double kdjMin = kdjLimit[1];

        double macdDifference = macdMax - macdMin;
        double rsiDifference = rsiMax - rsiMin;
        double biasDifference = biasMax - biasMin;
        double kdjDifference = kdjMax - kdjMin;

        double macdUnit = height / macdDifference;
        double rsiUnit = height / rsiDifference;
        double biasUnit = height / biasDifference;
        double kdjUnit = height / kdjDifference;

        setMacdItems(items, viewDatas, width, unitX, macdMax, macdMin, macdUnit, macdDifference, startSecondaryY);
        setRsiItems(items, viewDatas, width, unitX, rsiMax, rsiMin, rsiUnit, rsiDifference, startSecondaryY);
        setBiasItems(items, viewDatas, width, unitX, biasMax, biasMin, biasUnit, biasDifference, startSecondaryY);
        setKdjItems(items, viewDatas, width, unitX, kdjMax, kdjMin, kdjUnit, kdjDifference, startSecondaryY);

        double[] secondaryLimit = new double[]{macdMax, macdMin, rsiMax, rsiMin, biasMax, biasMin, kdjMax, kdjMin};

        return secondaryLimit;

    }

    public static void setMacdItems(List<KLineItem> items, List<KLineData> viewDatas, double width, double unitX, double maxValue, double minValue, double unitY, double difference, double startSecondarY) {

        int lastPosition;
        if (viewDatas.size() > itemNumber) {
            lastPosition = itemNumber;
        } else {
            lastPosition = viewDatas.size();
        }

        for (int i = 0; i < lastPosition; i++) {

            KLineItem kLinePoint = items.get(i);

            double left = width - ((i + 1) * unitX) + 1;
            double right = width - (i * unitX) - 1;
            double top;
            double bottom;
            // 计算MACD_DIF和MACD_DEA的参数
            double coordinateX = (left + right) / 2;
            double macd_difY = (maxValue - viewDatas.get(i).getMacd_dif()) * unitY + startSecondarY;
            double macd_deaY = (maxValue - viewDatas.get(i).getMacd_dea()) * unitY + startSecondarY;
            // 记录MACD的正负状态
            if (viewDatas.get(i).getMacd() > 0) {
                top = (difference - viewDatas.get(i).getMacd() + minValue) * unitY + startSecondarY;
                bottom = (difference - Math.abs(minValue)) * unitY + startSecondarY;
                kLinePoint.setMacdStatus(1);
            } else {
                bottom = (difference - viewDatas.get(i).getMacd() + minValue) * unitY + startSecondarY;
                top = (difference - Math.abs(minValue)) * unitY + startSecondarY;
                kLinePoint.setMacdStatus(2);
            }
            // 将计算到的参数设置给坐标对象
            kLinePoint.setMacdTop(top);
            kLinePoint.setMacdBottom(bottom);
            kLinePoint.setMacd_difPoint(new KLinePoint(coordinateX, macd_difY, viewDatas.get(i).getMacd_dif()));
            kLinePoint.setMacd_deaPoint(new KLinePoint(coordinateX, macd_deaY, viewDatas.get(i).getMacd_dea()));

        }

    }

    public static void setRsiItems(List<KLineItem> items, List<KLineData> viewDatas, double width, double unitX, double maxValue, double minValue, double unitY, double difference, double startSecondarY) {

        int lastPosition;
        if (viewDatas.size() > itemNumber) {
            lastPosition = itemNumber;
        } else {
            lastPosition = viewDatas.size();
        }

        for (int i = 0; i < lastPosition; i++) {

            KLineItem kLinePoint = items.get(i);

            double left = width - ((i + 1) * unitX) + 1;
            double right = width - (i * unitX) - 1;
            double coordinateX = (left + right) / 2;
            double rsi1Y = (maxValue - viewDatas.get(i).getRsi1()) * unitY + startSecondarY;
            double rsi2Y = (maxValue - viewDatas.get(i).getRsi2()) * unitY + startSecondarY;
            double rsi3Y = (maxValue - viewDatas.get(i).getRsi3()) * unitY + startSecondarY;
            // 记录MACD的正负状态
            kLinePoint.setRsi1Point(new KLinePoint(coordinateX, rsi1Y, viewDatas.get(i).getMacd_dif()));
            kLinePoint.setRsi2Point(new KLinePoint(coordinateX, rsi2Y, viewDatas.get(i).getMacd_dea()));
            kLinePoint.setRsi3Point(new KLinePoint(coordinateX, rsi3Y, viewDatas.get(i).getMacd_dea()));

        }

    }

    public static void setBiasItems(List<KLineItem> items, List<KLineData> viewDatas, double width, double unitX, double maxValue, double minValue, double unitY, double difference, double startSecondarY) {

        int lastPosition;
        if (viewDatas.size() > itemNumber) {
            lastPosition = itemNumber;
        } else {
            lastPosition = viewDatas.size();
        }

        for (int i = 0; i < lastPosition; i++) {

            KLineItem kLinePoint = items.get(i);

            double left = width - ((i + 1) * unitX) + 1;
            double right = width - (i * unitX) - 1;
            double top;
            double bottom;
            // 计算MACD_DIF和MACD_DEA的参数
            double coordinateX = (left + right) / 2;
            double bias1Y = (maxValue - viewDatas.get(i).getBias1()) * unitY + startSecondarY;
            double bias2Y = (maxValue - viewDatas.get(i).getBias2()) * unitY + startSecondarY;
            double bias3Y = (maxValue - viewDatas.get(i).getBias3()) * unitY + startSecondarY;
            // 记录MACD的正负状态
            kLinePoint.setBias1Point(new KLinePoint(coordinateX, bias1Y, viewDatas.get(i).getMacd_dif()));
            kLinePoint.setBias2Point(new KLinePoint(coordinateX, bias2Y, viewDatas.get(i).getMacd_dea()));
            kLinePoint.setBias3Point(new KLinePoint(coordinateX, bias3Y, viewDatas.get(i).getMacd_dea()));

        }

    }

    public static void setKdjItems(List<KLineItem> items, List<KLineData> viewDatas, double width, double unitX, double maxValue, double minValue, double unitY, double difference, double startSecondarY) {

        int lastPosition;
        if (viewDatas.size() > itemNumber) {
            lastPosition = itemNumber;
        } else {
            lastPosition = viewDatas.size();
        }

        for (int i = 0; i < lastPosition; i++) {

            KLineItem kLinePoint = items.get(i);

            double left = width - ((i + 1) * unitX) + 1;
            double right = width - (i * unitX) - 1;
            double top;
            double bottom;
            // 计算MACD_DIF和MACD_DEA的参数
            double coordinateX = (left + right) / 2;
            double kdjDY = (maxValue - viewDatas.get(i).getKdj_d()) * unitY + startSecondarY;
            double kdjJY = (maxValue - viewDatas.get(i).getKdj_j()) * unitY + startSecondarY;
            double kdjKY = (maxValue - viewDatas.get(i).getKdj_k()) * unitY + startSecondarY;
            // 记录MACD的正负状态
            kLinePoint.setKdj_dPoint(new KLinePoint(coordinateX, kdjDY, viewDatas.get(i).getMacd_dif()));
            kLinePoint.setKdj_jPoint(new KLinePoint(coordinateX, kdjJY, viewDatas.get(i).getMacd_dea()));
            kLinePoint.setKdj_kPoint(new KLinePoint(coordinateX, kdjKY, viewDatas.get(i).getMacd_dea()));

        }

    }

}
