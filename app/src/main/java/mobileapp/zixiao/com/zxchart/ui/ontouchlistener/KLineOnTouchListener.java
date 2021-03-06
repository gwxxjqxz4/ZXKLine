package mobileapp.zixiao.com.zxchart.ui.ontouchlistener;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import mobileapp.zixiao.com.zxchart.entity.util.KLineData;
import mobileapp.zixiao.com.zxchart.ui.FullScreenActivity;
import mobileapp.zixiao.com.zxchart.ui.onclicklistener.PagerClickListener;
import mobileapp.zixiao.com.zxchart.utils.calculation.LocalToView;
import mobileapp.zixiao.com.zxchart.entity.render.KLineRender;
import mobileapp.zixiao.com.zxchart.utils.global.GlobalViewsUtil;
import mobileapp.zixiao.com.zxchart.utils.global.Variable;
import mobileapp.zixiao.com.zxchart.render.data.KLineView;
import mobileapp.zixiao.com.zxchart.render.highlight.KLineHighLightView;
import mobileapp.zixiao.com.zxchart.render.highlight.SecondaryHighLight;
import mobileapp.zixiao.com.zxchart.utils.other.RefreshHelper;

/**
 * Created by gwx
 */

public class KLineOnTouchListener implements View.OnTouchListener {

    // 每次点击开始的位置
    private float startX;
    private float startY;
    // 点击位置是否没有改变，默认为true
    private boolean isNoMove = true;
    // 是否为长按事件，默认为false
    private boolean isLongClick = false;
    // 是否处于判断长按事件的阶段，若不是则不再更新相应变量
    private boolean isJudge = true;
    // 是否是第一点击
    private boolean isFirstClick = true;
    // 第一次点击的时间
    private long firstClickTime;
    // 滑动事件的参考位置
    private float scrollX;
    // 滑动事件的索引
    private int scrollPosition = 0;
    List<TextView> titles;
    // 每屏数据量
    private int itemNumber;
    // 用于判断双击、长按事件的子线程可运行对象
    JudgementRunnable runnable;

    private Activity activity;
    private RelativeLayout kLineMainLayout;
    private RelativeLayout kLineSecondaryLayout;
    private KLineHighLightView kLineHighLightView;
    private SecondaryHighLight secondaryHighLight;
    private KLineView kLineView;
    private View secondaryView;

    private KLineRender kLineRender;
    List<KLineData> kLineDatas;

    public KLineOnTouchListener(Activity activity, KLineRender kLineRender, List<KLineData> kLineDatas) {

        this.activity = activity;
        kLineMainLayout = GlobalViewsUtil.getMainLayout(activity);
        kLineSecondaryLayout = GlobalViewsUtil.getSecondaryLayout(activity);
        kLineHighLightView = GlobalViewsUtil.getKLineHighLight(activity);
        secondaryHighLight = GlobalViewsUtil.getSecondaryHighLight(activity);
        kLineView = GlobalViewsUtil.getKLineView(activity);
        secondaryView = GlobalViewsUtil.getSecondaryView(activity);
        titles = GlobalViewsUtil.getTitles(activity);

        itemNumber = Variable.getItemNumber();

        this.kLineRender = kLineRender;
        this.scrollPosition = kLineRender.getItems().size() - 36;
        this.kLineDatas = kLineDatas;
    }

    @Override
    public boolean onTouch(View v, final MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("点击事件正确触发", "点击事件正确触发");
                if (isFirstClick) {
                    isFirstClick = false;
                    firstClickTime = new Date(System.currentTimeMillis()).getTime();
                } else {
                    long secondClickTime = new Date(System.currentTimeMillis()).getTime();
                    if (secondClickTime - firstClickTime < 300) {
                        if (!(activity instanceof FullScreenActivity)) {
//                            Intent intent = new Intent(activity, FullScreenActivity.class);
//                            activity.startActivity(intent);
//                            activity.finish();
                        }
                    } else {
                        isFirstClick = false;
                        firstClickTime = new Date(System.currentTimeMillis()).getTime();
                    }
                }

                // 每次点击初始化长按判断变量
                isLongClick = false;
                isNoMove = true;
                isJudge = true;
                // 获取点击开始的坐标
                scrollX = event.getX();
                startX = event.getX();
                startY = event.getY();
                // 在子线程中等待一定时间以判断是否为长按事件
                runnable = new JudgementRunnable();
                runnable.setxCoordinate(event.getX());
                new Thread(runnable).start();
                break;
            case MotionEvent.ACTION_MOVE:
                // 一秒内任意一次坐标偏离50以上视为非长按事件，未移动状态变量置为false
                if ((Math.abs(event.getX() - startX) > 50 || Math.abs(event.getY() - startY) > 50) && isJudge == true) {
                    isNoMove = false;
                }
                // 若长按状态变量为true表明是长按事件，每次移动更新界面
                if (isLongClick == true) {

                    RefreshHelper.refreshMainHighLight(activity, kLineRender, event.getX());

                    RefreshHelper.refreshSecondaryHighLight(activity, kLineRender, event.getX());
                    GlobalViewsUtil.getCover(activity).setAlpha(1);
                    RefreshHelper.refreshCoverView(activity, null, kLineRender, event.getX());
                    for (TextView textView : titles) {
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                    }

                } else {

                    if (Math.abs(scrollX - event.getX()) > kLineView.getWidth() / 35) {

                        if (event.getX() > scrollX) {
                            if (scrollPosition < kLineDatas.size() - itemNumber - 1) {
                                scrollPosition += 2;
                            } else {
                                scrollPosition = kLineDatas.size() - itemNumber - 1;
                            }
                        } else {
                            if (scrollPosition > 1) {
                                scrollPosition -= 2;
                            } else {
                                scrollPosition = 0;
                            }
                        }
                        scrollX = event.getX();
                        Variable.setScrollStartPosition(scrollPosition);
                        kLineRender = LocalToView.getKLineRender(activity, kLineDatas);
                        RefreshHelper.refreshMainView(activity, kLineRender);

                        int secondaryType = Variable.getSecondaryType();
                        RefreshHelper.refreshSecondaryView(activity, kLineRender, secondaryType);

                    }


                }


                break;
            case MotionEvent.ACTION_UP:
                // 传入空参，使控件清除高亮线
                RefreshHelper.refreshMainHighLight(activity, null, event.getX());
                RefreshHelper.refreshSecondaryHighLight(activity, null, event.getX());
                RefreshHelper.refreshCoverView(activity, null, null, 0);
                GlobalViewsUtil.getCover(activity).setAlpha(0);
                for (TextView textView : titles) {
                    textView.setOnClickListener(new PagerClickListener(activity));
                }
                // 初始化各项参数，准备下一次触摸事件
                isLongClick = false;
                isNoMove = true;
                isJudge = false;
                runnable.setActionExist(false);
                break;
        }

        return false;
    }

    private class JudgementRunnable implements Runnable{

        private boolean isActionExist = true;
        private float xCoordinate;

        public void setxCoordinate(float x){
            this.xCoordinate = x;
        }

        public void setActionExist(boolean actionExist) {
            isActionExist = actionExist;
        }

        @Override
        public void run() {
            try {
                // 等待一秒钟
                Thread.sleep(300);
                // 若一秒内位置没有显著变化视为长按事件
                if (isNoMove && isActionExist) {
                    // 设置长按状态为true
                    isLongClick = true;
                    isJudge = false;
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            RefreshHelper.refreshMainHighLight(activity, kLineRender, xCoordinate);

                            RefreshHelper.refreshSecondaryHighLight(activity, kLineRender, xCoordinate);
                            GlobalViewsUtil.getCover(activity).setAlpha(1);
                            RefreshHelper.refreshCoverView(activity, null, kLineRender, xCoordinate);
                            for (TextView textView : titles) {
                                textView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                            }
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
