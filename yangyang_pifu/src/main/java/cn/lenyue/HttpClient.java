package cn.lenyue;

import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpClient {
    public static Builder client;
    private static String host = "cat-match.easygame2021.com";
    private static String Referer = "https://servicewechat.com/wx141bfb9b73c970a9/16/page-frame.html";
    private static String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/8.0.24(0x1800182f) NetType/WIFI Language/zh_CN";
    private static String xRequestedWith = "XMLHttpRequest";

    static {
        client = new Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(5, 20, TimeUnit.SECONDS))
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Host", host)
                                .addHeader("Referer", Referer)
                                .addHeader("User-Agent", userAgent)
                                .addHeader("xRequestedWith", xRequestedWith)
                                .build();
                        return chain.proceed(request);
                    }
                });
    }
}
