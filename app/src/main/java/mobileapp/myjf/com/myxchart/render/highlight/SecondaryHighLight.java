package mobileapp.myjf.com.myxchart.render.highlight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import mobileapp.myjf.com.myxchart.data.entity.render.KLineRender;
import mobileapp.myjf.com.myxchart.data.entity.util.KLineItem;
import mobileapp.myjf.com.myxchart.data.global.Variable;

/**
 * Created by gwx
 */

public class SecondaryHighLight extends View {

    // 显示分时线所需的坐标点集合，可从
    KLineRender kLineRender;
    // 上下文
    Context context;
    // 长按事件的横坐标数值
    float moveX;

    // 构造方法
    public SecondaryHighLight(Context context) {
        super(context);
        this.context = context;
    }

    public SecondaryHighLight(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SecondaryHighLight(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void setMoveX(float moveX) {
        this.moveX = moveX;
    }

    /**
     * 设置坐标集合，添加此控件前需先设置坐标集合，否则无法显示内容
     *
     * @param kLineRender 坐标集合，可由Calculation类中的calculationKLines方法返回
     */
    public void setKLineRender(KLineRender kLineRender) {
        this.kLineRender = kLineRender;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // 非空判断，若传入数据为空则清除高亮线
        if(kLineRender == null || kLineRender.getItems().size() < 0){
            super.onDraw(canvas);
            return;
        }

        List<KLineItem> kLineItems = kLineRender.getItems();

        if (kLineItems != null && kLineItems.size() != 0) {

            float height = getHeight();

            // 声明画笔
            Paint paint = new Paint();
            // 设置抗锯齿为true
            paint.setAntiAlias(true);
            // 设置线宽为3
            paint.setStrokeWidth(3);
            // 设置线的颜色为黑色
            paint.setColor(Color.BLACK);
            // 根据moveX画出竖线
            int itemNumber = Variable.getItemNumber();
            // 若数据量小于一屏时，设置开始索引
            int startX = 0;

            float unitX;
            if (kLineItems.size() >= itemNumber) {
                unitX = ((float) getWidth()) / kLineItems.size();
            } else {
                unitX = ((float) getWidth()) / itemNumber;
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
            canvas.drawLine(lineX, 0, lineX, height, paint);
        }
        super.onDraw(canvas);
    }


}
