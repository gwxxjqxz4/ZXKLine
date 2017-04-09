package mobileapp.myjf.com.myxchart.ui.onclicklistener;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import mobileapp.myjf.com.myxchart.R;
import mobileapp.myjf.com.myxchart.data.global.Cache;
import mobileapp.myjf.com.myxchart.data.entity.localdata.KLineLocal;
import mobileapp.myjf.com.myxchart.data.global.GlobalViewsUtil;
import mobileapp.myjf.com.myxchart.data.global.Variable;
import mobileapp.myjf.com.myxchart.render.draw.DrawKLine;
import mobileapp.myjf.com.myxchart.render.draw.DrawSecondary;

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

        KLineLocal kLineLocal = Cache.getkLineLocals().get(types[kLineType]);
        DrawSecondary.drawSecondary(activity,kLineLocal);


        ((TextView) titles.get(secondaryType).getChildAt(0)).setTextColor(Color.WHITE);
        titles.get(secondaryType).setBackgroundColor(Color.RED);

    }

}
