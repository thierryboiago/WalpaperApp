package com.thierryboiago.core.data.repository

interface PopularRemoteDataSrc<T> {
    suspend fun fetchPopular(page: Int, perPage: Int):T
}