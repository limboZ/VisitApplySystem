/**
 * 时间格式工具类<br>
 * 2015-1-7, com.vslimit, create this file. <br>
 */
package cn.com.scal.components.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DTFormatUtil {

    private static Logger log = LoggerFactory.getLogger(DTFormatUtil.class.getName());


    public static final SimpleDateFormat SDF_ALL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat MM_DD = new SimpleDateFormat("MM/dd");

    public static final SimpleDateFormat SDF_YY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");


    public static Date convertDateBySdf(String date,SimpleDateFormat simpleDateFormat) {
        if (StringUtil.isEmpty(date)) {
            return null;
        }
        Date parseDate = null;
        try {
            parseDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    /**
     * Date转换字符串（yyyy-MM-dd HH:mm:ss）
     *
     * @param date
     * @return String
     */
    public static String convertStrBySdf(Date date,SimpleDateFormat simpleDateFormat) {
        if (null == date) {
            return null;
        }
        String datestr = simpleDateFormat.format(date);
        return datestr;
    }

    /**
     * Date转换字符串（yyyy-MM-dd HH:mm:ss）
     *
     * @param date
     * @return String
     */
    public static String convertStr(Date date) {
        if (null == date) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr = df.format(date);
        return datestr;
    }

    public static String convertStrTime(Date date) {
        if (null == date) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("MM/dd HH:mm:ss");
        String datestr = df.format(date);
        return datestr;
    }

    /**
     * 字符串转换为Date
     *
     * @param date
     * @return Date
     */
    public static Date convertDate(String date) {
        if (StringUtil.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parseDate = null;
        try {
            parseDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    /**
     * 获取当前时间时间戳
     *
     * @return Timestamp
     */
    public static Timestamp getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        long time = calendar.getTimeInMillis();
        return new Timestamp(time);
    }

    /**
     * Date转换为时间戳
     *
     * @param date
     * @return Timestamp
     */
    public static Timestamp toSqlTime(Date date) {
        long time = date.getTime();
        return new Timestamp(time);
    }

    /**
     * 字符串转换为时间戳
     *
     * @param dateStr
     * @return String
     */
    public static Timestamp toSqlTime(String dateStr) {
        if (StringUtil.isEmpty(dateStr)) {
            return null;
        }
        SimpleDateFormat df = null;
        if (dateStr.length() > 10) {
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            df = new SimpleDateFormat("yyyy-MM-dd");
        }
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            log.info("date format error");
            Calendar calendar = Calendar.getInstance();
            date = calendar.getTime();
        }
        long time = date.getTime();
        return new Timestamp(time);
    }

    /**
     * 时间戳转换为String
     *
     * @param time
     * @return String
     */
    public static String convertTimestampToStr(Timestamp time) {
        if (null == time) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr = df.format(time);
        return datestr;
    }

    /**
     * Date转换字符串（yyyy-MM-dd）
     *
     * @param date
     * @return String
     */
    public static String convertQueryStr(Date date) {
        if (null == date) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String datestr = df.format(date);
        return datestr;
    }

    /**
     * Date（yyyy）
     *
     * @param date
     * @return String
     */
    public static String convertYearStr(Date date) {
        if (null == date) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String datestr = df.format(date);
        return datestr;
    }

    /**
     * Date转换字符串（yyyyMMdd）
     *
     * @param date
     * @return String
     */
    public static String convertSignStr(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String datestr = dateFormat.format(date);
        return datestr;
    }

    /**
     * 字符串（yyyy-MM-dd）转换Date
     *
     * @param date
     * @return Date
     */
    public static Date strToDay(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        return formatter.parse(date);
    }

    /**
     * 字符串（yyyy-MM-dd HH:mm:ss）转换Date
     *
     * @param date
     * @return Date
     */
    public static Date strToDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setLenient(false);
        return formatter.parse(date);
    }

    private static long convertDaysToMilliseconds(long days) {
        return 1000 * 3600 * 24 * days;
    }

    /**
     * 计算n天前的日期
     *
     * @param day,type:1--day天后，else--day天前
     * @return
     * @throws ParseException
     */
    public static String computeDate(long day, int type) throws ParseException {
        Calendar calendar = Calendar.getInstance();
//		long now = calendar.getTimeInMillis();
        long now = System.currentTimeMillis();
//		long time = convertDaysToMilliseconds(day);
        if (type == 1) {
            return DTFormatUtil
                    .convertTimestampToStr(new Timestamp(now + convertDaysToMilliseconds(day)));
        } else {
            return DTFormatUtil
                    .convertTimestampToStr(new Timestamp(now - convertDaysToMilliseconds(day)));
        }
    }

    public static String computeDate(Date date, long day, int type) throws ParseException {
        long now = date.getTime();
        if (type == 1) {
            return DTFormatUtil
                    .convertTimestampToStr(new Timestamp(now + convertDaysToMilliseconds(day)));
        } else {
            return DTFormatUtil
                    .convertTimestampToStr(new Timestamp(now - convertDaysToMilliseconds(day)));
        }
    }

    public static String computeTime(String from, String to, String type)
            throws ParseException {
        if (StringUtil.isEmpty(from) || StringUtil.isEmpty(to)) {
            return null;
        }
        String ret = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = df.parse(to);
        Date date = df.parse(from);
        long l = (now.getTime() - date.getTime()) / 1000;
        long day = l / (24 * 60 * 60);
        long year = day / 365;
        long month = day % 365 / 30;
        long hour = (l / (60 * 60) - day * 24);
        long min = ((l / 60) - day * 24 * 60 - hour * 60);
        long sec = (l - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        day = day % 365 % 30;

        if ("0".equals(type)) {
            if (year > 0) {
                ret = year + "年" + month + "月" + day + "天" + hour + "小时" + min
                        + "分钟" + sec + "秒";
            } else if (month > 0) {
                ret = month + "月" + day + "天" + hour + "小时" + min + "分" + sec + "秒";
            } else if (day > 0) {
                ret = day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
            } else if (hour > 0) {
                ret = hour + "小时" + min + "分钟" + sec + "秒";
            } else if (min > 0) {
                ret = min + "分钟" + sec + "秒";
            } else if (sec > 0) {
                ret = sec + "秒";
            }
        } else if ("1".equals(type)) {
            if (year > 0) {
                ret = year + "年";
            } else if (month > 0) {
                ret = month + "月";
            } else if (day > 0) {
                ret = day + "天";
            } else if (hour > 0) {
                ret = hour + "小时";
            } else if (min > 0) {
                ret = min + "分钟";
            } else if (sec > 0) {
                ret = sec + "秒";
            }
        }
        return ret;
    }

    public static long computeDays(String from, String to) throws ParseException {
        if (StringUtil.isEmpty(from) || StringUtil.isEmpty(to)) {
            return 0;
        }
        String ret = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date now = df.parse(to);
        Date date = df.parse(from);
        long l = (now.getTime() - date.getTime()) / 1000;
        long days = l / (24 * 60 * 60);
        return days;
    }

    public static long computeDays(Date from, Date to) {
        if (from == null || to == null) {
            return 0;
        }
        String ret = null;

        Date now = from;
        Date date = to;
        long l = (now.getTime() - date.getTime()) / 1000;
        long days = l / (24 * 60 * 60);
        return days;
    }



    public static Integer obtainedMonth(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static Integer obtainedYear(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static Integer obtainedDay(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    public static String getCurrentDate(SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(new Date());
    }

    public static Integer weekday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static List arriveDates(){
        List dateList=new ArrayList();
        for(int i=10;i<=23;i++){
            String str_date=i+":"+30;
            dateList.add(str_date);
        }
        return dateList;
    }



    public static List getStartAndEndDate(String startDate,String endDate) throws ParseException{
        List dateList=new ArrayList();
        Date firstDate=strToDay(startDate);
        Date overDate=strToDay(endDate);
        Long day=1L;
        dateList.add(convertQueryStr(firstDate));
        for(Long i=0L;i<computeDays(overDate,firstDate);i++){
            Long time=day*1000*60*60*24;
            String date=convertQueryStr(new Date(firstDate.getTime()+time));
            day++;
            dateList.add(date);
        }
        return dateList;
    }



    public static void main(String[] args) throws Exception {
        System.out.println(arriveDates());
    }
}
