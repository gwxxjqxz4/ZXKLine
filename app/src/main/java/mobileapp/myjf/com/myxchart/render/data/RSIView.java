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

import mobileapp.myjf.com.myxchart.calculation.PXUtils;
import mobileapp.myjf.com.myxchart.data.entity.render.KLineRender;
import mobileapp.myjf.com.myxchart.data.entity.util.KLineItem;
import mobileapp.myjf.com.myxchart.data.entity.util.KLinePoint;

/**
 * create by gwx
 * 显示K线图柱状物的控件
 */
public class RSIView extends View {

    // 要显示的副图指标
    private int type = 0;

    public final int RSI = 0;
    public final int BIAS = 1;
    public final int KDJ = 2;

    // 显示所需的坐标集合
    KLineRender kLineRender;

    // 构造方法
    public RSIView(Context context) {
        super(context);
    }

    public RSIView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RSIView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void setType(int type) {

        this.type = type;

    }


    /**
     * 设置坐标集合，添加此控件前需先设置坐标集合，否则无法显示内容
     *
     * @param kLineRender 坐标集合，可由Calculation类中的calculationKLines方法返回
     */
    public void setCoordinates(KLineRender kLineRender) {
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
            // 设置画笔粗细
            paint.setStrokeWidth(PXUtils.dip2px(getContext(), 1.5f));
            // 设置默认颜色为黑色
            paint.setColor(Color.BLACK);
            paint.setTextSize(PXUtils.dip2px(getContext(), 8));

            List<KLinePoint> firstLinePoints = new ArrayList<>();
            List<KLinePoint> secondLinePoints = new ArrayList<>();
            List<KLinePoint> lastLinePoints = new ArrayList<>();

            // 获取每个点的数据
            if (type == RSI) {
                canvas.drawText(kLineRender.getRsiTop() + "", 0, 0, paint);
                canvas.drawText(kLineRender.getRsiBottom() + "", 0, getHeight(), paint);
                for (int i = 0; i < kLineItems.size(); i++) {
                    firstLinePoints.add(kLineItems.get(i).getRsi1Point());
                    secondLinePoints.add(kLineItems.get(i).getRsi2Point());
                    lastLinePoints.add(kLineItems.get(i).getBias3Point());
                }
            } else if (type == BIAS) {
                canvas.drawText(kLineRender.getBiasTop() + "", 0, 0, paint);
                canvas.drawText(kLineRender.getRsiBottom() + "", 0, getHeight(), paint);
                for (int i = 0; i < kLineItems.size(); i++) {
                    firstLinePoints.add(kLineItems.get(i).getBias1Point());
                    secondLinePoints.add(kLineItems.get(i).getBias2Point());
                    lastLinePoints.add(kLineItems.get(i).getBias3Point());
                }
            } else if (type == KDJ) {
                canvas.drawText(kLineRender.getKdjTop() + "", 0, 0, paint);
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
