package mobileapp.myjf.com.myxchart.net.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 请求类
 */
public class KLineRestClient {
    public static final String BaseURL = "http://data.meiyuanjinfu.com";
    // 接口实例
    public static ApiService apiService;
    // 获取接口实例方法
    public static ApiService getInstance() {
        ApiInterceptor apiInterceptor = new ApiInterceptor();
        return build(apiInterceptor);
    }
    // 带参的获取接口实例的方法
    public static ApiService getInstance(String token) {
        ApiInterceptor apiInterceptor = new ApiInterceptor(token);
        return build(apiInterceptor);
    }
    // 创建接口实例的方法
    public static ApiService build(ApiInterceptor apiInterceptor) {
        // 若没有接口实例
        if (apiService == null) {
            // 获取解析类对象
            Gson gson = getGson();
            // 新建网络请求封装类
            Retrofit.Builder builder = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(BaseURL + "/");
            // 请求日志处理实例

            LogInterceptor logInterceptor = new LogInterceptor(new ApiLogger());
            // 打印头和内容的日志
            logInterceptor.setLevel(LogInterceptor.Level.BODY);
            // 网络请求客户端实例

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(apiInterceptor)
                    .addInterceptor(logInterceptor)
                    .build();
            builder.client(client);
            // 新建接口实例
            apiService = builder.build().create(ApiService.class);
        }
        // 返回接口实例
        return apiService;
    }

    /**
     * 获取json解析工具类
      */
    private static Gson getGson() {
        return new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    @Override
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                            throws JsonParseException {

                        try {
                            return JSON_STRING_DATE.parse(json.getAsString());
                        } catch (ParseException e) {
                            return null;
                        }
                    }
                }).create();
/*        .registerTypeAdapter(new TypeToken<CommonEntity<User>>() {
        }.getType(), new MyDeserializer("data", User.class))*/
    }

    private static final SimpleDateFormat JSON_STRING_DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

}
