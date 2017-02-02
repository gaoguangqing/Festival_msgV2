package com.spoon.ggq.festival_msg.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.spoon.ggq.festival_msg.R;
import com.spoon.ggq.festival_msg.bean.SendMsg;
import com.spoon.ggq.festival_msg.db.SmsProvider;
import com.spoon.ggq.festival_msg.view.FlowLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by ggq on 2017/2/2.
 */
public class SmsHistoryFragment extends ListFragment{


    private static final int LOADER_ID=1;
    private LayoutInflater mInflater;
    private CursorAdapter mCursorAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mInflater=LayoutInflater.from(getActivity());
        initLoader();
        setupListAdapter();
        System.out.println("=================oncreate==================");
    }

    private void setupListAdapter() {
        mCursorAdapter=new CursorAdapter(getActivity(),null,false) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
               View view= mInflater.inflate(R.layout.item_send_msg,parent,false);
                return view;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView msg= (TextView) view.findViewById(R.id.id_tv_msg);
                FlowLayout fl= (FlowLayout) view.findViewById(R.id.id_fl_contacts);
                TextView fes= (TextView) view.findViewById(R.id.id_tv_fes);
                TextView date= (TextView) view.findViewById(R.id.id_tv_date);

                msg.setText(cursor.getString(cursor.getColumnIndex(SendMsg.COULM_MSG)));
                fes.setText(cursor.getString(cursor.getColumnIndex(SendMsg.COULM_FES_NAME)));
                long dateVal=cursor.getLong(cursor.getColumnIndex(SendMsg.COULM_DATE));
                date.setText(parseDate(dateVal));

                String names=cursor.getString(cursor.getColumnIndex(SendMsg.COULM_NAMES));

                if(TextUtils.isEmpty(names))
                {
                    Toast.makeText(getActivity(),"无记录，快去发送祝福吧！",Toast.LENGTH_SHORT).show();
                    return;
                }

                fl.removeAllViews();
                for (String name:names.split(":")){
                    addTag(name,fl);
                }


            }
        };
        setListAdapter(mCursorAdapter);
    }

    DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private String parseDate(long dateVal) {

        return  df.format(dateVal);
    }

    private void addTag(String name, FlowLayout fl) {
        TextView tv= (TextView) mInflater.inflate(R.layout.tag,fl,false);
        tv.setText(name);
        fl.addView(tv);

    }

    private void initLoader() {
        getLoaderManager().initLoader(LOADER_ID,null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                CursorLoader loader=new CursorLoader(getActivity(), SmsProvider.URI_SMS_ALL,null,null,null,null);
                return loader;
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

                if (loader.getId()==LOADER_ID){
                    mCursorAdapter.swapCursor(data);
                }
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                mCursorAdapter.swapCursor(null);
            }
        });
    }
}
