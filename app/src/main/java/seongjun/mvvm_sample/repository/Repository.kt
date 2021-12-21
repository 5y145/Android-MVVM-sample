package seongjun.mvvm_sample.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import seongjun.mvvm_sample.model.RetrofitTodoData
import seongjun.mvvm_sample.model.RoomTodoData
import seongjun.mvvm_sample.repository.retrofit.RetrofitInstance
import seongjun.mvvm_sample.repository.room.AppDataBase

/**
ViewMovel에서는 로컬에서 가져왔는지 원격으로 가져왔는지 신경쓰지않고 Repository를 통해 Model을 가져옵니다.
Response<type> : 레트로핏으로 통신할때의 반환형입니다.
    result.isSuccessful : 통신에 성공했는지의 여부. 이때의 통신은 갔다 왔는가 그자체를 의미하는것입니다. 자세한 사항은 검색해 보아야합니다.
    result.body : 실질적으로 받게되는 데이터입니다. `as Type`으로 객체 타입을 명시합니다.
 **/

class Repository(context: Context) {

    // Room Dao
    val todoDao = AppDataBase.getInstance(context)!!.getTodoDao()

    // Room Data
    val roomTodoList: LiveData<List<RoomTodoData>> = roomSelectAllTodo()
    var retrofitTodoList: MutableLiveData<List<RetrofitTodoData>> = MutableLiveData<List<RetrofitTodoData>>().apply { setValue(emptyList()) }

    init {
        retrofitReloadAllTodo()
        Log.d("debug", "first reload")
    }

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
    fun retrofitReloadAllTodo(){
        CoroutineScope(Dispatchers.IO).launch {
            val result = retrofitSelectAllTodo()
            retrofitTodoList.postValue(result)
            Log.d("debug", "reload: $retrofitTodoList")
        }
    }

    suspend fun retrofitSelectAllTodo(): List<RetrofitTodoData> {
        var result: List<RetrofitTodoData> = emptyList()
        val response = RetrofitInstance.api.selectAllTodo()
        if (response.isSuccessful) {
            result = response.body() as List<RetrofitTodoData>
        }
        return result
    }

    suspend fun retrofitInsertTodo(todo: RetrofitTodoData) : Response<Unit> {
        return RetrofitInstance.api.insertTodo(todo.word)
    }

    suspend fun retrofitDeleteTodo(todo: RetrofitTodoData) : Response<Unit> {
        return RetrofitInstance.api.deleteTodo(todo.id)
    }
}