package mobileapp.myjf.com.myxchart.utils.global;

import android.app.Activity;

/**
 * Created by nethanhan on 2017/4/8.
 */

public class Variable {

    // 私有构造，防止被实例化
    private Variable(){}
    // 提供一个已经初始化好了的对象
    private static Variable variable = new Variable();
    // 令牌，由外界传输
    private static String token;
    // 机构代码，由外界传输
    private static String organizationCode;
    // 商品代码，由外界传输
    private static String productCode;
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

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Variable.token = token;
    }

    public static String getOrganizationCode() {
        return organizationCode;
    }

    public static void setOrganizationCode(String organizationCode) {
        Variable.organizationCode = organizationCode;
    }

    public static String getProductCode() {
        return productCode;
    }

    public static void setProductCode(String productCode) {
        Variable.productCode = productCode;
    }
}
