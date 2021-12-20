package seongjun.mvvm_sample.repository.room

import androidx.lifecycle.LiveData
import androidx.room.*
import seongjun.mvvm_sample.model.RoomTodoData

/**
@Query("sql문") : 쿼리문으로 Room 사용
@Insert : Room이 알아서 객체를 sqLite에 저장해줍니다.

onConflict = OnConflictStrategy : 검색해보면 여러 옵션을 알 수 있습니다.

suspend 함수 : 코루틴 개념입니다. 공부하시는것을 추천합니다.
 **/

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun selectAll(): LiveData<List<RoomTodoData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roomTodoData: RoomTodoData)

//    @Update
//    suspend fun update(roomTodoData: RoomTodoData)

    @Delete
    suspend fun delete(roomTodoData: RoomTodoData)

    @Query("DELETE FROM todo")
    suspend fun deleteAll()
}