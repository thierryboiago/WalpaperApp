package com.thierryboiago.walpaperapp.ui.fragment.popular.viewmodel

import androidx.paging.PagingData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.thierryboiago.core.usecase.insertusecase.InsertPhotoUseCase
import com.thierryboiago.core.usecase.popularusecase.GetPopularUseCase
import com.thierryboiago.testing.coroutinerule.MainCoroutineRule
import com.thierryboiago.testing.model.WallpaperFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
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
class PopularViewModelTest{

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var popularUseCase: GetPopularUseCase

    @Mock
    lateinit var insertPhotoUseCase: InsertPhotoUseCase

    lateinit var popularViewModel: PopularViewModel

    private val wallpaperFactory = WallpaperFactory()

    @Before
    fun setup(){
        popularViewModel = PopularViewModel(popularUseCase, insertPhotoUseCase)
    }

    @Test
    fun `Should val idate pagi  nati    on data`() = runTest {
        //Arrange
        whenever(popularUseCase(any())).thenReturn(flowOf(getPagingDataMock))

        //Act

        val result = popularViewModel.popularWallpaper()
        //Assert
        assertNotNull(result.first())
    }

    @Test(expected = RuntimeException::class)
    fun `Should return an empty PagingData When an error occurred`() = runTest{
        whenever(popularUseCase(any())).thenThrow(RuntimeException())

        popularViewModel.popularWallpaper()


    }

    private val getPagingDataMock = PagingData.from(
        listOf(
            wallpaperFactory.create(WallpaperFactory.Photo.PhotoDomainSuccess),
            wallpaperFactory.create(WallpaperFactory.Photo.PhotoDomainSuccess)

        )
    )
}