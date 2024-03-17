package edu.spi.impl;

import edu.spi.api.Logger;

public class Logback implements Logger {

    @Override
    public void info(String s) {
        System.out.println("Logback info 打印日志：" + s);
    }

    @Override
    public void debug(String s) {
        System.out.println("Logback debug 打印日志：" + s);
    }
}

