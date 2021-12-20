package seongjun.mvvm_sample.model

import com.google.gson.annotations.SerializedName

/**
@SerializedName("id") 은 서버에서 가져온 객체의 id 값을 매핑된 변수에 넣겠다는 뜻입니다.
서버에서 가져온 변수명과 내가 쓰려는 변수명이 다를때 써줘야합니다.
하지만 개발자의 입장에서는 혹시 모르니 일일이 지정해주는게 좋습니다.
 **/

data class RetrofitTodoData(
    @SerializedName("id")
    val id : Int,
    @SerializedName("word")
    val word : String
)
