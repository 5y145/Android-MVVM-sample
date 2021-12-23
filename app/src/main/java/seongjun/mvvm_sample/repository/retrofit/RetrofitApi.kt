package seongjun.mvvm_sample.repository.retrofit

import retrofit2.Response
import retrofit2.http.*
import seongjun.mvvm_sample.model.RetrofitTodoData

/**
Rest API 서버와 통신하는 방법을 정의한 인터페이스입니다.
 **/

interface RetrofitApi {

    @GET("todo")
    suspend fun selectAllTodo() : Response<List<RetrofitTodoData>>

    @POST("todo/{word}")
    suspend fun insertTodo(@Path("word") word: String) : Response<Unit>

    @DELETE("todo/{id}")
    suspend fun deleteTodo(@Path("id") id: Int) : Response<Unit>
}