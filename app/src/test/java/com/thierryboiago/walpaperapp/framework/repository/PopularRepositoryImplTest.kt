package com.thierryboiago.walpaperapp.framework.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.thierryboiago.core.data.repository.PopularRemoteDataSrc
import com.thierryboiago.core.data.repository.PopularRepository
import com.thierryboiago.core.domain.model.PhotoDomain
import com.thierryboiago.core.usecase.popularusecase.GetPopularUseCase
import com.thierryboiago.testing.coroutinerule.MainCoroutineRule
import com.thierryboiago.testing.model.WallpaperFactory
import com.thierryboiago.testing.pagingsource.PagingSourceFactory
import com.thierryboiago.walpaperapp.factory.DataWrapperResponseFactory
import com.thierryboiago.walpaperapp.framework.network.response.DataWrapperResponse
import com.thierryboiago.walpaperapp.framework.paging.PopularPagingSrc
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class PopularRepositoryImplTest{
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var popularRemoteDataSrc: PopularRemoteDataSrc<DataWrapperResponse>

    private lateinit var popularRepository: PopularRepository

    private val photos = WallpaperFactory().create(WallpaperFactory.Photo.PhotoDomainSuccess)

    private val mockPagingSource = PagingSourceFactory().create(listOf(photos))
    private val dataWrapperResponseFactory = DataWrapperResponseFactory()
    @Before
    fun setup(){
        popularRepository = PopularRepositoryImpl(popularRemoteDataSrc)
    }
    fun popularPaging() = PopularPagingSrc(popularRemoteDataSrc, 40
    )
    @Test
    fun `should return a success load result when load is called`() = runTest{
        //whenever(PopularPagingSrc(any(), any())).thenReturn(popularPaging())

        val result = popularRepository.fetchPopular(
            40
        )

        val expected = popularPaging()

        assertEquals(
            expected,
            result
        )
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() = runTest {
        whenever(popularRemoteDataSrc.fetchPopular(any(), any())).thenReturn(dataWrapperResponseFactory.create())
        //whenever(repository.fetchPopular(40)).thenReturn(mockPagingSource)

        //val result = getPopularUseCase.invoke(GetPopularUseCase.GetPopularParams(PagingConfig(40)))



        val result = popularRepository.fetchPopular(
            40
        )



        assertNotNull(result)
    }

    @Test(expected = RuntimeException::class)
    fun `Should return an empty PagingData When an error occurred`() = runTest{
        whenever(popularRepository.fetchPopular(any())).thenThrow(RuntimeException())

        popularRepository.fetchPopular(40)


    }

}