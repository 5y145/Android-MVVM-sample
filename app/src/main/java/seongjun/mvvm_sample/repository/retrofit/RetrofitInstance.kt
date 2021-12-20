package seongjun.mvvm_sample.repository.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // 통신을 확인하기 위해 제 서버주소를 넣었습니다.
    const val SERVER_URL = "http://seongjunjang.cafe24app.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
        .baseUrl(SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }

    val api : RetrofitApi by lazy { retrofit.create(RetrofitApi::class.java) }
}