package mobileapp.myjf.com.myxchart.data.global;


import java.util.HashMap;
import java.util.Map;

import mobileapp.myjf.com.myxchart.data.entity.localdata.KLineLocal;

public class Cache {

    // TODO 用于模拟本地缓存的键值对，后续要换成数据库
    private static Map<String,KLineLocal> kLineLocals = new HashMap<>();

    // 构造方法，第一次使用该类时初始化一些信息
    public Cache(){
        String[] types = new String[]{"", "Day", "60", "Week", "Month", "1", "5", "30", "240"};
        KLineLocal fenshi = null;
        KLineLocal rik = null;
        KLineLocal fen60 = null;
        KLineLocal zhouk = null;
        KLineLocal yuek = null;
        KLineLocal fen1 = null;
        KLineLocal fen5 = null;
        KLineLocal fen30 = null;
        KLineLocal fen240 = null;
        kLineLocals.put(types[0],fenshi);
        kLineLocals.put(types[1],rik);
        kLineLocals.put(types[2],fen60);
        kLineLocals.put(types[3],zhouk);
        kLineLocals.put(types[4],yuek);
        kLineLocals.put(types[5],fen1);
        kLineLocals.put(types[6],fen5);
        kLineLocals.put(types[7],fen30);
        kLineLocals.put(types[8],fen240);

    }

    public static Map<String,KLineLocal> getkLineLocals() {
        return kLineLocals;
    }

    public static void setkLineLocals(Map<String,KLineLocal> kLineLocals) {
        Cache.kLineLocals = kLineLocals;
    }
}
