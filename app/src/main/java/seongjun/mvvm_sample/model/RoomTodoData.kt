package seongjun.mvvm_sample.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
@Entity(tableName = 테이블 명) : 특정 테이블에 매핑시켜줍니다.
@PrimaryKey(autoGenerate = true) : 데이터를 추가할때마다 저절로 증가하는 옵션입니다. 보통 id에 많이 사용합니다.
@ColumnInfo(name = "word") : 특정 컬럼에 매핑시켜줍니다.
 **/

@Entity(tableName = "todo")
data class RoomTodoData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "word")
    val word: String
)
