package com.example.surfaceviewdemo;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private MySurfaceView mMySurfaceView;
    private OnBackPressedCallback mBackPressedCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMySurfaceView = new MySurfaceView(this);
        setContentView(mMySurfaceView);

        //注册返回键回调
        mBackPressedCallback = new OnBackPressedCallback(true) { // true = 初始启用回调
            @Override
            public void handleOnBackPressed() {
                //关闭当前Activity
                finish();
            }
        };
        //将回调注册到 Activity 的返回键分发器
        getOnBackPressedDispatcher().addCallback(this, mBackPressedCallback);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mMySurfaceView.x = event.getX();
            mMySurfaceView.y = event.getY();
        }
        return  true;
    }
}