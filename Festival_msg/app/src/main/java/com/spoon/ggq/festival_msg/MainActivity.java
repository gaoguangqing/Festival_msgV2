package com.spoon.ggq.festival_msg;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

//ctrl+shift+space

import com.spoon.ggq.festival_msg.fragment.FestivalCategoryFragment;
import com.spoon.ggq.festival_msg.fragment.SmsHistoryFragment;

import static com.spoon.ggq.festival_msg.R.id.id_tabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private String[] mTitles=new String[]{"节日短信","发送记录"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        mTabLayout= (TabLayout) findViewById(id_tabLayout);
        mViewPager= (ViewPager) findViewById(R.id.id_viewPager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position==1) {
                  return   new SmsHistoryFragment();
                }
                return new FestivalCategoryFragment();
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            finish();

        }
        return super.onKeyDown(keyCode, event);

    }
}
