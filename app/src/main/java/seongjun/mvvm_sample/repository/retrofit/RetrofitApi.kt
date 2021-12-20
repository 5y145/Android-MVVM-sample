package seongjun.mvvm_sample.repository.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import seongjun.mvvm_sample.model.RetrofitTodoData

/**
Rest API 서버와 통신하는 방법을 정의한 인터페이스입니다.
TV를 제어하기위한 리모컨이라고 생각하시면 편합니다.
 **/

/**
getAllTodo : 모든 투두리스트를 반환합니다.
addTodo : 투두를 추가합니다. // 성공 여부를 반환합니다.
deleteTodo : 투두를 삭제합니다. // 성공 여부를 반환합니다.
deleteAllTodo : 모든 투두를 삭제합니다. // 성공 여부를 반환합니다.
 **/

interface RetrofitApi {

    @GET("todo")
    suspend fun selectAllTodo() : Response<List<RetrofitTodoData>>

    @POST("todo")
    suspend fun insertTodo(@Body retrofitTodoData: RetrofitTodoData) : Response<Boolean>

    @DELETE("todo")
    suspend fun deleteTodo(@Body retrofitTodoData: RetrofitTodoData) : Response<Boolean>

    @DELETE("todo/all")
    suspend fun deleteAllTodo() : Response<Boolean>
}