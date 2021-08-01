package com.example.mvvm_mvihiltcwm.repository

import com.example.mvvm_mvihiltcwm.model.Blog
import com.example.mvvm_mvihiltcwm.retrofit.BlogNetworkEntity
import com.example.mvvm_mvihiltcwm.retrofit.BlogRetrofit
import com.example.mvvm_mvihiltcwm.room.BlogCacheEntity
import com.example.mvvm_mvihiltcwm.room.BlogDao
import com.example.mvvm_mvihiltcwm.room.CacheMapper
import com.example.mvvm_mvihiltcwm.util.DataState
import com.example.mvvm_mvihiltcwm.util.NetworkMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){

    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000L)

        try {
            val networkBlogs: List<BlogNetworkEntity> = blogRetrofit.get()
            val blogs: List<Blog> = networkMapper.mapFromEntityList(networkBlogs)

            for(blog in blogs) {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }

            val cachedBlogs: List<BlogCacheEntity> = blogDao.get()
            emit(DataState.Success<List<Blog>>(cacheMapper.mapFromEntityList(cachedBlogs)))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}
