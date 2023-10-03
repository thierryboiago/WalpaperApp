package com.thierryboiago.walpaperapp.framework.network.di.repositorymodule

import com.thierryboiago.core.data.repository.PopularRemoteDataSrc
import com.thierryboiago.core.data.repository.PopularRepository
import com.thierryboiago.walpaperapp.framework.network.response.DataWrapperResponse
import com.thierryboiago.walpaperapp.framework.remote.PopularRemoteDataSourceImpl
import com.thierryboiago.walpaperapp.framework.repository.PopularRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface WallpaperRepositoryModule {

    @Binds
    fun bindPopularRepository(repository: PopularRepositoryImpl): PopularRepository

    @Binds
    fun  bindPopularRemoteDataSource(dataSrc: PopularRemoteDataSourceImpl): PopularRemoteDataSrc<DataWrapperResponse>
}