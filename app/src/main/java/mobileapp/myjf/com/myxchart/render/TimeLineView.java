package mobileapp.myjf.com.myxchart.render;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import mobileapp.myjf.com.myxchart.data.entity.Coordinate;

/**
 * create by gwx
 * 显示分时线的控件
 */
public class TimeLineView extends View {

    // 显示分时线所需的坐标点集合，可从
    List<Coordinate> coordinates;

    // 构造方法
    public TimeLineView(Context context) {
        super(context);
    }

    public TimeLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置坐标集合，添加此控件前需先设置坐标集合，否则无法显示内容
     *
     * @param coordinates   坐标集合，可由Calculation类中的calculationKLines方法返回
     */
    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 声明画笔
        Paint paint = new Paint();
        // 设置抗锯齿为true
        paint.setAntiAlias(true);
        // 设置线宽为3
        paint.setStrokeWidth(3);
        // 设置线的颜色为黑色
        paint.setColor(Color.BLACK);
        // 遍历坐标点集合，画出线段
        for(int i = 1; i < coordinates.size();i++){
            // 线段起始点为集合中上一个坐标点
            float startX = (float) coordinates.get(i - 1).xValue;
            float startY = (float) coordinates.get(i - 1).yValue;
            // 线段结束点为集合中该坐标点
            float stopX = (float) coordinates.get(i).xValue;
            float stopY = (float) coordinates.get(i).yValue;
            // 画出线段
            canvas.drawLine(startX,startY,stopX,stopY,paint);
        }

        super.onDraw(canvas);
    }

}
