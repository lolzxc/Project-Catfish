package com.example.thesis

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.thesis.databinding.FragmentHistoryBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.thesis.databinding.FragmentHomeBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.database.ktx.database
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue


class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root
//        setHasOptionsMenu(true)

        val database =
            Firebase.database("https://thesis-4530c-default-rtdb.asia-southeast1.firebasedatabase.app/")
        val phHistory = database.reference.child("/WaterQualityMonitoringDevice/0xAA/pH/")
        val temperatureHistory = database.reference.child("/WaterQualityMonitoringDevice/0xAA/Temperature")
        val depthHistory = database.reference.child("/WaterQualityMonitoringDevice/0xAA/Depth")
        val fishCounterHistory = database.reference.child("/FishCounterDevice/0xBB")
        val rssiHistory = database.reference.child("/LoRa/RSSI")
        val snrHistory = database.reference.child("/LoRa/SNR")

        phHistory.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var count = 0
                var tv = arrayOf(
                    binding.phField0, binding.phField1, binding.phField2, binding.phField3,
                    binding.phField4, binding.phField5, binding.phField6, binding.phField7,
                    binding.phField8, binding.phField9, binding.phField10, binding.phField11,
                    binding.phField12, binding.phField13, binding.phField14, binding.phField15,
                    binding.phField16, binding.phField17, binding.phField18, binding.phField19,
                    binding.phField20, binding.phField21, binding.phField22, binding.phField23,
                    binding.phField24, binding.phField25, binding.phField26, binding.phField27,
                    binding.phField28, binding.phField29, binding.phField30, binding.phField31,
                    binding.phField32, binding.phField33, binding.phField34, binding.phField35,
                )

                for (ds in dataSnapshot.children.reversed()) {

                    if(ds.key.toString() == "LatestValue") {
                        continue
                    }

                    if (count == 35) {
                        break
                    }

                    tv[count].text = ds.key.toString().split(":").first()
                    count += 1

                    tv[count].text = ds.key.toString().split(":").takeLast(3).joinToString(":")
                    count += 1

                    tv[count].text = ds.getValue<Float>().toString()
                    count += 1

                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
        temperatureHistory.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var count = 0
                var tv = arrayOf(
                    binding.temperatureField0, binding.temperatureField1, binding.temperatureField2, binding.temperatureField3,
                    binding.temperatureField4, binding.temperatureField5, binding.temperatureField6, binding.temperatureField7,
                    binding.temperatureField8, binding.temperatureField9, binding.temperatureField10, binding.temperatureField11,
                    binding.temperatureField12, binding.temperatureField13, binding.temperatureField14, binding.temperatureField15,
                    binding.temperatureField16, binding.temperatureField17, binding.temperatureField18, binding.temperatureField19,
                    binding.temperatureField20, binding.temperatureField21, binding.temperatureField22, binding.temperatureField23,
                    binding.temperatureField24, binding.temperatureField25, binding.temperatureField26, binding.temperatureField27,
                    binding.temperatureField28, binding.temperatureField29, binding.temperatureField30, binding.temperatureField31,
                    binding.temperatureField32, binding.temperatureField33, binding.temperatureField34, binding.temperatureField35,
                )

                for (ds in dataSnapshot.children.reversed()) {

                    if(ds.key.toString() == "LatestValue") {
                        continue
                    }

                    if (count == 35) {
                        break
                    }

                    tv[count].text = ds.key.toString().split(":").first()
                    count += 1

                    tv[count].text = ds.key.toString().split(":").takeLast(3).joinToString(":").toString()
                    count += 1

                    tv[count].text = ds.getValue<Float>().toString()
                    count += 1

                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
        depthHistory.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var count = 0
                var tv = arrayOf(
                    binding.depthField0, binding.depthField1, binding.depthField2, binding.depthField3,
                    binding.depthField4, binding.depthField5, binding.depthField6, binding.depthField7,
                    binding.depthField8, binding.depthField9, binding.depthField10, binding.depthField11,
                    binding.depthField12, binding.depthField13, binding.depthField14, binding.depthField15,
                    binding.depthField16, binding.depthField17, binding.depthField18, binding.depthField19,
                    binding.depthField20, binding.depthField21, binding.depthField22, binding.depthField23,
                    binding.depthField24, binding.depthField25, binding.depthField26, binding.depthField27,
                    binding.depthField28, binding.depthField29, binding.depthField30, binding.depthField31,
                    binding.depthField32, binding.depthField33, binding.depthField34, binding.depthField35,
                )

                for (ds in dataSnapshot.children.reversed()) {

                    if(ds.key.toString() == "LatestValue") {
                        continue
                    }

                    if (count == 35) {
                        break
                    }

                    tv[count].text = ds.key.toString().split(":").first()
                    count += 1

                    tv[count].text = ds.key.toString().split(":").takeLast(3).joinToString(":").toString()
                    count += 1

                    tv[count].text = ds.getValue<Float>().toString()
                    count += 1

                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
        fishCounterHistory.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var count = 0
                var tv = arrayOf(
                    binding.fishcountField0, binding.fishcountField1, binding.fishcountField2, binding.fishcountField3,
                    binding.fishcountField4, binding.fishcountField5, binding.fishcountField6, binding.fishcountField7,
                    binding.fishcountField8, binding.fishcountField9, binding.fishcountField10, binding.fishcountField11,
                    binding.fishcountField12, binding.fishcountField13, binding.fishcountField14, binding.fishcountField15,
                    binding.fishcountField16, binding.fishcountField17, binding.fishcountField18, binding.fishcountField19,
                    binding.fishcountField20, binding.fishcountField21, binding.fishcountField22, binding.fishcountField23,
                    binding.fishcountField24, binding.fishcountField25, binding.fishcountField26, binding.fishcountField27,
                    binding.fishcountField28, binding.fishcountField29, binding.fishcountField30, binding.fishcountField31,
                    binding.fishcountField32, binding.fishcountField33, binding.fishcountField34, binding.fishcountField35,
                )

                for (ds in dataSnapshot.children.reversed()) {

                    if(ds.key.toString() == "LatestValue" || ds.key.toString() == "LatestValueTaken") {
                        continue
                    }

                    if (count == 35) {
                        break
                    }

                    tv[count].text = ds.key.toString().split(":").first()
                    count += 1

                    tv[count].text = ds.key.toString().split(":").takeLast(3).joinToString(":").toString()
                    count += 1

                    tv[count].text = ds.getValue<Float>().toString()
                    count += 1

                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
        rssiHistory.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var count = 0
                var tv = arrayOf(
                    binding.rssiField0, binding.rssiField1, binding.rssiField2, binding.rssiField3,
                    binding.rssiField4, binding.rssiField5, binding.rssiField6, binding.rssiField7,
                    binding.rssiField8, binding.rssiField9, binding.rssiField10, binding.rssiField11,
                    binding.rssiField12, binding.rssiField13, binding.rssiField14, binding.rssiField15,
                    binding.rssiField16, binding.rssiField17, binding.rssiField18, binding.rssiField19,
                    binding.rssiField20, binding.rssiField21, binding.rssiField22, binding.rssiField23,
                    binding.rssiField24, binding.rssiField25, binding.rssiField26, binding.rssiField27,
                    binding.rssiField28, binding.rssiField29, binding.rssiField30, binding.rssiField31,
                    binding.rssiField32, binding.rssiField33, binding.rssiField34, binding.rssiField35,
                )

                for (ds in dataSnapshot.children.reversed()) {

                    if(ds.key.toString() == "LatestValue") {
                        continue
                    }

                    if (count == 35) {
                        break
                    }

                    tv[count].text = ds.key.toString().split(":").first()
                    count += 1

                    tv[count].text = ds.key.toString().split(":").takeLast(3).joinToString(":").toString()
                    count += 1

                    tv[count].text = ds.getValue<Float>().toString()
                    count += 1

                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
        snrHistory.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var count = 0
                var tv = arrayOf(
                    binding.snrField0, binding.snrField1, binding.snrField2, binding.snrField3,
                    binding.snrField4, binding.snrField5, binding.snrField6, binding.snrField7,
                    binding.snrField8, binding.snrField9, binding.snrField10, binding.snrField11,
                    binding.snrField12, binding.snrField13, binding.snrField14, binding.snrField15,
                    binding.snrField16, binding.snrField17, binding.snrField18, binding.snrField19,
                    binding.snrField20, binding.snrField21, binding.snrField22, binding.snrField23,
                    binding.snrField24, binding.snrField25, binding.snrField26, binding.snrField27,
                    binding.snrField28, binding.snrField29, binding.snrField30, binding.snrField31,
                    binding.snrField32, binding.snrField33, binding.snrField34, binding.snrField35,
                )

                for (ds in dataSnapshot.children.reversed()) {

                    if(ds.key.toString() == "LatestValue") {
                        continue
                    }

                    if (count == 35) {
                        break
                    }

                    tv[count].text = ds.key.toString().split(":").first()
                    count += 1

                    tv[count].text = ds.key.toString().split(":").takeLast(3).joinToString(":").toString()
                    count += 1

                    tv[count].text = ds.getValue<Float>().toString()
                    count += 1

                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        return view
    }


}