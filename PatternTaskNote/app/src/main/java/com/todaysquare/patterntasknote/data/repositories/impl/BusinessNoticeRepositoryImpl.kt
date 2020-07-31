package com.todaysquare.patterntasknote.data.repositories.impl

import com.todaysquare.patterntasknote.data.databases.entities.BusinessNotice
import com.todaysquare.patterntasknote.data.repositories.BusinessNoticeRepository
import com.todaysquare.patterntasknote.data.repositories.local.business.BusinessNoticeLocalDataSource
import com.todaysquare.patterntasknote.data.repositories.remote.business.BusinessNoticeRemoteDataSource

import io.reactivex.Flowable
import io.reactivex.Single

class BusinessNoticeRepositoryImpl(private val noticeRemoteDataSource: BusinessNoticeRemoteDataSource,
    private val noticeLocalDataSource: BusinessNoticeLocalDataSource) : BusinessNoticeRepository {

    override fun requestNotice(): Flowable<List<BusinessNotice>> =
        noticeLocalDataSource.getNotice()
            .onErrorReturn { listOf() }
            .flatMapPublisher { cachedMovies ->
                if (cachedMovies.isEmpty())
                    requestBusinessNotice()
                        .toFlowable()
                        .onErrorReturn { listOf() }
                else {
                    val local = Single.just(cachedMovies)
                    val remote = requestBusinessNotice()
                        .onErrorResumeNext { local }

                    Single.concat(local, remote)

                }
            }

    override fun requestMoreNotice(offset: Int): Single<List<BusinessNotice>> =
        noticeRemoteDataSource.requestMoreNotice(offset)

    private fun requestBusinessNotice(): Single<List<BusinessNotice>> =
        noticeRemoteDataSource.requestNotice()
            .flatMap { noticeLocalDataSource.insertNotice(it).andThen(Single.just(it))}

}