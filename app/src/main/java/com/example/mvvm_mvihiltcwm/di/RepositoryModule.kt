package com.example.mvvm_mvihiltcwm.di

import com.example.mvvm_mvihiltcwm.repository.MainRepository
import com.example.mvvm_mvihiltcwm.retrofit.BlogRetrofit
import com.example.mvvm_mvihiltcwm.room.BlogDao
import com.example.mvvm_mvihiltcwm.room.CacheMapper
import com.example.mvvm_mvihiltcwm.util.NetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDao, retrofit, cacheMapper, networkMapper)
    }
}