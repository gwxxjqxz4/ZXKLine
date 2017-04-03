package mobileapp.myjf.com.myxchart.render;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mobileapp.myjf.com.myxchart.calculation.Calculation;
import mobileapp.myjf.com.myxchart.data.domain.GetKLineList;
import mobileapp.myjf.com.myxchart.data.domain.GetTimeLineList;
import mobileapp.myjf.com.myxchart.data.entity.CommonEntity;
import mobileapp.myjf.com.myxchart.data.entity.Coordinate;
import mobileapp.myjf.com.myxchart.data.entity.KLineCoordinate;
import mobileapp.myjf.com.myxchart.data.entity.KLineDate;
import mobileapp.myjf.com.myxchart.data.entity.KLineList;
import mobileapp.myjf.com.myxchart.data.entity.TimeLineData;
import mobileapp.myjf.com.myxchart.data.entity.TimeLineList;
import mobileapp.myjf.com.myxchart.data.interactor.DefaultSubscriber;

/**
 * Created by gwx
 * K线图与分时线图的容器，用于请求网络数据并传递给K线图和分时线图、设置对应参数、展示K线图与分时线图
 */

public class DrawLineLayout extends LinearLayout {

    // 记录请求类型，1为分时，2为K线主图，3为K线副图
    private int type = 0;
    // 上下文
    Context context;

    /**
     * 设置展示类型，添加该容器前需先设置该参数，否则将不能正常展示
     *
     * @param type 展示的类型，1为分时线，2为K线图
     */
    public void setType(int type) {
        this.type = type;
    }

    // 构造方法
    public DrawLineLayout(Context context) {
        super(context);
        // 初始化数据
        initDatas(context);
    }

    public DrawLineLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initDatas(context);
    }

    public DrawLineLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDatas(context);
    }

    /**
     * 根据给定请求类型进行网络请求，并在请求结果中添加子控件展示图标
     *
     * @param context
     */
    public void initDatas(Context context){
        // 初始化上下文对象
        this.context = context;
        // 进行分时线请求
        if(type == 1) {
            GetTimeLineList getTimeLineList = new GetTimeLineList();
            getTimeLineList.setOrganizationCode("QL");
            getTimeLineList.setProductCode("QLOIL10T");
            getTimeLineList.setToken("IOSMOBILECLIENT");
            getTimeLineList.execute(new GetTimeLineSubscriber());
        }
        // 进行K线请求
        else if(type == 2 || type == 3){
            GetKLineList getKLineList = new GetKLineList();
            getKLineList.setType("Day");
            getKLineList.setSyncOrgCode("QL");
            getKLineList.setProductCode("QLOIL10T");
            getKLineList.execute(new GetKLineSubscriber());
        }
    };

    /**
     * 分时线响应数据解析回调
     */
    private class GetTimeLineSubscriber extends DefaultSubscriber<CommonEntity<TimeLineList<TimeLineData>>> {

        /**
         * 数据解析成功
         *
         * @param timeLineListCommonEntity  解析得到的结果对象
         */
        @Override
        public void onNext(CommonEntity<TimeLineList<TimeLineData>> timeLineListCommonEntity) {
            super.onNext(timeLineListCommonEntity);
            // 提取结果对象中的数据，生成分时线数据对象集合
            List<TimeLineData> timeLineDatas = (List<TimeLineData>)timeLineListCommonEntity.getEntity().getEntity();
            // 提取昨日收盘价
            double yesterdayPrice = timeLineListCommonEntity.getEntity().getYesterdayPrice();
            // 使用当前控件宽高、昨日收盘价、数据对象集合作为参数计算分时线坐标点集合
            List<Coordinate> coordinates = Calculation.calculationLines(yesterdayPrice,timeLineDatas,getHeight(),getWidth());
            // 声明显示分时线的控件
            TimeLineView timeLineView = new TimeLineView(context);
            // 将坐标点集合设置给负责显示的控件
            timeLineView.setCoordinates(coordinates);
            // 将负责显示的控件添加到容器控件（此控件）中
            DrawLineLayout.this.addView(timeLineView);

        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            super.onError(e);
        }
    }

    /**
     * K线响应数据解析回调
     */
    private class GetKLineSubscriber extends DefaultSubscriber<KLineList>{

        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        /**
         * 数据解析成功
         *
         * @param kLineListCommonEntity  解析得到的结果对象
         */
        @Override
        public void onNext(KLineList kLineListCommonEntity) {
            super.onNext(kLineListCommonEntity);
            // 声明K线数据集合
            List<KLineDate> kLineDates = new ArrayList<>();
            // 获取解析Json的对象
            Gson gson = new Gson();
            // 以字符串形式获取响应中的K线数据
            String kLineRemoteString = kLineListCommonEntity.getData();
            // 将数据字符串解析为数据对象集合
            List<List> kLineRemoteDates = gson.fromJson(kLineRemoteString,ArrayList.class);
            // 遍历数据对象集合，用其中的数值填充K线数据对象
            for(int i = 0;i < kLineRemoteDates.size();i++){
                KLineDate kLineDate = new KLineDate();
                kLineDate.setTime(((Double)kLineRemoteDates.get(i).get(0)).intValue());
                kLineDate.setOpen((double)kLineRemoteDates.get(i).get(1));
                kLineDate.setHigh((double)kLineRemoteDates.get(i).get(2));
                kLineDate.setLow((double)kLineRemoteDates.get(i).get(3));
                kLineDate.setClose((double)kLineRemoteDates.get(i).get(4));
                kLineDate.setMa5((double)kLineRemoteDates.get(i).get(5));
                kLineDate.setMa10((double)kLineRemoteDates.get(i).get(6));
                kLineDate.setMa30((double)kLineRemoteDates.get(i).get(7));
                kLineDate.setMacd_dif((double)kLineRemoteDates.get(i).get(8));
                kLineDate.setMacd_dea((double)kLineRemoteDates.get(i).get(9));
                kLineDate.setMacd((double)kLineRemoteDates.get(i).get(10));
                kLineDate.setKdj_k((double)kLineRemoteDates.get(i).get(11));
                kLineDate.setKdj_d((double)kLineRemoteDates.get(i).get(12));
                kLineDate.setKdj_j((double)kLineRemoteDates.get(i).get(13));
                kLineDate.setRsi1((double)kLineRemoteDates.get(i).get(14));
                kLineDate.setRsi2((double)kLineRemoteDates.get(i).get(15));
                kLineDate.setRsi3((double)kLineRemoteDates.get(i).get(16));
                kLineDate.setBias1((double)kLineRemoteDates.get(i).get(17));
                kLineDate.setBias2((double)kLineRemoteDates.get(i).get(18));
                kLineDate.setBias3((double)kLineRemoteDates.get(i).get(19));
                kLineDates.add(kLineDate);
            }

            // 用K线数据对象和其他参数计算得出K线坐标点集合
            if(type == 2){
                List<KLineCoordinate> coordinates = Calculation.calculationKLines(kLineDates,35,getHeight()/2,getWidth());
                // 声明显示K线的控件
                KLineView kLineView = new KLineView(context);
                // 将坐标点集合设置给负责显示的控件
                kLineView.setCoordinates(coordinates);
                // 将负责显示的控件添加到容器控件（此控件）中
                // DrawLineLayout.this.addView(kLineView);
            }else if(type == 3){
                // 用K线数据对象和其他参数计算得出MACD坐标点集合
                List<KLineCoordinate> macdCoordinates = Calculation.calculationMACD(kLineDates,35,getHeight()/2,getWidth());
                // 声明显示MACD的控件
                MACDView macdView = new MACDView(context);
                // 将坐标点集合设置给负责显示的控件
                macdView.setCoordinates(macdCoordinates);
                // 将负责显示的控件添加到容器控件（此控件）中
                DrawLineLayout.this.addView(macdView);
            }
        }
    }



}
