package mobileapp.myjf.com.myxchart.utils.other;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import mobileapp.myjf.com.myxchart.net.domain.AddKLineOriginal;
import mobileapp.myjf.com.myxchart.net.domain.AddTimeLineOriginal;
import mobileapp.myjf.com.myxchart.net.domain.GetKLineOriginal;
import mobileapp.myjf.com.myxchart.net.domain.GetTimeLineOriginal;
import mobileapp.myjf.com.myxchart.entity.originaldata.TimeLineRemote;
import mobileapp.myjf.com.myxchart.entity.util.KLineData;
import mobileapp.myjf.com.myxchart.net.subscriber.AddKLineSubscriber;
import mobileapp.myjf.com.myxchart.utils.draw.DrawKLine;
import mobileapp.myjf.com.myxchart.utils.draw.DrawSecondary;
import mobileapp.myjf.com.myxchart.net.subscriber.GetKLineSubscriber;
import mobileapp.myjf.com.myxchart.net.subscriber.GetTimeLineSubscriber;
import mobileapp.myjf.com.myxchart.utils.dao.KLineManager;
import mobileapp.myjf.com.myxchart.utils.dao.TimeLineManager;
import mobileapp.myjf.com.myxchart.utils.global.Constants;
import mobileapp.myjf.com.myxchart.utils.global.GlobalViewsUtil;
import mobileapp.myjf.com.myxchart.utils.global.Variable;

/**
 * Created by gwx
 * 请求数据的类，用于向服务器请求数据并设置响应处理
 */

public class RequestHelper {

    // 是否有分时线背景，有则不再渲染
    private static boolean hasTimeLineBackground = false;

    /**
     * 根据不同情况像服务器发送请求并获取分时线数据
     *
     * @param activity 上下文对象
     */
    public static void getTimeLineDatas(Activity activity) {
        // 从数据库中获取分时线数据的本地缓存
        List<TimeLineRemote> timeLineRemotes = TimeLineManager.queryTimeLineRemotes(activity);
        // 若数据库中没有数据或获取到的数据列表长度小于3则向服务器请求全部数据
        if (timeLineRemotes == null || timeLineRemotes.size() < 3) {
            // 构造请求对象
            GetTimeLineOriginal getTimeLineList = new GetTimeLineOriginal();
            // 设置机构代码
            getTimeLineList.setOrganizationCode(Variable.getOrganizationCode());
            // 设置商品代码
            getTimeLineList.setProductCode(Variable.getProductCode());
            // 设置令牌
            getTimeLineList.setToken(Variable.getToken());
            // 执行请求并设置处理服务器响应的类
            getTimeLineList.execute(new GetTimeLineSubscriber(activity));
            // 渲染背景框
            if (!hasTimeLineBackground) {
                RefreshHelper.refreshTimeLineBackground(activity);
                hasTimeLineBackground = true;
            }
        } else {
            // 若数据库中数据为昨日数据则清空数据库并向服务器请求全部数据，否则只请求最新数据
            if (StampJudgement.isYesterdayData(timeLineRemotes.get(timeLineRemotes.size() - 2).getOpenTime())) {
                // 清空数据库
                TimeLineManager.deleteAllDatas(activity);
                // 构造请求对象
                GetTimeLineOriginal getTimeLineList = new GetTimeLineOriginal();
                // 设置机构代码
                getTimeLineList.setOrganizationCode(Variable.getOrganizationCode());
                // 设置商品代码
                getTimeLineList.setProductCode(Variable.getProductCode());
                // 设置令牌
                getTimeLineList.setToken(Variable.getToken());
                // 执行请求并设置处理服务器响应的类
                getTimeLineList.execute(new GetTimeLineSubscriber(activity));
                // 渲染背景框
                if (!hasTimeLineBackground) {
                    RefreshHelper.refreshTimeLineBackground(activity);
                    hasTimeLineBackground = true;
                }
            } else {
                // 先渲染本地数据
                // 构造请求对象
                AddTimeLineOriginal addTimeLineOriginal = new AddTimeLineOriginal();
                // 设置机构代码
                addTimeLineOriginal.setOrganizationCode(Variable.getOrganizationCode());
                // 设置商品代码
                addTimeLineOriginal.setProductCode(Variable.getProductCode());
                // 设置令牌
                addTimeLineOriginal.setToken(Variable.getToken());
                // 设置请求时间戳（设为倒数第二条避免漏取）
                addTimeLineOriginal.setOpenTime(timeLineRemotes.get(timeLineRemotes.size() - 2).getOpenTime());
                // 执行请求并设置处理服务器响应的类
                addTimeLineOriginal.execute(new GetTimeLineSubscriber(activity));
                // 渲染背景框
                if (!hasTimeLineBackground) {
                    RefreshHelper.refreshTimeLineBackground(activity);
                    hasTimeLineBackground = true;
                }
            }
        }
    }

    /**
     * 根据不同情况像服务器发送请求并获取K线数据
     *
     * @param activity 上下文对象
     */
    public static void getKLineDatas(final Activity activity, int type) {

        // 获取K线图总布局，用于控制K线图点击事件
        LinearLayout kLineLayout = GlobalViewsUtil.getKLineLayout(activity);
        // 全局变量记录用户选择的图表类型
        Variable.setSelectedType(type);
        // 用于请求不同类型K线数据的类型字段值，get为请求全部数据,同时也是数据库类型值，add为添加数据
        String[] getTypes = Constants.getKLineTypes;
        String[] addTypes = Constants.addKLineTypes;
        // 从数据库中获取本地缓存的分时线数据类型（用于判断数据库内容是否过期）
        List<TimeLineRemote> timeLineRemotes = TimeLineManager.queryTimeLineRemotes(activity);
        // 从数据库中获取本地缓存的对应类型K线数据
        final List<KLineData> kLineDatas = KLineManager.queryKLineDatas(activity, getTypes[type]);
        // 如果数据库中没有该类数据或条目数量为0则直接请求旧接口插入数据
        if (kLineDatas == null || kLineDatas.size() < 3) {
            // 构造请求对象
            GetKLineOriginal getKLineList = new GetKLineOriginal();
            // 设置图表类型
            getKLineList.setType(getTypes[type]);
            // 设置机构代码
            getKLineList.setSyncOrgCode(Variable.getOrganizationCode());
            // 设置商品代码
            getKLineList.setProductCode(Variable.getProductCode());
            // 执行请求并设置处理服务器响应的类
            getKLineList.execute(new GetKLineSubscriber(activity, type));
            // 请求数据时暂时停止对K线布局触摸事件的响应
            kLineLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
        } else {
            // 若数据库中有昨天的数据则清除数据库并请求网络
            if (timeLineRemotes.size() - 2 > 0 && StampJudgement.isYesterdayData(timeLineRemotes.get(timeLineRemotes.size() - 2).getOpenTime())) {
                // 清理数据库中的全部数据
                KLineManager.deleteAllDatas(activity);
                // 构造请求对象
                GetKLineOriginal getKLineList = new GetKLineOriginal();
                // 设置图表类型
                getKLineList.setType(getTypes[type]);
                // 设置机构代码
                getKLineList.setSyncOrgCode(Variable.getOrganizationCode());
                // 设置商品代码
                getKLineList.setProductCode(Variable.getProductCode());
                // 执行请求并设置处理服务器响应的类
                getKLineList.execute(new GetKLineSubscriber(activity, type));
                // 请求时暂停对K线图触摸事件的处理
                kLineLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
            }
            // 若数据库中只有今天的数据则请求新接口并添加数据,并优先将已有数据渲染
            else {
                // 调用工具方法将数据库中的数据先渲染在界面上
                DrawKLine.drawKLine(activity, kLineDatas, type);
                DrawSecondary.drawSecondary(activity, kLineDatas, type);
                // 构造请求对象
                AddKLineOriginal addKLineOriginal = new AddKLineOriginal();
                // 设置请求令牌
                addKLineOriginal.setToken(Variable.getToken());
                // 设置机构代码
                addKLineOriginal.setOrganizationCode(Variable.getOrganizationCode());
                // 设置商品代码
                addKLineOriginal.setProductCode(Variable.getProductCode());
                // 设置图表类型（要用新的接口字段）
                addKLineOriginal.setType(addTypes[type]);
                // 设置请求时间戳（数据库中本类数据倒数第二条的时间戳）
                addKLineOriginal.setOpenTime(kLineDatas.get(kLineDatas.size() - 3).getTime());
                Log.e("参数","Token = " + Variable.getToken() + ",OrganizationCode = " + Variable.getOrganizationCode() + ",ProductCode = " + Variable.getProductCode() + ",KType = " + addTypes[type] + ",OpenTime = "+ kLineDatas.get(kLineDatas.size() - 3).getTime());
                // 执行请求并设置处理服务器响应的类
                addKLineOriginal.execute(new AddKLineSubscriber(activity, type));
                // 请求时暂停对K线图触摸事件的处理
                kLineLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
            }
        }
    }

}
