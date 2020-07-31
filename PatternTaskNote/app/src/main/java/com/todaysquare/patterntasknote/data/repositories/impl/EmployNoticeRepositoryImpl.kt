package com.todaysquare.patterntasknote.data.repositories.impl

import com.todaysquare.patterntasknote.data.databases.entities.EmployNotice
import com.todaysquare.patterntasknote.data.repositories.EmployNoticeRepository
import com.todaysquare.patterntasknote.data.repositories.local.employ.EmployNoticeLocalDataSource
import com.todaysquare.patterntasknote.data.repositories.remote.employ.EmployNoticeRemoteDataSource

import io.reactivex.Flowable
import io.reactivex.Single

class EmployNoticeRepositoryImpl(private val noticeRemoteDataSource: EmployNoticeRemoteDataSource,
    private val noticeLocalDataSource: EmployNoticeLocalDataSource) : EmployNoticeRepository {

    override fun requestNotice(): Flowable<List<EmployNotice>> =
        noticeLocalDataSource.getNotice()
            .onErrorReturn { listOf() }
            .flatMapPublisher { cacheMovies ->
                if (cacheMovies.isEmpty())
                    requestBusinessNotice()
                        .toFlowable()
                        .onErrorReturn { listOf() }

                else {
                    val local = Single.just(cacheMovies)
                    val remote = requestBusinessNotice()
                        .onErrorResumeNext { local }

                    Single.concat(local, remote)

                }
            }

    override fun requestMoreNotice(offset: Int): Single<List<EmployNotice>> =
        noticeRemoteDataSource.requestMoreNotice(offset)

    private fun requestBusinessNotice(): Single<List<EmployNotice>> =
        noticeRemoteDataSource.requestNotice()
            .flatMap {
                noticeLocalDataSource.insertNotice(it)
                    .andThen(Single.just(it))

            }

}