package mobileapp.myjf.com.myxchart.ui.ontouchlistener;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Date;
import java.util.List;

import mobileapp.myjf.com.myxchart.calculation.LocalToView;
import mobileapp.myjf.com.myxchart.data.entity.localdata.KLineLocal;
import mobileapp.myjf.com.myxchart.data.entity.render.KLineRender;
import mobileapp.myjf.com.myxchart.data.entity.util.KLineItem;
import mobileapp.myjf.com.myxchart.render.data.KLineView;
import mobileapp.myjf.com.myxchart.render.data.MacdView;
import mobileapp.myjf.com.myxchart.render.data.RSIView;
import mobileapp.myjf.com.myxchart.render.highlight.KLineHighLightView;
import mobileapp.myjf.com.myxchart.render.highlight.SecondaryHighLight;
import mobileapp.myjf.com.myxchart.ui.MainActivity;
import mobileapp.myjf.com.myxchart.ui.layout.KLineMainLayout;
import mobileapp.myjf.com.myxchart.ui.layout.KLineSecondaryLayout;

/**
 * Created by nethanhan on 2017/4/7.
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
    // 每屏数据量
    private int itemNumber;

    private Context context;
    private List<KLineItem> kLineItems;
    private KLineHighLightView kLineHighLightView;
    private KLineMainLayout kLineMainLayout;
    private SecondaryHighLight secondaryHighLight;
    private KLineSecondaryLayout kLineSecondaryLayout;
    private KLineView kLineView;
    private View secondaryView;
    private KLineLocal kLineLocal;
    private List<KLineItem> secondaryItems;

    public KLineOnTouchListener(Context context, List<KLineItem> kLineItems, List<KLineItem> secondaryItems, KLineMainLayout kLineMainLayout, KLineView kLineView, KLineLocal kLineLocal, int itemNumber, KLineSecondaryLayout kLineSecondaryLayout, View secondaryView) {
        this.context = context;
        if (this.kLineItems == null) {
            this.kLineItems = kLineItems;
        }
        this.kLineMainLayout = kLineMainLayout;
        this.kLineView = kLineView;
        this.kLineLocal = kLineLocal;
        this.scrollPosition = kLineItems.size() - 36;
        this.itemNumber = itemNumber;
        this.kLineSecondaryLayout = kLineSecondaryLayout;
        this.secondaryView = secondaryView;
        this.secondaryItems = secondaryItems;
        this.kLineLocal = kLineLocal;
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
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    } else {
                        isFirstClick = false;
                        firstClickTime = new Date(System.currentTimeMillis()).getTime();
                    }
                }
                kLineHighLightView = new KLineHighLightView(context);
                secondaryHighLight = new SecondaryHighLight(context);

                // 每次点击初始化长按判断变量
                isLongClick = false;
                isNoMove = true;
                isJudge = true;
                // 获取点击开始的坐标
                scrollX = event.getX();
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
                                kLineHighLightView.setkLineItems(kLineItems);
                                secondaryHighLight.setkLineItems(secondaryItems);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case MotionEvent.ACTION_MOVE:
                // 一秒内任意一次坐标偏离50以上视为非长按事件，未移动状态变量置为false
                if ((Math.abs(event.getX() - startX) > 50 || Math.abs(event.getY() - startY) > 50) && isJudge == true) {
                    isNoMove = false;
                }
                // 若长按状态变量为true表明是长按事件，每次移动更新界面
                if (isLongClick == true) {

                    kLineMainLayout.removeView(kLineHighLightView);
                    kLineHighLightView.setMoveX(event.getX());
                    kLineHighLightView.invalidate();
                    kLineMainLayout.addView(kLineHighLightView);

                    kLineSecondaryLayout.removeView(secondaryHighLight);
                    secondaryHighLight.setMoveX(event.getX());
                    secondaryHighLight.invalidate();
                    kLineSecondaryLayout.addView(secondaryHighLight);

                } else {

                    if (Math.abs(scrollX - event.getX()) > kLineView.getWidth() / 35) {

                        if (event.getX() > scrollX) {
                            if (scrollPosition < kLineLocal.getkLineDatas().size() - itemNumber - 2) {
                                scrollPosition += 2;
                            } else {
                                scrollPosition = kLineLocal.getkLineDatas().size() - itemNumber - 2;
                            }
                        } else {
                            if (scrollPosition > 1) {
                                scrollPosition -= 2;
                            } else {
                                scrollPosition = 0;
                            }
                        }
                        scrollX = event.getX();
                        kLineMainLayout.removeView(kLineView);
                        KLineRender kLineRender = LocalToView.getKLineRender(kLineLocal, kLineMainLayout.getWidth(), kLineMainLayout.getHeight(), kLineMainLayout.getHeight(), scrollPosition, 35);
                        kLineItems = kLineRender.getItems();
                        kLineView.setkLineItems(kLineRender);
                        kLineMainLayout.addView(kLineView);

                        kLineSecondaryLayout.removeView(secondaryView);
                        kLineRender = LocalToView.getKLineRender(kLineLocal, kLineMainLayout.getWidth(), kLineSecondaryLayout.getHeight(), kLineSecondaryLayout.getHeight(), scrollPosition, 35);
                        secondaryItems = kLineRender.getItems();
                        if (secondaryView instanceof MacdView) {
                            ((MacdView) secondaryView).setCoordinates(kLineRender);
                        } else {
                            ((RSIView) secondaryView).setCoordinates(kLineRender);
                        }
                        kLineSecondaryLayout.addView(secondaryView);

                    }


                }


                break;
            case MotionEvent.ACTION_UP:
                kLineMainLayout.removeView(kLineHighLightView);
                kLineSecondaryLayout.removeView(secondaryHighLight);
                kLineHighLightView = null;
                secondaryHighLight = null;
                isLongClick = false;
                isNoMove = true;
                isJudge = false;
                break;
        }

        return false;
    }
}
