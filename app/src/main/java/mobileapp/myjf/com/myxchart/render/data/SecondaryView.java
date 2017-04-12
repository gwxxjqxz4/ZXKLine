package mobileapp.myjf.com.myxchart.render.data;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import mobileapp.myjf.com.myxchart.entity.util.KLinePoint;
import mobileapp.myjf.com.myxchart.utils.calculation.PXUtils;
import mobileapp.myjf.com.myxchart.entity.render.KLineRender;
import mobileapp.myjf.com.myxchart.entity.util.KLineItem;

/**
 * create by gwx
 * 显示K线图柱状物的控件
 */
public class SecondaryView extends View {

    // 显示所需的坐标集合
    private KLineRender kLineRender;
    // 用户选择的副图类型
    private int secondaryType;

    // 构造方法
    public SecondaryView(Context context) {
        super(context);
    }

    public SecondaryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SecondaryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSecondaryType(int secondaryType) {
        this.secondaryType = secondaryType;
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

        if (secondaryType == 0) {

            // 非空判断，若传入数据为空则清除主图数据
            if (kLineRender == null || kLineRender.getItems().size() < 0) {
                super.onDraw(canvas);
                return;
            }

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
                canvas.drawText(kLineRender.getMacdTop() + "", 0, getHeight() / 10, paint);
                canvas.drawText(kLineRender.getMacdBottom() + "", 0, getHeight(), paint);
        }else if(secondaryType > 0 && secondaryType <= 3){
            // 非空判断，若传入数据为空则清除主图数据
            if (kLineRender == null || kLineRender.getItems().size() < 0) {
                super.onDraw(canvas);
                return;
            }
                List<KLineItem> kLineItems = kLineRender.getItems();
                // 声明画笔
                Paint paint = new Paint();
                // 设置抗锯齿为true
                paint.setAntiAlias(true);
                // 设置画笔粗细
                paint.setStrokeWidth(PXUtils.dip2px(getContext(), 1.5f));
                // 设置默认颜色为黑色
                paint.setColor(Color.BLACK);
                paint.setTextSize(PXUtils.dip2px(getContext(), 8));

                List<KLinePoint> firstLinePoints = new ArrayList<>();
                List<KLinePoint> secondLinePoints = new ArrayList<>();
                List<KLinePoint> lastLinePoints = new ArrayList<>();

                // 获取每个点的数据
                if (secondaryType == 1) {
                    canvas.drawText(kLineRender.getRsiTop() + "", 0, getHeight() / 10, paint);
                    canvas.drawText(kLineRender.getRsiBottom() + "", 0, getHeight(), paint);
                    for (int i = 0; i < kLineItems.size(); i++) {
                        firstLinePoints.add(kLineItems.get(i).getRsi1Point());
                        secondLinePoints.add(kLineItems.get(i).getRsi2Point());
                        lastLinePoints.add(kLineItems.get(i).getBias3Point());
                    }
                } else if (secondaryType == 2) {
                    canvas.drawText(kLineRender.getBiasTop() + "", 0, getHeight() / 10, paint);
                    canvas.drawText(kLineRender.getRsiBottom() + "", 0, getHeight(), paint);
                    for (int i = 0; i < kLineItems.size(); i++) {
                        firstLinePoints.add(kLineItems.get(i).getBias1Point());
                        secondLinePoints.add(kLineItems.get(i).getBias2Point());
                        lastLinePoints.add(kLineItems.get(i).getBias3Point());
                    }
                } else if (secondaryType == 3) {
                    canvas.drawText(kLineRender.getKdjTop() + "", 0, getHeight() / 10, paint);
                    canvas.drawText(kLineRender.getKdjBottom() + "", 0, getHeight(), paint);
                    for (int i = 0; i < kLineItems.size(); i++) {
                        firstLinePoints.add(kLineItems.get(i).getKdj_dPoint());
                        secondLinePoints.add(kLineItems.get(i).getKdj_kPoint());
                        lastLinePoints.add(kLineItems.get(i).getKdj_jPoint());
                    }
                }

                for (int i = 1; i < kLineItems.size(); i++) {
                    if (firstLinePoints.get(i).getPrice() != 0) {
                        paint.setColor(Color.BLUE);
                        canvas.drawLine((float) firstLinePoints.get(i - 1).getCoordinateX(), (float) firstLinePoints.get(i - 1).getCoordinateY(), (float) firstLinePoints.get(i).getCoordinateX(), (float) firstLinePoints.get(i).getCoordinateY(), paint);
                    }
                    if (secondLinePoints.get(i).getPrice() != 0) {
                        paint.setColor(Color.CYAN);
                        canvas.drawLine((float) secondLinePoints.get(i - 1).getCoordinateX(), (float) secondLinePoints.get(i - 1).getCoordinateY(), (float) secondLinePoints.get(i).getCoordinateX(), (float) secondLinePoints.get(i).getCoordinateY(), paint);
                    }
                    if (lastLinePoints.get(i).getPrice() != 0) {
                        paint.setColor(Color.YELLOW);
                        canvas.drawLine((float) lastLinePoints.get(i - 1).getCoordinateX(), (float) lastLinePoints.get(i - 1).getCoordinateY(), (float) lastLinePoints.get(i).getCoordinateX(), (float) lastLinePoints.get(i).getCoordinateY(), paint);
                    }
                }
            }

        super.onDraw(canvas);
    }

}
