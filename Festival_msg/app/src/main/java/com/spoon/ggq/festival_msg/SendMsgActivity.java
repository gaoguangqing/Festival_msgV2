package com.spoon.ggq.festival_msg;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.spoon.ggq.festival_msg.bean.Festival;
import com.spoon.ggq.festival_msg.bean.FestivalLab;
import com.spoon.ggq.festival_msg.bean.Msg;
import com.spoon.ggq.festival_msg.bean.SendMsg;
import com.spoon.ggq.festival_msg.biz.SmsBiz;
import com.spoon.ggq.festival_msg.view.FlowLayout;

import java.util.HashSet;

public class SendMsgActivity extends AppCompatActivity {

    public static final String KEY_FESTIVAL_ID="festivalId";
    public static final String KEY_MSG_ID="msgId";
    private int mFestivalId;
    private int msgId;

    private Festival mFestival;
    private Msg mMsg;

    private EditText mEdMsg;
    private Button mBtnAdd;
    private FlowLayout mFlContacts;
    private FloatingActionButton mFabSend;
    private View mLayoutLoading;
    private static final int CODE_REQUEST=1;


    private HashSet<String> mContactNames =new HashSet<>();
    private HashSet<String> mContactNums=new HashSet<>();


    private LayoutInflater mInflater;


    public static  final String ACTION_SEND_MSG="ACTION_SEND_MSG";

    public static  final String ACTION_DELIVER_MSG="ACTION_DELIVER_MSG";

    private PendingIntent mSendPi;
    private PendingIntent mDeliverPi;

    private BroadcastReceiver mSendBroadcastReceiver;
    private BroadcastReceiver mDeliverBroadcastReceiver;


    private SmsBiz mSmsBiz;

    //短信接收成功
    private  int mMsgSendCount;


    private int mTotalCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_msg);

        mInflater=LayoutInflater.from(this);
        mSmsBiz=new SmsBiz(this);
        initDatas();
        
        initViews();

        initEvents();
        
        initReceivers();

    }

    private void initReceivers() {
        Intent sendIntent=new Intent(ACTION_SEND_MSG);
        mSendPi=PendingIntent.getBroadcast(this,0,sendIntent,0);
        Intent deliverIntent=new Intent(ACTION_DELIVER_MSG);
        mDeliverPi=PendingIntent.getBroadcast(this,0,deliverIntent,0);

        registerReceiver(mSendBroadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mMsgSendCount++;
                if(getResultCode()==RESULT_OK){

                }
                else {
                    Toast.makeText(SendMsgActivity.this,"发送失败",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(SendMsgActivity.this,(mMsgSendCount+"/"+mTotalCount)+"短信发送成功",Toast.LENGTH_SHORT).show();

                if(mMsgSendCount==mTotalCount){finish();}
            }
        },new IntentFilter(ACTION_SEND_MSG));


        registerReceiver(mDeliverBroadcastReceiver =new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(getResultCode()==RESULT_OK){

                    Toast.makeText(SendMsgActivity.this,"联系人已经收到短信",Toast.LENGTH_SHORT).show();
                }
                else {

                    Toast.makeText(SendMsgActivity.this,"联系人没收到短信",Toast.LENGTH_SHORT).show();
                }
            }
        },new IntentFilter(ACTION_DELIVER_MSG));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mSendBroadcastReceiver);
        unregisterReceiver(mDeliverBroadcastReceiver);
    }

    //初始化事件
    private void initEvents() {
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent,CODE_REQUEST);
            }
        });

        mFabSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContactNums.size()==0){
                    Toast.makeText(SendMsgActivity.this,"请选择联系人",Toast.LENGTH_SHORT).show();
                    return;
                }
                String msg=mEdMsg.getText().toString();
                if(TextUtils.isEmpty(msg)){
                    Toast.makeText(SendMsgActivity.this,"短信内容不能为空",Toast.LENGTH_SHORT).show();
                    return;

                }
                mLayoutLoading.setVisibility(View.VISIBLE);
                mTotalCount=mSmsBiz.sendMsg(mContactNums,buildSendMsg(msg),mSendPi,mDeliverPi);
                mMsgSendCount=0;
            }
        });
    }

    private SendMsg buildSendMsg(String msg) {
        SendMsg sendMsg=new SendMsg();
        sendMsg.setMsg(msg);
        sendMsg.setFestivalName(mFestival.getName());
        String names="";
        for (String name: mContactNames){
            names+=name+":";
        }

        String numbers="";
        for (String number: mContactNums){
            numbers+=number+":";
        }
        sendMsg.setNames(names.substring(0,names.length()-1));
        sendMsg.setNumbers(numbers.substring(0,numbers.length()-1));
        return sendMsg;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CODE_REQUEST){
            if (resultCode==RESULT_OK){
                Uri contactUri=data.getData();
                Cursor cursor=getContentResolver().query(contactUri,null,null,null,null);
                cursor.moveToFirst();
                String contactName=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


                String number=getContactNumber(cursor);


                if(!TextUtils.isEmpty(number))
                {
                    mContactNums.add(number);
                    mContactNames.add(contactName);

                    addTag(contactName);
                }

            }
            else {
                Toast.makeText(SendMsgActivity.this,"失败",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addTag(String contactName) {
        TextView view= (TextView) mInflater.inflate(R.layout.tag,mFlContacts,false);
        view.setText(contactName);
        mFlContacts.addView(view);
    }

    private String getContactNumber(Cursor cursor) {
        int numberCount=cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
        String number=null;

        if(numberCount>0)
        {
            int contactId=cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));

            Cursor phoneCursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,  ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ " = " + contactId,   null, null);
            phoneCursor.moveToFirst();
             number=phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            phoneCursor.close();
        }
        cursor.close();
        return number;
    }


    private void initViews() {
        mEdMsg= (EditText) findViewById(R.id.id_et_content);
        mBtnAdd= (Button) findViewById(R.id.id_btn_add);
        mFlContacts= (FlowLayout) findViewById(R.id.id_fl_contacts);
        mFabSend= (FloatingActionButton) findViewById(R.id.id_fab_send);
        mLayoutLoading=findViewById(R.id.id_layout_loading);
        mLayoutLoading.setVisibility(View.GONE);
        if(msgId!=-1)
        {
            mMsg=(FestivalLab.getInstance().getMsgsByFestivalId(mFestivalId)).get(msgId-1);
            mEdMsg.setText(mMsg.getContent());
        }
    }

    private void initDatas() {
        mFestivalId=getIntent().getIntExtra(KEY_FESTIVAL_ID,-1);
        msgId=getIntent().getIntExtra(KEY_MSG_ID,-1);

        mFestival=FestivalLab.getInstance().getFestivalById(mFestivalId);
        setTitle(mFestival.getName());
    }

    public static void toActivity(Context context, int festivalId, int msgId){
        Intent intent=new Intent(context,SendMsgActivity.class);
        intent.putExtra(KEY_FESTIVAL_ID,festivalId);
        intent.putExtra(KEY_MSG_ID,msgId);
        context.startActivity(intent);


    }
}
