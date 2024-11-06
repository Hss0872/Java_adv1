package com.company.thread.sync;

import static com.company.util.MyLogger.log;

public class BankMain {

    public static void main(String[] args) {
        BankAccountV5 accountV5 = new BankAccountV5(1000);
        log("테스트");
    }
}
