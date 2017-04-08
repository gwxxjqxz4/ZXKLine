package mobileapp.myjf.com.myxchart.render.data;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import mobileapp.myjf.com.myxchart.calculation.PXUtils;
import mobileapp.myjf.com.myxchart.data.entity.render.KLineRender;
import mobileapp.myjf.com.myxchart.data.entity.util.KLineItem;

/**
 * create by gwx
 * 显示K线图柱状物的控件
 */
public class KLineView extends View {

    // 显示所需的坐标集合
    KLineRender kLineRender;

    // 构造方法
    public KLineView(Context context) {
        super(context);
    }

    public KLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置坐标集合，添加此控件前需先设置坐标集合，否则无法显示内容
     *
     * @param kLineRender 坐标集合，可由Calculation类中的calculationKLines方法返回
     */
    public void setkLineItems(KLineRender kLineRender) {
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
                // 根据涨跌状态设置画笔颜色
                if (kLineItems.get(i).getStatus() == 1) {
                    paint.setColor(Color.RED);
                } else {
                    paint.setColor(Color.GREEN);
                }
                // 使用坐标对象的参数和画笔绘制矩形
                canvas.drawRect((float) kLineItems.get(i).getRectLeft(), (float) kLineItems.get(i).getRectTop(), (float) kLineItems.get(i).getRectRight(), (float) kLineItems.get(i).getRectBottom(), paint);
                canvas.drawLine((float) kLineItems.get(i).gethLineTop().getCoordinateX(), (float) kLineItems.get(i).gethLineTop().getCoordinateY(), (float) kLineItems.get(i).gethLineBottom().getCoordinateX(), (float) kLineItems.get(i).gethLineBottom().getCoordinateY(), paint);
            }
            // 设置ma5线颜色
            paint.setColor(Color.BLUE);
            // 遍历所有坐标对象，生成ma5线
            for (int i = 1; i < kLineItems.size(); i++) {
                if (kLineItems.get(i).getMa5Point().getPrice() != 0) {
                    canvas.drawLine((float) kLineItems.get(i - 1).getMa5Point().getCoordinateX(), (float) kLineItems.get(i - 1).getMa5Point().getCoordinateY(), (float) kLineItems.get(i).getMa5Point().getCoordinateX(), (float) kLineItems.get(i).getMa5Point().getCoordinateY(), paint);
                }
            }
            // 设置ma10线颜色
            paint.setColor(Color.CYAN);
            // 遍历所有坐标对象，生成ma10线
            for (int i = 1; i < kLineItems.size(); i++) {
                if (kLineItems.get(i).getMa10Point().getPrice() != 0) {
                    canvas.drawLine((float) kLineItems.get(i - 1).getMa10Point().getCoordinateX(), (float) kLineItems.get(i - 1).getMa10Point().getCoordinateY(), (float) kLineItems.get(i).getMa10Point().getCoordinateX(), (float) kLineItems.get(i).getMa10Point().getCoordinateY(), paint);
                }
            }
            // 设置ma30线颜色
            paint.setColor(Color.MAGENTA);
            // 遍历所有坐标对象，生成ma30线
            for (int i = 1; i < kLineItems.size(); i++) {
                if (kLineItems.get(i).getMa30Point().getPrice() != 0) {
                    canvas.drawLine((float) kLineItems.get(i - 1).getMa30Point().getCoordinateX(), (float) kLineItems.get(i - 1).getMa30Point().getCoordinateY(), (float) kLineItems.get(i).getMa30Point().getCoordinateX(), (float) kLineItems.get(i).getMa30Point().getCoordinateY(), paint);
                }
            }

            float height = getHeight() * 0.9f;
            float startHeight = getHeight() / 40;
            paint.setColor(Color.BLACK);
            canvas.drawText(kLineRender.getTopPrice() + "", 0, 0, paint);
            canvas.drawText(kLineRender.getAbovePrice() + "", 0, height / 4 + startHeight, paint);
            canvas.drawText(kLineRender.getMidPrice() + "", 0, height / 4 * 2 + startHeight, paint);
            canvas.drawText(kLineRender.getUnderPrice() + "", 0, height / 4 * 3 + startHeight, paint);
            canvas.drawText(kLineRender.getBottomPrice() + "", 0, height, paint);

            canvas.drawText(kLineItems.get((kLineItems.size() - 1)).getDate(), 0, getHeight(), paint);
            canvas.drawText(kLineItems.get((kLineItems.size() - 1) / 4 * 3 + 2).getDate(), getWidth() / 4, getHeight(), paint);
            canvas.drawText(kLineItems.get((kLineItems.size() - 1) / 4 * 2 + 1).getDate(), getWidth() / 4 * 2, getHeight(), paint);
            canvas.drawText(kLineItems.get((kLineItems.size() - 1) / 4).getDate(), getWidth() / 4 * 3, getHeight(), paint);
            canvas.drawText(kLineItems.get(0).getDate(), getWidth(), getHeight(), paint);
        }
        super.onDraw(canvas);
    }

}
