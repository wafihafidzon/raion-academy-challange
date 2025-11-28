package com.example.raionchallange.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.raionchallange.data.database.entities.MoodEntity
import com.example.raionchallange.data.database.entities.SongEntity
import com.example.raionchallange.data.database.dao.MoodDao
import com.example.raionchallange.data.database.dao.SongDao

@Database(
    entities = [MoodEntity::class, SongEntity::class],
    version = 2,
    exportSchema = false
)
abstract class MoodDatabase : RoomDatabase() {
    
    abstract fun moodDao(): MoodDao
    abstract fun songDao(): SongDao
    
    companion object {
        @Volatile
        private var INSTANCE: MoodDatabase? = null
        
        fun getDatabase(context: Context): MoodDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoodDatabase::class.java,
                    "mood_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}