package mobileapp.myjf.com.myxchart.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mobileapp.myjf.com.myxchart.R;
import mobileapp.myjf.com.myxchart.data.domain.GetTimeLineOriginal;
import mobileapp.myjf.com.myxchart.data.entity.util.KLineItem;
import mobileapp.myjf.com.myxchart.data.global.Variable;
import mobileapp.myjf.com.myxchart.render.background.TimeLineBackgroundView;
import mobileapp.myjf.com.myxchart.ui.layout.KLineSecondaryLayout;
import mobileapp.myjf.com.myxchart.ui.layout.TimeLineLayout;
import mobileapp.myjf.com.myxchart.ui.onclicklistener.SecondaryClickListener;
import mobileapp.myjf.com.myxchart.ui.subscriber.GetTimeLineSubscriber;
import mobileapp.myjf.com.myxchart.ui.util.ClickControler;

/**
 * Created by gwx
 */

public class KTLineFragment extends Fragment {

    List<TextView> titles;
    List<View> titleIndectors;
    Activity context;
    RelativeLayout timeLineLayout;
    LinearLayout kLineLayout;
    private HorizontalScrollView pagerSelecter;

    public int kLineType;

    public KTLineFragment() {}

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
        context = getActivity();
        initClickListener();
        initTimeLineLayout();
        initGlobalVariable();
    }

    public void initClickListener(){

        List views = ClickControler.initClickListener(this,titles,titleIndectors,context,pagerSelecter,timeLineLayout,kLineLayout);
        titles = (List) views.get(0);
        titleIndectors = (List)views.get(1);
        kLineLayout = (LinearLayout) views.get(2);
        timeLineLayout = (RelativeLayout) views.get(3);
        pagerSelecter = (HorizontalScrollView) views.get(4);

    }


    public void initTimeLineLayout(){

        RelativeLayout timeLineLayout = (RelativeLayout) context.findViewById(R.id.layout_timeline);
        GetTimeLineOriginal getTimeLineList = new GetTimeLineOriginal();
        getTimeLineList.setOrganizationCode("QL");
        getTimeLineList.setProductCode("QLOIL10T");
        getTimeLineList.setToken("IOSMOBILECLIENT");
        getTimeLineList.execute(new GetTimeLineSubscriber(context,timeLineLayout));
        TimeLineBackgroundView timeLineBackgroundView = new TimeLineBackgroundView(context);
        timeLineLayout.addView(timeLineBackgroundView);
    }

    public void initGlobalVariable(){
        Variable.setItemNumber(35);
        Variable.setSelectedType(0);
    }

}
