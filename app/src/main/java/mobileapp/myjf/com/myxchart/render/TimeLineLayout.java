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
 * 分时线图的容器，用于请求网络数据并传递给分时线图、设置对应参数、展示分时线图
 */

public class TimeLineLayout extends LinearLayout {

    Context context;

    // 构造方法
    public TimeLineLayout(Context context) {
        super(context);
        // 初始化数据
        initDatas(context);
    }

    public TimeLineLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initDatas(context);
    }

    public TimeLineLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDatas(context);
    }

    /**
     * 根据给定请求类型进行网络请求，并在请求结果中添加子控件展示图标
     *
     * @param context
     */
    public void initDatas(Context context) {
        // 初始化上下文对象
        this.context = context;
        // 进行分时线请求
        GetTimeLineList getTimeLineList = new GetTimeLineList();
        getTimeLineList.setOrganizationCode("QL");
        getTimeLineList.setProductCode("QLOIL10T");
        getTimeLineList.setToken("IOSMOBILECLIENT");
        getTimeLineList.execute(new GetTimeLineSubscriber());
    }

    ;

    /**
     * 分时线响应数据解析回调
     */
    private class GetTimeLineSubscriber extends DefaultSubscriber<CommonEntity<TimeLineList<TimeLineData>>> {

        /**
         * 数据解析成功
         *
         * @param timeLineListCommonEntity 解析得到的结果对象
         */
        @Override
        public void onNext(CommonEntity<TimeLineList<TimeLineData>> timeLineListCommonEntity) {
            super.onNext(timeLineListCommonEntity);
            // 提取结果对象中的数据，生成分时线数据对象集合
            List<TimeLineData> timeLineDatas = (List<TimeLineData>) timeLineListCommonEntity.getEntity().getEntity();
            // 提取昨日收盘价
            double yesterdayPrice = timeLineListCommonEntity.getEntity().getYesterdayPrice();
            // 使用当前控件宽高、昨日收盘价、数据对象集合作为参数计算分时线坐标点集合
            List<Coordinate> coordinates = Calculation.calculationLines(yesterdayPrice, timeLineDatas, getHeight(), getWidth());
            // 声明显示分时线的控件
            TimeLineView timeLineView = new TimeLineView(context);
            // 将坐标点集合设置给负责显示的控件
            timeLineView.setCoordinates(coordinates);
            // 将负责显示的控件添加到容器控件（此控件）中
            TimeLineLayout.this.addView(timeLineView);

        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            super.onError(e);
        }
    }


}
