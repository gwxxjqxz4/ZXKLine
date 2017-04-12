package mobileapp.myjf.com.myxchart.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import mobileapp.myjf.com.myxchart.entity.originaldata.AddKLineRemote;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ADD_KLINE_REMOTE".
*/
public class AddKLineRemoteDao extends AbstractDao<AddKLineRemote, Long> {

    public static final String TABLENAME = "ADD_KLINE_REMOTE";

    /**
     * Properties of entity AddKLineRemote.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Low = new Property(1, double.class, "low", false, "LOW");
        public final static Property High = new Property(2, double.class, "high", false, "HIGH");
        public final static Property Time = new Property(3, long.class, "time", false, "TIME");
        public final static Property OpenPrice = new Property(4, double.class, "openPrice", false, "OPEN_PRICE");
        public final static Property ClosePrice = new Property(5, double.class, "closePrice", false, "CLOSE_PRICE");
        public final static Property Ma5 = new Property(6, double.class, "ma5", false, "MA5");
        public final static Property Ma10 = new Property(7, double.class, "ma10", false, "MA10");
        public final static Property Ma30 = new Property(8, double.class, "ma30", false, "MA30");
        public final static Property Macd = new Property(9, double.class, "macd", false, "MACD");
        public final static Property Macd_dif = new Property(10, double.class, "macd_dif", false, "MACD_DIF");
        public final static Property Macd_dea = new Property(11, double.class, "macd_dea", false, "MACD_DEA");
        public final static Property Rsi1 = new Property(12, double.class, "rsi1", false, "RSI1");
        public final static Property Rsi2 = new Property(13, double.class, "rsi2", false, "RSI2");
        public final static Property Rsi3 = new Property(14, double.class, "rsi3", false, "RSI3");
        public final static Property Bias1 = new Property(15, double.class, "bias1", false, "BIAS1");
        public final static Property Bias2 = new Property(16, double.class, "bias2", false, "BIAS2");
        public final static Property Bias3 = new Property(17, double.class, "bias3", false, "BIAS3");
        public final static Property Kdj_d = new Property(18, double.class, "kdj_d", false, "KDJ_D");
        public final static Property Kdj_k = new Property(19, double.class, "kdj_k", false, "KDJ_K");
        public final static Property Kdj_j = new Property(20, double.class, "kdj_j", false, "KDJ_J");
    };


    public AddKLineRemoteDao(DaoConfig config) {
        super(config);
    }
    
    public AddKLineRemoteDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ADD_KLINE_REMOTE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"LOW\" REAL NOT NULL ," + // 1: low
                "\"HIGH\" REAL NOT NULL ," + // 2: high
                "\"TIME\" INTEGER NOT NULL ," + // 3: time
                "\"OPEN_PRICE\" REAL NOT NULL ," + // 4: openPrice
                "\"CLOSE_PRICE\" REAL NOT NULL ," + // 5: closePrice
                "\"MA5\" REAL NOT NULL ," + // 6: ma5
                "\"MA10\" REAL NOT NULL ," + // 7: ma10
                "\"MA30\" REAL NOT NULL ," + // 8: ma30
                "\"MACD\" REAL NOT NULL ," + // 9: macd
                "\"MACD_DIF\" REAL NOT NULL ," + // 10: macd_dif
                "\"MACD_DEA\" REAL NOT NULL ," + // 11: macd_dea
                "\"RSI1\" REAL NOT NULL ," + // 12: rsi1
                "\"RSI2\" REAL NOT NULL ," + // 13: rsi2
                "\"RSI3\" REAL NOT NULL ," + // 14: rsi3
                "\"BIAS1\" REAL NOT NULL ," + // 15: bias1
                "\"BIAS2\" REAL NOT NULL ," + // 16: bias2
                "\"BIAS3\" REAL NOT NULL ," + // 17: bias3
                "\"KDJ_D\" REAL NOT NULL ," + // 18: kdj_d
                "\"KDJ_K\" REAL NOT NULL ," + // 19: kdj_k
                "\"KDJ_J\" REAL NOT NULL );"); // 20: kdj_j
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ADD_KLINE_REMOTE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AddKLineRemote entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindDouble(2, entity.getLow());
        stmt.bindDouble(3, entity.getHigh());
        stmt.bindLong(4, entity.getTime());
        stmt.bindDouble(5, entity.getOpenPrice());
        stmt.bindDouble(6, entity.getClosePrice());
        stmt.bindDouble(7, entity.getMa5());
        stmt.bindDouble(8, entity.getMa10());
        stmt.bindDouble(9, entity.getMa30());
        stmt.bindDouble(10, entity.getMacd());
        stmt.bindDouble(11, entity.getMacd_dif());
        stmt.bindDouble(12, entity.getMacd_dea());
        stmt.bindDouble(13, entity.getRsi1());
        stmt.bindDouble(14, entity.getRsi2());
        stmt.bindDouble(15, entity.getRsi3());
        stmt.bindDouble(16, entity.getBias1());
        stmt.bindDouble(17, entity.getBias2());
        stmt.bindDouble(18, entity.getBias3());
        stmt.bindDouble(19, entity.getKdj_d());
        stmt.bindDouble(20, entity.getKdj_k());
        stmt.bindDouble(21, entity.getKdj_j());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AddKLineRemote entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindDouble(2, entity.getLow());
        stmt.bindDouble(3, entity.getHigh());
        stmt.bindLong(4, entity.getTime());
        stmt.bindDouble(5, entity.getOpenPrice());
        stmt.bindDouble(6, entity.getClosePrice());
        stmt.bindDouble(7, entity.getMa5());
        stmt.bindDouble(8, entity.getMa10());
        stmt.bindDouble(9, entity.getMa30());
        stmt.bindDouble(10, entity.getMacd());
        stmt.bindDouble(11, entity.getMacd_dif());
        stmt.bindDouble(12, entity.getMacd_dea());
        stmt.bindDouble(13, entity.getRsi1());
        stmt.bindDouble(14, entity.getRsi2());
        stmt.bindDouble(15, entity.getRsi3());
        stmt.bindDouble(16, entity.getBias1());
        stmt.bindDouble(17, entity.getBias2());
        stmt.bindDouble(18, entity.getBias3());
        stmt.bindDouble(19, entity.getKdj_d());
        stmt.bindDouble(20, entity.getKdj_k());
        stmt.bindDouble(21, entity.getKdj_j());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public AddKLineRemote readEntity(Cursor cursor, int offset) {
        AddKLineRemote entity = new AddKLineRemote( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getDouble(offset + 1), // low
            cursor.getDouble(offset + 2), // high
            cursor.getLong(offset + 3), // time
            cursor.getDouble(offset + 4), // openPrice
            cursor.getDouble(offset + 5), // closePrice
            cursor.getDouble(offset + 6), // ma5
            cursor.getDouble(offset + 7), // ma10
            cursor.getDouble(offset + 8), // ma30
            cursor.getDouble(offset + 9), // macd
            cursor.getDouble(offset + 10), // macd_dif
            cursor.getDouble(offset + 11), // macd_dea
            cursor.getDouble(offset + 12), // rsi1
            cursor.getDouble(offset + 13), // rsi2
            cursor.getDouble(offset + 14), // rsi3
            cursor.getDouble(offset + 15), // bias1
            cursor.getDouble(offset + 16), // bias2
            cursor.getDouble(offset + 17), // bias3
            cursor.getDouble(offset + 18), // kdj_d
            cursor.getDouble(offset + 19), // kdj_k
            cursor.getDouble(offset + 20) // kdj_j
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AddKLineRemote entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLow(cursor.getDouble(offset + 1));
        entity.setHigh(cursor.getDouble(offset + 2));
        entity.setTime(cursor.getLong(offset + 3));
        entity.setOpenPrice(cursor.getDouble(offset + 4));
        entity.setClosePrice(cursor.getDouble(offset + 5));
        entity.setMa5(cursor.getDouble(offset + 6));
        entity.setMa10(cursor.getDouble(offset + 7));
        entity.setMa30(cursor.getDouble(offset + 8));
        entity.setMacd(cursor.getDouble(offset + 9));
        entity.setMacd_dif(cursor.getDouble(offset + 10));
        entity.setMacd_dea(cursor.getDouble(offset + 11));
        entity.setRsi1(cursor.getDouble(offset + 12));
        entity.setRsi2(cursor.getDouble(offset + 13));
        entity.setRsi3(cursor.getDouble(offset + 14));
        entity.setBias1(cursor.getDouble(offset + 15));
        entity.setBias2(cursor.getDouble(offset + 16));
        entity.setBias3(cursor.getDouble(offset + 17));
        entity.setKdj_d(cursor.getDouble(offset + 18));
        entity.setKdj_k(cursor.getDouble(offset + 19));
        entity.setKdj_j(cursor.getDouble(offset + 20));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(AddKLineRemote entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(AddKLineRemote entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
