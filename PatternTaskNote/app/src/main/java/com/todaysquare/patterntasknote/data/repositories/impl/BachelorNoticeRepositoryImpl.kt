package com.todaysquare.patterntasknote.data.repositories.impl

import com.todaysquare.patterntasknote.data.databases.entities.BachelorNotice
import com.todaysquare.patterntasknote.data.repositories.BachelorNoticeRepository
import com.todaysquare.patterntasknote.data.repositories.local.BachelorNoticeLocalDataSource
import com.todaysquare.patterntasknote.data.repositories.remote.BachelorNoticeRemoteDataSource

import io.reactivex.Flowable
import io.reactivex.Single

class BachelorNoticeRepositoryImpl(private val bachelorNoticeRemoteDataSource: BachelorNoticeRemoteDataSource,
    private val bachelorNoticeLocalDataSource: BachelorNoticeLocalDataSource) : BachelorNoticeRepository {

    override fun requestNotice(): Flowable<List<BachelorNotice>> =
        bachelorNoticeLocalDataSource.getNotice()
        .onErrorReturn { listOf() }
        .flatMapPublisher { cachedMovies ->
            if (cachedMovies.isEmpty())
                requestBachelorNotice()
                    .toFlowable()
                    .onErrorReturn { listOf() }
            else {
                val local = Single.just(cachedMovies)
                val remote = requestBachelorNotice()
                    .onErrorResumeNext { local }

                Single.concat(local, remote)
            }
        }

    override fun requestMoreRequest(offset: Int): Single<List<BachelorNotice>> =
        bachelorNoticeRemoteDataSource.requestMoreNotice(offset)

    private fun requestBachelorNotice(): Single<List<BachelorNotice>> =
        bachelorNoticeRemoteDataSource.requestNotice()
            .flatMap {
                bachelorNoticeLocalDataSource.insertNotice(it)
                    .andThen(Single.just(it))
            }

}