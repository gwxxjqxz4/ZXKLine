package mobileapp.myjf.com.myxchart.entity.render;

import java.util.List;

import mobileapp.myjf.com.myxchart.entity.util.KLineItem;

/**
 * Created by nethanhan on 2017/4/6.
 */

public class KLineRender {

    private List<KLineItem> items;
    private double topPrice;
    private double bottomPrice;
    private double abovePrice;
    private double underPrice;
    private double midPrice;

    private double macdTop;
    private double macdBottom;
    private double rsiTop;
    private double rsiBottom;
    private double biasTop;
    private double biasBottom;
    private double kdjTop;
    private double kdjBottom;

    public List<KLineItem> getItems() {
        return items;
    }

    public void setItems(List<KLineItem> items) {
        this.items = items;
    }

    public double getTopPrice() {
        return topPrice;
    }

    public void setTopPrice(double topPrice) {
        this.topPrice = topPrice;
    }

    public double getBottomPrice() {
        return bottomPrice;
    }

    public void setBottomPrice(double bottomPrice) {
        this.bottomPrice = bottomPrice;
    }

    public double getAbovePrice() {
        return abovePrice;
    }

    public void setAbovePrice(double abovePrice) {
        this.abovePrice = abovePrice;
    }

    public double getUnderPrice() {
        return underPrice;
    }

    public void setUnderPrice(double underPrice) {
        this.underPrice = underPrice;
    }

    public double getMidPrice() {
        return midPrice;
    }

    public void setMidPrice(double midPrice) {
        this.midPrice = midPrice;
    }

    public double getMacdTop() {
        return macdTop;
    }

    public void setMacdTop(double macdTop) {
        this.macdTop = macdTop;
    }

    public double getMacdBottom() {
        return macdBottom;
    }

    public void setMacdBottom(double macdBottom) {
        this.macdBottom = macdBottom;
    }

    public double getRsiTop() {
        return rsiTop;
    }

    public void setRsiTop(double rsiTop) {
        this.rsiTop = rsiTop;
    }

    public double getRsiBottom() {
        return rsiBottom;
    }

    public void setRsiBottom(double rsiBottom) {
        this.rsiBottom = rsiBottom;
    }

    public double getBiasTop() {
        return biasTop;
    }

    public void setBiasTop(double biasTop) {
        this.biasTop = biasTop;
    }

    public double getBiasBottom() {
        return biasBottom;
    }

    public void setBiasBottom(double biasBottom) {
        this.biasBottom = biasBottom;
    }

    public double getKdjTop() {
        return kdjTop;
    }

    public void setKdjTop(double kdjTop) {
        this.kdjTop = kdjTop;
    }

    public double getKdjBottom() {
        return kdjBottom;
    }

    public void setKdjBottom(double kdjBottom) {
        this.kdjBottom = kdjBottom;
    }
}
