package com.example.myserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyClass {
    public static void main(String args[]){
        new MyClass();
    }
    public  MyClass(){
        try{
            //创建server socket
            ServerSocket serverSocket = new ServerSocket(9999);
            while(true){
                System.out.println("waiting client connect");
                Socket socket = serverSocket.accept();
                System.out.println("accept client connect" + socket);
                new Thread(new Service(socket)).start();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    class Service implements Runnable{
        private Socket socket;
        private BufferedReader in = null;
        private PrintWriter pout = null;
        public Service(Socket socket){
            this.socket = socket;
            try{
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                pout = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream(),"utf-8")),true);
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        @Override
        public void run() {
            //处理客户端发送的信息，向客户端发送信息
            String content = "";
            sendMessge(socket,"connection successful");
            System.out.println("wait client message " );
            try {
                while ((content = in.readLine()) != null) {
                    //接收客户端信息
                    if(content.equals("bye")){
                        System.out.println("disconnect from client,close socket");
                        socket.shutdownInput();
                        socket.shutdownOutput();
                        socket.close();
                        break;
                    }else {
                        this.sendMessge(socket,"hello,client!");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        public void sendMessge(Socket socket,String message) {
            //发送信息给客户端
            System.out.println("messge to client:" + message);
            pout.println(message);
        }
    }
}