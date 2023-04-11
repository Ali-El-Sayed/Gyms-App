package com.example.gymcompose

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Gym::class], version = 2, exportSchema = false
)
abstract class GymsDatabase : RoomDatabase() {
    abstract val dao: GymsDAO

    companion object {
        @Volatile // Visible to all threads
        private var daoInstance: GymsDAO? = null
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, GymsDatabase::class.java, "gyms_database"
        ).fallbackToDestructiveMigration().build()

        fun getDaoInstance(context: Context): GymsDAO {
            // Synchronized: Only one thread can access this block at a time
            synchronized(this) {
                if (daoInstance == null) daoInstance = buildDatabase(context).dao
                return daoInstance as GymsDAO
            }
        }
    }
}