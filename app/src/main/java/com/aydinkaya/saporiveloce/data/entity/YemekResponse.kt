package com.aydinkaya.saporiveloce.data.entity

import com.google.gson.annotations.SerializedName

data class YemekResponse(
    @SerializedName("yemekler")
    val yemekler: List<Yemek>,
    @SerializedName("success")
    val success: Int
)