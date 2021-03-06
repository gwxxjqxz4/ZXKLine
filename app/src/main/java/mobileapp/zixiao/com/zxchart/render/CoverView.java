package mobileapp.zixiao.com.zxchart.render;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import mobileapp.zixiao.com.zxchart.entity.render.KLineRender;
import mobileapp.zixiao.com.zxchart.entity.render.TimeLineRender;
import mobileapp.zixiao.com.zxchart.render.draw.CoverDrawer;
import mobileapp.zixiao.com.zxchart.ui.FullScreenActivity;
import mobileapp.zixiao.com.zxchart.utils.global.Variable;
import mobileapp.zixiao.com.zxchart.utils.calculation.PXUtils;

/**
 * Created by nethanhan on 2017/4/5.
 */

public class CoverView extends View {

    // 显示分时线所需的坐标点集合，可从
    KLineRender kLineRender;
    // 上下文
    Context context;
    // 长按事件的横坐标数值
    float moveX;
    TimeLineRender timeLineRender;

    // 构造方法
    public CoverView(Context context) {
        super(context);
        this.context = context;
    }

    public CoverView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CoverView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

    public void setTimeLineRender(TimeLineRender timeLineRender) {
        this.timeLineRender = timeLineRender;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int type;
        if(context instanceof FullScreenActivity){
            type = Variable.getFullSelectedType();
        }else{
            type = Variable.getNormalSelectedType();
        }

        if(type == 0 && timeLineRender == null){
            super.onDraw(canvas);
            return;
        }
        if(type != 0 && kLineRender == null){
            super.onDraw(canvas);
            return;
        }
        setVisibility(VISIBLE);

        if (type == 0) {
            CoverDrawer.renderTimeCover(canvas, getHeight(), getWidth() -PXUtils.dip2px(context,20), context, timeLineRender, moveX);
        }else{
            CoverDrawer.renderKCover(canvas, getHeight(), getWidth() -PXUtils.dip2px(context,20), context, kLineRender.getItems(),moveX);
        }

        super.onDraw(canvas);
    }


}
