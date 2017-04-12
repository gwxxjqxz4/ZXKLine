package mobileapp.myjf.com.myxchart.net.network;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求拦截
 */
class ApiInterceptor implements Interceptor {
    // 令牌
    private String accessToken;
    // 本类实例
    public ApiInterceptor() {
    }
    // 带参本类实例
    public ApiInterceptor(String accessToken) {
        this.accessToken = accessToken;
    }

    public ApiInterceptor(String compToken, String accessToken) {
        this(accessToken);
    }

    /**
     * 响应出错的处理方法
     *
     * @param chain 请求节点
     * @return  被拦截的响应内容
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        // 获取请求对象
        Request request = chain.request();
        // 重置请求头
//        Request newReq = request.newBuilder()
//                .addHeader("version-code", ApiHeader.getInstance().getVersionCode())
//                .addHeader("access-token", ApiHeader.getInstance().getAccessToken())
//                .addHeader("device", ApiHeader.getInstance().getDevice())
//                .build();

        Request newReq = request.newBuilder()
                .build();

        // 返回重新请求的结果
        return chain.proceed(newReq);
    }
}
