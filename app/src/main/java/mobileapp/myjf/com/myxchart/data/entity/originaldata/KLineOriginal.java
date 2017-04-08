package mobileapp.myjf.com.myxchart.data.entity.originaldata;

import com.google.gson.annotations.SerializedName;

/**
 * create by gwx
 * K线图列表数据类型，用于解析和操作从服务器请求的K线图数据列表
 */
public class KLineOriginal {

    // 商品名称
    @SerializedName("ProductName")
    private String productName;

    // 商品K线数据
    @SerializedName("Data")
    private String data;

    // 商品编号
    @SerializedName("ProductCode")
    private String productCode;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public KLineOriginal(String productName, String data, String productCode) {
        this.productName = productName;
        this.data = data;
        this.productCode = productCode;
    }

    @Override
    public String toString() {
        return "KLineOriginal{" +
                "productName='" + productName + '\'' +
                ", data=" + data +
                ", productCode='" + productCode + '\'' +
                '}';
    }
}
