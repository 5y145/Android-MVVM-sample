package seongjun.mvvm_sample.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import seongjun.mvvm_sample.model.RoomTodoData

/**
여러 엔터티(model)를 사용하는 경우
    entities = [RoomTodoData::class, OtherData::class] <- 추가합니다.

    abstract fun TodoDao(): RetrofitTodoData
    abstract fun otherDao(): OtherData <- 추가합니다.

    앱이 깔린 상태에서 엔터티(model)를 수정한 경우 : 앱을 다시 깔거나 버전을 올려줍니다.
 **/

@Database(entities = [RoomTodoData::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getTodoDao(): TodoDao

    companion object {

        const val DB_NAME = "db_name"
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase? { // 싱글톤 패턴
            if (instance == null) {
                synchronized(this){
                    instance = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, DB_NAME).build()
                }
            }
            return instance
        }
    }
}