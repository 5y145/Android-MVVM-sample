package seongjun.mvvm_sample.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import seongjun.mvvm_sample.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie ORDER BY id DESC")
    fun selectAll(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("DELETE FROM movie")
    suspend fun deleteAll()
}