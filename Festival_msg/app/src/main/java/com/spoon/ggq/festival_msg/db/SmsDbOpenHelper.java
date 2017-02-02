package com.spoon.ggq.festival_msg.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.spoon.ggq.festival_msg.bean.SendMsg;

/**
 * Created by ggq on 2017/2/1.
 */
public class SmsDbOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="sms.db";
    private static final int DB_VERSION=1;
    private SmsDbOpenHelper(Context context) {
        super(context.getApplicationContext(), DB_NAME, null, DB_VERSION);
    }

    private static SmsDbOpenHelper mHelper;

    public static  SmsDbOpenHelper getInstance(Context context){

        if (mHelper==null)
        {
            synchronized (SmsDbOpenHelper.class)
            {
                if(mHelper==null)
                {
                    mHelper=new SmsDbOpenHelper(context);
                }
            }
        }
        return mHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("===============创建数据库=============");
        String sql="create table "+ SendMsg.TABLE_NAME+" ( "+
                " _id integer primary key autoincrement ,"+
                SendMsg.COULM_DATE+" integer , "+
                SendMsg.COULM_FES_NAME+" text , "+
                SendMsg.COULM_MSG+" text , "+
                SendMsg.COULM_NAMES+" text , "+
                SendMsg.COULM_NUMBERS+" text "+
                " ) ";
        System.out.println("========="+sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
