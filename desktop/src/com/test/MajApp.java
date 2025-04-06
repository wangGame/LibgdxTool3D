package com.test;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class MajApp {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.stencil=8;
        config.y = 0;
        config.height = (int) (1920 * 0.5f);
        config.width = (int) (1080 * 0.8f);
        new LwjglApplication(new MajGame(), config);

//        int max = Integer.MIN_VALUE;
//        int min = Integer.MAX_VALUE;
//        for (int i = 0; i < 100; i++) {
//            int v = (int)(Math.random() * 26 + 1);
//            if (max < v){
//                max = v;
//            }
//            if (min > v){
//                min = v;
//            }
//            System.out.println(v);
//        }
//        System.out.println(max +"   "+min);

    }
}
