package com.aydinkaya.saporiveloce.retrofit

/*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class RetrofitClient private constructor() {

    companion object {
        @Volatile
        private var retrofit: Retrofit? = null

        private const val BASE_URL = "http://kasimadalan.pe.hu/yemekler/"

        fun getClient(): Retrofit {
            return retrofit ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofit = instance
                instance
            }
        }
    }
}

 */