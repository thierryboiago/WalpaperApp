package com.thierryboiago.core.usecase.popularusecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.thierryboiago.core.data.repository.PopularRepository
import com.thierryboiago.core.domain.model.PhotoDomain
import com.thierryboiago.testing.coroutinerule.MainCoroutineRule
import com.thierryboiago.testing.model.WallpaperFactory
import com.thierryboiago.testing.pagingsource.PagingSourceFactory
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
class GetPopularUseCaseImplTest{

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: PopularRepository

    private lateinit var getPopularUseCase: GetPopularUseCase

    private val photos = WallpaperFactory().create(WallpaperFactory.Photo.PhotoDomainSuccess)

    private val mockPagingSource = PagingSourceFactory().create(listOf(photos))

    @Before
    fun setup(){
        getPopularUseCase = GetPopularUseCaseImpl(repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() = runTest {
        whenever(repository.fetchPopular(40)).thenReturn(mockPagingSource)

        val result = getPopularUseCase.invoke(GetPopularUseCase.GetPopularParams(PagingConfig(40)))

        verify(repository).fetchPopular(40)

        assertNotNull(result.first())
    }


    @Test(expected = RuntimeException::class)
    fun `Should return an empty PagingData When an error occurred`() = runTest{
        whenever(repository.fetchPopular(any())).thenThrow(RuntimeException())

        getPopularUseCase.invoke(GetPopularUseCase.GetPopularParams(PagingConfig(40)))


    }



}