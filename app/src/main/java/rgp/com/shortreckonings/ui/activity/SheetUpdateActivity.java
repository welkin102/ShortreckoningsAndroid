package rgp.com.shortreckonings.ui.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import rgp.com.shortreckonings.R;
import rgp.com.shortreckonings.adapter.TabsStateAdapter;
import rgp.com.shortreckonings.model.Person;
import rgp.com.shortreckonings.ui.fragment.AddExpenseFragment;
import rgp.com.shortreckonings.ui.fragment.AddPersonFragment;
import rgp.com.shortreckonings.ui.fragment.CalculateFragment;
import rgp.com.shortreckonings.ui.fragment.ExpensesFragment;
import rgp.com.shortreckonings.ui.fragment.GroupFragment;

public class SheetUpdateActivity extends AppCompatActivity implements View.OnClickListener, AddPersonFragment.AddPersonFragListner, AddExpenseFragment.AddExpenseFragListner {

    private FloatingActionButton mFab;
    private ViewPager view_pager;
    private TabLayout mTabLayout;
    private LinearLayout linearLayout;
    private Toolbar toolbar;
    private TabsStateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_update);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(this);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        linearLayout = (LinearLayout) findViewById(R.id.frag_container);
        view_pager = (ViewPager) findViewById(R.id.pager);
        adapter = new TabsStateAdapter(getSupportFragmentManager());
        view_pager.setAdapter(adapter);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTabLayout.setupWithViewPager(view_pager);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 2) {
                    mFab.setVisibility(View.GONE);
                } else mFab.setVisibility(View.VISIBLE);
                view_pager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void renderFragment(Fragment fragment, String key, String value) {
        if (value != null) {
            Bundle bundle = new Bundle();
            bundle.putString(key, value);
            fragment.setArguments(bundle);
        }
        hideTabPager();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frag_container, fragment, fragment.getClass().getName());
        transaction.commitAllowingStateLoss();
//        transaction.commit();
    }

    private void removeFragment(String tag) {

        if(getSupportFragmentManager().findFragmentByTag(tag) != null) {
            getSupportFragmentManager()
                    .beginTransaction().
                    remove(getSupportFragmentManager().findFragmentByTag(tag)).commit();
        }
        showTabPager();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                int tab = mTabLayout.getSelectedTabPosition();
                if (tab == 0) {
                    renderFragment(new AddPersonFragment(), null, null);
                } else if (tab == 1) {
                    renderFragment(new AddExpenseFragment(), null, null);
                }
        }
    }

    private void hideTabPager(){
        mFab.setVisibility(View.GONE);
        view_pager.setVisibility(View.GONE);
        mTabLayout.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);

    }

    private void showTabPager(){
        view_pager.setVisibility(View.VISIBLE);
        mTabLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
        mFab.setVisibility(View.VISIBLE);

    }

    @Override
    public void onCancel(String tag) {
        removeFragment(tag);
        view_pager.setAdapter(adapter);
    }

    @Override
    public void onReset() {
        view_pager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sheet_update_activity, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.download) {
            Toast.makeText(SheetUpdateActivity.this, "Download Sheet", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
