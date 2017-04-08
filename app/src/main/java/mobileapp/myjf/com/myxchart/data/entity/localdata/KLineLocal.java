package mobileapp.myjf.com.myxchart.data.entity.localdata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import mobileapp.myjf.com.myxchart.data.entity.util.KLineData;

/**
 * Created by nethanhan on 2017/4/6.
 */

public class KLineLocal {

    private List<KLineData> kLineDatas;

    public KLineLocal() {}

    public List<KLineData> getkLineDatas() {
        return kLineDatas;
    }

    public void setkLineDatas(List<KLineData> kLineDatas) {
        this.kLineDatas = kLineDatas;
    }
}
