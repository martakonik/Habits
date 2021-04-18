package com.example.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class HabitDatabaseModule {
    @Provides
    fun provideHabitItemDao(habitDatabase: HabitDatabase): HabitItemDao {
        return habitDatabase.habitItemDao()
    }

    @Provides
    @Singleton
    fun provideHabitDatabase(@ApplicationContext appContext: Context): HabitDatabase {
        return Room.databaseBuilder(
            appContext,
            HabitDatabase::class.java,
            "habit_database"
//        )
        ).fallbackToDestructiveMigration()
            .build()
    }

}