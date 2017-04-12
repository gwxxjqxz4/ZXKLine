package mobileapp.myjf.com.myxchart.net.network;

/**
 * 请求头
 */
public class ApiHeader {
    // 机器识别字符串
    public String device;
    // 令牌
    public String accessToken = "";
    // 版本码
    public String versionCode = "";
    // 实例
    private static ApiHeader instance = new ApiHeader();
    // 获取实例
    public static ApiHeader getInstance(){
        return instance;
    }

    /**
     * 初始化
     *
     * @param versionCode 版本码
     * @param accessToken 令牌
     */
    public static void init(String versionCode, String accessToken){
        // 设置请求头信息
        instance.device = "android";
        instance.versionCode = versionCode;
        instance.accessToken = accessToken;
    }
    // 获取设备识别字符串
    public String getDevice() {
        return device;
    }
    // 设置设备识别字符串
    public void setDevice(String device) {
        this.device = device;
    }
    // 获取令牌
    public String getAccessToken() {
        return accessToken;
    }
    // 设置令牌
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    // 获取版本号
    public String getVersionCode() {
        return versionCode;
    }
    // 设置版本号
    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }
}
