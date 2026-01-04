package com.prilepskiy.domain.pointUseCase

import com.prilepskiy.data.repository.NoteRepository
import com.prilepskiy.data.repository.PointRepository
import com.prilepskiy.data.repository.StageRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeletePointUseCase @Inject constructor(
    private val repository: PointRepository,
    private val repositoryNote: NoteRepository,
    private val repositoryStage: StageRepository
) {
    suspend operator fun invoke(id: Long) {
        repositoryNote.deleteNotesForPoint(id)
        repository.deletePoint(id)
        repositoryStage.deleteStageForPoint(id)
    }
}