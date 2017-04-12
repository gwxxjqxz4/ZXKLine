package mobileapp.myjf.com.myxchart.render.data;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import mobileapp.myjf.com.myxchart.utils.global.Variable;
import mobileapp.myjf.com.myxchart.utils.calculation.PXUtils;
import mobileapp.myjf.com.myxchart.entity.render.KLineRender;
import mobileapp.myjf.com.myxchart.entity.util.KLineItem;

/**
 * create by gwx
 * 显示K线图柱状物的控件
 */
public class KLineView extends View {

    Activity activity;
    // 显示所需的坐标集合
    private KLineRender kLineRender;
    private int startPosition;
    private int stopPosition;

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
    public void setKLineRender(KLineRender kLineRender) {
        this.kLineRender = kLineRender;
        activity = (Activity) getContext();
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
                if (kLineItems.get(i).getRectBottom() == kLineItems.get(i).getRectTop()) {
                    paint.setStrokeWidth(PXUtils.dip2px(getContext(), 2.5f));
                    canvas.drawLine((float) kLineItems.get(i).getRectLeft(),(float) kLineItems.get(i).getRectTop(),(float) kLineItems.get(i).getRectRight(),(float) kLineItems.get(i).getRectTop(),paint);
                    paint.setStrokeWidth(PXUtils.dip2px(getContext(), 1.5f));
                }
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
            canvas.drawText(kLineRender.getTopPrice() + "", 0, 2 * startHeight, paint);
            canvas.drawText(kLineRender.getAbovePrice() + "", 0, height / 4 + startHeight, paint);
            canvas.drawText(kLineRender.getMidPrice() + "", 0, height / 4 * 2 + 2 * startHeight, paint);
            canvas.drawText(kLineRender.getUnderPrice() + "", 0, height / 4 * 3 + 2 *startHeight, paint);
            canvas.drawText(kLineRender.getBottomPrice() + "", 0, height + 2 * startHeight, paint);

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
            int position1 = (int) (getWidth() / unitX) - startX;
            int position2 = (int) (getWidth() / 4 * 3 / unitX) - startX;
            int position3 = (int) (getWidth() / 4 * 2 / unitX) - startX;
            int position4 = (int) (getWidth() / 4 / unitX) - startX;

            canvas.drawText(kLineItems.get(position1 - 1).getDate(), 0, getHeight(), paint);
            canvas.drawText(kLineItems.get(position2 + startX).getDate(), getWidth() / 4 - getWidth() / 100 * 4, getHeight(), paint);
            canvas.drawText(kLineItems.get(position3 + startX).getDate(), getWidth() / 4 * 2 - getWidth() / 100 * 6, getHeight(), paint);
            canvas.drawText(kLineItems.get(position4 + startX).getDate(), getWidth() / 4 * 3 - getWidth() / 100 * 8, getHeight(), paint);
            canvas.drawText(kLineItems.get(0 + startX).getDate(), getWidth() - getWidth() / 10, getHeight(), paint);

        }
        super.onDraw(canvas);
    }

}
