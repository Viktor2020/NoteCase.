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
import student.ppjava13v1.itstep.notecase.adapter.DoneTaskAdapter;
import student.ppjava13v1.itstep.notecase.database.DBHelper;
import student.ppjava13v1.itstep.notecase.model.ModelTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoneTaskFragment extends TaskFragment {


    public DoneTaskFragment() {
        // Required empty public constructor
    }

    OnTaskRestoreListener onTaskDoneListener;

    public interface OnTaskRestoreListener {
        void onTaskRestore(ModelTask task);
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);

        try {
            onTaskDoneListener = (OnTaskRestoreListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnTaskRestoreListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_done_task, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvDoneTasks);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DoneTaskAdapter(this);

        recyclerView.setAdapter(adapter);

        return rootView;
    }


    @Override
    public void moveTask(ModelTask task) {
        onTaskDoneListener.onTaskRestore(task);
    }

    @Override
    public void addTaskFromDB() {

        List<ModelTask> tasks = new ArrayList<>();
        tasks.addAll(activity.dbHelper.query().getTasks(
                DBHelper.SELECTION_STATUS ,
                new String[]{Integer.toString(ModelTask.STATUS_DONE)},
                DBHelper.TASK_DATE_COLUMN));
        for (int i = 0; i < tasks.size(); ++i) {
            addTask(tasks.get(i), false);
        }

    }
}
