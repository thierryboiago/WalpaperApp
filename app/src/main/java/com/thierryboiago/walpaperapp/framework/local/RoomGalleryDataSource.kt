package com.thierryboiago.walpaperapp.framework.local

import com.thierryboiago.core.data.repository.dbrepository.GalleryLocalDataSource
import com.thierryboiago.core.domain.model.PhotoDomain
import com.thierryboiago.walpaperapp.framework.db.Entity.PhotoEntity
import com.thierryboiago.walpaperapp.framework.db.Entity.toPhotoDomain
import com.thierryboiago.walpaperapp.framework.db.dao.WallpaperDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomGalleryDataSource @Inject constructor(
    private val dao: WallpaperDao
): GalleryLocalDataSource {

    override suspend fun getAllWallpapers(): Flow<List<PhotoDomain>> =
        dao.getAllPhotos().map {
            it.toPhotoDomain()
        }


    override suspend fun insert(photoDomain: PhotoDomain) {
        dao.insert(photoDomain.toEntity())
    }

    override suspend fun delete(photoDomain: PhotoDomain) {
       dao.delete(photoDomain.toEntity())
    }
}

private fun PhotoDomain.toEntity() =
    PhotoEntity(
        id = this.id ?: 0,
        urlPhoto = this.srcDomain?.original ?: "",
        smallPhoto = this.srcDomain?.small ?: "",
        avgColor = this.avgColor ?: "",
        photographer = this.photographer ?: ""
    )