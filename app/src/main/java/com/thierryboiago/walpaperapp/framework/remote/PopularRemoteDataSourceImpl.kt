package com.thierryboiago.walpaperapp.framework.remote

import com.thierryboiago.core.data.repository.PopularRemoteDataSrc
import com.thierryboiago.walpaperapp.framework.network.api.WallpaperApi
import com.thierryboiago.walpaperapp.framework.network.response.DataWrapperResponse

class PopularRemoteDataSourceImpl(
    private val api: WallpaperApi
): PopularRemoteDataSrc<DataWrapperResponse> {
    override suspend fun fetchPopular(page: Int, perPage: Int): DataWrapperResponse = api.getPopularWallpapers(page, perPage)
}