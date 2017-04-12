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
            float height = getHeight() / 20 * 19;

            List<TimeLinePoint> timeLinePoints = timeLineRender.getPoints();
            // 声明画笔
            Paint paint = new Paint();
            // 设置抗锯齿为true
            paint.setAntiAlias(true);
            // 设置线宽为3
            paint.setStrokeWidth(2);
            // 设置线的颜色为黑色
            paint.setColor(Color.BLACK);
            Path path = new Path();
            path.moveTo((float) timeLinePoints.get(0).getCoordinateX(), (float) timeLinePoints.get(0).getCoordinateY());
            // 遍历坐标点集合，画出线段
            for (int i = 1; i < timeLinePoints.size(); i++) {
                // 线段起始点为集合中上一个坐标点
                float startX = (float) timeLinePoints.get(i - 1).getCoordinateX();
                float startY = (float) timeLinePoints.get(i - 1).getCoordinateY();
                // 线段结束点为集合中该坐标点
                float stopX = (float) timeLinePoints.get(i).getCoordinateX();
                float stopY = (float) timeLinePoints.get(i).getCoordinateY();
                // 画出线段
                canvas.drawLine(startX, startY, stopX, stopY, paint);

                path.lineTo(stopX, stopY);
            }


            paint.setColor(0xFFDDDDDD);
            path.lineTo((float) timeLinePoints.get(timeLinePoints.size() - 1).getCoordinateX(), height);
            path.lineTo(0, height);
            path.lineTo(0, (float) timeLinePoints.get(0).getCoordinateY());

            canvas.drawPath(path, paint);

            // 设置画笔的字体大小
            paint.setTextSize(PXUtils.dip2px(context, 8));
            paint.setColor(Color.BLACK);
            canvas.drawText(timeLineRender.getTopPrice() + "", PXUtils.dip2px(context, 3), PXUtils.dip2px(context, 7), paint);
            canvas.drawText(timeLineRender.getAbovePrice() + "", PXUtils.dip2px(context, 3), height / 4 + PXUtils.dip2px(context, 3), paint);
            canvas.drawText(timeLineRender.getMidPrice() + "", PXUtils.dip2px(context, 3), height / 4 * 2 + PXUtils.dip2px(context, 3), paint);
            canvas.drawText(timeLineRender.getUnderPrice() + "", PXUtils.dip2px(context, 3), height / 4 * 3 + PXUtils.dip2px(context, 3), paint);
            canvas.drawText(timeLineRender.getBottomPrice() + "", PXUtils.dip2px(context, 3), height - PXUtils.dip2px(context, 1), paint);

            canvas.drawText(timeLineRender.getTopPercent() + "%", getWidth() - PXUtils.dip2px(context, 26), PXUtils.dip2px(context, 7), paint);
            canvas.drawText(timeLineRender.getAbovePercent() + "%", getWidth() - PXUtils.dip2px(context, 26), height / 4 + PXUtils.dip2px(context, 3), paint);
            canvas.drawText(timeLineRender.getMidPercent() + "%", getWidth() - PXUtils.dip2px(context, 26), height / 4 * 2 + PXUtils.dip2px(context, 3), paint);
            canvas.drawText(timeLineRender.getUnderPercent() + "%", getWidth() - PXUtils.dip2px(context, 26), height / 4 * 3 + PXUtils.dip2px(context, 3), paint);
            canvas.drawText(timeLineRender.getBottomPercent() + "%", getWidth() - PXUtils.dip2px(context, 26), height - PXUtils.dip2px(context, 1), paint);

        }
        super.onDraw(canvas);
    }

}
