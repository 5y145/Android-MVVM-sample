package seongjun.mvvm_sample.repository

import android.content.Context
import androidx.lifecycle.LiveData
import retrofit2.Response
import seongjun.mvvm_sample.model.RetrofitTodoData
import seongjun.mvvm_sample.model.RoomTodoData
import seongjun.mvvm_sample.repository.retrofit.RetrofitInstance
import seongjun.mvvm_sample.repository.room.AppDataBase

/**
LiveData<type> : JetPack의 AAC 구성요소입니다.
Response<type> : 레트로핏으로 통신할때의 반환형입니다. 통신 성공 / 실패에 대한 정보를 포함합니다.
 **/

class Repository(context: Context) {

    // Room
    val dataBase = AppDataBase.getInstance(context)
    val todoDao = dataBase!!.getTodoDao()

    val roomTodoList: LiveData<List<RoomTodoData>> = todoDao.selectAll()

    fun selectRoomAllTodo(): LiveData<List<RoomTodoData>>{
        return roomTodoList
    }

    suspend fun insertRoomTodo(romTodoData: RoomTodoData) {
        todoDao.insert(romTodoData)
    }

    suspend fun deleteRoomTodo(romTodoData: RoomTodoData) {
        todoDao.delete(romTodoData)
    }

    suspend fun deleteRoomAllTodo() {
        todoDao.deleteAll()
    }

    // Retrofit
    suspend fun selectRetrofitAllTodo() : Response<List<RetrofitTodoData>> {
        return RetrofitInstance.api.selectAllTodo()
    }

    suspend fun insertRetrofitTodo(retrofitTodoData: RetrofitTodoData) : Response<Boolean> {
        return RetrofitInstance.api.insertTodo(retrofitTodoData)
    }

    suspend fun deleteRetrofitTodo(retrofitTodoData: RetrofitTodoData) : Response<Boolean> {
        return RetrofitInstance.api.deleteTodo(retrofitTodoData)
    }

    suspend fun deleteRetrofitAllTodo() : Response<Boolean> {
        return RetrofitInstance.api.deleteAllTodo()
    }
}