package net.scode.commons.constant;

/**
 * 常用的时间间隔
 *
 * @author tanghuang 2020年03月29日
 */
public final class TimeInterval {

    //------------------------------以秒为单位------------------------------
    /**
     * 1秒
     */
    public static final int SECONDS_PER_SECOND = 1;

    /**
     * 1分钟
     */
    public static final int SECONDS_PER_MINUTE = SECONDS_PER_SECOND * 60;

    /**
     * 1小时
     */
    public static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * 60;

    /**
     * 1天
     */
    public static final int SECONDS_PER_DAY = SECONDS_PER_HOUR * 24;

    /**
     * 1个星期
     */
    public static final int SECONDS_PER_WEEK = SECONDS_PER_DAY * 7;

    /**
     * 30天, 一个月(没有精确一个月28/29天 还是30 31天)
     */
    public static final int SECONDS_PER_MONTH = SECONDS_PER_DAY * 30;

    /**
     * 365天, 1年
     */
    public static final int SECONDS_PER_YEAR = SECONDS_PER_DAY * 365;


    //------------------------------以毫秒为单位------------------------------
    /**
     * 1秒 1000ms(毫秒)
     */
    public static final int MILLISECOND_PER_SECONDS = 1000;

    /**
     * 1分钟 60 000ms(毫秒)
     */
    public static final int MILLISECOND_PER_MINUTE = SECONDS_PER_MINUTE * 1000;

    /**
     * 1小时 3600 000ms(毫秒)
     */
    public static final int MILLISECOND_PER_HOUR = SECONDS_PER_HOUR * 1000;

    /**
     * 1天 86400 000ms(毫秒)
     */
    public static final int MILLISECOND_PER_DAY = SECONDS_PER_DAY * 1000;

    /**
     * 1个星期 604 800 000ms(毫秒)
     */
    public static final int MILLISECOND_PER_WEEK = SECONDS_PER_WEEK * 1000;

    /**
     * 30天, 1个月(没有精确一个月28/29天 还是30 31天),2592000 000ms(毫秒)
     */
    public static final long MILLISECOND_PER_MONTH = SECONDS_PER_MONTH * 1000L;

    /**
     * 365天, 1年 31536000 000ms(毫秒)
     */
    public static final long MILLISECOND_PER_YEAR = SECONDS_PER_YEAR * 1000L;

}