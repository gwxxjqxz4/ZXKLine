package mobileapp.zixiao.com.zxchart.render.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mobileapp.zixiao.com.zxchart.entity.render.KLineRender;
import mobileapp.zixiao.com.zxchart.entity.util.KLineItem;
import mobileapp.zixiao.com.zxchart.entity.util.KLinePoint;
import mobileapp.zixiao.com.zxchart.utils.calculation.PXUtils;
import mobileapp.zixiao.com.zxchart.utils.global.Variable;

/**
 * Created by yuankai on 17/4/13.
 */

public class SecondaryDrawer {

    public static void renderBackground(Canvas canvas, float height, float width) {
        // 声明画笔
        Paint paint = new Paint();
        // 设置抗锯齿为true
        paint.setAntiAlias(true);
        // 设置线宽为3
        paint.setStrokeWidth(1);
        // 设置线的颜色为黑色
        paint.setColor(Color.parseColor("#DDDDDD"));
        // 画出4条边框
        canvas.drawLine(0, 0, 0, height, paint);
        canvas.drawLine(0, 0, width, 0, paint);
        canvas.drawLine(0, height, width, height, paint);
        canvas.drawLine(width, 0, width, height, paint);
        paint.setStrokeWidth(1);
        // 画出纵向坐标线
        canvas.drawLine(width / 4, 0, width / 4, height, paint);
        canvas.drawLine(width / 4 * 2, 0, width / 4 * 2, height, paint);
        canvas.drawLine(width / 4 * 3, 0, width / 4 * 3, height, paint);
        // 画出横向坐标线
        canvas.drawLine(0, height / 2, width, height / 2, paint);
    }

    public static void renderDatas(Canvas canvas, float height, float width, Context context, KLineRender kLineRender) {
        if (Variable.getSecondaryType() == 0) {

            List<KLineItem> kLineItems = kLineRender.getItems();
            // 声明画笔
            Paint paint = new Paint();
            // 设置抗锯齿为true
            paint.setAntiAlias(true);
            // 设置填充模式为填满
            paint.setStyle(Paint.Style.FILL);
            // 设置画笔粗细
            paint.setStrokeWidth(1);
            // 设置默认颜色为黑色
            paint.setColor(Color.parseColor("#888888"));

            // 遍历所有坐标对象，生成对应的矩形
            for (int i = 0; i < kLineItems.size(); i++) {
                // 根据MACD正负状态设置画笔颜色
                if (kLineItems.get(i).getMacdStatus() == 1) {
                    paint.setColor(Color.parseColor("#ff4848"));
                } else {
                    paint.setColor(Color.parseColor("#00c053"));
                }
                // 使用坐标对象的参数和画笔绘制矩形
                canvas.drawRect((float) kLineItems.get(i).getRectLeft(), (float) kLineItems.get(i).getMacdTop(), (float) kLineItems.get(i).getRectRight(), (float) kLineItems.get(i).getMacdBottom(), paint);
            }

            // 设置macd_dif线颜色
            paint.setColor(Color.parseColor("#fe4ebd"));
            // 遍历所有坐标对象，生成macd_dif线
            for (int i = 1; i < kLineItems.size(); i++) {
                if (kLineItems.get(i).getMacd_difPoint().getPrice() != 0) {
                    canvas.drawLine((float) kLineItems.get(i - 1).getMacd_difPoint().getCoordinateX(), (float) kLineItems.get(i - 1).getMacd_difPoint().getCoordinateY(), (float) kLineItems.get(i).getMacd_difPoint().getCoordinateX(), (float) kLineItems.get(i).getMacd_difPoint().getCoordinateY(), paint);
                }
            }
            // 设置macd_dea线颜色
            paint.setColor(Color.parseColor("#f1b521"));
            // 遍历所有坐标对象，生成macd_dea线
            for (int i = 1; i < kLineItems.size(); i++) {
                if (kLineItems.get(i).getMacd_deaPoint().getPrice() != 0) {
                    canvas.drawLine((float) kLineItems.get(i - 1).getMacd_deaPoint().getCoordinateX(), (float) kLineItems.get(i - 1).getMacd_deaPoint().getCoordinateY(), (float) kLineItems.get(i).getMacd_deaPoint().getCoordinateX(), (float) kLineItems.get(i).getMacd_deaPoint().getCoordinateY(), paint);
                }
            }

            paint.setTextSize(PXUtils.dip2px(context, 9));
            paint.setColor(Color.parseColor("#888888"));
            Log.e("", "macdTop为" + kLineRender.getMacdTop());
            canvas.drawText(kLineRender.getMacdTop(), 0, height / 10, paint);
            canvas.drawText(kLineRender.getMacdBottom(), 0, height, paint);
        } else if (Variable.getSecondaryType() > 0 && Variable.getSecondaryType() <= 3) {

            List<KLineItem> kLineItems = kLineRender.getItems();
            // 声明画笔
            Paint paint = new Paint();
            // 设置抗锯齿为true
            paint.setAntiAlias(true);
            // 设置画笔粗细
            paint.setStrokeWidth(1);
            // 设置默认颜色为黑色
            paint.setColor(Color.BLACK);
            paint.setTextSize(PXUtils.dip2px(context, 8));

            List<KLinePoint> firstLinePoints = new ArrayList<>();
            List<KLinePoint> secondLinePoints = new ArrayList<>();
            List<KLinePoint> lastLinePoints = new ArrayList<>();

            // 获取每个点的数据
            if (Variable.getSecondaryType() == 1) {
                paint.setColor(Color.parseColor("#888888"));
                canvas.drawText(kLineRender.getRsiTop() + "", 0, height / 10, paint);
                canvas.drawText(kLineRender.getRsiBottom() + "", 0, height, paint);
                for (int i = 0; i < kLineItems.size(); i++) {
                    firstLinePoints.add(kLineItems.get(i).getRsi1Point());
                    secondLinePoints.add(kLineItems.get(i).getRsi2Point());
                    lastLinePoints.add(kLineItems.get(i).getBias3Point());
                }
            } else if (Variable.getSecondaryType() == 2) {
                paint.setColor(Color.parseColor("#888888"));
                canvas.drawText(kLineRender.getBiasTop() + "", 0, height / 10, paint);
                canvas.drawText(kLineRender.getBiasBottom() + "", 0, height, paint);
                for (int i = 0; i < kLineItems.size(); i++) {
                    firstLinePoints.add(kLineItems.get(i).getBias1Point());
                    secondLinePoints.add(kLineItems.get(i).getBias2Point());
                    lastLinePoints.add(kLineItems.get(i).getBias3Point());
                }
            } else if (Variable.getSecondaryType() == 3) {
                paint.setColor(Color.parseColor("#888888"));
                canvas.drawText(kLineRender.getKdjTop() + "", 0, height / 10, paint);
                canvas.drawText(kLineRender.getKdjBottom() + "", 0, height, paint);
                for (int i = 0; i < kLineItems.size(); i++) {
                    firstLinePoints.add(kLineItems.get(i).getKdj_kPoint());
                    secondLinePoints.add(kLineItems.get(i).getKdj_dPoint());
                    lastLinePoints.add(kLineItems.get(i).getKdj_jPoint());
                }
            }

            for (int i = 1; i < kLineItems.size(); i++) {
                if (firstLinePoints.get(i).getPrice() != 0) {
                    paint.setColor(Color.parseColor("#fe4ebd"));
                    canvas.drawLine((float) firstLinePoints.get(i - 1).getCoordinateX(), (float) firstLinePoints.get(i - 1).getCoordinateY(), (float) firstLinePoints.get(i).getCoordinateX(), (float) firstLinePoints.get(i).getCoordinateY(), paint);
                }
                if (secondLinePoints.get(i).getPrice() != 0) {
                    paint.setColor(Color.parseColor("#f1b521"));
                    canvas.drawLine((float) secondLinePoints.get(i - 1).getCoordinateX(), (float) secondLinePoints.get(i - 1).getCoordinateY(), (float) secondLinePoints.get(i).getCoordinateX(), (float) secondLinePoints.get(i).getCoordinateY(), paint);
                }
                if (lastLinePoints.get(i).getPrice() != 0) {
                    paint.setColor(Color.parseColor("#609bec"));
                    canvas.drawLine((float) lastLinePoints.get(i - 1).getCoordinateX(), (float) lastLinePoints.get(i - 1).getCoordinateY(), (float) lastLinePoints.get(i).getCoordinateX(), (float) lastLinePoints.get(i).getCoordinateY(), paint);
                }
            }
        }

    }

}
