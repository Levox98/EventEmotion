package com.eventemotion.data_event.data

import android.content.Context
import androidx.room.Room
import com.eventemotion.data_event.data.db.EventDatabase
import com.eventemotion.data_event.data.repository.EventRepositoryImpl
import com.eventemotion.data_event.domain.repository.EventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventDiModule {

    @Provides
    @Singleton
    internal fun provideEventDb(@ApplicationContext context: Context): EventDatabase =
        Room.databaseBuilder(context, EventDatabase::class.java, "events_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    internal fun provideEventsDao(db: EventDatabase) = db.eventsDao()

    @Provides
    @Singleton
    internal fun bindEventRepository(r: EventRepositoryImpl): EventRepository = r

}