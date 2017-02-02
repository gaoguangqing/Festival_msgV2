package com.spoon.ggq.festival_msg.biz;

import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.telephony.SmsManager;

import com.spoon.ggq.festival_msg.bean.SendMsg;
import com.spoon.ggq.festival_msg.db.SmsProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by ggq on 2017/2/1.
 */
public class SmsBiz {
    private Context context;
    public SmsBiz(Context context){
        this.context=context;
    }
    public int sendMsg(String number, String msg, PendingIntent sentPi,PendingIntent deliverPi){
        SmsManager smsManager=SmsManager.getDefault();
        ArrayList<String> contents=smsManager.divideMessage(msg);
        for (String content:contents
                ) {
            smsManager.sendTextMessage(number,null,content,sentPi,deliverPi);
        }
        return contents.size();
    }

    public int sendMsg(Set<String> numbers, SendMsg msg, PendingIntent sentPi, PendingIntent deliverPi){
        save(msg);
        int result=0;
        for (String number:numbers
                ) {
            int count=sendMsg(number,msg.getMsg(),sentPi,deliverPi);
            result+=count;
        }
        return result;
    }
    private void save(SendMsg sendMsg){
        sendMsg.setDate(new Date());
        ContentValues values=new ContentValues();
        values.put(SendMsg.COULM_DATE,sendMsg.getDate().getTime());
        values.put(SendMsg.COULM_FES_NAME,sendMsg.getFestivalName());
        values.put(SendMsg.COULM_MSG,sendMsg.getMsg());
        values.put(SendMsg.COULM_NAMES,sendMsg.getNames());
        values.put(SendMsg.COULM_NUMBERS,sendMsg.getNumbers());

        context.getContentResolver().insert(SmsProvider.URI_SMS_ALL,values);


    }
}
