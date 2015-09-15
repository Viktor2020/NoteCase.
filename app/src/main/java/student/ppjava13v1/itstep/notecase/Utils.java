package student.ppjava13v1.itstep.notecase;


import java.text.SimpleDateFormat;

public class Utils {
    public static String getDate(long date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        return dateFormat.format(date);
    }

    public static String getTime(long time){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH.mm");
        return dateFormat.format(time);
    }

    public static String getFullDate(long date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy  HH.mm");
        return dateFormat.format(date);
    }
}
