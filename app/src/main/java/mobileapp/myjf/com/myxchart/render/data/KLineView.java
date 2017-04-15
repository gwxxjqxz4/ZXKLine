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

import mobileapp.myjf.com.myxchart.render.draw.KLineDrawer;
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

            KLineDrawer.renderDatas(canvas, getHeight(), getWidth(), getContext(), kLineRender);

        }
        super.onDraw(canvas);
    }

}
