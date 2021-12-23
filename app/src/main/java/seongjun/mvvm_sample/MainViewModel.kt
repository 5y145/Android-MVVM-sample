package seongjun.mvvm_sample

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import seongjun.mvvm_sample.model.RetrofitTodoData
import seongjun.mvvm_sample.model.RoomTodoData
import seongjun.mvvm_sample.repository.Repository

class MainViewModel(private val repository: Repository): ViewModel() {

    val roomTodoList: LiveData<List<RoomTodoData>> = repository.roomSelectAllTodo()
    val retrofitTodoList: MutableLiveData<List<RetrofitTodoData>> = MutableLiveData()

    var roomInput: MutableLiveData<String> = MutableLiveData()
    var retrofitInput: MutableLiveData<String> = MutableLiveData()

    init {
        viewModelScope.launch {
            retrofitTodoList.value = repository.retrofitSelectAllTodo()
        }
    }

    // 코루틴으로 Room 제어
    fun insertRoom() = viewModelScope.launch {
        repository.roomInsertTodo(RoomTodoData(0, roomInput.value.toString()))
        roomInput.value = ""
    }

    fun deleteRoom(todo: RoomTodoData) = viewModelScope.launch {
        repository.roomDeleteTodo(todo)
    }

    // 코루틴으로 Retrofit 제어
    fun insertRetrofit() = viewModelScope.launch {
        val response = repository.retrofitInsertTodo(RetrofitTodoData(0, retrofitInput.value.toString()))
        if (response.isSuccessful) retrofitTodoList.value = repository.retrofitSelectAllTodo()
        retrofitInput.value = ""
    }

    fun deleteRetrofit(todo: RetrofitTodoData) = viewModelScope.launch {
        val response = repository.retrofitDeleteTodo(todo)
        if (response.isSuccessful) retrofitTodoList.value = repository.retrofitSelectAllTodo()
    }

    class Factory(private val application : Application) : ViewModelProvider.Factory { // 팩토리 패턴
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(Repository.getInstance(application)!!) as T
        }
    }
}