package mobileapp.myjf.com.myxchart.ui.subscriber;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import mobileapp.myjf.com.myxchart.data.entity.localdata.KLineLocal;
import mobileapp.myjf.com.myxchart.data.entity.originaldata.KLineOriginal;
import mobileapp.myjf.com.myxchart.data.entity.util.KLineData;
import mobileapp.myjf.com.myxchart.data.global.Variable;
import mobileapp.myjf.com.myxchart.data.interactor.DefaultSubscriber;
import mobileapp.myjf.com.myxchart.render.draw.DrawKLine;
import mobileapp.myjf.com.myxchart.render.draw.DrawSecondary;
import mobileapp.myjf.com.myxchart.utils.calculation.OriginalToLocal;
import mobileapp.myjf.com.myxchart.utils.dao.KLineManager;

/**
 * Created by gwx
 */

public class AddKLineSubscriber extends DefaultSubscriber<KLineOriginal> {

    private Activity activity;
    // 用户选择的标题栏项(本次请求获得的数据类型)
    private int type = 0;

    public AddKLineSubscriber(Activity activity, int type) {

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

        Log.e("服务器返回的数据","服务器返回了" + kLineOriginal.toString());
        Log.e("","请求了新的接口");
        // 将网络中请求到的数据转换成适于保存和操作的本地数据并缓存
        KLineLocal kLineLocal = OriginalToLocal.getKLineLocal(kLineOriginal,type);
        List<KLineData> kLineDatas = kLineLocal.getkLineDatas();
        if (kLineDatas != null && kLineDatas.size() > 0) {
            // 设置缓存标题，标识缓存的数据类型
            // 获取缓存列表，将该本地数据加入列表起到缓存效果
            KLineManager.addKLineDatas(activity, kLineDatas);

        }

        String[] types = new String[]{"", "Day", "60", "Week", "Month", "1", "5", "30", "240"};
        // 当前用户选择的数据类型，用于决定缓存完成后是否加载该数据（如请求到Day的数据而用户在这期间选择了week则取消渲染）
        int selectedType = Variable.getSelectedType();
        if (selectedType == type) {
            List<KLineData> kLineDatas1 = KLineManager.queryKLineDatas(activity,types[type]);
            DrawKLine.drawKLine(activity, kLineDatas1);
            DrawSecondary.drawSecondary(activity, kLineDatas1);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, "网络请求完成，刷新界面", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
