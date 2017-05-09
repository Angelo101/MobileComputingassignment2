/**
 * Created by noobtube on 9/05/2017.
 *
 */
package com.example.noobtube.memorygame;

class Background {
    static void run(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }
}