package com.thierryboiago.walpaperapp.framework.network.di.usecasemodule

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
}