package mobileapp.myjf.com.myxchart.data.global;

import mobileapp.myjf.com.myxchart.data.entity.render.KLineRender;
import mobileapp.myjf.com.myxchart.data.entity.render.TimeLineRender;

/**
 * Created by nethanhan on 2017/4/8.
 */

public class Data {

    private Data(){}

    // 该类型的分时线渲染数据对象
    private static TimeLineRender timeLineRender;
    // 该类型的主图渲染数据对象
    private static KLineRender mainRender;
    // 该类型的副图渲染数据对象
    private static KLineRender secondaryRender;

    public static KLineRender getMainRender() {
        return mainRender;
    }

    public static void setMainRender(KLineRender mainRender) {
        Data.mainRender = mainRender;
    }

    public static KLineRender getSecondaryRender() {
        return secondaryRender;
    }

    public static void setSecondaryRender(KLineRender secondaryRender) {
        Data.secondaryRender = secondaryRender;
    }

    public static TimeLineRender getTimeLineRender() {
        return timeLineRender;
    }

    public static void setTimeLineRender(TimeLineRender timeLineRender) {
        Data.timeLineRender = timeLineRender;
    }
}
