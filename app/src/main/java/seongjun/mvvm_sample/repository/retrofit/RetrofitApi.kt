package seongjun.mvvm_sample.repository.retrofit

import retrofit2.Response
import retrofit2.http.*
import seongjun.mvvm_sample.model.RetrofitTodoData

/**
Rest API 서버와 통신하는 방법을 정의한 인터페이스입니다.
 **/

/**
getAllTodo : 투두리스트를 반환합니다.
addTodo : 투두를 추가합니다.
deleteTodo : 투두를 삭제합니다.
deleteAllTodo : 모든 투두를 삭제합니다.
 **/

interface RetrofitApi {

    @GET("todo")
    suspend fun selectAllTodo() : Response<List<RetrofitTodoData>>

    @POST("todo/{word}")
    suspend fun insertTodo(@Path("word") word: String) : Response<Unit>

    @DELETE("todo/{id}")
    suspend fun deleteTodo(@Path("id") id: Int) : Response<Unit>
}