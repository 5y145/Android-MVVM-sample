package seongjun.mvvm_sample.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import seongjun.mvvm_sample.model.Movie

@Database(entities = [Movie::class], version = 2, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase? {
            if (instance == null) {
                synchronized(this){
                    instance = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "db_name").build()
                }
            }
            return instance
        }
    }
}