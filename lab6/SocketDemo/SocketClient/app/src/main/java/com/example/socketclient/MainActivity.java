package com.example.socketclient;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter writer;
    private EditText txt;
    private static  final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnConn = findViewById(R.id.btnConn);
        Button btnSend = findViewById(R.id.btnSend);
        Button btnDiscon = findViewById(R.id.btnDiscon);
        btnConn.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        btnDiscon.setOnClickListener(this);
        txt = (EditText) findViewById(R.id.textView);
    }

    @Override
    public void onClick(View view){
        if (view.getId() == R.id.btnConn) {
            new Thread(new NetConn()).start();
        }
        if (view.getId() == R.id.btnSend) {
            new Thread(){
                @Override
                public void run(){
                    writer.println("hello,server");
                }
            }.start();
        }
        if(view.getId() == R.id.btnDiscon){
            new Thread(){
                @Override
                public void run(){
                    writer.println("bye");
                }
            }.start();
        }
    }

    protected class NetConn extends Thread{
        @Override
        public void run(){
            try{
                socket = new Socket();
                socket.connect(new InetSocketAddress
                        ("10.0.2.2",9999),5000);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
                writer = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream(),"utf-8")),true);
                //接收服务器发送的数据
                String fromserver = null;
                try{
                    while((fromserver = in.readLine())!=null)
                    {
                        String msg = fromserver;
                        runOnUiThread(() -> {txt.setText(msg);});
                    }
                    Log.i(TAG, "检测到服务器关闭连接，客户端开始关闭");
                    // 客户端关闭Socket
                    socket.close();
                    runOnUiThread(() -> { txt.setText("connection closed");});
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }catch(UnknownHostException ex){
                ex.printStackTrace();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

}

