package mobileapp.myjf.com.myxchart.render.highlight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

import mobileapp.myjf.com.myxchart.utils.calculation.PXUtils;
import mobileapp.myjf.com.myxchart.data.entity.render.TimeLineRender;
import mobileapp.myjf.com.myxchart.data.entity.util.TimeLinePoint;

/**
 * create by gwx
 * 显示分时线的控件
 */
public class TimeLineHighLightView extends View {

    // 显示分时线所需的坐标点集合，可从
    TimeLineRender timeLineRender;
    // 上下文
    Context context;
    // 长按事件的横坐标数值
    float moveX;

    // 构造方法
    public TimeLineHighLightView(Context context) {
        super(context);
        this.context = context;
    }

    public TimeLineHighLightView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public TimeLineHighLightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void setMoveX(float moveX) {
        this.moveX = moveX;
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

        // 非空判断，若传入数据为空则清除高亮线
        if (timeLineRender == null || timeLineRender.getPoints().size() < 0) {
            super.onDraw(canvas);
            return;
        }

        float height = getHeight() / 20 * 19;
        List<TimeLinePoint> timeLinePoints = timeLineRender.getPoints();
        // 声明画笔
        Paint paint = new Paint();
        // 设置抗锯齿为true
        paint.setAntiAlias(true);
        // 设置线宽为3
        paint.setStrokeWidth(3);
        // 设置线的颜色为黑色
        paint.setColor(Color.BLACK);
        // 根据moveX画出竖线
        float unitX = ((float) getWidth()) / 1440;
        int position = (int) (moveX / unitX) + 1;
        if (position < 1) {
            position = 1;
        }

        if (position > timeLinePoints.size() - 1) {
            position = timeLinePoints.size() - 1;
        }
        float lineX = position * unitX;
        int priceTemp = (int) (timeLinePoints.get(position).getPrice() * 100);
        float price = (float) priceTemp / 100;
        String timeString = timeLinePoints.get(position).getTime();
        double lineY = timeLinePoints.get(position).getCoordinateY();
        Log.e("TimeLineHighLight","分时线长按事件正确触发,数据为：");
        Log.e("TimeLineHighLight","lineX = " + lineX);
        Log.e("TimeLineHighLight","lineY = " + lineY);
        canvas.drawLine(lineX, 0, lineX, height, paint);
        canvas.drawLine(0, (float) lineY, getWidth(), (float) lineY, paint);
        paint.setTextSize(PXUtils.dip2px(context, 12));
        canvas.drawText("" + price, 0, (float) lineY, paint);
        canvas.drawText(timeString, lineX, height, paint);

        super.onDraw(canvas);
    }

}
