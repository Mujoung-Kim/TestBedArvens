package com.todaysquare.patterntasknote.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.todaysquare.patterntasknote.data.databases.dao.ContactDao
import com.todaysquare.patterntasknote.data.databases.entities.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {

    //  Singleton 으로 사용하기 위해 companion object 로 정의
    companion object {
        private var INSTANCE: ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase? {
            if (INSTANCE == null)
                synchronized(ContactDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    ContactDatabase::class.java, "contact")
                        .fallbackToDestructiveMigration()
                        .build()

                }

            return INSTANCE

        }
    }
    abstract fun contactDao(): ContactDao

}