package com.thierryboiago.core.data.repository

import androidx.paging.PagingSource
import com.thierryboiago.core.domain.model.PhotoDomain

interface PopularRepository {
    fun fetchPopular(pages: Int): PagingSource<Int, PhotoDomain>
}