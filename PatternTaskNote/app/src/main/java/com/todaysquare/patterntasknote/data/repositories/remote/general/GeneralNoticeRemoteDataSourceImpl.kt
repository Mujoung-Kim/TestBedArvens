package com.todaysquare.patterntasknote.data.repositories.remote.general

import com.todaysquare.patterntasknote.data.databases.entities.GeneralNotice
import com.todaysquare.patterntasknote.utils.Constants.Url.Companion.GENE_URL
import com.todaysquare.patterntasknote.utils.Constants.Url.Companion.IMITATION_GEN

import io.reactivex.Observable
import io.reactivex.Single

import org.jsoup.Jsoup

class GeneralNoticeRemoteDataSourceImpl : GeneralNoticeRemoteDataSource {

    override fun requestNotice(): Single<List<GeneralNotice>> =
        Single.fromObservable(
            Observable.create {
                val generalNoticeList = ArrayList<GeneralNotice>()
                val document = Jsoup.connect(GENE_URL).get()
                val contentElements =
                    document.select("div[class=b-title-box]").select("a")
                val idElements = document.select("td[class=b-num-box]")

                for ((i, element) in contentElements.withIndex())
                    generalNoticeList.add(GeneralNotice(idElements[i].text(), element.text(),
                        IMITATION_GEN + element.attr("href")))

                it.onNext(generalNoticeList)
                it.onComplete()

            })

    override fun requestMoreNotice(offset: Int): Single<List<GeneralNotice>> =
        Single.fromObservable(
            Observable.create {
                val generalNoticeList = ArrayList<GeneralNotice>()
                val document = Jsoup
                    .connect("$IMITATION_GEN?mode=list&&articleLimit=10&article.offset=$offset").get()
                val contentElements =
                    document.select("div[class=b-title-box]").select("a")
                val idElements = document.select("td[class=b-num-box]")

                for ((i, element) in contentElements.withIndex())
                    generalNoticeList.add(GeneralNotice(idElements[i].text(), element.text(),
                        IMITATION_GEN + element.attr("href")))

                it.onNext(generalNoticeList)
                it.onComplete()

            })

}