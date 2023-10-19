package com.eventemotion.data_event.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.eventemotion.data_event.data.mapper.DateTypeConverter
import java.util.Date

@Entity(tableName = "events")
@TypeConverters(DateTypeConverter::class)
data class EventEntryDataEntity(
    val name: String,
    val feeling: String,
    val thought: String,
    @PrimaryKey
    val date: Date
)
