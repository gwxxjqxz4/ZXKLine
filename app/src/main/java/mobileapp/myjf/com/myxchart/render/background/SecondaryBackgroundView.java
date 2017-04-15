package mobileapp.myjf.com.myxchart.render.background;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import mobileapp.myjf.com.myxchart.render.draw.SecondaryDrawer;

public class SecondaryBackgroundView extends View {

    Context context;

    public SecondaryBackgroundView(Context context) {
        super(context);
        this.context = context;
    }

    public SecondaryBackgroundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SecondaryBackgroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 副图线图所占高度
        float height = getHeight();

        SecondaryDrawer.renderBackground(canvas,height,getWidth());

        super.onDraw(canvas);
    }
}
