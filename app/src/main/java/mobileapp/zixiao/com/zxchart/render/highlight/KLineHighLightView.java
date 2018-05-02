package mobileapp.zixiao.com.zxchart.render.highlight;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import mobileapp.zixiao.com.zxchart.entity.render.KLineRender;
import mobileapp.zixiao.com.zxchart.render.draw.KLineDrawer;
import mobileapp.zixiao.com.zxchart.entity.util.KLineItem;

/**
 * Created by nethanhan on 2017/4/5.
 */

public class KLineHighLightView extends View {

    // 显示分时线所需的坐标点集合，可从
    KLineRender kLineRender;
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
     * @param kLineRender 坐标集合，可由Calculation类中的calculationKLines方法返回
     */
    public void setKLineRender(KLineRender kLineRender) {
        this.kLineRender = kLineRender;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // 非空判断，若传入数据为空则清除高亮线
        if (kLineRender == null || kLineRender.getItems().size() < 0) {
            super.onDraw(canvas);
            return;
        }

        List<KLineItem> kLineItems = kLineRender.getItems();

        if (kLineItems != null && kLineItems.size() != 0) {

            float height = getHeight() * 0.95f;

            KLineDrawer.renderHighLight(canvas, height, getWidth(), context, kLineItems, moveX);
            KLineDrawer.renderBigRect(canvas,height,getWidth(),context,kLineItems,moveX);

        }
        super.onDraw(canvas);
    }


}
