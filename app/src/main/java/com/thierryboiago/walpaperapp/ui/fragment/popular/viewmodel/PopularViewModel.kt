package com.thierryboiago.walpaperapp.ui.fragment.popular.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.thierryboiago.core.domain.model.PhotoDomain
import com.thierryboiago.core.usecase.popularusecase.GetPopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularUseCase: GetPopularUseCase
) : ViewModel() {

    fun popularWallpaper(): Flow<PagingData<PhotoDomain>> {
        return popularUseCase(
            GetPopularUseCase.GetPopularParams(getPagingConfig())
        ).cachedIn(viewModelScope)
    }

    private fun getPagingConfig() = PagingConfig(pageSize = 40)
}