package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {

  List<String> list1 = new ArrayList<>();
        String[] str = new String[] { "you", "wu" };
        List list = Arrays.asList(str);
        str[0]="test";
        System.out.println(str[0]);
    }
}
