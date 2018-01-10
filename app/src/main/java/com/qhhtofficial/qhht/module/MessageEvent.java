package com.qhhtofficial.qhht.module;

import java.util.ArrayList;

/**
 * Created by ehs_app_1 on 2018/1/10.
 */

public class MessageEvent {
    public ArrayList arrayList = new ArrayList();

    public MessageEvent(ArrayList arrayList) {
        this.arrayList = arrayList;
    }
    public MessageEvent(Object obj){
        this.arrayList.add(obj);
    }
    public MessageEvent(Object... objects){
        for (int i = 0; i < objects.length; i++) {
            arrayList.add(objects[i]);
        }
    }

    public Object getMessage(int idx){
        Object returnObj = null;
        if(arrayList.size()>idx){
            returnObj = arrayList.get(idx);
        }
        return returnObj;
    }
}
