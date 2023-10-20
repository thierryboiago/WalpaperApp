package com.thierryboiago.walpaperapp.framework.network.di.usecasemodule

import com.thierryboiago.core.usecase.getallphotos.GetAllPhotosUseCase
import com.thierryboiago.core.usecase.getallphotos.GetAllPhotosUseCaseImpl
import com.thierryboiago.core.usecase.insertusecase.InsertPhotoUseCase
import com.thierryboiago.core.usecase.insertusecase.InsertPhotoUseCaseImpl
import com.thierryboiago.core.usecase.popularusecase.GetPopularUseCase
import com.thierryboiago.core.usecase.popularusecase.GetPopularUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindPopularUseCase(useCaseImpl: GetPopularUseCaseImpl): GetPopularUseCase

    @Binds
    fun bindGetAllPhotosUseCaseImpl(getAllPhotosUseCaseImpl: GetAllPhotosUseCaseImpl): GetAllPhotosUseCase

    @Binds
    fun bindInsertPhotoUseCase(useCaseImpl: InsertPhotoUseCaseImpl): InsertPhotoUseCase
}