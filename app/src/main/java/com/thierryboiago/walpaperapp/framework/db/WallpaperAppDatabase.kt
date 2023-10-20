package com.thierryboiago.walpaperapp.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thierryboiago.walpaperapp.framework.db.Entity.PhotoEntity
import com.thierryboiago.walpaperapp.framework.db.dao.WallpaperDao

@Database(entities = [
    PhotoEntity::class
], version = 1, exportSchema = false)
abstract class WallpaperAppDatabase : RoomDatabase() {
    abstract fun wallpaperDao(): WallpaperDao
}