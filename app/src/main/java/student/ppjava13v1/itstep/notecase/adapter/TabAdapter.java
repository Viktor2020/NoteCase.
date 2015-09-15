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

    public static final int CURRENT_TASK_FRAGMENT_POSITION = 0;
    public static final int DONE_TASK_FRAGMENT_POSITION = 1;

    private CurrentTaskFragment currentTaskFragment;
    private DoneTaskFragment doneTaskFragment;

    public TabAdapter(FragmentManager fm, int countOfTabs) {
        super(fm);
        this.countOfTabs = countOfTabs;
        currentTaskFragment = new CurrentTaskFragment();
        doneTaskFragment = new DoneTaskFragment();

    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case CURRENT_TASK_FRAGMENT_POSITION:
                return currentTaskFragment;
            case DONE_TASK_FRAGMENT_POSITION:
                return doneTaskFragment;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return countOfTabs;
    }
}
