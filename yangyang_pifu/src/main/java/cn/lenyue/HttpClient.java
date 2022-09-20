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
    private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36 Edg/105.0.1343.33";
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
                                .addHeader("User-Agent", userAgent)
                                .addHeader("xRequestedWith", xRequestedWith)
                                .build();
                        return chain.proceed(request);
                    }
                });
    }
}
