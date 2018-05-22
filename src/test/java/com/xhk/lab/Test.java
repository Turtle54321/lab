package com.xhk.lab;

public class Test {

    public static void main(String []args){
        String headUrl = "http://localhost:12345/static/base/img/member/1526463276709.jpg";
        System.out.println(headUrl.substring(headUrl.lastIndexOf("/")+1));
    }
}
