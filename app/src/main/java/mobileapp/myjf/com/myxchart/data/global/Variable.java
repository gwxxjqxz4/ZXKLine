package mobileapp.myjf.com.myxchart.data.global;

import android.app.Activity;
import android.view.View;

import mobileapp.myjf.com.myxchart.data.entity.render.KLineRender;

/**
 * Created by nethanhan on 2017/4/8.
 */

public class Variable {

    // 私有构造，防止被实例化
    private Variable(){}
    // 提供一个已经初始化好了的对象
    private static Variable variable = new Variable();
    // 用户选择的图表类型(按标题栏从左至右)
    private static int selectedType;
    // 用户选择的副图类型（按标题从左至右）
    private static int secondaryType;
    // 滚动状态的开始索引
    private static int scrollStartPosition;
    // 滚动状态的结束索引
    private static int scrollStopPosition;
    // 每屏的条目数量
    private static int itemNumber = 35;
    // 该类型的条目总数
    private static int totalNumber;
    // 上下文对象，
    private Activity activity;

    public static int getScrollStartPosition() {
        return scrollStartPosition;
    }

    public static void setScrollStartPosition(int scrollStartPosition) {
        Variable.scrollStartPosition = scrollStartPosition;
    }

    public static int getScrollStopPosition() {
        return scrollStopPosition;
    }

    public static void setScrollStopPosition(int scrollStopPosition) {
        Variable.scrollStopPosition = scrollStopPosition;
    }

    public static int getSelectedType() {
        return selectedType;
    }

    public static void setSelectedType(int selectedType) {
        Variable.selectedType = selectedType;
    }

    public static int getItemNumber() {
        return itemNumber;
    }

    public static void setItemNumber(int itemNumber) {
        Variable.itemNumber = itemNumber;
    }

    public static int getTotalNumber() {
        return totalNumber;
    }

    public static void setTotalNumber(int totalNumber) {
        Variable.totalNumber = totalNumber;
    }

    public static Variable getVariable() {
        return variable;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public static int getSecondaryType() {
        return secondaryType;
    }

    public static void setSecondaryType(int secondaryType) {
        Variable.secondaryType = secondaryType;
    }

}
