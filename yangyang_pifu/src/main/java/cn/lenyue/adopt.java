package cn.lenyue;


import okhttp3.HttpUrl;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Scanner;

public class adopt {

    //在这里吧token替换
    private static String token_or_t = "";
    //完成时间
    private static int finish_time = 1;
    //请求Url
    private static String finish_api = "https://cat-match.easygame2021.com/sheep/v1/game/update_user_skin?rank_score=1&skin=24&rank_state=1&rank_role=1&rank_time=%s";
    //okhttp3实例
    private static Builder client = HttpClient.client;

    public static void main(String[] args) {
        try {
            System.out.println("羊了个羊一键通关脚本开始启动...");
            System.out.println("请输入要通关的次数：");
            Scanner scanner = new Scanner(System.in);
            int count = scanner.nextInt();
            for (int n = 1; n <= count; n++) {
                System.out.println("第"+n+"次，开始一键通关...");
                finish_game();
                System.out.println("羊了个羊一键通关成功"+n+"次...");
                Thread.sleep(300L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void finish_game() {
        try {
            Request.Builder reqBuild = new Request.Builder();
            HttpUrl.Builder urlBuilder = HttpUrl.parse(String.format(finish_api,finish_time)).newBuilder();
            reqBuild.header("t", token_or_t);
            reqBuild.url(urlBuilder.build());
            Request request = reqBuild.build();

            
            Response response = null;
            try {
                response = HttpClient.client.build().newCall(request).execute();
                if (!response.isSuccessful()) {
                    System.out.println("请求失败..."+response.toString());
                }
                System.out.println("通关成功..."+response.body().string());
            } catch (IOException e) {
                System.out.println("请求超时，服务器崩溃啦...");
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
