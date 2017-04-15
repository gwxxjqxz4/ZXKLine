package mobileapp.myjf.com.myxchart.render;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import mobileapp.myjf.com.myxchart.entity.render.KLineRender;
import mobileapp.myjf.com.myxchart.entity.render.TimeLineRender;
import mobileapp.myjf.com.myxchart.render.draw.CoverDrawer;
import mobileapp.myjf.com.myxchart.render.draw.KLineDrawer;
import mobileapp.myjf.com.myxchart.ui.FullScreenActivity;
import mobileapp.myjf.com.myxchart.utils.global.Variable;
import mobileapp.myjf.com.myxchart.utils.calculation.PXUtils;
import mobileapp.myjf.com.myxchart.entity.util.KLineItem;

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
