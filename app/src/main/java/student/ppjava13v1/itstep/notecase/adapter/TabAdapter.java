package student.ppjava13v1.itstep.notecase.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import student.ppjava13v1.itstep.notecase.fragments.CurrentTaskFragment;
import student.ppjava13v1.itstep.notecase.fragments.DoneTaskFragment;

/**
 * Created by Admin on 12.09.2015.
 */
public class TabAdapter extends FragmentStatePagerAdapter {

    private int countOfTabs;

    public TabAdapter(FragmentManager fm, int countOfTabs) {
        super(fm);
        this.countOfTabs = countOfTabs;

    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new CurrentTaskFragment();
            case 1:
                return new DoneTaskFragment();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return countOfTabs;
    }
}
