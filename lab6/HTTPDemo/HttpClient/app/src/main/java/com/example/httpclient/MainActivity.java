package com.example.httpclient;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvUserInfo;
    private OkHttpClient okHttpClient = new OkHttpClient();
    private static final String SERVER_IP = "10.0.2.2";
    private static final String SERVER_PORT = "8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUserInfo = findViewById(R.id.tv_user_info);
        findViewById(R.id.layout_root).setOnClickListener(v -> requestUserInfo("1001"));
    }

    private void requestUserInfo(String userId) {
        String url = String.format("http://%s:%s/api/user/info?userId=%s",
                SERVER_IP, SERVER_PORT, userId);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        // 直接在主线程调用 enqueue()
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String jsonStr = response.body().string();
                    Gson gson = new Gson();
                    User user = gson.fromJson(jsonStr, User.class);

                    // 子线程回调 → 切主线程更新 UI
                    runOnUiThread(() -> tvUserInfo.setText(user.toString()));
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> tvUserInfo.setText("网络请求失败"));
            }
        });
    }
}