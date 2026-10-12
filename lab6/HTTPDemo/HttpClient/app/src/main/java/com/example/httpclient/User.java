package com.example.httpclient;

public class User {
    private String userId;
    private String userName;
    private int age;
    private String major;

    // Getter和Setter方法
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    // 重写toString，方便打印展示
    @Override
    public String toString() {
        return "用户信息：\n" +
                "用户ID：" + userId + "\n" +
                "姓名：" + userName + "\n" +
                "年龄：" + age + "\n" +
                "专业：" + major;
    }
}