package com.thierryboiago.core.usecase.insertusecase

import com.thierryboiago.core.data.repository.dbrepository.GalleryRepository
import com.thierryboiago.core.domain.model.PhotoDomain
import com.thierryboiago.core.usecase.base.CoroutinesDispatchers
import com.thierryboiago.core.usecase.base.FlowUseCase
import com.thierryboiago.core.usecase.base.ResultStatus
import com.thierryboiago.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject



interface InsertPhotoUseCase{
     operator fun invoke(params: Params): Flow<ResultStatus<Unit>>
     data class Params(
         val photoDomain: PhotoDomain
     )
}
class InsertPhotoUseCaseImpl @Inject constructor(
    private val repository: GalleryRepository,
    private val dispatcher: CoroutinesDispatchers

): UseCase<InsertPhotoUseCase.Params, Unit>(), InsertPhotoUseCase {
    override suspend fun doWork(params: InsertPhotoUseCase.Params): ResultStatus<Unit> = withContext(dispatcher.io()){
        repository.insert(photoDomain = params.photoDomain)
        ResultStatus.Success(Unit)
    }


}