package com.eventemotion.data_event.data.mapper

import androidx.room.TypeConverter
import java.util.Date

class DateTypeConverter {
    @TypeConverter
    fun toLong(date: Date): Long = date.time

    @TypeConverter
    fun fromLong(time: Long): Date = Date(time)
}