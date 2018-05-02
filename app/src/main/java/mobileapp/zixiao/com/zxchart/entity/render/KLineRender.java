package mobileapp.zixiao.com.zxchart.entity.render;

import java.text.DecimalFormat;
import java.util.List;

import mobileapp.zixiao.com.zxchart.entity.util.KLineItem;

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

    public String getTopPrice() {
        return new DecimalFormat("#0.0000").format(topPrice);
    }

    public void setTopPrice(double topPrice) {
        this.topPrice = topPrice;
    }

    public String getBottomPrice() {
        return new DecimalFormat("#0.0000").format(bottomPrice);
    }

    public void setBottomPrice(double bottomPrice) {
        this.bottomPrice = bottomPrice;
    }

    public String getAbovePrice() {
        return new DecimalFormat("#0.0000").format(abovePrice);
    }

    public void setAbovePrice(double abovePrice) {
        this.abovePrice = abovePrice;
    }

    public String getUnderPrice() {
        return new DecimalFormat("#0.0000").format(underPrice);
    }

    public void setUnderPrice(double underPrice) {
        this.underPrice = underPrice;
    }

    public String getMidPrice() {
        return new DecimalFormat("#0.0000").format(midPrice);
    }

    public void setMidPrice(double midPrice) {
        this.midPrice = midPrice;
    }

    public String getMacdTop() {
        return new DecimalFormat("#0.0000").format(macdTop);
    }

    public void setMacdTop(double macdTop) {
        this.macdTop = macdTop;
    }

    public String getMacdBottom() {
        return new DecimalFormat("#0.0000").format(macdBottom);
    }

    public void setMacdBottom(double macdBottom) {
        this.macdBottom = macdBottom;
    }

    public String getRsiTop() {
        return new DecimalFormat("#0.0000").format(rsiTop);
    }

    public void setRsiTop(double rsiTop) {
        this.rsiTop = rsiTop;
    }

    public String getRsiBottom() {
        return new DecimalFormat("#0.0000").format(rsiBottom);
    }

    public void setRsiBottom(double rsiBottom) {
        this.rsiBottom = rsiBottom;
    }

    public String getBiasTop() {
        return new DecimalFormat("#0.0000").format(biasTop);
    }

    public void setBiasTop(double biasTop) {
        this.biasTop = biasTop;
    }

    public String getBiasBottom() {
        return new DecimalFormat("#0.0000").format(biasBottom);
    }

    public void setBiasBottom(double biasBottom) {
        this.biasBottom = biasBottom;
    }

    public String getKdjTop() {
        return new DecimalFormat("#0.0000").format(kdjTop);
    }

    public void setKdjTop(double kdjTop) {
        this.kdjTop = kdjTop;
    }

    public String getKdjBottom() {
        return new DecimalFormat("#0.0000").format(kdjBottom);
    }

    public void setKdjBottom(double kdjBottom) {
        this.kdjBottom = kdjBottom;
    }
}
