package com.example.thesis

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thesis.databinding.ActivityHomeScreenBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate


class HomeScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        saveWaterQualityData()
        retrieveRecordsAndPopulateCharts()

    }

        fun saveWaterQualityData () {
            val databaseHandler : WaterQualityDatabaseHandler = WaterQualityDatabaseHandler(this)
            val record1 = databaseHandler.addWaterQualityData(WaterQualityDataModel(
                1, 6.7, 32.0, 3.2))
            val record2 = databaseHandler.addWaterQualityData(WaterQualityDataModel(
                2, 7.8, 33.5, 4.5))
            val record3 = databaseHandler.addWaterQualityData(WaterQualityDataModel(
                3, 8.4, 34.1, 5.7))
            val record4 = databaseHandler.addWaterQualityData(WaterQualityDataModel(
                4, 5.5, 30.7, 6.8))
            val record5 = databaseHandler.addWaterQualityData(WaterQualityDataModel(
                5, 9.6, 35.9, 7.9))
        }


        fun populateLineChart(values: Array<Double>) {
            val ourLineChartEntries : ArrayList<Entry> = ArrayList()

            var i = 0

            for (entry in values) {
                var value = values[i].toFloat()

                ourLineChartEntries.add(Entry(i.toFloat(), value))
                i++
            }

            val lineDataSet = LineDataSet(ourLineChartEntries, "")
            lineDataSet.setColors(*ColorTemplate.PASTEL_COLORS)
            val data = LineData(lineDataSet)
            binding.ourLineChart.axisLeft.setDrawGridLines(false)
            val xAxis: XAxis = binding.ourLineChart.xAxis
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(false)
            binding.ourLineChart.legend.isEnabled = false

            //remove description label
            binding.ourLineChart.description.isEnabled = false

            //add animation
            binding.ourLineChart.animateX(1000, Easing.EaseInSine)
            binding.ourLineChart.data = data
            //refresh
            binding.ourLineChart.invalidate()
        }

        fun retrieveRecordsAndPopulateCharts() {
            val databaseHandler : WaterQualityDatabaseHandler = WaterQualityDatabaseHandler(this)

            val waterQuality : List<WaterQualityDataModel> = databaseHandler.retrieveWaterQualityData()

            val waterQualityIdArray = Array<Int>(waterQuality.size) { 0 }
            val phLevelArray = Array<Double>(waterQuality.size) { 0.0 }
            val temperatureLevelArray = Array<Double>(waterQuality.size) { 0.0 }
            val depthLevelArray = Array<Double>(waterQuality.size) { 0.0 }

            var index = 0
            for (a in waterQuality) {
                waterQualityIdArray[index] = a.waterQualityId
                phLevelArray[index] = a.phLevel
                temperatureLevelArray[index] = a.temperatureLevel
                depthLevelArray[index] = a.depthLevel
                index++
            }

//            populateLineChart(waterQualityIdArray, phLevelArray, temperatureLevelArray, depthLevelArray)

            populateLineChart(phLevelArray)
        }
}