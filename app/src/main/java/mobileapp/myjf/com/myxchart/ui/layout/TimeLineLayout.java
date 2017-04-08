package mobileapp.myjf.com.myxchart.ui.layout;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import mobileapp.myjf.com.myxchart.data.domain.GetTimeLineOriginal;
import mobileapp.myjf.com.myxchart.data.entity.render.TimeLineRender;
import mobileapp.myjf.com.myxchart.render.background.TimeLineBackgroundView;
import mobileapp.myjf.com.myxchart.render.highlight.TimeLineHighLightView;
import mobileapp.myjf.com.myxchart.ui.subscriber.GetTimeLineSubscriber;

/**
 * Created by gwx
 * 分时线图的容器，用于请求网络数据并传递给分时线图、设置对应参数、展示分时线图
 */

public class TimeLineLayout extends RelativeLayout {

    // 构造方法
    public TimeLineLayout(Context context) {
        super(context);
    }

    public TimeLineLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeLineLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
