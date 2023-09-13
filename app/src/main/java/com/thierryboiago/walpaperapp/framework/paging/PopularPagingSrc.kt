package com.thierryboiago.walpaperapp.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thierryboiago.core.data.repository.PopularRemoteDataSrc
import com.thierryboiago.core.domain.model.PhotoDomain
import com.thierryboiago.walpaperapp.framework.network.response.DataWrapperResponse
import com.thierryboiago.walpaperapp.framework.network.response.toPhotoDomain
import java.lang.Exception

class PopularPagingSrc(
    private val dataSrc: PopularRemoteDataSrc<DataWrapperResponse>,
    private val pages: Int
) : PagingSource<Int, PhotoDomain>() {
    override fun getRefreshKey(state: PagingState<Int, PhotoDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoDomain> {
        return try {
            val nextPage = params.key ?: PAGE_INDEX
            val popularResponse = dataSrc.fetchPopular(nextPage, pages)

            LoadResult.Page(
                data = popularResponse.photos.map {
                    it.toPhotoDomain()
                },
                prevKey = null,
                nextKey = if (popularResponse.page >= nextPage) nextPage + PAGE_INDEX else null
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    companion object{
        private const val PAGE_INDEX = 1;
    }
}