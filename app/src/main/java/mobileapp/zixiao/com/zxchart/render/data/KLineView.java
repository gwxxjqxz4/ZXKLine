package mobileapp.zixiao.com.zxchart.render.data;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import mobileapp.zixiao.com.zxchart.render.draw.KLineDrawer;
import mobileapp.zixiao.com.zxchart.entity.render.KLineRender;

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
