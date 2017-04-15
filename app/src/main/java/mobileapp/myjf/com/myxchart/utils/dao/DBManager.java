package mobileapp.myjf.com.myxchart.utils.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import mobileapp.myjf.com.myxchart.entity.originaldata.TimeLineRemote;
import mobileapp.myjf.com.myxchart.entity.util.KLineData;
import mobileapp.myjf.com.myxchart.greendao.gen.DaoMaster;
import mobileapp.myjf.com.myxchart.greendao.gen.DaoSession;
import mobileapp.myjf.com.myxchart.greendao.gen.KLineDataDao;
import mobileapp.myjf.com.myxchart.greendao.gen.TimeLineRemoteDao;
import mobileapp.myjf.com.myxchart.utils.global.Variable;

/**
 * Created by gwx
 */

public class DBManager {

    private Context context;
    private DaoMaster.DevOpenHelper openHelper;
    private static DBManager instance;

    private final static String dbName = Variable.getOrganizationCode() + Variable.getProductCode();

    private DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DBManager.class) {
                if (instance == null) {
                    instance = new DBManager(context);
                }
            }
        }

        return instance;
    }

    /**
     * 获取可读数据库
     *
     * @return
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName);
        }

        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     *
     * @return
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName);
        }

        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    /**
     * 插入一条分时线数据
     *
     * @param timeLineRemote
     */

    public void insertTimeLineRemote(TimeLineRemote timeLineRemote) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        TimeLineRemoteDao timeLineRemoteDao = daoMaster.newSession().getTimeLineRemoteDao();
        timeLineRemoteDao.insert(timeLineRemote);
    }

    /**
     * 插入一条K线数据
     *
     * @param kLineData
     */

    public void insertKLineData(KLineData kLineData) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        KLineDataDao kLineDataDao = daoMaster.newSession().getKLineDataDao();
        kLineData.setId(null);
        kLineDataDao.insert(kLineData);
    }

    /**
     * 插入分时线数据列表
     *
     * @param
     */
    public void insertTimeLineRemoteList(List<TimeLineRemote> timeLineRemotes) {
        if (timeLineRemotes == null || timeLineRemotes.isEmpty()) return;

        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        TimeLineRemoteDao timeLineRemotesDao = daoSession.getTimeLineRemoteDao();
        timeLineRemotesDao.insertInTx(timeLineRemotes);
    }

    /**
     * 插入K线数据列表
     *
     * @param
     */
    public void insertKLineDataList(List<KLineData> kLineDatas) {
        if (kLineDatas == null || kLineDatas.isEmpty()) return;

        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        KLineDataDao kLineDatasDao = daoSession.getKLineDataDao();
        kLineDatasDao.insertInTx(kLineDatas);
    }

    /**
     * 通过时间戳删除一条分时线数据
     *
     * @param
     */
    public void deleteTimeLineRemote(TimeLineRemote timeLineRemote) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        TimeLineRemoteDao timeLineRemoteDao = daoMaster.newSession().getTimeLineRemoteDao();
        TimeLineRemote dbTimeLineRemote = timeLineRemoteDao.queryBuilder().where(TimeLineRemoteDao.Properties.OpenTime.eq(timeLineRemote.getOpenTime())).build().unique();
        if(timeLineRemote != null){
            timeLineRemoteDao.deleteByKey(timeLineRemote.getId());
        }
    }

    /**
     * 通过时间戳和类型删除一条K线数据
     *
     * @param
     */
    public void deleteKLineData(KLineData kLineData) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        KLineDataDao kLineDataDao = daoMaster.newSession().getKLineDataDao();
        KLineData dbkLineData = kLineDataDao.queryBuilder().where(KLineDataDao.Properties.Type.eq(kLineData.getType()),KLineDataDao.Properties.Time.eq(kLineData.getTime())).build().unique();
        if(kLineData != null){
            kLineDataDao.deleteByKey(kLineData.getId());
        }
    }

    /**
     * 通过时间戳更新一条分时线数据（若不存在则插入）
     *
     * @param timeLineRemote
     */
    public void updateTimeLineRemote(TimeLineRemote timeLineRemote) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        TimeLineRemoteDao timeLineRemoteDao = daoMaster.newSession().getTimeLineRemoteDao();
        TimeLineRemote dbTimeLineRemote = timeLineRemoteDao.queryBuilder().where(TimeLineRemoteDao.Properties.OpenTime.eq(timeLineRemote.getOpenTime())).build().list().get(0);
        if(dbTimeLineRemote != null){
            dbTimeLineRemote.setClose(timeLineRemote.getClose());
            dbTimeLineRemote.setOpenTime(timeLineRemote.getOpenTime());
            timeLineRemoteDao.update(dbTimeLineRemote);
        }else{
            insertTimeLineRemote(timeLineRemote);
        }
    }

    /**
     * 通过时间戳与类型更新一条K线数据(若不存在则插入)
     *
     * @param kLineData
     */
    public void updateKLineData(KLineData kLineData) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        KLineDataDao kLineDataDao = daoMaster.newSession().getKLineDataDao();
        KLineData dbKLineData = kLineDataDao.queryBuilder().where(KLineDataDao.Properties.Time.eq(kLineData.getTime()),KLineDataDao.Properties.Type.eq(kLineData.getType())).build().list().get(0);
        if(dbKLineData != null){
            dbKLineData.setClose(kLineData.getClose());
            dbKLineData.setOpen(kLineData.getOpen());
            dbKLineData.setHigh(kLineData.getHigh());
            dbKLineData.setLow(kLineData.getLow());
            dbKLineData.setTime(kLineData.getTime());
            dbKLineData.setMa5(kLineData.getMa5());
            dbKLineData.setMa10(kLineData.getMa10());
            dbKLineData.setMa30(kLineData.getMa30());
            dbKLineData.setBias1(kLineData.getBias1());
            dbKLineData.setBias2(kLineData.getBias2());
            dbKLineData.setBias3(kLineData.getBias3());
            dbKLineData.setRsi1(kLineData.getRsi1());
            dbKLineData.setRsi2(kLineData.getRsi2());
            dbKLineData.setRsi3(kLineData.getRsi3());
            dbKLineData.setMacd(kLineData.getMacd());
            dbKLineData.setMacd_dea(kLineData.getMacd_dea());
            dbKLineData.setMacd_dif(kLineData.getMacd_dif());
            dbKLineData.setKdj_d(kLineData.getKdj_d());
            dbKLineData.setKdj_k(kLineData.getKdj_k());
            dbKLineData.setKdj_j(kLineData.getKdj_j());
            kLineDataDao.update(dbKLineData);
        }else {
            insertKLineData(kLineData);
        }
    }


    /**
     * 按照openTime排序并获取分时线数据
     *
     * @param
     * @return
     */

    public List<TimeLineRemote> queryTimeLineRemotes()

    {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        TimeLineRemoteDao timeLineRemoteDao = daoMaster.newSession().getTimeLineRemoteDao();

        return timeLineRemoteDao.queryBuilder()
                .orderAsc(TimeLineRemoteDao.Properties.OpenTime)
                .list();
    }

    /**
     * 按照openTime排序并获取K线数据
     *
     * @param
     * @return
     */

    public List<KLineData> queryKLineDatas(String type)

    {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        KLineDataDao kLineDataDao = daoMaster.newSession().getKLineDataDao();

        return kLineDataDao.queryBuilder()
                .where(KLineDataDao.Properties.Type.eq(type))
                .orderAsc(KLineDataDao.Properties.Time)
                .list();
    }

    /**
     * 删除全部数据
     */
    public void deleteAllDatas(){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        KLineDataDao kLineDataDao = daoMaster.newSession().getKLineDataDao();
        TimeLineRemoteDao timeLineRemoteDao = daoMaster.newSession().getTimeLineRemoteDao();
        kLineDataDao.deleteAll();
        timeLineRemoteDao.deleteAll();
    }
}