package mobileapp.zixiao.com.zxchart.ui.ontouchlistener;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import mobileapp.zixiao.com.zxchart.entity.render.TimeLineRender;
import mobileapp.zixiao.com.zxchart.ui.FullScreenActivity;
import mobileapp.zixiao.com.zxchart.ui.onclicklistener.PagerClickListener;
import mobileapp.zixiao.com.zxchart.utils.global.GlobalViewsUtil;
import mobileapp.zixiao.com.zxchart.render.highlight.TimeLineHighLightView;
import mobileapp.zixiao.com.zxchart.utils.other.RefreshHelper;

/**
 * Created by gwx
 */

public class TimeLineOnTouchListener implements View.OnTouchListener {

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
    List<TextView> titles;
    // 用于判断双击、长按事件的子线程可运行对象
    JudgementRunnable runnable;

    private Activity activity;
    private TimeLineHighLightView timeLineHighLightView;
    private TimeLineRender timeLineRender;

    public TimeLineOnTouchListener(Activity activity, TimeLineRender timeLineRender) {
        this.activity = activity;
        this.timeLineRender = timeLineRender;
        titles = GlobalViewsUtil.getTitles(activity);

        timeLineHighLightView = GlobalViewsUtil.getTimeLineHighLight(activity);

    }

    @Override
    public boolean onTouch(View v, final MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (timeLineHighLightView == null) {
                    timeLineHighLightView = new TimeLineHighLightView(activity);
                }
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

                    RefreshHelper.refreshTimeLineHighLight(activity, timeLineRender, event.getX());
                    GlobalViewsUtil.getCover(activity).setAlpha(1);
                    RefreshHelper.refreshCoverView(activity, timeLineRender, null, event.getX());
                    for (TextView textView : titles) {
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        });
                    }

                }


                break;
            case MotionEvent.ACTION_UP:
                // 传入空数据刷新，隐藏分时线高亮
                RefreshHelper.refreshTimeLineHighLight(activity, null, 0);
                RefreshHelper.refreshCoverView(activity, null, null, 0);
                GlobalViewsUtil.getCover(activity).setAlpha(0);
                for (TextView textView : titles) {
                    textView.setOnClickListener(new PagerClickListener(activity));
                }
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
                            RefreshHelper.refreshTimeLineHighLight(activity, timeLineRender, xCoordinate);
                            GlobalViewsUtil.getCover(activity).setAlpha(1);
                            RefreshHelper.refreshCoverView(activity, timeLineRender, null, xCoordinate);
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

