package mobileapp.myjf.com.myxchart.render;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

import mobileapp.myjf.com.myxchart.data.entity.KLineCoordinate;

/**
 * create by gwx
 * 显示K线图柱状物的控件
 */
public class MACDView extends View {

    static {
        Log.e("MACD","视图创建成功");
    }
    // 显示所需的坐标集合
    List<KLineCoordinate> coordinates;

    // 构造方法
    public MACDView(Context context) {
        super(context);
    }

    public MACDView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MACDView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置坐标集合，添加此控件前需先设置坐标集合，否则无法显示内容
     *
     * @param coordinates   坐标集合，可由Calculation类中的calculationKLines方法返回
     */
    public void setCoordinates(List<KLineCoordinate> coordinates) {
        this.coordinates = coordinates;
        Log.e("MACD","坐标点设置成功");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 声明画笔
        Paint paint = new Paint();
        // 设置抗锯齿为true
        paint.setAntiAlias(true);
        // 设置填充模式为填满
        paint.setStyle(Paint.Style.FILL);
        // 设置默认颜色为黑色
        paint.setColor(Color.BLACK);
        Log.e("MACD","进入绘制流程");
        // 遍历所有坐标对象，生成对应的矩形
        for (int i = 0; i < coordinates.size(); i++){
            // 根据MACD正负状态设置画笔颜色
            Log.e("coordinates",coordinates.get(i).toString());
            if(coordinates.get(i).getStatus() == 1){
                paint.setColor(Color.RED);
            }else{
                paint.setColor(Color.GREEN);
            }
            // 使用坐标对象的参数和画笔绘制矩形
            canvas.drawRect((float)coordinates.get(i).getRectLeft(),(float)coordinates.get(i).getRectTop(),(float)coordinates.get(i).getRectRight(),(float)coordinates.get(i).getRectBottom(),paint);
        }

        // 设置macd_dif线颜色
        paint.setColor(Color.BLUE);
        // 遍历所有坐标对象，生成macd_dif线
        for(int i = 1;i < coordinates.size();i++){
            canvas.drawLine((float)coordinates.get(i -1).getMacd_difPoint().xValue,(float)coordinates.get(i -1).getMacd_difPoint().yValue,(float)coordinates.get(i).getMacd_difPoint().xValue,(float)coordinates.get(i).getMacd_difPoint().yValue,paint);
        }
        // 设置macd_dea线颜色
        paint.setColor(Color.CYAN);
        // 遍历所有坐标对象，生成macd_dea线
        for(int i = 1;i < coordinates.size();i++){
            canvas.drawLine((float)coordinates.get(i -1).getMacd_deaPoint().xValue,(float)coordinates.get(i -1).getMacd_deaPoint().yValue,(float)coordinates.get(i).getMacd_deaPoint().xValue,(float)coordinates.get(i).getMacd_deaPoint().yValue,paint);
        }

        super.onDraw(canvas);
    }

}
