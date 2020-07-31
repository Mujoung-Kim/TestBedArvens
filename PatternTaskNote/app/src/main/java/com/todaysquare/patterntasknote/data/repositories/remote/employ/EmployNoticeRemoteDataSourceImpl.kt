package com.todaysquare.patterntasknote.data.repositories.remote.employ

import com.todaysquare.patterntasknote.data.databases.entities.EmployNotice
import com.todaysquare.patterntasknote.utils.Constants.Url.Companion.EMPL_URL
import com.todaysquare.patterntasknote.utils.Constants.Url.Companion.IMITATION_EMP

import io.reactivex.Observable
import io.reactivex.Single

import org.jsoup.Jsoup

class EmployNoticeRemoteDataSourceImpl : EmployNoticeRemoteDataSource {

    override fun requestNotice(): Single<List<EmployNotice>> =
        Single.fromObservable(
            Observable.create {
                val noticeList = ArrayList<EmployNotice>()
                val document = Jsoup.connect(EMPL_URL).get()
                val contentElements = document.select("div[class=b-title-box]").select("a")
                val idElements = document.select("td[class=b-num-box]")

                for ((i, element) in contentElements.withIndex())
                    noticeList.add(EmployNotice(idElements[i].text(), element.text(),
                        IMITATION_EMP + element.attr("href")))

                it.onNext(noticeList)
                it.onComplete()

            })

    override fun requestMoreNotice(offset: Int): Single<List<EmployNotice>> =
        Single.fromObservable(
            Observable.create {
                val noticeList = ArrayList<EmployNotice>()
                val document = Jsoup
                    .connect("$IMITATION_EMP?mode=list&&articleLimit=10&article.offset=$offset").get()
                val contentElements = document.select("div[class=b-title-box]").select("a")
                val idElements = document.select("td[class=b-num-box]")

                for ((i, element) in contentElements.withIndex())
                    if (idElements[i].text() != "공지")
                        noticeList.add(EmployNotice(idElements[i].text(), element.text(),
                            IMITATION_EMP + element.attr("href")))

                it.onNext(noticeList)
                it.onComplete()

            })

}