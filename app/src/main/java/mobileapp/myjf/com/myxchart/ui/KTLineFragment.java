package mobileapp.myjf.com.myxchart.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import mobileapp.myjf.com.myxchart.R;
import mobileapp.myjf.com.myxchart.data.domain.GetTimeLineOriginal;
import mobileapp.myjf.com.myxchart.data.global.GlobalViewsUtil;
import mobileapp.myjf.com.myxchart.data.global.Variable;
import mobileapp.myjf.com.myxchart.render.background.TimeLineBackgroundView;
import mobileapp.myjf.com.myxchart.ui.onclicklistener.PagerClickListener;
import mobileapp.myjf.com.myxchart.ui.subscriber.GetTimeLineSubscriber;
import mobileapp.myjf.com.myxchart.utils.uitools.RefreshHelper;

/**
 * Created by gwx
 */

public class KTLineFragment extends Fragment {

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
        initClickListener();
        initTimeLineLayout();
        initGlobalVariable();
    }

    public void initClickListener(){

        initClickListener(getActivity());

    }


    public void initTimeLineLayout(){

        GetTimeLineOriginal getTimeLineList = new GetTimeLineOriginal();
        getTimeLineList.setOrganizationCode("QL");
        getTimeLineList.setProductCode("QLOIL10T");
        getTimeLineList.setToken("IOSMOBILECLIENT");
        getTimeLineList.execute(new GetTimeLineSubscriber(getActivity()));
        RefreshHelper.refreshTimeLineBackground(getActivity());

    }

    public void initGlobalVariable(){
        Variable.setItemNumber(35);
        Variable.setSelectedType(0);
    }

    public static void initClickListener(Activity activity){
        List<TextView> titles = GlobalViewsUtil.getTitles(activity);
        for(TextView tv:titles){
            tv.setOnClickListener(new PagerClickListener(activity));
        }

    }

}
