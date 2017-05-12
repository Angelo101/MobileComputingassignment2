package com.example.noobtube.memorygame;

/**
 * Created by noobtube on 12/05/2017.
 */

class Background {
    static void run(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
