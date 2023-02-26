package com.example.thesis

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class WaterQualityDatabaseHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "waterQualityDB"
        private val MAIN_TABLE = "mainTable"
        private val ID_FIELD = "_id"
        private val PH_FIELD = "ph"
        private val TEMPERATURE_FIELD = "temperature"
        private val DEPTH_FIELD = "depth"
    }
        override fun onCreate(ourDB: SQLiteDatabase?) {
            //creating our table with the respective fields
            val CREATE_MAIN_TABLE = ("CREATE TABLE " + MAIN_TABLE + "("
                    + ID_FIELD + " INTEGER PRIMARY KEY,"
                    + PH_FIELD + " REAL,"
                    + TEMPERATURE_FIELD + " REAL,"
                    + DEPTH_FIELD + " REAL" + ")")
            //executing the create table query
            ourDB?.execSQL(CREATE_MAIN_TABLE)
        }

        override fun onUpgrade(ourDB: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            ourDB!!.execSQL("DROP TABLE IF EXISTS " + MAIN_TABLE)
            onCreate(ourDB)
        }

        fun addWaterQualityData(waterQuality : WaterQualityDataModel) : Long {
            val ourDB = this.writableDatabase
            val ourContentValues = ContentValues()
            ourContentValues.put(ID_FIELD, waterQuality.waterQualityId)
            ourContentValues.put(PH_FIELD, waterQuality.phLevel)
            ourContentValues.put(TEMPERATURE_FIELD, waterQuality.temperatureLevel)
            ourContentValues.put(DEPTH_FIELD, waterQuality.depthLevel)
            val success = ourDB.insert(MAIN_TABLE, null, ourContentValues)

            ourDB.close()
            return success
        }

        @SuppressLint("Range")
        fun retrieveWaterQualityData():List<WaterQualityDataModel> {
            val waterQualityDataList:ArrayList<WaterQualityDataModel> = ArrayList<WaterQualityDataModel>()
            val selectQuery = "SELECT * FROM $MAIN_TABLE"
            val ourDB = this.readableDatabase
            var ourCursor : Cursor? = null

            try {
                ourCursor = ourDB.rawQuery(selectQuery, null)
            } catch (e: SQLException) {
                ourDB.execSQL(selectQuery)
                return ArrayList()
            }

            var waterQualityIdReturned : Int
            var waterQualityPhLevelReturned : Double
            var waterQualityTemperatureLevelReturned : Double
            var waterQualityDepthLevelReturned : Double

            if (ourCursor.moveToFirst()) {
                do {
                    waterQualityIdReturned = ourCursor.getInt(ourCursor.getColumnIndex("_id"))
                    waterQualityPhLevelReturned = ourCursor.getDouble(ourCursor.getColumnIndex("ph"))
                    waterQualityTemperatureLevelReturned = ourCursor.getDouble(ourCursor.getColumnIndex("temperature"))
                    waterQualityDepthLevelReturned = ourCursor.getDouble(ourCursor.getColumnIndex("depth"))

                    val waterQualityRow = WaterQualityDataModel(waterQualityId = waterQualityIdReturned,
                    phLevel = waterQualityPhLevelReturned, temperatureLevel = waterQualityTemperatureLevelReturned,
                    depthLevel = waterQualityDepthLevelReturned)

                    waterQualityDataList.add(waterQualityRow)
                } while (ourCursor.moveToNext())
            }
            return waterQualityDataList
        }



}