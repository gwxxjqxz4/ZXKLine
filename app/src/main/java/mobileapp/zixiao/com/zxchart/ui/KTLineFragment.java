package mobileapp.zixiao.com.zxchart.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import mobileapp.zixiao.com.zxchart.R;
import mobileapp.zixiao.com.zxchart.utils.calculation.PXUtils;
import mobileapp.zixiao.com.zxchart.utils.global.GlobalViewsUtil;
import mobileapp.zixiao.com.zxchart.utils.global.Variable;
import mobileapp.zixiao.com.zxchart.ui.onclicklistener.PagerClickListener;
import mobileapp.zixiao.com.zxchart.utils.other.RequestHelper;

/**
 * Created by gwx
 */

public class KTLineFragment extends Fragment {

    Timer timer;

    public KTLineFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ktline, container, true);
        timer = new Timer();
        timer.schedule(task, 1000 * 60, 1000 * 60);
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
        if (Variable.getFullSelectedType() == 0) {
            RequestHelper.getTimeLineDatas(getActivity());
        } else {
            RequestHelper.getKLineDatas(getActivity(), Variable.getFullSelectedType());
            GlobalViewsUtil.getTitles(getActivity()).get(Variable.getFullSelectedType()).performClick();
        }

        initFullScreenButton();

        initPagerSelecter();

    }

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    /**
     * 初始化全局变量的方法
     */
    public void initGlobalVariable() {
        Intent intent = getActivity().getIntent();
        // 根据上个界面传来的信息设置令牌、机构代码和商品代码
        Variable.setToken(intent.getStringExtra("token"));
        Variable.setOrganizationCode(intent.getStringExtra("organizationCode"));
        Variable.setProductCode(intent.getStringExtra("productCode"));
        Variable.setProductName(intent.getStringExtra("productName"));
        // 模拟的令牌信息、机构代码和商品代码
        // 初始化请求网络用到的令牌、机构代码和商品代码
//        Variable.setToken("IOSMOBILECLIENT");
//        Variable.setOrganizationCode("QL");
//        Variable.setProductCode("QLOIL10T");
        // 初始化一屏显示的K线条目数量
        Variable.setItemNumber(35);
        // 初始化用户选择的图表类型（默认为分时线）
        if (Variable.getFullSelectedType() == -1) {
            Variable.setFullSelectedType(0);
        }
        // 初始化用户选择的副图类型（默认为MACD）
        Variable.setSecondaryType(0);
        // 初始化滚动开始索引（默认为0）
        Variable.setScrollStartPosition(0);


    }


    /**
     * 初始化图表类型栏的点击事件
     *
     * @param activity 上下文对象
     */
    public static void initClickListener(Activity activity) {
        // 获取图表类型栏的控件对象
        List<TextView> titles = GlobalViewsUtil.getTitles(activity);
        // 遍历控件对象并设置点击事件
        for (TextView tv : titles) {
            tv.setOnClickListener(new PagerClickListener(activity));
        }

    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if (Variable.getNormalSelectedType() == 0) {
                RequestHelper.getTimeLineDatas(getActivity());
            } else {
                RequestHelper.getKLineDatas(getActivity(),Variable.getNormalSelectedType());
            }
        }
    };

    public void initFullScreenButton() {
        Button button = (Button) getActivity().findViewById(R.id.bt_full_screen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FullScreenActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
    }

    public void initPagerSelecter() {
        final HorizontalScrollView pagerSelecter = GlobalViewsUtil.getPagerSelecter(getActivity());
        Log.e("","当前要展示的界面为" + Variable.getFullSelectedType());
        switch (Variable.getFullSelectedType()) {
            case 0:
                pagerSelecter.post(new Runnable() {
                    @Override
                    public void run() {
                        pagerSelecter.scrollTo(PXUtils.dip2px(getActivity(), 0), 0);
                        pagerSelecter.smoothScrollTo(PXUtils.dip2px(getActivity(), 0), 0);
                    }
                });
                break;
            case 1:
                pagerSelecter.post(new Runnable() {
                    @Override
                    public void run() {
                        pagerSelecter.scrollTo(PXUtils.dip2px(getActivity(), 0), 0);
                        pagerSelecter.smoothScrollTo(PXUtils.dip2px(getActivity(), 0), 0);
                    }
                });                break;
            case 2:
                pagerSelecter.post(new Runnable() {
                    @Override
                    public void run() {
                        pagerSelecter.scrollTo(PXUtils.dip2px(getActivity(), 0), 0);
                        pagerSelecter.smoothScrollTo(PXUtils.dip2px(getActivity(), 0), 0);
                    }
                });                break;
            case 3:
                pagerSelecter.post(new Runnable() {
                    @Override
                    public void run() {
                        pagerSelecter.scrollTo(PXUtils.dip2px(getActivity(), 30), 0);
                        pagerSelecter.smoothScrollTo(PXUtils.dip2px(getActivity(), 30), 0);
                    }
                });
                break;
            case 4:
                pagerSelecter.post(new Runnable() {
                    @Override
                    public void run() {
                        pagerSelecter.scrollTo(PXUtils.dip2px(getActivity(), 90), 0);
                        pagerSelecter.smoothScrollTo(PXUtils.dip2px(getActivity(), 90), 0);
                    }
                });
                break;
            case 5:
                pagerSelecter.post(new Runnable() {
                    @Override
                    public void run() {
                        pagerSelecter.scrollTo(PXUtils.dip2px(getActivity(), 150), 0);
                        pagerSelecter.smoothScrollTo(PXUtils.dip2px(getActivity(), 150), 0);
                    }
                });
                break;
            case 6:
                pagerSelecter.post(new Runnable() {
                    @Override
                    public void run() {
                        pagerSelecter.scrollTo(PXUtils.dip2px(getActivity(), 210), 0);
                        pagerSelecter.smoothScrollTo(PXUtils.dip2px(getActivity(), 210), 0);
                    }
                });
                break;
            case 7:
                pagerSelecter.post(new Runnable() {
                    @Override
                    public void run() {
                        pagerSelecter.scrollTo(PXUtils.dip2px(getActivity(), 210), 0);
                        pagerSelecter.smoothScrollTo(PXUtils.dip2px(getActivity(), 210), 0);
                    }
                });
                break;
            case 8:
                pagerSelecter.post(new Runnable() {
                    @Override
                    public void run() {
                        pagerSelecter.scrollTo(PXUtils.dip2px(getActivity(), 210), 0);
                        pagerSelecter.smoothScrollTo(PXUtils.dip2px(getActivity(), 210), 0);
                    }
                });
                break;
        }
    }

}
