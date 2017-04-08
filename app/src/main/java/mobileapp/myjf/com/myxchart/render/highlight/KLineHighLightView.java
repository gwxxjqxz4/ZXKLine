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

import mobileapp.myjf.com.myxchart.calculation.PXUtils;
import mobileapp.myjf.com.myxchart.data.entity.util.KLineItem;

/**
 * Created by nethanhan on 2017/4/5.
 */

public class KLineHighLightView extends View {

    // 显示分时线所需的坐标点集合，可从
    List<KLineItem> kLineItems;
    // 上下文
    Context context;
    // 长按事件的横坐标数值
    float moveX;

    // 构造方法
    public KLineHighLightView(Context context) {
        super(context);
        this.context = context;
    }

    public KLineHighLightView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public KLineHighLightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void setMoveX(float moveX) {
        this.moveX = moveX;
    }


    /**
     * 设置坐标集合，添加此控件前需先设置坐标集合，否则无法显示内容
     *
     * @param kLineItems 坐标集合，可由Calculation类中的calculationKLines方法返回
     */
    public void setkLineItems(List<KLineItem> kLineItems) {
        this.kLineItems = kLineItems;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (kLineItems != null && kLineItems.size() != 0) {

            float height = getHeight() * 0.95f;

            // 声明画笔
            Paint paint = new Paint();
            // 设置抗锯齿为true
            paint.setAntiAlias(true);
            // 设置线宽为3
            paint.setStrokeWidth(3);
            // 设置线的颜色为黑色
            paint.setColor(Color.BLACK);
            // 根据moveX画出竖线
            float unitX = ((float) getWidth()) / kLineItems.size();
            int position = (int) (moveX / unitX);
            if (position < 0) {
                position = 0;
            }
            if (position > kLineItems.size() - 1) {
                position = kLineItems.size() - 1;
            }
            float lineX = position * unitX + unitX / 2;

            position = kLineItems.size() - (int) (moveX / unitX) - 1;

            if (position < 0) {
                position = 0;
            }
            if (position > kLineItems.size() - 1) {
                position = kLineItems.size() - 1;
            }

            int priceTemp = (int) (kLineItems.get(position).getPrice() * 100);
            float price = (float) priceTemp / 100;

            String timeString = kLineItems.get(position).getDate();
            double lineY = kLineItems.get(position).getCloseY();
            canvas.drawLine(lineX, 0, lineX, height, paint);
            canvas.drawLine(0, (float) lineY, getWidth(), (float) lineY, paint);
            paint.setTextSize(PXUtils.dip2px(context, 12));
            canvas.drawText("" + price, 0, (float) lineY, paint);
            canvas.drawText(timeString, lineX, height, paint);


        }
        super.onDraw(canvas);
    }


}
