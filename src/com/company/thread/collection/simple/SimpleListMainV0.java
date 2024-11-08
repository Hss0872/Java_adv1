package com.company.thread.collection.simple;

import com.company.thread.collection.simple.list.BasicList;
import com.company.thread.collection.simple.list.SimpleList;

public class SimpleListMainV0 {

    public static void main(String[] args) {
        SimpleList list = new BasicList();

        list.add("A");
        list.add("B");
        System.out.println(list);
    }
}