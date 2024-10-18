package com.aydinkaya.saporiveloce.di


import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.aydinkaya.saporiveloce.data.datasource.YemeklerDataSource
import com.aydinkaya.saporiveloce.data.repo.YemeklerRepository
import com.example.graduationproject.retrofit.ApiUtils
import com.example.graduationproject.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideYemeklerDao(): YemeklerDao {
        return ApiUtils.getYemeklerDao()
    }

    @Provides
    @Singleton
    fun provideYemeklerDataSource(ydao: YemeklerDao): YemeklerDataSource {
        return YemeklerDataSource(ydao)
    }

    @Provides
    @Singleton
    fun provideYemeklerRepository(yds: YemeklerDataSource): YemeklerRepository {
        return YemeklerRepository(yds)
    }


}
