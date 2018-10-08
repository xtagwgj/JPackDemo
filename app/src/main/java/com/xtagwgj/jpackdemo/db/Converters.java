package com.xtagwgj.jpackdemo.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * 类型转换
 */
public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}
