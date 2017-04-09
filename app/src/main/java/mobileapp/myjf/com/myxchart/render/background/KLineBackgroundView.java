package mobileapp.myjf.com.myxchart.render.background;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class KLineBackgroundView extends View {

    Context context;

    public KLineBackgroundView(Context context) {
        super(context);
        this.context = context;
    }

    public KLineBackgroundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public KLineBackgroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // K线图所占高度（剩余部分为日期所占高度）
        // float height = getHeight() / 20 * 19;
        float height = getHeight() / 40 * 39;
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
        canvas.drawLine(getWidth() / 4, 0, getWidth() / 4, height, paint);
        canvas.drawLine(getWidth() / 4 * 2, 0, getWidth() / 4 * 2, height, paint);
        canvas.drawLine(getWidth() / 4 * 3, 0, getWidth() / 4 * 3, height, paint);
        // 画出横向坐标线
        canvas.drawLine(0, height / 4, getWidth(), height / 4, paint);
        canvas.drawLine(0, height / 4 * 2, getWidth(), height / 4 * 2, paint);
        canvas.drawLine(0, height / 4 * 3, getWidth(), height / 4 * 3, paint);
        super.onDraw(canvas);
    }
}
