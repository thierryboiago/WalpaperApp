package com.thierryboiago.testing.popularpagingsrc

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thierryboiago.core.domain.model.PhotoDomain

class PopularPagingSrcFactory {
    fun create(photos: List<PhotoDomain>) = object: PagingSource<Int, PhotoDomain>(){
        override fun getRefreshKey(state: PagingState<Int, PhotoDomain>): Int = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoDomain> =
            LoadResult.Page(
                data = photos,
                prevKey = null,
                nextKey = 40
            )

    }

}