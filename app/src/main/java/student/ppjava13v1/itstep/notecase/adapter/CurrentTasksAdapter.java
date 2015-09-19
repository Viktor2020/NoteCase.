package student.ppjava13v1.itstep.notecase.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import student.ppjava13v1.itstep.notecase.R;
import student.ppjava13v1.itstep.notecase.Utils;
import student.ppjava13v1.itstep.notecase.fragments.CurrentTaskFragment;
import student.ppjava13v1.itstep.notecase.model.Item;
import student.ppjava13v1.itstep.notecase.model.ModelTask;

public class CurrentTasksAdapter extends TaskAdapter {


    private static final int TYPE_TASK = 0;
    private static final int TYPE_SEPARATOR = 1;

    public CurrentTasksAdapter(CurrentTaskFragment taskFragment) {
        super(taskFragment);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        switch (viewType) {
            case TYPE_TASK:
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.model_task, viewGroup, false);
                TextView title = (TextView) view.findViewById(R.id.tvTaskTitle);
                TextView date = (TextView) view.findViewById(R.id.tvTaskDate);
                CircleImageView priority = (CircleImageView) view.findViewById(R.id.rvTaskPriority);

                return new TaskViewHolder(view, title, date, priority);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Item item = items.get(position);
        if (item.isTask()) {
            viewHolder.itemView.setEnabled(true);
            final ModelTask task = (ModelTask) item;
            final TaskViewHolder taskViewHolder = (TaskViewHolder) viewHolder;
            taskViewHolder.title.setText(task.getTitle());

            final View itemView = taskViewHolder.itemView;
            final Resources resources = itemView.getResources();


            if (task.getDate() != 0) {
                taskViewHolder.date.setText(Utils.getFullDate(task.getDate()));
            } else {
                taskViewHolder.date.setText(null);
            }

            itemView.setVisibility(View.VISIBLE);
            taskViewHolder.priority.setEnabled(true);

            itemView.setBackgroundColor(resources.getColor(R.color.gray_50));

            taskViewHolder.title.setTextColor(resources.getColor(R.color.colorPrimary));
            taskViewHolder.date.setTextColor(resources.getColor(R.color.colorPrimaryDark));
            taskViewHolder.priority.setColorFilter(resources.getColor(task.getPriorityColor()));
            taskViewHolder.priority.setImageResource(R.drawable.ic_check_circle_white_24dp);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getTaskFragment().removeTaskDialog(taskViewHolder.getLayoutPosition());
                        }
                    }, 1000);


                    return true;
                }
            });

            taskViewHolder.priority.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    taskViewHolder.priority.setEnabled(false);
                    task.setStatus(ModelTask.STATUS_DONE);

                    getTaskFragment().activity.dbHelper.update().status(task.getTimeStamp(), task.getStatus());

                    itemView.setBackgroundColor(resources.getColor(R.color.gray_200));

                    taskViewHolder.title.setTextColor(resources.getColor(R.color.colorPrimaryC));
                    taskViewHolder.date.setTextColor(resources.getColor(R.color.colorPrimaryDarkC));
                    taskViewHolder.priority.setColorFilter(resources.getColor(task.getPriorityColor()));

                    ObjectAnimator animatorIn = ObjectAnimator.ofFloat(taskViewHolder.priority, "rotationY", -180, 0f);

                    animatorIn.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            if (task.getStatus() == ModelTask.STATUS_DONE) {
                                taskViewHolder.priority.setImageResource(R.drawable.ic_blur_circular_black_24dp);

                                ObjectAnimator animator = ObjectAnimator.ofFloat(itemView,
                                        "translationX", 0f, itemView.getWidth());

                                ObjectAnimator animatorBack = ObjectAnimator.ofFloat(itemView,
                                        "translationX", itemView.getWidth(), 0f);

                                animator.addListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        itemView.setVisibility(View.GONE);
                                        getTaskFragment().moveTask(task);
                                        removeItem(taskViewHolder.getLayoutPosition());
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                });

                                AnimatorSet animatorSet = new AnimatorSet();
                                animatorSet.play(animator).before(animatorBack);
                                animatorSet.start();
                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });

                    animatorIn.start();
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position).isTask()) {
            return TYPE_TASK;
        } else {
            return TYPE_SEPARATOR;
        }
    }
}
