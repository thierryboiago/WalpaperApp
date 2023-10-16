package com.thierryboiago.walpaperapp.framework.repository

import androidx.paging.PagingSource
import com.thierryboiago.core.data.repository.PopularRemoteDataSrc
import com.thierryboiago.core.data.repository.PopularRepository
import com.thierryboiago.core.domain.model.PhotoDomain
import com.thierryboiago.walpaperapp.framework.network.response.DataWrapperResponse
import com.thierryboiago.walpaperapp.framework.paging.PopularPagingSrc
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val remoteDataSrc: PopularRemoteDataSrc<DataWrapperResponse>
):PopularRepository {
    override fun fetchPopular(pages: Int): PagingSource<Int, PhotoDomain> = PopularPagingSrc(remoteDataSrc, pages)
}