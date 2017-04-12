package mobileapp.myjf.com.myxchart.render.background;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import mobileapp.myjf.com.myxchart.utils.calculation.PXUtils;

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
        // 分时线图所占高度（剩余部分为日期所占高度）
        float height = getHeight()  / 20 * 19;
        // 声明画笔
        Paint paint = new Paint();
        // 设置抗锯齿为true
        paint.setAntiAlias(true);
        // 设置线宽为3
        paint.setStrokeWidth(4);
        // 设置线的颜色为黑色
        paint.setColor(Color.BLACK);
        // 画出4条边框
        canvas.drawLine(0, 0, 0, height, paint);
        canvas.drawLine(0, 0, getWidth(), 0, paint);
        canvas.drawLine(0, height, getWidth(), height, paint);
        canvas.drawLine(getWidth(), 0, getWidth(), height, paint);
        paint.setStrokeWidth(1);
        // 画出纵向坐标线
        canvas.drawLine(getWidth() / 6, 0, getWidth() / 6, height, paint);
        canvas.drawLine(getWidth() / 6 * 2, 0, getWidth() / 6 * 2, height, paint);
        canvas.drawLine(getWidth() / 6 * 3, 0, getWidth() / 6 * 3, height, paint);
        canvas.drawLine(getWidth() / 6 * 4, 0, getWidth() / 6 * 4, height, paint);
        canvas.drawLine(getWidth() / 6 * 5, 0, getWidth() / 6 * 5, height, paint);
        // 画出横向坐标线
        canvas.drawLine(0, height / 4, getWidth(), height / 4, paint);
        canvas.drawLine(0, height / 4 * 2, getWidth(), height / 4 * 2, paint);
        canvas.drawLine(0, height / 4 * 3, getWidth(), height / 4 * 3, paint);
        // 设置画出的日期字体大小
        paint.setTextSize(PXUtils.dip2px(context, 8));
        // 画出日期文字
        canvas.drawText("06:01", 0, getHeight(), paint);
        canvas.drawText("10:00", getWidth() / 6 - (PXUtils.dip2px(context, 8)), getHeight(), paint);
        canvas.drawText("14:00", getWidth() / 6 * 2 - (PXUtils.dip2px(context, 8)), getHeight(), paint);
        canvas.drawText("18:00", getWidth() / 6 * 3 - (PXUtils.dip2px(context, 8)), getHeight(), paint);
        canvas.drawText("22:00", getWidth() / 6 * 4 - (PXUtils.dip2px(context, 8)), getHeight(), paint);
        canvas.drawText("02:00", getWidth() / 6 * 5 - (PXUtils.dip2px(context, 8)), getHeight(), paint);
        canvas.drawText("06:00", getWidth() / 6 * 6 - (PXUtils.dip2px(context, 21)), getHeight(), paint);
        super.onDraw(canvas);
    }
}
