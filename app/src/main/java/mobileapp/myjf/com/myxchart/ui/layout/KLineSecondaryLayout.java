package mobileapp.myjf.com.myxchart.ui.layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by gwx
 * K线图副图的容器，用于请求网络数据并传递给K线图副图、设置对应参数、展示K线图副图
 */

public class KLineSecondaryLayout extends RelativeLayout {


    // 构造方法
    public KLineSecondaryLayout(Context context) {
        super(context);
    }

    public KLineSecondaryLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KLineSecondaryLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
