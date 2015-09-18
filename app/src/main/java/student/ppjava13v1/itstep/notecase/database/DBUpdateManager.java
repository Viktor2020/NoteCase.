package student.ppjava13v1.itstep.notecase.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import student.ppjava13v1.itstep.notecase.model.ModelTask;

public class DBUpdateManager {

    SQLiteDatabase database;

    public DBUpdateManager(SQLiteDatabase database) {
        this.database = database;
    }

    public void status(long timeStamp, int status) {
        update(DBHelper.TASK_STATUS_COLUMN, timeStamp, status);
    }

    public void title(long timeStamp, String title) {
        update(DBHelper.TASK_TITLE_COLUMN, timeStamp, title);
    }

    public void date(long timeStamp, long date) {
        update(DBHelper.TASK_DATE_COLUMN, timeStamp, date);
    }

    public void priority(long timeStamp, int priority) {
        update(DBHelper.TASK_PRIORITY_COLUMN, timeStamp, priority);
    }



    public void task(ModelTask task) {
        status(task.getTimeStamp(), task.getStatus());
        title(task.getTimeStamp(), task.getTitle());
        date(task.getTimeStamp(), task.getDate());
        priority(task.getTimeStamp(), task.getPriority());

    }

    private void update(String column, long key, String value) {
        ContentValues cv = new ContentValues();
        cv.put(column, value);
        database.update(DBHelper.TASKS_TABLE, cv, DBHelper.TASK_TIME_STAMP_COLUMN + " = " + key, null);
    }

    private void update(String column, long key, long value) {
        ContentValues cv = new ContentValues();
        cv.put(column, value);
        database.update(DBHelper.TASKS_TABLE, cv, DBHelper.TASK_TIME_STAMP_COLUMN + " = " + key, null);
    }


}
