package mobileapp.myjf.com.myxchart.data.global;


import java.util.HashMap;
import java.util.Map;

import mobileapp.myjf.com.myxchart.data.entity.localdata.KLineLocal;

public class Cache {

    private static Map<String,KLineLocal> kLineLocals = new HashMap<>();

    public Cache(){
        String[] types = new String[]{"fenshi","rik","60fen","zhouk","yuek","1fen","5fen","30fen","240fen"};
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
