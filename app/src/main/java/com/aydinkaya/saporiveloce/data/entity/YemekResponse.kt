package com.aydinkaya.saporiveloce.data.entity

import com.google.gson.annotations.SerializedName

data class YemeklerCevap(
    val success: Int,
    val yemekler: List<Yemekler>
)
