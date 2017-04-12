package mobileapp.myjf.com.myxchart.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mobileapp.myjf.com.myxchart.R;
import mobileapp.myjf.com.myxchart.utils.global.GlobalViewsUtil;
import mobileapp.myjf.com.myxchart.utils.global.Variable;
import mobileapp.myjf.com.myxchart.ui.onclicklistener.PagerClickListener;
import mobileapp.myjf.com.myxchart.utils.other.RequestHelper;

/**
 * Created by gwx
 */

public class KTLineFragment extends Fragment {

    public KTLineFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ktline, container, false);
        return view;
    }

    /**
     * 界面可操作监听回调，用于进行网络请求
     */
    @Override
    public void onResume() {
        super.onResume();
        // 初始化一些全局变量，用于网络请求、状态控制等
        initGlobalVariable();
        // 初始化图表类型栏的点击事件
        initClickListener(getActivity());
        // 初始化分时线布局及内容（进行第一次渲染）
        RequestHelper.getTimeLineDatas(getActivity());
    }


    /**
     * 初始化全局变量的方法
     */
    public void initGlobalVariable() {
//        Intent intent = new Intent();
//        // 根据上个界面传来的信息设置令牌、机构代码和商品代码
//        Variable.setToken(intent.getStringExtra("token"));
//        Variable.setOrganizationCode(intent.getStringExtra("organizationCode"));
//        Variable.setProductCode(intent.getStringExtra("ProductCode"));
        // 模拟的令牌信息、机构代码和商品代码
        // 初始化请求网络用到的令牌、机构代码和商品代码
        Variable.setToken("IOSMOBILECLIENT");
        Variable.setOrganizationCode("QL");
        Variable.setProductCode("QLOIL10T");
        // 初始化一屏显示的K线条目数量
        Variable.setItemNumber(35);
        // 初始化用户选择的图表类型（默认为分时线）
        Variable.setSelectedType(0);
        // 初始化用户选择的副图类型（默认为MACD）
        Variable.setSecondaryType(0);
        // 初始化滚动开始索引（默认为0）
        Variable.setScrollStartPosition(0);
    }


    /**
     * 初始化图表类型栏的点击事件
     *
     * @param activity      上下文对象
     */
    public static void initClickListener(Activity activity) {
        // 获取图表类型栏的控件对象
        List<TextView> titles = GlobalViewsUtil.getTitles(activity);
        // 遍历控件对象并设置点击事件
        for (TextView tv : titles) {
            tv.setOnClickListener(new PagerClickListener(activity));
        }

    }

}
