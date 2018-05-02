package mobileapp.zixiao.com.zxchart.net.subscriber;

import android.app.Activity;
import android.util.Log;

import java.util.List;

import mobileapp.zixiao.com.zxchart.entity.util.CommonEntity;
import mobileapp.zixiao.com.zxchart.entity.util.KLineData;
import mobileapp.zixiao.com.zxchart.net.interactor.DefaultSubscriber;
import mobileapp.zixiao.com.zxchart.utils.calculation.OriginalToLocal;
import mobileapp.zixiao.com.zxchart.utils.dao.KLineManager;
import mobileapp.zixiao.com.zxchart.utils.draw.DrawKLine;
import mobileapp.zixiao.com.zxchart.utils.draw.DrawSecondary;
import mobileapp.zixiao.com.zxchart.utils.global.Constants;

/**
 * Created by gwx
 */

public class AddKLineSubscriber extends DefaultSubscriber<CommonEntity<double[][]>> {

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
     * 数据解析成功（注：该回调内的方法在主线程中执行）
     *
     * @param commonEntity 解析得到的结果对象
     */
    @Override
    public void onNext(final CommonEntity<double[][]> commonEntity) {
        super.onNext(commonEntity);

        Log.e("新接口", "接口返回的数据为：" + commonEntity.getEntity().toString());
        // 开启一个子线程来处理数据以保证流畅性
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 将网络中请求到的数据转换成适于保存和操作的本地数据并缓存
                List<KLineData> kLineDatas = OriginalToLocal.addKLineLocal(commonEntity, type);
                // 将数据缓存到数据库中
                KLineManager.addKLineDatas(activity, kLineDatas);
                // 从数据库中读取数据
                List<KLineData> dbKLineDatas = KLineManager.queryKLineDatas(activity, Constants.getKLineTypes[type]);
                // 绘制K线图及副图（绘制方法中包含了页面切换监测，决定是否显示新数据）
                DrawKLine.drawKLine(activity, dbKLineDatas, type);
                DrawSecondary.drawSecondary(activity, dbKLineDatas, type);
            }
        }).start();
    }

}
