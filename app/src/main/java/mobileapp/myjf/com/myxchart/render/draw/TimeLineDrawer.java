package mobileapp.myjf.com.myxchart.render.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.List;

import mobileapp.myjf.com.myxchart.entity.render.TimeLineRender;
import mobileapp.myjf.com.myxchart.entity.util.TimeLinePoint;
import mobileapp.myjf.com.myxchart.utils.calculation.PXUtils;

/**
 * Created by yuankai on 17/4/13.
 */

public class TimeLineDrawer {

    // 绘制分时线外边框
    public static void renderMargin(Canvas canvas, float height, float width, Context context) {

        // 声明画笔
        Paint paint = new Paint();
        // 设置抗锯齿为true
        paint.setAntiAlias(true);
        // 设置线宽
        paint.setStrokeWidth(PXUtils.dip2px(context, 1));
        // 设置线的颜色
        paint.setColor(Color.parseColor("#DDDDDD"));
        // 画出4条边框
        canvas.drawLine(0, 0, 0, height - PXUtils.dip2px(context, 1), paint);
        canvas.drawLine(0, 0, width, 0, paint);
        canvas.drawLine(0, height, width, height, paint);
        canvas.drawLine(width, 0, width, height, paint);

    }

    // 绘制分时线内边框
    public static void renderBorder(Canvas canvas, float height, float width, Context context) {

        // 声明画笔
        Paint paint = new Paint();
        // 设置抗锯齿为true
        paint.setAntiAlias(true);
        // 设置线宽
        paint.setStrokeWidth(PXUtils.dip2px(context, 1));
        // 设置线的颜色
        paint.setColor(Color.parseColor("#EBEBEB"));

        // 画出纵向坐标线
        canvas.drawLine(width / 6, 0, width / 6, height, paint);
        canvas.drawLine(width / 6 * 2, 0, width / 6 * 2, height, paint);
        canvas.drawLine(width / 6 * 3, 0, width / 6 * 3, height, paint);
        canvas.drawLine(width / 6 * 4, 0, width / 6 * 4, height, paint);
        canvas.drawLine(width / 6 * 5, 0, width / 6 * 5, height, paint);
        // 画出横向坐标线
        canvas.drawLine(0, height / 4, width, height / 4, paint);
        canvas.drawLine(0, height / 4 * 3, width, height / 4 * 3, paint);
        // 画出中间的线
        paint.setColor(Color.parseColor("#CED6E0"));
        paint.setStrokeWidth(PXUtils.dip2px(context, 1));
        canvas.drawLine(0, height / 4 * 2, width, height / 4 * 2, paint);

    }

    // 绘制日期文字
    public static void renderDates(Canvas canvas, float height, float width, Context context) {

        // 声明画笔
        Paint paint = new Paint();
        // 设置抗锯齿为true
        paint.setAntiAlias(true);
        // 设置字体
        paint.setTextSize(PXUtils.dip2px(context, 9));
        // 设置文字颜色
        paint.setColor(Color.parseColor("#888888"));

        // 画出日期文字
        canvas.drawText("06:01", 0, height, paint);
        canvas.drawText("10:00", width / 6 - (PXUtils.dip2px(context, 9)), height, paint);
        canvas.drawText("14:00", width / 6 * 2 - (PXUtils.dip2px(context, 9)), height, paint);
        canvas.drawText("18:00", width / 6 * 3 - (PXUtils.dip2px(context, 9)), height, paint);
        canvas.drawText("22:00", width / 6 * 4 - (PXUtils.dip2px(context, 9)), height, paint);
        canvas.drawText("02:00", width / 6 * 5 - (PXUtils.dip2px(context, 9)), height, paint);
        canvas.drawText("06:00", width / 6 * 6 - (PXUtils.dip2px(context, 22)), height, paint);


    }

    // 绘制分时线和阴影
    public static void renderLine(Canvas canvas, float height, float width, Context context, TimeLineRender timeLineRender) {

        List<TimeLinePoint> timeLinePoints = timeLineRender.getPoints();
        // 声明画笔
        Paint paint = new Paint();
        // 设置抗锯齿为true
        paint.setAntiAlias(true);
        // 设置线宽
        paint.setStrokeWidth(1);
        // 设置线的颜色
        paint.setColor(Color.parseColor("#6486e9"));
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


        paint.setColor(Color.parseColor("#e4ecff"));
        paint.setAlpha(50);
        path.lineTo((float) timeLinePoints.get(timeLinePoints.size() - 1).getCoordinateX(), height);
        path.lineTo(0, height);
        path.lineTo(0, (float) timeLinePoints.get(0).getCoordinateY());

        canvas.drawPath(path, paint);


    }

    // 绘制价格和百分比
    public static void renderPrice(Canvas canvas, float height, float width, Context context, TimeLineRender timeLineRender) {

        // 声明画笔
        Paint paint = new Paint();
        // 设置抗锯齿为true
        paint.setAntiAlias(true);
        // 设置画笔的字体大小
        paint.setTextSize(PXUtils.dip2px(context, 9));
        // 设置画笔颜色
        paint.setColor(Color.parseColor("#888888"));
        canvas.drawText(timeLineRender.getTopPrice() + "", PXUtils.dip2px(context, 3), PXUtils.dip2px(context, 7), paint);
        canvas.drawText(timeLineRender.getAbovePrice() + "", PXUtils.dip2px(context, 3), height / 4 + PXUtils.dip2px(context, 3), paint);
        canvas.drawText(timeLineRender.getMidPrice() + "", PXUtils.dip2px(context, 3), height / 4 * 2 + PXUtils.dip2px(context, 3), paint);
        canvas.drawText(timeLineRender.getUnderPrice() + "", PXUtils.dip2px(context, 3), height / 4 * 3 + PXUtils.dip2px(context, 3), paint);
        canvas.drawText(timeLineRender.getBottomPrice() + "", PXUtils.dip2px(context, 3), height - PXUtils.dip2px(context, 1), paint);

        canvas.drawText(timeLineRender.getTopPercent() + "%", width - PXUtils.dip2px(context, 26), PXUtils.dip2px(context, 7), paint);
        canvas.drawText(timeLineRender.getAbovePercent() + "%", width - PXUtils.dip2px(context, 26), height / 4 + PXUtils.dip2px(context, 3), paint);
        canvas.drawText(timeLineRender.getMidPercent() + "%", width - PXUtils.dip2px(context, 26), height / 4 * 2 + PXUtils.dip2px(context, 3), paint);
        canvas.drawText(timeLineRender.getUnderPercent() + "%", width - PXUtils.dip2px(context, 26), height / 4 * 3 + PXUtils.dip2px(context, 3), paint);
        canvas.drawText(timeLineRender.getBottomPercent() + "%", width - PXUtils.dip2px(context, 26), height - PXUtils.dip2px(context, 1), paint);
    }

    // 绘制高亮线
    public static void renderHighLight(Canvas canvas, float height, float width, Context context, TimeLineRender timeLineRender, float moveX) {

        List<TimeLinePoint> timeLinePoints = timeLineRender.getPoints();
        // 声明画笔
        Paint paint = new Paint();
        // 设置抗锯齿为true
        paint.setAntiAlias(true);
        // 设置线宽为3
        paint.setStrokeWidth(1);
        // 设置线的颜色为黑色
        paint.setColor(Color.parseColor("#888888"));
        // 根据moveX画出竖线
        float unitX = width / 1440;
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
        int percentTemp = (int) (timeLinePoints.get(position).getPercent() * 10000);
        float percent = (float) percentTemp / 100;
        String timeString = timeLinePoints.get(position).getTime();
        double lineY = timeLinePoints.get(position).getCoordinateY();
        canvas.drawLine(lineX, 0, lineX, height, paint);
        canvas.drawLine(0, (float) lineY, width, (float) lineY, paint);
        paint.setTextSize(PXUtils.dip2px(context, 12));

        if(lineX < PXUtils.dip2px(context,30)){
            lineX = PXUtils.dip2px(context,30);
        }if(lineX > width - PXUtils.dip2px(context,30)){
            lineX = width - PXUtils.dip2px(context,30);
        }

        // 绘制提示背景
        paint.setColor(Color.parseColor("#666666"));
        canvas.drawRect(lineX - PXUtils.dip2px(context, 30), 0, lineX + PXUtils.dip2px(context, 30), height / 20, paint);
        canvas.drawRect(0, (float) lineY - PXUtils.dip2px(context, 8), PXUtils.dip2px(context, 50), (float) lineY + PXUtils.dip2px(context, 8), paint);
        canvas.drawRect(width - PXUtils.dip2px(context, 50), (float) lineY - PXUtils.dip2px(context, 8), width, (float) lineY + PXUtils.dip2px(context, 8), paint);


        // 绘制提示文字
        paint.setColor(Color.WHITE);
        // 价格
        canvas.drawText("" + price, PXUtils.dip2px(context, 5), (float) lineY + PXUtils.dip2px(context, 4), paint);
        // 时间
        canvas.drawText(timeString, lineX - PXUtils.dip2px(context, 12), height / 20 - PXUtils.dip2px(context, 3), paint);
        // 百分比
        canvas.drawText(percent + "%", width - PXUtils.dip2px(context, 40), (float) lineY + PXUtils.dip2px(context, 4), paint);

    }

}
