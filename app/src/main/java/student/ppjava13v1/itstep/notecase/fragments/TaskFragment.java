package student.ppjava13v1.itstep.notecase.fragments;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;

import student.ppjava13v1.itstep.notecase.adapter.TaskAdapter;
import student.ppjava13v1.itstep.notecase.model.ModelTask;

public abstract class TaskFragment extends Fragment {

    protected RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    protected TaskAdapter adapter;

    public void addTask(ModelTask newTask) {
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
    }

    public abstract void moveTask(ModelTask task);

}
