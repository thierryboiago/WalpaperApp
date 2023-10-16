package com.thierryboiago.walpaperapp.framework.db.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thierryboiago.core.data.DbConstants
import com.thierryboiago.core.domain.model.PhotoDomain
import com.thierryboiago.core.domain.model.SrcDomain

@Entity(tableName = DbConstants.PHOTO_TABLE_NAME)
data class PhotoEntity(
    @PrimaryKey
    val id: Int,
    val urlPhoto: String,
    val smallPhoto: String,
    val photographer: String,
    val avgColor: String
)


fun List<PhotoEntity>.toPhotoDomain() = map {
    PhotoDomain(
        id = it.id,
        photographer = it.photographer,
        avgColor = it.avgColor,
        srcDomain = SrcDomain(original = it.urlPhoto, small = it.smallPhoto)
    )
}