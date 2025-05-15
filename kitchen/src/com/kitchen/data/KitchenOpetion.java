package com.kitchen.data;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.kitchen.content.Content;

public class KitchenOpetion {
    public static ArrayMap<Integer,Array<Integer>> caiDan = new ArrayMap<>();
    static {
        caiDan.clear();
        {
            Array<Integer> a = new Array<>();
            a.add(Content.BREAD);
            caiDan.put(1, a);
        }
        {
            Array<Integer> a = new Array<>();
            a.add(Content.BREAD);
            a.add(Content.CABBAGE);
            caiDan.put(2, a);
        }
        {
            Array<Integer> a = new Array<>();
            a.add(Content.BREAD);
            a.add(Content.CABBAGE);
            a.add(Content.CHEESE);
            caiDan.put(3, a);
        }
        {
            Array<Integer> a = new Array<>();
            a.add(Content.BREAD);
            a.add(Content.CABBAGE);
            a.add(Content.CHEESE);
            a.add(Content.MEAT);
            caiDan.put(4, a);
        }
        {
            Array<Integer> a = new Array<>();
            a.add(Content.BREAD);
            a.add(Content.CABBAGE);
            a.add(Content.CHEESE);
            a.add(Content.MEAT);
            a.add(Content.TOMATO);
            caiDan.put(5, a);
        }

    }
}
