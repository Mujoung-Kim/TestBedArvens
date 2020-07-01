package com.todaysquare.dagger2_example.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import com.todaysquare.dagger2_example.R
import com.todaysquare.dagger2_example.ui.viewmodels.StockViewModel

import kotlinx.android.synthetic.main.fragment_stock.*

class StockFragment : Fragment() {
    private val stockViewModel: StockViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_stock, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chart1.setPinchZoom(true)
        chart1.setDrawGridBackground(false)
        chart1.legend.setDrawInside(false)

//        val arrayEntry = ArrayList<CandleEntry>()
        val arrayEntry1 = ArrayList<Entry>()
        val arrayEntry2 = ArrayList<Entry>()
        val arrayEntry3 = ArrayList<Entry>()
        val arrayEntry4 = ArrayList<Entry>()
        var set1: LineDataSet
        var set2: LineDataSet
        var set3: LineDataSet
        var set4: LineDataSet

        stockViewModel.getMutableCryptoData()
            .observe(this, Observer { data ->
                /*for (x in data.Data.Data)
                    arrayEntry.add(
                        CandleEntry(
                            x.time.toFloat(),
                            x.high.toFloat(),
                            x.close.toFloat(),
                            x.open.toFloat(),
                            x.low.toFloat()
                        )
                    )*/

                    for (x in data.Data.Data) {
                    arrayEntry1.add(Entry(x.time.toFloat(), x.high.toFloat()))
                    arrayEntry2.add(Entry(x.time.toFloat(), x.low.toFloat()))
                    arrayEntry3.add(Entry(x.time.toFloat(), x.close.toFloat()))
                    arrayEntry4.add(Entry(x.time.toFloat(), x.open.toFloat()))

                }

                set1 = LineDataSet(arrayEntry1, "High")
                set2 = LineDataSet(arrayEntry2, "Low")
                set3 = LineDataSet(arrayEntry3, "Close")
                set4 = LineDataSet(arrayEntry4, "Open")

                set1.axisDependency = YAxis.AxisDependency.LEFT
                set1.color = ColorTemplate.rgb("0000CC")
                set1.setCircleColor(Color.BLUE)
                set1.lineWidth = 2f
                set1.circleRadius = 3f
                set1.fillAlpha = 65
                set1.fillColor = ColorTemplate.getHoloBlue()
                set1.highLightColor = Color.rgb(0, 0, 200)
                set1.setDrawCircleHole(true)

                set2.axisDependency = YAxis.AxisDependency.LEFT
                set2.color = ColorTemplate.rgb("CC0000")
                set2.setCircleColor(Color.RED)
                set2.lineWidth = 2f
                set2.circleRadius = 3f
                set2.fillAlpha = 65
                set2.fillColor = ColorTemplate.getHoloBlue()
                set2.highLightColor = Color.rgb(200, 0, 0)
                set2.setDrawCircleHole(true)

                set3.axisDependency = YAxis.AxisDependency.LEFT
                set3.color = ColorTemplate.rgb("A9A9A9")
                set3.setCircleColor(Color.DKGRAY)
                set3.lineWidth = 2f
                set3.circleRadius = 3f
                set3.fillAlpha = 65
                set3.fillColor = ColorTemplate.getHoloBlue()
                set3.highLightColor = Color.rgb(170, 170, 170)
                set3.setDrawCircleHole(true)

                set4.axisDependency = YAxis.AxisDependency.LEFT
                set4.color = ColorTemplate.rgb("00CC00")
                set4.setCircleColor(Color.GREEN)
                set4.lineWidth = 2f
                set4.circleRadius = 3f
                set4.fillAlpha = 65
                set4.fillColor = ColorTemplate.getHoloBlue()
                set4.highLightColor = Color.rgb(0, 200, 0)
                set4.setDrawCircleHole(true)

                val lineData = LineData(set1, set2, set3, set4)

                lineData.setValueTextColor(Color.DKGRAY)
                lineData.setValueTextSize(9f)

                // set data
                chart1.data = lineData
                chart1.invalidate()

//                val dataSet = CandleDataSet(arrayEntry, "BitCoin")
//
//                dataSet.setDrawIcons(false)
//                dataSet.axisDependency = YAxis.AxisDependency.LEFT
//                dataSet.shadowColor = Color.BLUE
//
//                val candleData = CandleData(dataSet)
//
//                chart1.data = candleData
//                chart1.invalidate()

            })

    }
}
