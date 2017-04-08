package mobileapp.myjf.com.myxchart.ui.onclicklistener;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import mobileapp.myjf.com.myxchart.R;
import mobileapp.myjf.com.myxchart.data.global.Cache;
import mobileapp.myjf.com.myxchart.data.entity.localdata.KLineLocal;
import mobileapp.myjf.com.myxchart.data.entity.util.KLineItem;
import mobileapp.myjf.com.myxchart.render.background.SecondaryBackgroundView;
import mobileapp.myjf.com.myxchart.render.data.KLineView;
import mobileapp.myjf.com.myxchart.render.data.RSIView;
import mobileapp.myjf.com.myxchart.render.draw.DrawKLine;
import mobileapp.myjf.com.myxchart.ui.layout.KLineMainLayout;
import mobileapp.myjf.com.myxchart.ui.layout.KLineSecondaryLayout;

/**
 * Created by nethanhan on 2017/4/7.
 */

public class SecondaryClickListener implements View.OnClickListener {

    private List<RelativeLayout> titles;
    private KLineSecondaryLayout kLineSecondaryLayout;
    private Activity context;
    private List<KLineItem> kLineItems;
    private SecondaryBackgroundView secondaryBackgroundView;
    private RSIView rsiView;
    private LinearLayout kLineLayout;
    private int kLineType;


    public SecondaryClickListener(List<RelativeLayout> titles, KLineSecondaryLayout kLineSecondaryLayout, List<KLineItem> kLineItems, Activity context, LinearLayout kLineLayout,int kLineType) {
        this.titles = titles;
        this.kLineSecondaryLayout = kLineSecondaryLayout;
        this.context = context;
        this.kLineItems = kLineItems;
        this.kLineLayout = kLineLayout;
        this.kLineType = kLineType;
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

    public void clearStatus() {
        for (RelativeLayout rl : titles) {
            ((TextView) rl.getChildAt(0)).setTextColor(Color.BLACK);
            rl.setBackgroundColor(Color.WHITE);
        }
    }

    public void setTab(int index) {

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
        clearStatus();
        ((TextView) titles.get(index).getChildAt(0)).setTextColor(Color.WHITE);
        titles.get(index).setBackgroundColor(Color.RED);

    }

    public void changeSecondary(int secondaryType) {

        String[] types = new String[]{"fenshi", "rik", "60fen", "zhouk", "yuek", "1fen", "5fen", "30fen", "240fen"};

        clearStatus();

        KLineMainLayout kLineMainLayout = (KLineMainLayout) context.findViewById(R.id.layout_main);
        KLineSecondaryLayout kLineSecondaryLayout = (KLineSecondaryLayout) context.findViewById(R.id.layout_secondary);
        kLineSecondaryLayout.removeAllViews();
        kLineMainLayout.removeAllViews();
        KLineView kLineView = new KLineView(context);

        KLineLocal kLineLocal = Cache.getkLineLocals().get(types[kLineType]);
        DrawKLine.drawKLine(context,kLineLocal,kLineLayout,kLineMainLayout,kLineSecondaryLayout,secondaryType,35,kLineType);

    }

}
