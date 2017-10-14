package cn.com.scal.components.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by vslimit on 15/1/27.
 */
public class DateUtil {

    public static Timestamp getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        long time = calendar.getTimeInMillis();
        return new Timestamp(time);
    }

    public static String convertStr(Date date) {
        if (null == date) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String datestr = df.format(date);
        return datestr;
    }

    public static Date strToDay(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        return formatter.parse(date);
    }

    @SuppressWarnings("deprecation")
    public static int getDutyDays(Date startDate, Date endDate) {
        int result = 0;
        if (null == endDate) {
            endDate = getCurrentTime();
        }
        if (null == startDate) {
            return result;
        }
        try {
            String start = convertStr(startDate);
            String end = convertStr(endDate);
            startDate = strToDay(start);
            endDate = strToDay(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        while (startDate.compareTo(endDate) < 0) {
            if (startDate.getDay() != 6 && startDate.getDay() != 0)
                result++;
            startDate.setDate(startDate.getDate() + 1);
        }
        return result;
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(DateUtil.getCurrentTime());
        System.out.println(DTFormatUtil.convertTimestampToStr(DateUtil.getCurrentTime()));
    }
}
