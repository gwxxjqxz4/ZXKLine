package mobileapp.myjf.com.myxchart.ui.subscriber;

import android.content.Context;
import android.widget.LinearLayout;

import java.util.Map;

import mobileapp.myjf.com.myxchart.calculation.OriginalToLocal;
import mobileapp.myjf.com.myxchart.data.global.Cache;
import mobileapp.myjf.com.myxchart.data.entity.localdata.KLineLocal;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.KLineOriginal;
import mobileapp.myjf.com.myxchart.data.interactor.DefaultSubscriber;
import mobileapp.myjf.com.myxchart.render.draw.DrawKLine;
import mobileapp.myjf.com.myxchart.ui.layout.KLineMainLayout;
import mobileapp.myjf.com.myxchart.ui.layout.KLineSecondaryLayout;

/**
 * Created by nethanhan on 2017/4/7.
 */

public class GetMainSubscriber extends DefaultSubscriber<KLineOriginal> {

    // 请求的类型，从左至右顺序与显示相同
    private int type;
    private Context context;
    private KLineMainLayout kLineMainLayout;
    private KLineSecondaryLayout kLineSecondaryLayout;
    private int itemNumber;
    private LinearLayout kLineLayout;
    // 0 MACD   1 RSI   2 BIAS   3  KDJ
    private int secondaryType = 0;

    public GetMainSubscriber(Context context, KLineMainLayout kLineMainLayout,KLineSecondaryLayout kLineSecondaryLayout,int itemNumber,LinearLayout kLineLayout,int secondaryType,int type) {
        this.context = context;
        this.kLineMainLayout = kLineMainLayout;
        this.kLineSecondaryLayout = kLineSecondaryLayout;
        this.kLineLayout = kLineLayout;
        this.itemNumber = itemNumber;
        this.secondaryType = secondaryType;
        this.type = type;
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
    }

    /**
     * 数据解析成功
     *
     * @param kLineOriginal 解析得到的结果对象
     */
    @Override
    public void onNext(KLineOriginal kLineOriginal) {
        super.onNext(kLineOriginal);

        KLineLocal kLineLocal = OriginalToLocal.getKLineLocal(kLineOriginal);
        String[] types = new String[]{"fenshi","rik","60fen","zhouk","yuek","1fen","5fen","30fen","240fen"};
        Map<String,KLineLocal> cache = Cache.getkLineLocals();
        cache.put(types[type],kLineLocal);

        DrawKLine.drawKLine(context,kLineLocal,kLineLayout,kLineMainLayout,kLineSecondaryLayout,secondaryType,itemNumber,type);

    }

}
