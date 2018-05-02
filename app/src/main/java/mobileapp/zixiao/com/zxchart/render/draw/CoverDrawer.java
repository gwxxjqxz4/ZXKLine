package mobileapp.zixiao.com.zxchart.render.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import mobileapp.zixiao.com.zxchart.entity.render.TimeLineRender;
import mobileapp.zixiao.com.zxchart.entity.util.KLineItem;
import mobileapp.zixiao.com.zxchart.entity.util.TimeLinePoint;
import mobileapp.zixiao.com.zxchart.utils.calculation.PXUtils;
import mobileapp.zixiao.com.zxchart.utils.global.Variable;

/**
 * Created by yuankai on 17/4/14.
 */

public class CoverDrawer {

    public static void renderTimeCover(Canvas canvas, float height, float width, Context context, TimeLineRender timeLineRender, float moveX) {

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
        int priceTemp = (int) (timeLinePoints.get(position).getPrice() * 100);
        float price = (float) priceTemp / 100;
        paint.setTextSize(PXUtils.dip2px(context, 12));


        // 价格
        canvas.drawText("价格 ： " + price, PXUtils.dip2px(context, 20), (float) PXUtils.dip2px(context, 25), paint);

    }

    public static void renderKCover(Canvas canvas, float height, float width, Context context, List<KLineItem> kLineItems, float moveX) {

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
        paint.setTextSize(PXUtils.dip2px(context, 10));

        // 价格
        canvas.drawText("开:" + kLineItems.get(position).getOpenPriceString(), PXUtils.dip2px(context, 20), (float) PXUtils.dip2px(context, 25), paint);
        canvas.drawText("收:" + kLineItems.get(position).getClosePriceString(), PXUtils.dip2px(context, 90), (float) PXUtils.dip2px(context, 25), paint);
        canvas.drawText("高:" + kLineItems.get(position).getHighPriceString(), PXUtils.dip2px(context, 160), (float) PXUtils.dip2px(context, 25), paint);
        canvas.drawText("低:" + kLineItems.get(position).getLowPriceString(), PXUtils.dip2px(context, 230), (float) PXUtils.dip2px(context, 25), paint);

    }

}
