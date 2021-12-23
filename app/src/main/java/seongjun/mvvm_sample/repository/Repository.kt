package seongjun.mvvm_sample.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Response
import seongjun.mvvm_sample.model.RetrofitTodoData
import seongjun.mvvm_sample.model.RoomTodoData
import seongjun.mvvm_sample.repository.retrofit.RetrofitInstance
import seongjun.mvvm_sample.repository.room.AppDataBase

/**
ViewMovel에서는 로컬데이터인지 원격데이터인지 신경쓰지않고 Repository를 사용할 수 있습니다.
Response<type> : 레트로핏으로 통신할때의 반환형입니다.
    result.isSuccessful : 통신에 성공했는지의 여부. 이때의 통신은 갔다 왔는가 그자체를 의미하는것입니다. 자세한 사항은 검색해 보아야합니다.
    result.body : 실질적으로 받게되는 데이터입니다. `as Type`으로 객체 타입을 명시합니다.
 **/

class Repository(application : Application) {

    // Room Dao
    private val todoDao = AppDataBase.getInstance(application)!!.getTodoDao()

    // Use Room
    fun roomSelectAllTodo(): LiveData<List<RoomTodoData>> {
        return todoDao.selectAllTodo()
    }

    suspend fun roomInsertTodo(todo: RoomTodoData) {
        todoDao.insertTodo(todo)
    }

    suspend fun roomDeleteTodo(todo: RoomTodoData) {
        todoDao.deleteTodo(todo)
    }

    // Use Retrofit
    suspend fun retrofitSelectAllTodo(): List<RetrofitTodoData> {
        val response = RetrofitInstance.api.selectAllTodo()
        return if (response.isSuccessful) response.body() as List<RetrofitTodoData> else ArrayList()
    }

    suspend fun retrofitInsertTodo(todo: RetrofitTodoData) : Response<Unit> {
        return RetrofitInstance.api.insertTodo(todo.word)
    }

    suspend fun retrofitDeleteTodo(todo: RetrofitTodoData) : Response<Unit> {
        return RetrofitInstance.api.deleteTodo(todo.id)
    }

    companion object {
        private var instance: Repository? = null

        fun getInstance(application : Application): Repository? { // singleton pattern
            if (instance == null) instance = Repository(application)
            return instance
        }
    }
}