package com.aydinkaya.saporiveloce.di

import com.aydinkaya.saporiveloce.data.repo.YemekRepository
import com.aydinkaya.saporiveloce.retrofit.YemekDao
import com.aydinkaya.saporiveloce.data.datasource.YemekDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "http://kasimadalan.pe.hu/yemekler/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideYemekDao(retrofit: Retrofit): YemekDao {
        return retrofit.create(YemekDao::class.java)
    }

    @Provides
    @Singleton
    fun provideYemekDataSource(yemekDao: YemekDao): YemekDataSource {
        return YemekDataSource(yemekDao)
    }

    @Provides
    @Singleton
    fun provideYemekRepository(yemekDataSource: YemekDataSource): YemekRepository {
        return YemekRepository(yemekDataSource)
    }
}


/*
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "http://kasimadalan.pe.hu/yemekler/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideYemekDao(retrofit: Retrofit): YemekDao {
        return retrofit.create(YemekDao::class.java)
    }

}

 */