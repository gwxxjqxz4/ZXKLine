package mobileapp.myjf.com.myxchart.render.draw;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import mobileapp.myjf.com.myxchart.entity.render.KLineRender;
import mobileapp.myjf.com.myxchart.entity.util.KLineItem;
import mobileapp.myjf.com.myxchart.ui.FullScreenActivity;
import mobileapp.myjf.com.myxchart.utils.calculation.PXUtils;
import mobileapp.myjf.com.myxchart.utils.global.Variable;

/**
 * Created by yuankai on 17/4/13.
 */

public class KLineDrawer {

    public static void renderBackground(Canvas canvas, float height, float width, Context context) {

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
        canvas.drawLine(0, height / 4, width, height / 4, paint);
        canvas.drawLine(0, height / 4 * 2, width, height / 4 * 2, paint);
        canvas.drawLine(0, height / 4 * 3, width, height / 4 * 3, paint);

    }

    public static void renderDatas(Canvas canvas, float height, float width, Context context, KLineRender kLineRender) {
        List<KLineItem> kLineItems = kLineRender.getItems();
        // 声明画笔
        Paint paint = new Paint();
        // 设置抗锯齿为true
        paint.setAntiAlias(true);
        // 设置填充模式为填满
        paint.setStyle(Paint.Style.FILL);
        // 设置画笔粗细
        paint.setStrokeWidth(PXUtils.dip2px(context, 1));
        // 设置默认颜色为黑色
        paint.setColor(Color.BLACK);
        paint.setTextSize(PXUtils.dip2px(context, 9));
        // 遍历所有坐标对象，生成对应的矩形
        for (int i = 0; i < kLineItems.size(); i++) {
            // 根据涨跌状态设置画笔颜色
            if (kLineItems.get(i).getStatus() == 1) {
                paint.setColor(Color.parseColor("#ff4848"));
            } else {
                paint.setColor(Color.parseColor("#00c053"));
            }
            // 使用坐标对象的参数和画笔绘制矩形
            canvas.drawRect((float) kLineItems.get(i).getRectLeft(), (float) kLineItems.get(i).getRectTop(), (float) kLineItems.get(i).getRectRight(), (float) kLineItems.get(i).getRectBottom(), paint);
            canvas.drawLine((float) kLineItems.get(i).gethLineTop().getCoordinateX(), (float) kLineItems.get(i).gethLineTop().getCoordinateY(), (float) kLineItems.get(i).gethLineBottom().getCoordinateX(), (float) kLineItems.get(i).gethLineBottom().getCoordinateY(), paint);
            if (kLineItems.get(i).getRectBottom() == kLineItems.get(i).getRectTop()) {
                paint.setStrokeWidth(PXUtils.dip2px(context, 2.5f));
                canvas.drawLine((float) kLineItems.get(i).getRectLeft(), (float) kLineItems.get(i).getRectTop(), (float) kLineItems.get(i).getRectRight(), (float) kLineItems.get(i).getRectTop(), paint);
                paint.setStrokeWidth(PXUtils.dip2px(context, 1.5f));
            }
        }
        // 设置线宽
        paint.setStrokeWidth(1);
        // 设置ma5线颜色
        paint.setColor(Color.parseColor("#609bec"));
        // 遍历所有坐标对象，生成ma5线
        for (int i = 1; i < kLineItems.size(); i++) {
            if (kLineItems.get(i).getMa5Point().getPrice() != 0) {
                canvas.drawLine((float) kLineItems.get(i - 1).getMa5Point().getCoordinateX(), (float) kLineItems.get(i - 1).getMa5Point().getCoordinateY(), (float) kLineItems.get(i).getMa5Point().getCoordinateX(), (float) kLineItems.get(i).getMa5Point().getCoordinateY(), paint);
            }
        }
        // 设置ma10线颜色
        paint.setColor(Color.parseColor("#fe4ebd"));
        // 遍历所有坐标对象，生成ma10线
        for (int i = 1; i < kLineItems.size(); i++) {
            if (kLineItems.get(i).getMa10Point().getPrice() != 0) {
                canvas.drawLine((float) kLineItems.get(i - 1).getMa10Point().getCoordinateX(), (float) kLineItems.get(i - 1).getMa10Point().getCoordinateY(), (float) kLineItems.get(i).getMa10Point().getCoordinateX(), (float) kLineItems.get(i).getMa10Point().getCoordinateY(), paint);
            }
        }
        // 设置ma30线颜色
        paint.setColor(Color.parseColor("#f1b521"));
        // 遍历所有坐标对象，生成ma30线
        for (int i = 1; i < kLineItems.size(); i++) {
            if (kLineItems.get(i).getMa30Point().getPrice() != 0) {
                canvas.drawLine((float) kLineItems.get(i - 1).getMa30Point().getCoordinateX(), (float) kLineItems.get(i - 1).getMa30Point().getCoordinateY(), (float) kLineItems.get(i).getMa30Point().getCoordinateX(), (float) kLineItems.get(i).getMa30Point().getCoordinateY(), paint);
            }
        }

        // 设置价格
        paint.setColor(Color.parseColor("#888888"));
        canvas.drawText(kLineRender.getTopPrice() + "", 0, PXUtils.dip2px(context, 8), paint);
        canvas.drawText(kLineRender.getAbovePrice() + "", 0, height / 4, paint);
        canvas.drawText(kLineRender.getMidPrice() + "", 0, height / 4 * 2 - PXUtils.dip2px(context, 2), paint);
        canvas.drawText(kLineRender.getUnderPrice() + "", 0, height / 4 * 3 - PXUtils.dip2px(context, 4), paint);
        canvas.drawText(kLineRender.getBottomPrice() + "", 0, height - PXUtils.dip2px(context, 10), paint);

        int itemNumber = Variable.getItemNumber();
        // 若数据量小于一屏时，设置开始索引
        int startX = 0;

        float unitX;
        if (kLineItems.size() >= itemNumber) {
            unitX = width / kLineItems.size();
        } else {
            unitX = width / itemNumber;
            startX = itemNumber - kLineItems.size();
        }
        int position1 = (int) (width / unitX) - startX;
        int position2 = (int) (width / 4 * 3 / unitX) - startX;
        int position3 = (int) (width / 4 * 2 / unitX) - startX;
        int position4 = (int) (width / 4 / unitX) - startX;

        canvas.drawText(kLineItems.get(position1 - 1).getDate((Activity) context), 0, height, paint);
        canvas.drawText(kLineItems.get(position2 + startX).getDate((Activity) context), width / 4 - width / 100 * 2, height, paint);
        canvas.drawText(kLineItems.get(position3 + startX).getDate((Activity) context), width / 4 * 2 - width / 100 * 3, height, paint);
        canvas.drawText(kLineItems.get(position4 + startX).getDate((Activity) context), width / 4 * 3 - width / 100 * 4, height, paint);
        canvas.drawText(kLineItems.get(0 + startX).getDate((Activity) context), width - width / 20 - PXUtils.dip2px(context, 5), height, paint);
    }

    public static void renderHighLight(Canvas canvas, float height, float width, Context context, List<KLineItem> kLineItems, float moveX) {

        // 声明画笔
        Paint paint = new Paint();
        // 设置抗锯齿为true
        paint.setAntiAlias(true);
        // 设置线宽为3
        paint.setStrokeWidth(1);
        // 设置线的颜色为黑色
        paint.setColor(Color.parseColor("#888888"));
        // 根据moveX画出竖线
        int itemNumber = Variable.getItemNumber();
        // 若数据量小于一屏时，设置开始索引
        int startX = 0;

        float unitX;
        if (kLineItems.size() >= itemNumber) {
            unitX = (width) / kLineItems.size();
        } else {
            unitX = (width) / itemNumber;
            startX = itemNumber - kLineItems.size();
        }
        int position = (int) (moveX / unitX) - startX;
        if (position < 0) {
            position = 0;
        }
        if (position > kLineItems.size() - 1) {
            position = kLineItems.size() - 1;
        }
        float lineX = (position + startX) * unitX + unitX / 2;

        position = kLineItems.size() - (int) (moveX / unitX) - 1 + startX;

        if (position < 0) {
            position = 0;
        }
        if (position > kLineItems.size() - 1) {
            position = kLineItems.size() - 1;
        }

        int priceTemp = (int) (kLineItems.get(position).getPrice() * 100);
        float price = (float) priceTemp / 100;

        String timeString = kLineItems.get(position).getDate((Activity) context);
        double lineY = kLineItems.get(position).getCloseY();
        canvas.drawLine(lineX, 0, lineX, height, paint);
        canvas.drawLine(0, (float) lineY, width, (float) lineY, paint);
        paint.setTextSize(PXUtils.dip2px(context, 10));

        if (lineX < PXUtils.dip2px(context, 30)) {
            lineX = PXUtils.dip2px(context, 30);
        }
        if (lineX > width - PXUtils.dip2px(context, 30)) {
            lineX = width - PXUtils.dip2px(context, 30);
        }

        // 绘制提示背景
        paint.setColor(Color.parseColor("#000000"));
        paint.setAlpha(160);
        canvas.drawRect(lineX - PXUtils.dip2px(context, 30), 0, lineX + PXUtils.dip2px(context, 30), height / 100 * 8, paint);
        canvas.drawRect(0, (float) lineY - PXUtils.dip2px(context, 8), PXUtils.dip2px(context, 50), (float) lineY + PXUtils.dip2px(context, 8), paint);


        // 绘制提示文字
        paint.setColor(Color.WHITE);
        // 价格
        canvas.drawText("" + price, PXUtils.dip2px(context, 5), (float) lineY + PXUtils.dip2px(context, 4), paint);
        // 时间
        canvas.drawText(timeString, lineX - PXUtils.dip2px(context, 12), height / 100 * 8 - PXUtils.dip2px(context, 3), paint);

    }

    public static void renderBigRect(Canvas canvas, float heigt, float width, Context context, List<KLineItem> kLineItems, float moveX) {

        // 声明画笔
        Paint paint = new Paint();
        // 设置抗锯齿为true
        paint.setAntiAlias(true);
        // 设置线宽为3
        paint.setStrokeWidth(1);
        // 设置线的颜色为黑色
        paint.setColor(Color.parseColor("#444444"));
        // 根据moveX画出竖线
        int itemNumber = Variable.getItemNumber();
        // 若数据量小于一屏时，设置开始索引
        int startX = 0;

        float unitX;
        if (kLineItems.size() >= itemNumber) {
            unitX = (width) / kLineItems.size();
        } else {
            unitX = (width) / itemNumber;
            startX = itemNumber - kLineItems.size();
        }
        int position = (int) (moveX / unitX) - startX;
        if (position < 0) {
            position = 0;
        }
        if (position > kLineItems.size() - 1) {
            position = kLineItems.size() - 1;
        }
        float lineX = (position + startX) * unitX + unitX / 2;

        position = kLineItems.size() - (int) (moveX / unitX) - 1 + startX;

        if (position < 0) {
            position = 0;
        }
        if (position > kLineItems.size() - 1) {
            position = kLineItems.size() - 1;
        }

        String date = kLineItems.get(position).getDate2();
        String name1 = "Ma5:";
        String name2 = "Ma10:";
        String name3 = "Ma30:";
        String name4 = "";
        String name5 = "";
        String name6 = "";
        String value1 = kLineItems.get(position).getMa5Point().getPriceString() + "";
        String value2 = kLineItems.get(position).getMa10Point().getPriceString() + "";
        String value3 = kLineItems.get(position).getMa30Point().getPriceString() + "";
        String value4 = "";
        String value5 = "";
        String value6 = "";

        switch (Variable.getSecondaryType()) {
            case 0:
                name4 = "Macd:";
                name5 = "Macd_dea:";
                name6 = "Macd_dif:";
                value4 = kLineItems.get(position).getMa5Point().getPriceString() + "";
                value5 = kLineItems.get(position).getMacd_deaPoint().getPriceString() + "";
                value6 = kLineItems.get(position).getMacd_difPoint().getPriceString() + "";
                break;
            case 1:
                name4 = "Rsi1:";
                name5 = "Rsi2:";
                name6 = "Rsi3:";
                value4 = kLineItems.get(position).getRsi1Point().getPriceString() + "";
                value5 = kLineItems.get(position).getRsi2Point().getPriceString() + "";
                value6 = kLineItems.get(position).getRsi3Point().getPriceString() + "";
                break;
            case 2:
                name4 = "Bias1:";
                name5 = "Bias2:";
                name6 = "Bias3:";
                value4 = kLineItems.get(position).getBias1Point().getPriceString() + "";
                value5 = kLineItems.get(position).getBias2Point().getPriceString() + "";
                value6 = kLineItems.get(position).getBias3Point().getPriceString() + "";
                break;
            case 3:
                name4 = "K:";
                name5 = "D:";
                name6 = "J:";
                value4 = kLineItems.get(position).getKdj_kPoint().getPriceString() + "";
                value5 = kLineItems.get(position).getKdj_dPoint().getPriceString() + "";
                value6 = kLineItems.get(position).getKdj_jPoint().getPriceString() + "";
                break;
        }

        int rightX = 0;

        if (context instanceof FullScreenActivity) {

            if (moveX > (width / 5 * 3)) {
                rightX = 0;
            } else {
                rightX = PXUtils.dip2px(context, 440);
            }

        } else {

            if (moveX > (width / 5 * 3)) {
                rightX = 0;
            } else {
                rightX = PXUtils.dip2px(context, 160);
            }

        }

        paint.setAlpha(160);
        canvas.drawRect(PXUtils.dip2px(context, 52) + rightX, PXUtils.dip2px(context, 17), PXUtils.dip2px(context, 150) + rightX, PXUtils.dip2px(context, 90), paint);
        paint.setAlpha(100);
        paint.setTextSize(PXUtils.dip2px(context, 8));
        paint.setColor(Color.WHITE);
        canvas.drawText(date, PXUtils.dip2px(context, 85) + rightX, PXUtils.dip2px(context, 27), paint);
        canvas.drawText(name1, PXUtils.dip2px(context, 55) + rightX, PXUtils.dip2px(context, 37), paint);
        canvas.drawText(name2, PXUtils.dip2px(context, 55) + rightX, PXUtils.dip2px(context, 47), paint);
        canvas.drawText(name3, PXUtils.dip2px(context, 55) + rightX, PXUtils.dip2px(context, 57), paint);
        canvas.drawText(name4, PXUtils.dip2px(context, 55) + rightX, PXUtils.dip2px(context, 67), paint);
        canvas.drawText(name5, PXUtils.dip2px(context, 55) + rightX, PXUtils.dip2px(context, 77), paint);
        canvas.drawText(name6, PXUtils.dip2px(context, 55) + rightX, PXUtils.dip2px(context, 87), paint);

        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setColor(Color.parseColor("#4d97fe"));
        canvas.drawText(value1, PXUtils.dip2px(context, 147) + rightX, PXUtils.dip2px(context, 37), paint);
        paint.setColor(Color.parseColor("#fe4ebd"));
        canvas.drawText(value2, PXUtils.dip2px(context, 147) + rightX, PXUtils.dip2px(context, 47), paint);
        paint.setColor(Color.parseColor("#f1b521"));
        canvas.drawText(value3, PXUtils.dip2px(context, 147) + rightX, PXUtils.dip2px(context, 57), paint);
        paint.setColor(Color.parseColor("#ff4848"));
        canvas.drawText(value4, PXUtils.dip2px(context, 147) + rightX, PXUtils.dip2px(context, 67), paint);
        paint.setColor(Color.parseColor("#f1b521"));
        canvas.drawText(value5, PXUtils.dip2px(context, 147) + rightX, PXUtils.dip2px(context, 77), paint);
        paint.setColor(Color.parseColor("#e241a7"));
        canvas.drawText(value6, PXUtils.dip2px(context, 147) + rightX, PXUtils.dip2px(context, 87), paint);


    }

}
