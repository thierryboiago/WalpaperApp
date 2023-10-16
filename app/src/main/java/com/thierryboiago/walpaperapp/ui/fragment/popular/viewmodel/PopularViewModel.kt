package com.thierryboiago.walpaperapp.ui.fragment.popular.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.thierryboiago.core.domain.model.PhotoDomain
import com.thierryboiago.core.usecase.insertusecase.InsertPhotoUseCase
import com.thierryboiago.core.usecase.popularusecase.GetPopularUseCase
import com.thierryboiago.walpaperapp.extensions.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularUseCase: GetPopularUseCase,
    private val insertPhotoUseCase: InsertPhotoUseCase
) : ViewModel() {

    fun popularWallpaper(): Flow<PagingData<PhotoDomain>> {
        return popularUseCase(
            GetPopularUseCase.GetPopularParams(getPagingConfig())
        ).cachedIn(viewModelScope)
    }

    private val _favoriteUiState = MutableLiveData<FavoriteUiState>()
    val favoriteUiState: LiveData<FavoriteUiState> get() = _favoriteUiState
    private fun getPagingConfig() = PagingConfig(pageSize = 40)

    fun favoritePhoto(photoDomain: PhotoDomain) = viewModelScope.launch {
        photoDomain.run {
            insertPhotoUseCase(InsertPhotoUseCase.Params(this)).watchStatus(
                loading = { _favoriteUiState.value = FavoriteUiState.Loading },
                success = { _favoriteUiState.value = FavoriteUiState.FavoritePhoto(true) },
                error = { _favoriteUiState.value = FavoriteUiState.FavoritePhoto(false) }
            )
        }
    }

    sealed class FavoriteUiState {
        object Loading : FavoriteUiState()

        class FavoritePhoto(
            val saved: Boolean
        ) : FavoriteUiState()

        class FavoriteIcon(@DrawableRes val icon: Int) : FavoriteUiState()
    }
}