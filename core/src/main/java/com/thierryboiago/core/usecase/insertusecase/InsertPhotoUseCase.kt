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
    suspend operator fun invoke(params: Unit = Unit): ResultStatus<Unit>
}
class InsertPhotoUseCaseImpl @Inject constructor(
    private val repository: GalleryRepository,
    private val dispatcher: CoroutinesDispatchers

): UseCase<Unit, Unit>(), InsertPhotoUseCase {
    override suspend fun doWork(params: Unit): ResultStatus<Unit> {
        TODO("Not yet implemented")
    }


}