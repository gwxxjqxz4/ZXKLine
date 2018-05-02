package mobileapp.zixiao.com.zxchart.utils.other;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mobileapp.zixiao.com.zxchart.data.KLineTestData;
import mobileapp.zixiao.com.zxchart.entity.originaldata.KLineOriginal;
import mobileapp.zixiao.com.zxchart.net.domain.AddKLineOriginal;
import mobileapp.zixiao.com.zxchart.net.domain.AddTimeLineOriginal;
import mobileapp.zixiao.com.zxchart.net.domain.GetKLineOriginal;
import mobileapp.zixiao.com.zxchart.net.domain.GetTimeLineOriginal;
import mobileapp.zixiao.com.zxchart.entity.originaldata.TimeLineRemote;
import mobileapp.zixiao.com.zxchart.entity.util.KLineData;
import mobileapp.zixiao.com.zxchart.net.subscriber.AddKLineSubscriber;
import mobileapp.zixiao.com.zxchart.net.subscriber.AddTimeLineSubscriber;
import mobileapp.zixiao.com.zxchart.ui.FullScreenActivity;
import mobileapp.zixiao.com.zxchart.utils.calculation.OriginalToLocal;
import mobileapp.zixiao.com.zxchart.utils.draw.DrawKLine;
import mobileapp.zixiao.com.zxchart.utils.draw.DrawSecondary;
import mobileapp.zixiao.com.zxchart.net.subscriber.GetKLineSubscriber;
import mobileapp.zixiao.com.zxchart.net.subscriber.GetTimeLineSubscriber;
import mobileapp.zixiao.com.zxchart.utils.dao.KLineManager;
import mobileapp.zixiao.com.zxchart.utils.dao.TimeLineManager;
import mobileapp.zixiao.com.zxchart.utils.global.Constants;
import mobileapp.zixiao.com.zxchart.utils.global.GlobalViewsUtil;
import mobileapp.zixiao.com.zxchart.utils.global.Variable;

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
        if (activity instanceof FullScreenActivity) {
            Variable.setFullSelectedType(0);
        } else {
            Variable.setNormalSelectedType(0);
        }
        // 从数据库中获取分时线数据的本地缓存
        long date1 = new Date(System.currentTimeMillis()).getTime();
        Log.e("性能优化", "查询数据库的时间：" + date1);
        List<TimeLineRemote> timeLineRemotes = TimeLineManager.queryTimeLineRemotes(activity);
        SharedPreferences sp = activity.getPreferences(Context.MODE_PRIVATE);
        long timeStamp = sp.getLong(Constants.getKLineTypes[0], 0);
        // 若数据库中没有数据或获取到的数据列表长度小于3则向服务器请求全部数据
        if (timeLineRemotes == null || timeLineRemotes.size() < 3) {
            long date2 = new Date(System.currentTimeMillis()).getTime();
            Log.e("性能优化", "发起请求的时间" + date2);
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
            if (StampJudgement.isYesterdayData(timeStamp)) {
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
                addTimeLineOriginal.execute(new AddTimeLineSubscriber(activity));
                // 渲染背景框
                if (!hasTimeLineBackground) {
                    RefreshHelper.refreshTimeLineBackground(activity);
                    hasTimeLineBackground = true;
                }
            }
        }
        sp.edit().putLong(Constants.getKLineTypes[0], new Date(System.currentTimeMillis()).getTime()).commit();
    }

    /**
     * 根据不同情况像服务器发送请求并获取K线数据
     *
     * @param activity 上下文对象
     */
    public static void getKLineDatas(final Activity activity, final int type) {
        if (activity instanceof FullScreenActivity) {
            Variable.setFullSelectedType(type);
        } else {
            Variable.setNormalSelectedType(type);
        }
        // 获取K线图总布局，用于控制K线图点击事件
        LinearLayout kLineLayout = GlobalViewsUtil.getKLineLayout(activity);
        // 用于请求不同类型K线数据的类型字段值，get为请求全部数据,同时也是数据库类型值，add为添加数据
        String[] getTypes = Constants.getKLineTypes;
        String[] addTypes = Constants.addKLineTypes;
        // 从数据库中获取本地缓存的分时线数据类型（用于判断数据库内容是否过期）
        SharedPreferences sp = activity.getPreferences(Context.MODE_PRIVATE);
        long timeStamp = sp.getLong(Constants.getKLineTypes[type], 0);
        // 从数据库中获取本地缓存的对应类型K线数据
        final List<KLineData> kLineDatas = KLineManager.queryKLineDatas(activity, getTypes[type]);
        // 如果数据库中没有该类数据或条目数量为0则直接请求旧接口插入数据

        // 开启一个子线程来处理数据以保证流畅性
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 将网络中请求到的数据转换成适于保存和操作的本地数据并缓
                Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                            @Override
                            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                                    throws JsonParseException {

                                try {
                                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(json.getAsString());
                                } catch (ParseException e) {
                                    return null;
                                }
                            }
                        }).create();
                KLineOriginal kLineOriginal = gson.fromJson(KLineTestData.testData.trim(), KLineOriginal.class);
                List<KLineData> kLineDatas = OriginalToLocal.getKLineLocal(kLineOriginal, type);
                // 将数据缓存到数据库中
                if (kLineDatas == null || kLineDatas.size() < 3) {
                    KLineManager.writeKLineDatas(activity, kLineDatas);
                }
                // 从数据库中读取数据
                List<KLineData> dbKLineDatas = KLineManager.queryKLineDatas(activity, Constants.getKLineTypes[type]);
                // 绘制K线图及副图（绘制方法中包含了页面切换监测，决定是否显示新数据）
                DrawKLine.drawKLine(activity, dbKLineDatas, type);
                DrawSecondary.drawSecondary(activity, dbKLineDatas, type);
            }
        }).start();

//        if (kLineDatas == null || kLineDatas.size() < 3) {
//            // 构造请求对象
//            GetKLineOriginal getKLineList = new GetKLineOriginal();
//            // 设置图表类型
//            getKLineList.setType(getTypes[type]);
//            // 设置机构代码
//            getKLineList.setSyncOrgCode(Variable.getOrganizationCode());
//            // 设置商品代码
//            getKLineList.setProductCode(Variable.getProductCode());
//            // 执行请求并设置处理服务器响应的类
//            getKLineList.execute(new GetKLineSubscriber(activity, type));
//            // 请求数据时暂时停止对K线布局触摸事件的响应
//            kLineLayout.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    return false;
//                }
//            });
//        } else {
//            // 若数据库中有昨天的数据则清除数据库并请求网络
//            if (StampJudgement.isYesterdayData(timeStamp)) {
//                // 清理数据库中的全部数据
//                KLineManager.deleteAllDatas(activity);
//                // 构造请求对象
//                GetKLineOriginal getKLineList = new GetKLineOriginal();
//                // 设置图表类型
//                getKLineList.setType(getTypes[type]);
//                // 设置机构代码
//                getKLineList.setSyncOrgCode(Variable.getOrganizationCode());
//                // 设置商品代码
//                getKLineList.setProductCode(Variable.getProductCode());
//                // 执行请求并设置处理服务器响应的类
//                getKLineList.execute(new GetKLineSubscriber(activity, type));
//                // 请求时暂停对K线图触摸事件的处理
//                kLineLayout.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        return false;
//                    }
//                });
//            }
//            // 若数据库中只有今天的数据则请求新接口并添加数据,并优先将已有数据渲染
//            else {
//                // 调用工具方法将数据库中的数据先渲染在界面上
//                DrawKLine.drawKLine(activity, kLineDatas, type);
//                DrawSecondary.drawSecondary(activity, kLineDatas, type);
//                // 构造请求对象
//                AddKLineOriginal addKLineOriginal = new AddKLineOriginal();
//                // 设置请求令牌
//                addKLineOriginal.setToken(Variable.getToken());
//                // 设置机构代码
//                addKLineOriginal.setOrganizationCode(Variable.getOrganizationCode());
//                // 设置商品代码
//                addKLineOriginal.setProductCode(Variable.getProductCode());
//                // 设置图表类型（要用新的接口字段）
//                addKLineOriginal.setType(addTypes[type]);
//                // 设置请求时间戳（数据库中本类数据倒数第二条的时间戳）
//                addKLineOriginal.setOpenTime(kLineDatas.get(kLineDatas.size() - 3).getTime());
//                Log.e("参数", "Token = " + Variable.getToken() + ",OrganizationCode = " + Variable.getOrganizationCode() + ",ProductCode = " + Variable.getProductCode() + ",KType = " + addTypes[type] + ",OpenTime = " + kLineDatas.get(kLineDatas.size() - 3).getTime());
//                // 执行请求并设置处理服务器响应的类
//                addKLineOriginal.execute(new AddKLineSubscriber(activity, type));
//                // 请求时暂停对K线图触摸事件的处理
//                kLineLayout.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        return false;
//                    }
//                });
//            }
//        }
        sp.edit().putLong(Constants.getKLineTypes[type], new Date(System.currentTimeMillis()).getTime()).commit();
    }

}
