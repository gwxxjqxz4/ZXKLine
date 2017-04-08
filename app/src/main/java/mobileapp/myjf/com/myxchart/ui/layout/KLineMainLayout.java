package mobileapp.myjf.com.myxchart.ui.layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
/**
 * Created by gwx
 * K线图主图的容器，用于请求网络数据并传递给K线图主图、设置对应参数、展示K线图主图
 */

public class KLineMainLayout extends RelativeLayout {

    // 构造方法
    public KLineMainLayout(Context context) {
        super(context);
    }

    public KLineMainLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KLineMainLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
    }
}
