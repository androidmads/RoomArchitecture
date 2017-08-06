package com.androidmads.room_arch.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by AJ on 7/29/2017.
 */

class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
