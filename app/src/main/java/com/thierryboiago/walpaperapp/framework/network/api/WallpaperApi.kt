package com.thierryboiago.walpaperapp.framework.network.api

import com.thierryboiago.walpaperapp.framework.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WallpaperApi {
    @GET("v1/curated")
    suspend fun getPopularWallpapers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : DataWrapperResponse
}