package com.todaysquare.functiontest.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.todaysquare.functiontest.data.databases.dao.CharacterDao
import com.todaysquare.functiontest.data.databases.entites.Character

@Database(entities = [Character::class], version = 1, exportSchema = false)
abstract class AppDatabases : RoomDatabase() {
    companion object {
        @Volatile private var instance: AppDatabases? = null

        fun getDatabase(context: Context) = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }

        }

        private fun buildDatabase(appContext: Context) = Room
            .databaseBuilder(appContext, AppDatabases::class.java, "characters.db")
            .fallbackToDestructiveMigration()
            .build()

    }

    abstract fun charterDao(): CharacterDao

}