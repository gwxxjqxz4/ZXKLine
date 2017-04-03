package mobileapp.myjf.com.myxchart.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by gwx
 */

public class KLineLayout extends LinearLayout {

    public KLineLayout(Context context) {
        super(context);
    }

    public KLineLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KLineLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public class MyOnTouchListener implements OnTouchListener{
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    }

}
