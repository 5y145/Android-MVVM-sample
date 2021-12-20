package seongjun.mvvm_sample.repository.retrofit

import retrofit2.Response
import retrofit2.http.GET
import seongjun.mvvm_sample.model.RetrofitData

interface RetrofitApi {

    @GET("posts/1")
    suspend fun getPost() : Response<RetrofitData>
}