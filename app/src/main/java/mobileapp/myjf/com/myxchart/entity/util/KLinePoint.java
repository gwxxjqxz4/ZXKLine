package mobileapp.myjf.com.myxchart.entity.util;

import java.text.DecimalFormat;

/**
 * Created by nethanhan on 2017/4/6.
 */

public class KLinePoint {

    private double coordinateX;
    private double coordinateY;
    private double price;

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceString(){
        return new DecimalFormat("#0.0000").format(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public KLinePoint(double coordinateX, double coordinateY, double price) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.price = price;
    }
}
