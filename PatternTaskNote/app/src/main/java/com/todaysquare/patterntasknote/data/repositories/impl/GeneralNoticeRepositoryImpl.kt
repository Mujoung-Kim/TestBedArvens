package com.todaysquare.patterntasknote.data.repositories.impl

import com.todaysquare.patterntasknote.data.databases.entities.GeneralNotice
import com.todaysquare.patterntasknote.data.repositories.GeneralNoticeRepository
import com.todaysquare.patterntasknote.data.repositories.local.general.GeneralNoticeLocalDataSource
import com.todaysquare.patterntasknote.data.repositories.remote.general.GeneralNoticeRemoteDataSource

import io.reactivex.Flowable
import io.reactivex.Single

class GeneralNoticeRepositoryImpl(private val generalNoticeRemoteDataSource: GeneralNoticeRemoteDataSource,
    private val generalNoticeLocalDataSource: GeneralNoticeLocalDataSource) : GeneralNoticeRepository {

    override fun requestNotice(): Flowable<List<GeneralNotice>> =
        generalNoticeLocalDataSource.getNotice()
            .onErrorReturn { listOf() }
            .flatMapPublisher { cachedMovies ->
                if (cachedMovies.isEmpty())
                    requestGeneralNotice()
                        .toFlowable()
                        .onErrorReturn { listOf() }
                else {
                    val local = Single.just(cachedMovies)
                    val remote = requestGeneralNotice()
                        .onErrorResumeNext { local }

                    Single.concat(local, remote)

                }
            }

    override fun requestMoreNotice(offset: Int): Single<List<GeneralNotice>> =
        generalNoticeRemoteDataSource.requestMoreNotice(offset)

    private fun requestGeneralNotice(): Single<List<GeneralNotice>> =
        generalNoticeRemoteDataSource.requestNotice()
            .flatMap { generalNoticeLocalDataSource.insertNotice(it).andThen(Single.just(it)) }

}