package mobileapp.myjf.com.myxchart.render.background;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import mobileapp.myjf.com.myxchart.render.draw.TimeLineDrawer;

/**
 * Created by nethanhan on 2017/4/4.
 */

public class TimeLineBackgroundView extends View {

    Context context;

    public TimeLineBackgroundView(Context context) {
        super(context);
        this.context = context;
    }

    public TimeLineBackgroundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public TimeLineBackgroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float height = getHeight() / 20 * 19;
        // 绘制外边框的方法
        TimeLineDrawer.renderMargin(canvas, height, getWidth(), context);
        // 绘制内边框的方法
        TimeLineDrawer.renderBorder(canvas, height,getWidth(),context);
        // 绘制日期文字的方法
        TimeLineDrawer.renderDates(canvas,getHeight(),getWidth(),context);

        super.onDraw(canvas);
    }
}
