package student.ppjava13v1.itstep.notecase.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import student.ppjava13v1.itstep.notecase.MainActivity;
import student.ppjava13v1.itstep.notecase.adapter.TaskAdapter;
import student.ppjava13v1.itstep.notecase.model.ModelTask;

public abstract class TaskFragment extends Fragment {

    protected RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    protected TaskAdapter adapter;

    public MainActivity activity;

    public void addTask(ModelTask newTask, boolean saveToDB) {
        int position = -1;

        for (int i = 0; i < adapter.getItemCount(); ++i) {
            ModelTask task = (ModelTask) adapter.getItem(i);
            if (newTask.getDate() < task.getDate()) {
                position = i;
                break;
            }
        }

        if (position != -1) {
            adapter.addItem(position, newTask);
        } else {
            adapter.addItem(newTask);
        }

        if (saveToDB) {
            activity.dbHelper.saveTask(newTask);
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            activity = (MainActivity) getActivity();
        }

        addTaskFromDB();
    }

    public abstract void addTaskFromDB();
    public abstract void moveTask(ModelTask task);

}
