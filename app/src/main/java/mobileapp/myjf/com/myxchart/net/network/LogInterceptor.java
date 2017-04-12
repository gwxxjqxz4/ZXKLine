package mobileapp.myjf.com.myxchart.net.network;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * An OkHttp interceptor which logs request and response information. Can be applied as an
 * {@linkplain OkHttpClient#interceptors() application interceptor} or as a {@linkplain
 * OkHttpClient#networkInterceptors() network interceptor}. <p> The format of the logs created by
 * this class should not be considered stable and may change slightly between releases. If you need
 * a stable logging format, use your own interceptor.
 */

/**
 * 网络请求/响应拦截并打印日志
 */
public final class LogInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    public enum Level {
        /** No logs. */
        NONE,
        /**
         * Logs request and response lines.
         *
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1 (3-byte body)
         *
         * <-- 200 OK (22ms, 6-byte body)
         * }</pre>
         */
        BASIC,
        /**
         * Logs request and response lines and their respective headers.
         *
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <-- END HTTP
         * }</pre>
         */
        HEADERS,
        /**
         * Logs request and response lines and their respective headers and bodies (if present).
         *
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         *
         * Hi?
         * --> END GET
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         *
         * Hello!
         * <-- END HTTP
         * }</pre>
         */
        BODY
    }

    /**
     * 日志接口
     */
    public interface Logger {
        void log(String message);

        void json(String json);

    }

    /**
     * 构造方法
     *
     * @param logger
     */
    public LogInterceptor(Logger logger) {
        this.logger = logger;
    }

    // 日志接口实例
    private final Logger logger;
    // 默认级别为NONE
    private volatile Level level = Level.NONE;

    /** Change the level at which this interceptor logs. */
    /**
     * 设置打印级别，若参数为空则报错
     *
     * @param level
     * @return
     */
    public LogInterceptor setLevel(Level level) {
        if (level == null) throw new NullPointerException("level == null. Use Level.NONE instead.");
        this.level = level;
        return this;
    }

    /**
     * 获取打印级别
     *
     * @return
     */
    public Level getLevel() {
        return level;
    }

    /**
     * 拦截
     *
     * @param chain
     * @return
     * @throws IOException
     */
    @Override public Response intercept(Chain chain) throws IOException {
        Level level = this.level;
        // 获取请求对象
        Request request = chain.request();
        // 若级别为NONE则退出
        if (level == Level.NONE) {
            return chain.proceed(request);
        }

        // 获取打印级别
        boolean logBody = level == Level.BODY;
        boolean logHeaders = logBody || level == Level.HEADERS;
        // 获取请求体并获取是否为NONE的判断
        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;
        // 获取连接对象
        Connection connection = chain.connection();
        // 获取协议对象，默认为HTTP_1_1
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        // 获取请求开始信息为请求方法+地址+协议
        String requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol;

        // 若无日志头且有请求体输出此语句
        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }
        // 用子类方法打印请求消息
        logger.log(requestStartMessage);
        // 若有日志头
        if (logHeaders) {
            if (hasRequestBody) {
                // Request body headers are only present when installed as a network interceptor. Force
                // them to be included (when available) so there values are known.
                // 若有请求格式则打印
                if (requestBody.contentType() != null) {
                    logger.log("Content-Type: " + requestBody.contentType());
                }
                // 若有正文长度则打印
                if (requestBody.contentLength() != -1) {
                    logger.log("Content-Length: " + requestBody.contentLength());
                }

            }

            // 获取请求头集合
            Headers headers = request.headers();
            // 打印每个请求头
            for (int i = 0, count = headers.size(); i < count; i++) {
                String name = headers.name(i);
                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    logger.log(name + ": " + headers.value(i));
                }
            }
            // 若既无日志体也无请求体则打印结束
            if (!logBody || !hasRequestBody) {
                logger.log("--> END " + request.method());
            } else if (bodyEncoded(request.headers())) {
                logger.log("--> END " + request.method() + " (encoded body omitted)");
            } else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                // 设置编码格式为UTF-8
                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                // 打印结束
                logger.log("");
                logger.log(buffer.readString(charset));

                logger.log("--> END " + request.method()
                        + " (" + requestBody.contentLength() + "-byte body)");
            }
        }
        // 获取开始时间
        long startNs = System.nanoTime();
        // 执行请求，获取响应
        Response response = chain.proceed(request);
        // 获取执行时间的毫秒数
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        // 获取请求体对象
        ResponseBody responseBody = response.body();
        // 获取正文长度
        long contentLength = responseBody.contentLength();
        // 若正文长度不为-1则缓存正文长度，否则显示未知长度
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
        // 打印响应体参数
        logger.log("<-- " + response.code() + ' ' + response.message() + ' '
                + response.request().url() + " (" + tookMs + "ms" + (!logHeaders ? ", "
                + bodySize + " body" : "") + ')');
        // 若有日志头则打印
        if (logHeaders) {
            // 获取响应头
            Headers headers = response.headers();
            // 打印响应头
            for (int i = 0, count = headers.size(); i < count; i++) {
                logger.log(headers.name(i) + ": " + headers.value(i));
            }

         /*   if (!logBody || !HttpEngine.hasBody(response)) {
                logger.log("<-- END HTTP");
            } else*/
         // 打印响应体
         if (bodyEncoded(response.headers())) {
                logger.log("<-- END HTTP (encoded body omitted)");
            } else {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();

                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    try {
                        charset = contentType.charset(UTF8);
                    } catch (UnsupportedCharsetException e) {
                        logger.log("");
                        logger.log("Couldn't decode the response body; charset is likely malformed.");
                        logger.log("<-- END HTTP");

                        return response;
                    }
                }

                if (contentLength != 0) {
                    logger.log("");
                    logger.json(buffer.clone().readString(charset));
                }

                logger.log("<-- END HTTP (" + buffer.size() + "-byte body)");
            }
        }

        return response;
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
}

