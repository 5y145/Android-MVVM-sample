package seongjun.mvvm_sample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import seongjun.mvvm_sample.model.Movie
import seongjun.mvvm_sample.model.RetrofitData
import seongjun.mvvm_sample.repository.AppDataBase
import seongjun.mvvm_sample.repository.Repository

class MainViewModel(private val repository: Repository): ViewModel() {

    val movieList = repository.selectAllMovie()


    //////////////////
    val myResponse : MutableLiveData<Response<RetrofitData>> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }

    fun insertMovie(movie: Movie) = viewModelScope.launch {
        repository.insertMovie(movie)
    }

    fun updateMovie(movie: Movie) = viewModelScope.launch {
        repository.updateMovie(movie)
    }

    fun deleteMovie(movie: Movie) = viewModelScope.launch {
        repository.deleteMovie(movie)
    }

    fun deleteAllMovie() = viewModelScope.launch {
        repository.deleteAllMovie()
    }

    class Factory(private val repository : Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}