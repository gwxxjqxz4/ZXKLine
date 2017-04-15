package mobileapp.myjf.com.myxchart.render.background;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import mobileapp.myjf.com.myxchart.render.draw.KLineDrawer;

/**
 * create by gwx
 * K线背景控件
 */
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
        // K线图所占高度（剩余部分为日期所占高度)
        float height = getHeight() / 40 * 39;

        KLineDrawer.renderBackground(canvas, height, getWidth(), context);

        super.onDraw(canvas);
    }
}
