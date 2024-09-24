package com.aydinkaya.saporiveloce.retrofit

class ApiUtils {
    companion object {
        const val BASE_URL = "http://kasimadalan.pe.hu/yemekler/"

        fun getYemekDao(): YemekDao {
            return RetrofitClient.getClient(BASE_URL).create(YemekDao::class.java)
        }
    }
}
