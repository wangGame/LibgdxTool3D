package com.kitchen.data;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

public class KitchenBuju {
    public ArrayMap<Integer,Array<Integer>> buju;
    public KitchenBuju(){
        buju = new ArrayMap<>();
        Array<Integer> left = new Array<>();
        left.add(0);
        left.add(0);
        left.add(0);
        left.add(0);
        left.add(1);//垃圾桶
        left.add(0);
        left.add(0);
        buju.put(1,left);
        Array<Integer> right = new Array<>();
        right.add(0);
        right.add(0);
        right.add(0);
        right.add(0);
        right.add(2);//盘子
        right.add(3);//出餐
        right.add(0);
        buju.put(3,right);
        Array<Integer> forward = new Array<>();
        forward.add(4);//煤气
        forward.add(5);//meat
        forward.add(6);//cheese
        forward.add(0);
        forward.add(10);//盘子
        forward.add(7);//面包
        forward.add(8);//cabbage
        forward.add(9);//cut
        buju.put(2,forward);
        Array<Integer> back = new Array<>();
        back.add(0);
        back.add(0);
        back.add(4);//煤气
        back.add(9);//cut
        back.add(0);//垃圾桶
        back.add(1);
        back.add(0);//cabbage
        back.add(0);//cut
        buju.put(4,back);

    }

    public ArrayMap<Integer, Array<Integer>> getBuju() {
        return buju;
    }
}
