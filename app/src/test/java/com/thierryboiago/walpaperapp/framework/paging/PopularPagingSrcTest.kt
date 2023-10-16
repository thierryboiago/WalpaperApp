package com.thierryboiago.walpaperapp.framework.paging

import androidx.paging.PagingSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.thierryboiago.core.data.repository.PopularRemoteDataSrc
import com.thierryboiago.core.domain.model.PhotoDomain
import com.thierryboiago.testing.coroutinerule.MainCoroutineRule
import com.thierryboiago.testing.model.WallpaperFactory
import com.thierryboiago.walpaperapp.factory.DataWrapperResponseFactory
import com.thierryboiago.walpaperapp.framework.network.response.DataWrapperResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PopularPagingSrcTest{

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var remoteDataSource: PopularRemoteDataSrc<DataWrapperResponse>

    private lateinit var pagingSrc: PopularPagingSrc

    private val photos = WallpaperFactory().create(WallpaperFactory.Photo.PhotoDomainSuccess)

    private val dataWrapperResponseFactory = DataWrapperResponseFactory()

    @Before
    fun setup(){
        pagingSrc = PopularPagingSrc(remoteDataSource, 40)
    }

    @Test
    fun `should return a success load result when load is called`() = runTest{
        whenever(remoteDataSource.fetchPopular(any(), any())).thenReturn(dataWrapperResponseFactory.create())

        val result = pagingSrc.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val expected = listOf(photos, photos)

        assertEquals(
            PagingSource.LoadResult.Page(data = expected, prevKey = null, nextKey = 2),
            result
        )
    }

    @Test
    fun `should return error load result when load is called`() = runTest {
        val exception = RuntimeException()

        whenever(remoteDataSource.fetchPopular(any(), any())).thenThrow(exception)

        val result = pagingSrc.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertEquals(
            PagingSource.LoadResult.Error<Int, PhotoDomain>(exception),
            result
        )
    }
}