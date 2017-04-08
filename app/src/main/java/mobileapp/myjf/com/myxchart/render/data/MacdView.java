package mobileapp.myjf.com.myxchart.render.data;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

import mobileapp.myjf.com.myxchart.calculation.PXUtils;
import mobileapp.myjf.com.myxchart.data.entity.render.KLineRender;
import mobileapp.myjf.com.myxchart.data.entity.util.KLineItem;

/**
 * create by gwx
 * 显示K线图柱状物的控件
 */
public class MacdView extends View {

    static {
    }

    // 显示所需的坐标集合
    KLineRender kLineRender;

    // 构造方法
    public MacdView(Context context) {
        super(context);
    }

    public MacdView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MacdView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置坐标集合，添加此控件前需先设置坐标集合，否则无法显示内容
     *
     * @param kLineRender 坐标集合，可由Calculation类中的calculationKLines方法返回
     */
    public void setCoordinates(KLineRender kLineRender) {
        Log.e("ChangeSecondary", "数据添加成功");
        this.kLineRender = kLineRender;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (kLineRender != null && kLineRender.getItems().size() != 0) {
            List<KLineItem> kLineItems = kLineRender.getItems();
            // 声明画笔
            Paint paint = new Paint();
            // 设置抗锯齿为true
            paint.setAntiAlias(true);
            // 设置填充模式为填满
            paint.setStyle(Paint.Style.FILL);
            // 设置画笔粗细
            paint.setStrokeWidth(PXUtils.dip2px(getContext(), 1.5f));
            // 设置默认颜色为黑色
            paint.setColor(Color.BLACK);
            paint.setTextSize(PXUtils.dip2px(getContext(), 8));

            // 遍历所有坐标对象，生成对应的矩形
            for (int i = 0; i < kLineItems.size(); i++) {
                // 根据MACD正负状态设置画笔颜色
                if (kLineItems.get(i).getMacdStatus() == 1) {
                    paint.setColor(Color.RED);
                } else {
                    paint.setColor(Color.GREEN);
                }
                // 使用坐标对象的参数和画笔绘制矩形
                canvas.drawRect((float) kLineItems.get(i).getRectLeft(), (float) kLineItems.get(i).getMacdTop(), (float) kLineItems.get(i).getRectRight(), (float) kLineItems.get(i).getMacdBottom(), paint);
            }

            // 设置macd_dif线颜色
            paint.setColor(Color.BLUE);
            // 遍历所有坐标对象，生成macd_dif线
            for (int i = 1; i < kLineItems.size(); i++) {
                if (kLineItems.get(i).getMacd_difPoint().getPrice() != 0) {
                    canvas.drawLine((float) kLineItems.get(i - 1).getMacd_difPoint().getCoordinateX(), (float) kLineItems.get(i - 1).getMacd_difPoint().getCoordinateY(), (float) kLineItems.get(i).getMacd_difPoint().getCoordinateX(), (float) kLineItems.get(i).getMacd_difPoint().getCoordinateY(), paint);
                }
            }
            // 设置macd_dea线颜色
            paint.setColor(Color.CYAN);
            // 遍历所有坐标对象，生成macd_dea线
            for (int i = 1; i < kLineItems.size(); i++) {
                if (kLineItems.get(i).getMacd_deaPoint().getPrice() != 0) {
                    canvas.drawLine((float) kLineItems.get(i - 1).getMacd_deaPoint().getCoordinateX(), (float) kLineItems.get(i - 1).getMacd_deaPoint().getCoordinateY(), (float) kLineItems.get(i).getMacd_deaPoint().getCoordinateX(), (float) kLineItems.get(i).getMacd_deaPoint().getCoordinateY(), paint);
                }
            }

            paint.setColor(Color.BLACK);
            canvas.drawText(kLineRender.getMacdTop() + "", 0, 0, paint);
            canvas.drawText(kLineRender.getMacdBottom() + "", 0, getHeight(), paint);
        }
        super.onDraw(canvas);
    }

}
