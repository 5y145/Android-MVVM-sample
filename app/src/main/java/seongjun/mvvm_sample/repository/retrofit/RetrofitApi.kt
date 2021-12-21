package seongjun.mvvm_sample.repository.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
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

    @POST("todo")
    suspend fun insertTodo(@Body todo: RetrofitTodoData) : Response<Boolean>

    @DELETE("todo")
    suspend fun deleteTodo(@Body todo: RetrofitTodoData) : Response<Boolean>
}