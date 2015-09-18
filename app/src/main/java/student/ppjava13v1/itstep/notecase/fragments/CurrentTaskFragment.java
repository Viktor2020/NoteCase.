package student.ppjava13v1.itstep.notecase.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import student.ppjava13v1.itstep.notecase.R;
import student.ppjava13v1.itstep.notecase.adapter.CurrentTasksAdapter;
import student.ppjava13v1.itstep.notecase.database.DBHelper;
import student.ppjava13v1.itstep.notecase.model.ModelTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentTaskFragment extends TaskFragment {


    public CurrentTaskFragment() {
        // Required empty public constructor
    }

    OnTaskDoneListener onTaskDoneListener;

    public interface OnTaskDoneListener {
        void onTaskDone(ModelTask task);
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);

        try {
            onTaskDoneListener = (OnTaskDoneListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnTaskDoneListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_current_task, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvCurrentTasks);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CurrentTasksAdapter(this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void addTaskFromDB() {

        List<ModelTask> tasks = new ArrayList<>();
        tasks.addAll(activity.dbHelper.query().getTasks(DBHelper.SELECTION_STATUS + " OR "
                + DBHelper.SELECTION_STATUS,
                new String[]{Integer.toString(ModelTask.STATUS_CURRENT),
                Integer.toString(ModelTask.STATUS_OVERDUE)}, DBHelper.TASK_DATE_COLUMN));
        for (int i = 0; i < tasks.size(); ++i) {
            addTask(tasks.get(i), false);
        }

    }

    @Override
    public void moveTask(ModelTask task) {
        onTaskDoneListener.onTaskDone(task);
    }
}
