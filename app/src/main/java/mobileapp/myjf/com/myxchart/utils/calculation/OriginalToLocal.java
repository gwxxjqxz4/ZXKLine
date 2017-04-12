package mobileapp.myjf.com.myxchart.utils.calculation;

import android.app.Activity;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mobileapp.myjf.com.myxchart.entity.util.KLineData;
import mobileapp.myjf.com.myxchart.entity.localdata.TimeLineLocal;
import mobileapp.myjf.com.myxchart.entity.originaldata.KLineOriginal;
import mobileapp.myjf.com.myxchart.entity.originaldata.TimeLineOriginal;
import mobileapp.myjf.com.myxchart.entity.util.TimeLineData;
import mobileapp.myjf.com.myxchart.entity.originaldata.TimeLineRemote;

/**
 * create by gwx
 * 计算工具类，用于将服务器解析到的数据转化为本地缓存用的数据
 */
public class OriginalToLocal {

    /**
     * 将分时线的数据处理成便于计算的格式
     *
     * @param timeLineOriginal
     * @param activity
     * @return
     */
    public static TimeLineLocal getTimeLineLocal(List<TimeLineRemote> timeLineRemotes,TimeLineOriginal<TimeLineRemote> timeLineOriginal, Activity activity) {

        // 用于记录分时线中的最大价格
        double maxPrice = timeLineRemotes.get(0).getClose();
        // 用于记录分时线中的最小价格
        double minPrice = timeLineRemotes.get(0).getClose();
        // 遍历集合求出最大最小价格
        for (int i = 0; i < timeLineRemotes.size(); i++) {

            if (timeLineRemotes.get(i).getClose() > maxPrice) {
                maxPrice = timeLineRemotes.get(i).getClose();
            }
            if (timeLineRemotes.get(i).getClose() < minPrice) {
                minPrice = timeLineRemotes.get(i).getClose();
            }
        }

        // 用于本地缓存每个时间点数据的集合
        List<TimeLineData> datas = new ArrayList<>();
        // 初始化集合
        for (int i = 0; i < 1440; i++) {
            TimeLineData timeLineData = new TimeLineData();
            timeLineData.setPrice(0);
            timeLineData.setHour(0);
            timeLineData.setMinute(0);

            datas.add(timeLineData);
        }

        // 将数据库中的时间和价格填充到集合中
        // 经过本次遍历集合的时间顺序为从6：01到6：00
        for(TimeLineRemote timeLineRemote:timeLineRemotes){

            // 将时间戳转换成毫秒值
            Long timeValue = timeLineRemote.getOpenTime() * 1000;
            // 将毫秒值转换成时—分格式
            SimpleDateFormat sdr = new SimpleDateFormat("HH-mm");
            String timeString = sdr.format(timeValue);
            // 用-做分隔符将时和分抽取出来
            String[] timeStrings = timeString.split("-");
            // 分钟数为小时数*60+分钟数，得出这是本日的第几分钟
            int minutePosition = Integer.parseInt(timeStrings[0]) * 60 + Integer.parseInt(timeStrings[1]);
            int localPosition;
            if(minutePosition >= 360){
                localPosition = minutePosition - 360;
            }else{
                localPosition = minutePosition + 1080;
            }
            int hour = Integer.parseInt(timeStrings[0]);
            int minute = Integer.parseInt(timeStrings[1]);

            datas.get(localPosition).setPrice((float)timeLineRemote.getClose());
            datas.get(localPosition).setHour(hour);
            datas.get(localPosition).setMinute(minute);

        }

        double yesterdayPrice = timeLineOriginal.getYesterdayPrice();

        // 若6点01没有数据则将该点价格设为昨日收盘价
        if (datas.get(0).getPrice() == 0) {
            datas.get(0).setPrice(yesterdayPrice);
        }

        // 用于记录最后一个有效价格的索引
        int flag = 0;
        // 求出最后一个有效价格的索引
        for (int i = datas.size() - 1; i >= 0; i--) {
            if (datas.get(i).getPrice() != 0) {
                flag = i;
                break;
            }
        }

        // 补充无数据的点的价格和时间
        for (int i = 1; i <= flag; i++) {
            // 价格为0的数据用上一条数据的价格填充，并计算出它的时间
            if ((int) datas.get(i).getPrice() == 0) {
                datas.get(i).setPrice(datas.get(i - 1).getPrice());
                if(datas.get(i - 1).getMinute() < 59){
                    datas.get(i).setHour(datas.get(i - 1).getHour());
                }else{
                    if(datas.get(i - 1).getHour() < 23){
                        datas.get(i).setHour(datas.get(i - 1).getHour());
                    }else{
                        datas.get(i).setHour(0);
                    }
                }
                if(datas.get(i - 1).getMinute() < 59){
                    datas.get(i).setMinute(datas.get(i - 1).getMinute() + 1);
                }else{
                    datas.get(i).setMinute(0);
                }
            }
        }
        // 将计算得到的数据保存起来以供之后使用
        TimeLineLocal timeLineLocal = new TimeLineLocal();
        timeLineLocal.setMaxPrice(maxPrice);
        timeLineLocal.setMinPrice(minPrice);
        timeLineLocal.setYesterdayPrice(yesterdayPrice);
        timeLineLocal.setTimeLineDatas(datas);

        return timeLineLocal;

    }

    /**
     * 解析服务器发送的json获取K线数据的方法
     *
     * @param kLineOriginal     服务器返回的数据
     * @return
     */
    public static List<KLineData> getKLineLocal(KLineOriginal kLineOriginal,int type){

        // K线数据的类型
        String[] types = new String[]{"", "Day", "60", "Week", "Month", "1", "5", "30", "240"};

        // 声明K线数据集合
        List<KLineData> kLineDates = new ArrayList<>();
        // 获取解析Json的对象
        Gson gson = new Gson();
        // 以字符串形式获取响应中的K线数据
        String kLineRemoteString = kLineOriginal.getData();
        // 将数据字符串解析为数据对象集合
        List<List> kLineRemoteDates = gson.fromJson(kLineRemoteString, ArrayList.class);
        // 遍历数据对象集合，用其中的数值填充K线数据对象
        if(kLineRemoteDates != null && kLineRemoteDates.size() > 0)
        for (int i = kLineRemoteDates.size() - 1; i >= 0; i--) {
            KLineData kLineData = new KLineData();
            kLineData.setTime(((Double) kLineRemoteDates.get(i).get(0)).intValue());
            kLineData.setOpen((double) kLineRemoteDates.get(i).get(1));
            kLineData.setHigh((double) kLineRemoteDates.get(i).get(2));
            kLineData.setLow((double) kLineRemoteDates.get(i).get(3));
            kLineData.setClose((double) kLineRemoteDates.get(i).get(4));
            kLineData.setMa5((double) kLineRemoteDates.get(i).get(5));
            kLineData.setMa10((double) kLineRemoteDates.get(i).get(6));
            kLineData.setMa30((double) kLineRemoteDates.get(i).get(7));
            kLineData.setMacd_dif((double) kLineRemoteDates.get(i).get(8));
            kLineData.setMacd_dea((double) kLineRemoteDates.get(i).get(9));
            kLineData.setMacd((double) kLineRemoteDates.get(i).get(10));
            kLineData.setKdj_k((double) kLineRemoteDates.get(i).get(11));
            kLineData.setKdj_d((double) kLineRemoteDates.get(i).get(12));
            kLineData.setKdj_j((double) kLineRemoteDates.get(i).get(13));
            kLineData.setRsi1((double) kLineRemoteDates.get(i).get(14));
            kLineData.setRsi2((double) kLineRemoteDates.get(i).get(15));
            kLineData.setRsi3((double) kLineRemoteDates.get(i).get(16));
            kLineData.setBias1((double) kLineRemoteDates.get(i).get(17));
            kLineData.setBias2((double) kLineRemoteDates.get(i).get(18));
            kLineData.setBias3((double) kLineRemoteDates.get(i).get(19));
            kLineData.setType(types[type]);

            kLineDates.add(kLineData);
        }

        return kLineDates;

    }

}