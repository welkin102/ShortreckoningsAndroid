package rgp.com.shortreckonings.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.widget.Toast;

import rgp.com.shortreckonings.ui.fragment.CalculateFragment;
import rgp.com.shortreckonings.ui.fragment.ExpensesFragment;
import rgp.com.shortreckonings.ui.fragment.GroupFragment;

/**
 * Created by khyagupt on 14-05-2016.
 */
public class TabsStateAdapter extends FragmentPagerAdapter {

    int totalTabs;

    public TabsStateAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                GroupFragment group = new GroupFragment();
                return group;
            case 1:
                ExpensesFragment expenses = new ExpensesFragment();
                return expenses;
            case 2:
                CalculateFragment calculate = new CalculateFragment();
                return calculate;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "GROUP";
            case 1:
                return "EXPENSES";
            case 2:
                return "EXCHANGES";
            default:
                return null;
        }
    }
}
