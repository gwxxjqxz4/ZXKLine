package mobileapp.myjf.com.myxchart.data.entity.util;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * create by gwx
 * 基本响应类型，用于解析有status、action、message、entity字段的服务器响应
 *
 * @param <T>   响应中数据的类型
 */
public class CommonEntity<T> implements Serializable {

    // 响应的状态
    @SerializedName("status")
    private  int status;
    // 响应的事件
    @SerializedName("action")
    private  int action;
    // 响应的消息
    @SerializedName("message")
    private  String message;
    // 响应的数据
    @SerializedName("entity")
    private T entity;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
