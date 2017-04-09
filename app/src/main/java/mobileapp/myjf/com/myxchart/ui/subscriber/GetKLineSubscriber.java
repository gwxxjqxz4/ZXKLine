package mobileapp.myjf.com.myxchart.ui.subscriber;

import android.app.Activity;

import java.util.Map;

import mobileapp.myjf.com.myxchart.render.draw.DrawSecondary;
import mobileapp.myjf.com.myxchart.utils.calculation.OriginalToLocal;
import mobileapp.myjf.com.myxchart.data.global.Cache;
import mobileapp.myjf.com.myxchart.data.entity.localdata.KLineLocal;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.KLineOriginal;
import mobileapp.myjf.com.myxchart.data.global.Variable;
import mobileapp.myjf.com.myxchart.data.interactor.DefaultSubscriber;
import mobileapp.myjf.com.myxchart.render.draw.DrawKLine;

/**
 * Created by gwx
 */

public class GetKLineSubscriber extends DefaultSubscriber<KLineOriginal> {

    private Activity activity;
    // 用户选择的标题栏项(本次请求获得的数据类型)
    private int type = 0;

    public GetKLineSubscriber(Activity activity,int type) {

        this.activity = activity;

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

        // 将网络中请求到的数据转换成适于保存和操作的本地数据并缓存
        KLineLocal kLineLocal = OriginalToLocal.getKLineLocal(kLineOriginal);
        // 设置缓存标题，标识缓存的数据类型
        String[] types = new String[]{"", "Day", "60", "Week", "Month", "1", "5", "30", "240"};
        // 获取缓存列表，将该本地数据加入列表起到缓存效果
        Map<String, KLineLocal> cache = Cache.getkLineLocals();
        cache.put(types[type], kLineLocal);
        // 当前用户选择的数据类型，用于决定缓存完成后是否加载该数据（如请求到Day的数据而用户在这期间选择了week则取消渲染）
        int selectedType = Variable.getSelectedType();
        if (selectedType == type) {
            DrawKLine.drawKLine(activity, kLineLocal);
            DrawSecondary.drawSecondary(activity,kLineLocal);
        }
    }

}
