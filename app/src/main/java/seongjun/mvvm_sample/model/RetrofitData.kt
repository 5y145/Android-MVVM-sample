package seongjun.mvvm_sample.model

import com.google.gson.annotations.SerializedName

data class RetrofitData(
    @SerializedName("userId")
    val myUserId : Int,
    val id : Int,
    val title : String,
    val body : String
)
