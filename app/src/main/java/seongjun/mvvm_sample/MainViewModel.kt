package seongjun.mvvm_sample

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import seongjun.mvvm_sample.model.RetrofitTodoData
import seongjun.mvvm_sample.model.RoomTodoData
import seongjun.mvvm_sample.repository.Repository

class MainViewModel(private val repository: Repository): ViewModel() {

    val roomTodoList: LiveData<List<RoomTodoData>> = repository.roomTodoList
    val retrofitTodoList: MutableLiveData<List<RetrofitTodoData>> = repository.retrofitTodoList

    // 코루틴으로 Room 제어
    fun insertRoom(todo: RoomTodoData) = viewModelScope.launch {
        repository.roomInsertTodo(todo)
    }

    fun deleteRoom(todo: RoomTodoData) = viewModelScope.launch {
        repository.roomDeleteTodo(todo)
    }

    // 코루틴으로 Retrofit 제어
    fun selectAllRetrofit() {
        repository.retrofitTodoList = repository.retrofitSelectAllTodo()
    }

    fun insertRetrofit(todo: RetrofitTodoData) = viewModelScope.launch {
        val response = repository.retrofitInsertTodo(todo)
        if (response.isSuccessful) {
            // ...
            selectAllRetrofit()
        } else { Log.d("DEBUG", response.errorBody().toString()) }
    }

    fun deleteRetrofit(todo: RetrofitTodoData) = viewModelScope.launch {
        val response = repository.retrofitDeleteTodo(todo)
        if (response.isSuccessful) {
            // ...
            selectAllRetrofit()
        } else { Log.d("DEBUG", response.errorBody().toString()) }
    }

    class Factory(private val application : Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(Repository(application)) as T
        }
    }
}