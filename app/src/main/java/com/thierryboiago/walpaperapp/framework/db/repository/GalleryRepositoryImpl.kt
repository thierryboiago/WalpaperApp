package com.thierryboiago.walpaperapp.framework.db.repository

import com.thierryboiago.core.data.repository.dbrepository.GalleryLocalDataSource
import com.thierryboiago.core.data.repository.dbrepository.GalleryRepository
import com.thierryboiago.core.domain.model.PhotoDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val dataSource: GalleryLocalDataSource
): GalleryRepository {

    override suspend fun getAllWallpapers(): Flow<List<PhotoDomain>> = dataSource.getAllWallpapers()

    override suspend fun insert(photoDomain: PhotoDomain) = dataSource.insert(photoDomain)

    override suspend fun delete(photoDomain: PhotoDomain) = dataSource.delete(photoDomain)
}