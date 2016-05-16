package rgp.com.shortreckonings.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import rgp.com.shortreckonings.R;
import rgp.com.shortreckonings.ui.appconstants.Config;
import rgp.com.shortreckonings.ui.fragment.AppTuteFragment;

public class AppTutorialActivity extends AppCompatActivity implements OnClickListener {
    private MyPagerAdapter mAdapter;
    private int currentItem;
    private TextView mNext, mSkip;
    private ViewPager mPager;
    private Context context;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                if (AppTutorialActivity.this.mPager.getCurrentItem() != AppTutorialActivity.this.mPager.getAdapter().getCount() - 1) {
                    AppTutorialActivity.this.mPager.setCurrentItem(AppTutorialActivity.this.mPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(context, HomeActivity.class));
                    finish();
                }
                AppTutorialActivity.this.setNavigator();
                break;
            case R.id.skip:
                startActivity(new Intent(context, HomeActivity.class));
                finish();
                break;
        }
    }

    class PagerListener implements OnPageChangeListener {
        PagerListener() {
        }

        public void onPageSelected(int position) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int position) {
            if (AppTutorialActivity.this.mPager.getCurrentItem() == AppTutorialActivity.this.mPager.getAdapter().getCount() - 1) {
                AppTutorialActivity.this.mNext.setText("FINISH");
            } else {
                AppTutorialActivity.this.mNext.setText("NEXT");
            }
            AppTutorialActivity.this.setNavigator();
        }
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public CharSequence getPageTitle(int position) {
            return null;
        }

        public int getCount() {
            return 4;
        }

        public Fragment getItem(int position) {
            if (position == 0) {
                return AppTuteFragment.newInstance(position);
            }
            if (position == 1) {
                return AppTuteFragment.newInstance(position);
            }
            return AppTuteFragment.newInstance(position);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_tutorial);
        currentItem = 0;
        context = this;
        mPager = (ViewPager) findViewById(R.id.pager);
        mNext = (TextView) findViewById(R.id.next);
        mSkip = (TextView) findViewById(R.id.skip);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(this.mAdapter);
        mPager.setCurrentItem(this.currentItem);
        setNavigator();
        mPager.addOnPageChangeListener(new PagerListener());
        mNext.setOnClickListener(this);
        mSkip.setOnClickListener(this);
    }

    public void setNavigator() {
        // bottom dots to implement
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
