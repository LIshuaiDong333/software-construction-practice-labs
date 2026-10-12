package com.example.myserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * 模拟用户信息的HTTP服务器
 */
public class MyClass {
    // 模拟用户数据（实际开发中可替换为数据库查询）
    private static final String USER_DATA =
            "{\n" +
                    "  \"userId\": \"1001\",\n" +
                    "  \"userName\": \"张三\",\n" +
                    "  \"age\": 20,\n" +
                    "  \"major\": \"计算机科学与技术\"\n" +
                    "}";

    public static void main(String[] args) throws IOException {
        // 1. 创建服务器，绑定8080端口，最大并发数10
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 10);
        // 2. 注册用户信息接口：/api/user/info
        server.createContext("/api/user/info", new UserInfoHandler());
        // 3. 启动服务器
        server.start();
        System.out.println("用户信息服务器启动成功！");
    }

    // 处理用户信息请求的处理器
    static class UserInfoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // 设置响应头（允许跨域、指定JSON格式）
            exchange.getResponseHeaders().add("Content-Type", "application/json;charset=UTF-8");
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            // 模拟根据请求参数（userId）返回对应信息（此处简化为固定数据）
            String query = exchange.getRequestURI().getQuery();
            String responseData = USER_DATA;
            // 若传了userId参数，简单模拟不同用户返回
            if (query != null && query.contains("userId=")) {
                String userId = query.split("=")[1];
                responseData = responseData.replace("1001", userId);
            }
            // 发送响应（200表示成功，第二个参数是响应数据长度）
            exchange.sendResponseHeaders(200, responseData.getBytes(StandardCharsets.UTF_8).length);
            OutputStream os = exchange.getResponseBody();
            os.write(responseData.getBytes(StandardCharsets.UTF_8));
            os.close(); // 必须关闭流，否则客户端会阻塞
        }
    }
}