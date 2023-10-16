package com.thierryboiago.walpaperapp.framework.network.di

import android.content.Context
import androidx.room.Room
import com.thierryboiago.core.data.DbConstants
import com.thierryboiago.walpaperapp.framework.db.WallpaperAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DBModule {
    @Provides
    fun provideWallpaperDatabase(@ApplicationContext app: Context): WallpaperAppDatabase =
        Room.databaseBuilder(
            app,
            WallpaperAppDatabase::class.java,
            DbConstants.APP_DATA_BASE_NAME
        ).build()

    @Provides
    fun provideWallpaperDao(database: WallpaperAppDatabase) = database.wallpaperDao()


}