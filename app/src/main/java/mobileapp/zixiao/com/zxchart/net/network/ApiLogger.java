package mobileapp.zixiao.com.zxchart.net.network;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * 服务器数据日志打印
 */
public class ApiLogger implements LogInterceptor.Logger {

    // 初始化日志设置
    static {
        Logger.init("okhttp----")                 // default PRETTYLOGGER or use just init()
                .methodCount(0)                             // default 2
                .hideThreadInfo()                         // default shown
                .logLevel(LogLevel.NONE)                // default LogLevel.FULL
                .methodOffset(0);                            // default 0
    }

    // 打印日志信息
    @Override
    public void log(String message) {
        Logger.d(message);
       /* Platform.get().log(message);*/
     /*   try {
            StringReader reader = new StringReader(message);
            Properties properties = new Properties();
            properties.load(reader);
            properties.list(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 获取json开始位置
     *
     * @param json json对象
     */
    @Override
    public void json(String json) {
        if (json.indexOf("{") >= 0) {
            Logger.json(json.substring(json.indexOf("{")));
        } else {
            Logger.e("***[" + json + "]***");
        }
    }

}
