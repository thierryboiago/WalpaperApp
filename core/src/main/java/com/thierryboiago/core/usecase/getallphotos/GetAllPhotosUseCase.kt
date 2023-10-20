package com.thierryboiago.core.usecase.getallphotos

import com.thierryboiago.core.data.repository.dbrepository.GalleryRepository
import com.thierryboiago.core.domain.model.PhotoDomain
import com.thierryboiago.core.usecase.base.CoroutinesDispatchers
import com.thierryboiago.core.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetAllPhotosUseCase{
    suspend operator fun invoke(params: Unit = Unit): Flow<List<PhotoDomain>>
}
class GetAllPhotosUseCaseImpl @Inject constructor(
    private val repository: GalleryRepository,
    private val dispatcher: CoroutinesDispatchers

): FlowUseCase<Unit, List<PhotoDomain>>(), GetAllPhotosUseCase {
    override suspend fun createFlowObservable(params: Unit): Flow<List<PhotoDomain>> =
        withContext(dispatcher.io()){
            repository.getAllWallpapers()
        }



}