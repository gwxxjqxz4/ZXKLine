package mobileapp.zixiao.com.zxchart.render.data;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import mobileapp.zixiao.com.zxchart.render.draw.SecondaryDrawer;
import mobileapp.zixiao.com.zxchart.entity.render.KLineRender;

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

        // 非空判断，若传入数据为空则清除主图数据
        if (kLineRender == null || kLineRender.getItems().size() < 0) {
            super.onDraw(canvas);
            return;
        }

        SecondaryDrawer.renderDatas(canvas, getHeight(), getWidth(), getContext(), kLineRender);

        super.onDraw(canvas);
    }

}
