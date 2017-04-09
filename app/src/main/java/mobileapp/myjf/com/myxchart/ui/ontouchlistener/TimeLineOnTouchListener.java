package mobileapp.myjf.com.myxchart.ui.ontouchlistener;

import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Date;

import mobileapp.myjf.com.myxchart.data.entity.render.TimeLineRender;
import mobileapp.myjf.com.myxchart.data.global.GlobalViewsUtil;
import mobileapp.myjf.com.myxchart.render.highlight.TimeLineHighLightView;
import mobileapp.myjf.com.myxchart.ui.MainActivity;

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

    private Activity activity;
    private RelativeLayout timeLineLayout;
    private TimeLineHighLightView timeLineHighLightView;
    private TimeLineRender timeLineRender;

    public TimeLineOnTouchListener(Activity activity, TimeLineRender timeLineRender) {
        this.activity = activity;
        this.timeLineRender = timeLineRender;

        timeLineLayout = GlobalViewsUtil.getTimeLineLayout(activity);
        timeLineHighLightView = GlobalViewsUtil.getTimeLineHighLight(activity);

    }

    @Override
    public boolean onTouch(View v, final MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(timeLineHighLightView == null){
                    timeLineHighLightView = new TimeLineHighLightView(activity);
                }
                if(isFirstClick){
                    isFirstClick = false;
                    firstClickTime = new Date(System.currentTimeMillis()).getTime();
                }else{
                    long secondClickTime = new Date(System.currentTimeMillis()).getTime();
                    if(secondClickTime - firstClickTime < 300){
                        Intent intent = new Intent(activity, MainActivity.class);
                        activity.startActivity(intent);
                    }else{
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // 等待一秒钟
                            Thread.sleep(300);
                            // 若一秒内位置没有显著变化视为长按事件
                            if (isNoMove) {
                                // 设置长按状态为true
                                isLongClick = true;
                                isJudge = false;
                                timeLineHighLightView.setTimeLineRender(timeLineRender);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case MotionEvent.ACTION_MOVE:
                // 一秒内任意一次坐标偏离20以上视为非长按事件，未移动状态变量置为false
                if ((Math.abs(event.getX() - startX) > 50 || Math.abs(event.getY() - startY) > 50) && isJudge == true) {
                    isNoMove = false;
                }
                // 若长按状态变量为true表明是长按事件，每次移动更新界面
                if (isLongClick == true) {

                    timeLineLayout.removeView(timeLineHighLightView);
                    timeLineHighLightView.setMoveX(event.getX());
                    timeLineHighLightView.invalidate();
                    timeLineLayout.addView(timeLineHighLightView);

                }


                break;
            case MotionEvent.ACTION_UP:
                timeLineLayout.removeView(timeLineHighLightView);
                timeLineHighLightView = null;
                isLongClick = false;
                isNoMove = true;
                isJudge = false;
                break;
        }
        return false;
    }
}

