package mobileapp.myjf.com.myxchart.ui.onclicklistener;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import mobileapp.myjf.com.myxchart.R;
import mobileapp.myjf.com.myxchart.entity.util.KLineData;
import mobileapp.myjf.com.myxchart.utils.global.GlobalViewsUtil;
import mobileapp.myjf.com.myxchart.utils.global.Variable;
import mobileapp.myjf.com.myxchart.utils.draw.DrawSecondary;
import mobileapp.myjf.com.myxchart.utils.dao.KLineManager;

/**
 * Created by gwx
 */

public class SecondaryClickListener implements View.OnClickListener {

    private List<RelativeLayout> titles;
    private Activity activity;
    private int kLineType;


    public SecondaryClickListener(Activity activity) {

        this.activity = activity;
        titles = GlobalViewsUtil.getTypes(activity);

        kLineType = Variable.getSelectedType();

        ((TextView) titles.get(0).getChildAt(0)).setTextColor(Color.WHITE);
        titles.get(0).setBackgroundColor(Color.RED);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_macd:
                setTab(0);
                break;
            case R.id.rl_rsi:
                setTab(1);
                break;
            case R.id.rl_bias:
                setTab(2);
                break;
            case R.id.rl_kdj:
                setTab(3);
                break;
        }
    }

    private void clearStatus() {
        for (RelativeLayout rl : titles) {
            ((TextView) rl.getChildAt(0)).setTextColor(Color.BLACK);
            rl.setBackgroundColor(Color.WHITE);
        }
    }

    private void setTab(int index) {

        switch (index) {
            case 0:
                changeSecondary(0);
                break;
            case 1:
                changeSecondary(1);
                break;
            case 2:
                changeSecondary(2);
                break;

            case 3:
                changeSecondary(3);
                break;
        }

    }

    private void changeSecondary(int secondaryType) {

        Variable.setSecondaryType(secondaryType);

        String[] types = new String[]{"","Day","60","Week","Month","1","5","30","240"};

        clearStatus();

        List<KLineData> kLineDatas = KLineManager.queryKLineDatas(activity,types[Variable.getSelectedType()]);
        DrawSecondary.drawSecondary(activity,kLineDatas,Variable.getSelectedType());


        ((TextView) titles.get(secondaryType).getChildAt(0)).setTextColor(Color.WHITE);
        titles.get(secondaryType).setBackgroundColor(Color.RED);

    }

}
