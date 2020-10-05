package com.nashtech.training.shape.utils;

import lombok.SneakyThrows;

public class LongRunning {

    @SneakyThrows
    public static void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
