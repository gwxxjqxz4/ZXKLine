package mobileapp.myjf.com.myxchart.render.data;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import mobileapp.myjf.com.myxchart.render.draw.TimeLineDrawer;
import mobileapp.myjf.com.myxchart.utils.calculation.PXUtils;
import mobileapp.myjf.com.myxchart.entity.render.TimeLineRender;
import mobileapp.myjf.com.myxchart.entity.util.TimeLinePoint;

/**
 * create by gwx
 * 显示分时线的控件
 */
public class TimeLineView extends View {

    // 显示分时线所需的坐标点集合，可从
    TimeLineRender timeLineRender;
    // 上下文
    Context context;

    // 构造方法
    public TimeLineView(Context context) {
        super(context);
        this.context = context;
    }

    public TimeLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public TimeLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    /**
     * 设置坐标集合，添加此控件前需先设置坐标集合，否则无法显示内容
     *
     * @param timeLineRender 坐标集合，可由Calculation类中的calculationKLines方法返回
     */
    public void setTimeLineRender(TimeLineRender timeLineRender) {
        this.timeLineRender = timeLineRender;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (timeLineRender != null && timeLineRender.getPoints().size() != 0) {

            TimeLineDrawer.renderLine(canvas, getHeight() / 20 * 19, getWidth(), context, timeLineRender);
            TimeLineDrawer.renderPrice(canvas, getHeight() / 20 * 19, getWidth(), context, timeLineRender);

        }
        super.onDraw(canvas);
    }

}
