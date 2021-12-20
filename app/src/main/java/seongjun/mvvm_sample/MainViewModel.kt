package seongjun.mvvm_sample

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import seongjun.mvvm_sample.model.RetrofitTodoData
import seongjun.mvvm_sample.model.RoomTodoData
import seongjun.mvvm_sample.repository.Repository

class MainViewModel(private val repository: Repository): ViewModel() {

    val roomTodoList: LiveData<List<RoomTodoData>> = repository.selectRoomAllTodo()
    val retrofitTodoList: MutableLiveData<List<RetrofitTodoData>> = MutableLiveData()


//    val myResponse : MutableLiveData<Response<RetrofitData>> = MutableLiveData()



//    fun getPost() {
//        viewModelScope.launch {
//            val response = repository.getPost()
//            myResponse.value = response
//        }
//    }

    // 코루틴으로 Room 제어
    fun insertRoom(roomTodoData: RoomTodoData) = viewModelScope.launch {
        repository.insertRoomTodo(roomTodoData)
    }

    fun deleteRoom(roomTodoData: RoomTodoData) = viewModelScope.launch {
        repository.deleteRoomTodo(roomTodoData)
    }

    fun deleteAllRoom() = viewModelScope.launch {
        repository.deleteRoomAllTodo()
    }

    // 코루틴으로 Retrofit 제어
    fun selectAllRetrofit() {
        Log.d("DEBUG", "호출2")
        viewModelScope.launch {
            val response = repository.selectRetrofitAllTodo()
            if (response.isSuccessful) {
                retrofitTodoList.value = response.body() as List<RetrofitTodoData>
                Log.d("DEBUG", "호출3 ${retrofitTodoList.value}")
            } else {
                Log.d("DEBUG", response.errorBody().toString())
            }
        }
    }

    fun insertRetrofit(retrofitTodoData: RetrofitTodoData) {
        viewModelScope.launch {
            val response = repository.insertRetrofitTodo(retrofitTodoData)
            if (response.isSuccessful) {
                // ...
            } else {
                Log.d("DEBUG", response.errorBody().toString())
            }
        }
    }

    fun deleteRetrofit(retrofitTodoData: RetrofitTodoData) {
        viewModelScope.launch {
            val response = repository.deleteRetrofitTodo(retrofitTodoData)
            if (response.isSuccessful) {
                // ...
            } else {
                Log.d("DEBUG", response.errorBody().toString())
            }
        }
    }

    fun deleteAllRetrofit() {
        viewModelScope.launch {
            val response = repository.deleteRetrofitAllTodo()
            if (response.isSuccessful) {
                // ...
            } else {
                Log.d("DEBUG", response.errorBody().toString())
            }
        }
    }

    class Factory(private val application : Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(Repository(application)) as T
        }
    }
}