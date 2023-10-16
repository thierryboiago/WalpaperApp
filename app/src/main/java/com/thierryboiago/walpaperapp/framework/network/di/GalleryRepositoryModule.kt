package com.thierryboiago.walpaperapp.framework.network.di

import com.thierryboiago.core.data.repository.dbrepository.GalleryLocalDataSource
import com.thierryboiago.core.data.repository.dbrepository.GalleryRepository
import com.thierryboiago.walpaperapp.framework.db.repository.GalleryRepositoryImpl
import com.thierryboiago.walpaperapp.framework.local.RoomGalleryDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GalleryRepositoryModule {
    @Binds
    fun bindGalleryRepository(repository: GalleryRepositoryImpl): GalleryRepository

    @Binds
    fun bindGalleryLocalDataSource(roomGalleryDataSource: RoomGalleryDataSource): GalleryLocalDataSource
}