package seongjun.mvvm_sample.repository

import android.content.Context
import androidx.lifecycle.LiveData
import retrofit2.Response
import seongjun.mvvm_sample.model.Movie
import seongjun.mvvm_sample.model.RetrofitData
import seongjun.mvvm_sample.repository.retrofit.RetrofitInstance

class Repository(context: Context) {

    val dataBase = AppDataBase.getInstance(context)
    val movieDao = dataBase!!.movieDao()

    val movieList: LiveData<List<Movie>> = movieDao.selectAll()

    /////////////
    fun selectAllMovie(): LiveData<List<Movie>>{
        return movieList
    }

    suspend fun insertMovie(movie: Movie) {
        movieDao.insert(movie)
    }

    suspend fun updateMovie(movie: Movie) {
        movieDao.update(movie)
    }

    suspend fun deleteMovie(movie: Movie) {
        movieDao.delete(movie)
    }

    suspend fun deleteAllMovie() {
        movieDao.deleteAll()
    }

    ///////////////////////////////

    suspend fun getPost() : Response<RetrofitData> {
        return RetrofitInstance.api.getPost()
    }
}