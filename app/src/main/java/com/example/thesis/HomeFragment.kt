package com.example.thesis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thesis.databinding.FragmentHomeBinding
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.graphics.Color
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        readAndWrite()
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun readAndWrite() {
        val phLevel = binding.phLevelTextView
        val temperatureLevel = binding.temperatureLevelTextView
        val depthLevel = binding.depthLevelTextView
        val fishCount = binding.fishCountTextView
        val rssi = binding.rssiTextView
        val snr = binding.snrTextView

        val phToolTip = binding.phToolTip
        val temperatureToolTip = binding.temperatureToolTip
        val depthToolTip = binding.depthLevelToolTip
        val fishCountToolTip = binding.fishCountToolTip
        val rssiToolTip = binding.rssiToolTip
        val snrToolTip = binding.snrToolTip

        val dateUpdatedWaterQuality = binding.dateUpdatedWaterQuality
        val dateUpdatedFishCounter = binding.dateUpdatedFishCounter
        val dateUpdatedLoRa = binding.dateUpdatedLoRa

        val database =
            Firebase.database("https://thesis-4530c-default-rtdb.asia-southeast1.firebasedatabase.app/")
        val myRefPH = database.reference.child("/WaterQualityMonitoringDevice/0xAA/pH/LatestValue")
        val myRefTemperature = database.reference.child("/WaterQualityMonitoringDevice/0xAA/Temperature/LatestValue")
        val myRefDepth = database.reference.child("/WaterQualityMonitoringDevice/0xAA/Depth/LatestValue")
        val myRefDateUpdatedWaterQuality = database.reference.child("/WaterQualityMonitoringDevice/0xAA/LatestValueTaken")

        val myRefFishCount = database.reference.child("/FishCounterDevice/0xBB/LatestValue")
        val myRefDateUpdatedFishCounter = database.reference.child("/FishCounterDevice/0xBB/LatestValueTaken")

        val myRefRSSI = database.reference.child("/LoRa/RSSI/LatestValue")
        val myRefSNR = database.reference.child("/LoRa/SNR/LatestValue")
        val myRefDateUpdatedLoRa = database.reference.child("/LoRa/LatestValueTaken")

        myRefDateUpdatedLoRa.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue<String>()
                dateUpdatedLoRa.text = "Date Updated: $value"
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        myRefDateUpdatedFishCounter.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue<String>()
                dateUpdatedFishCounter.text = "Date Updated: $value"
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        myRefDateUpdatedWaterQuality.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue<String>()
                dateUpdatedWaterQuality.text = "Date Updated: $value"
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        // Read from the database
        myRefPH.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue<Float>()
                if (value != null) {
                    phLevel.text = value.toString()
                        if (value < 4 || value > 11) {
                            phLevel.setTextColor(Color.RED)
                            phToolTip.setOnClickListener {
                                createPopUp("Danger! The acidity of the Fishpond is in deadly level.")
                            }
                        } else if (value in 6.0..9.0) {
                            phLevel.setTextColor(Color.GREEN)
                            phToolTip.setOnClickListener {
                                createPopUp("The acidity of the Fishpond is in normal level.")
                            }

                        } else if ((value in 4.0..6.0) || (value in 9.0..11.0)) {
                            phLevel.setTextColor(Color.rgb(255, 165, 0))
                            phToolTip.setOnClickListener {
                                createPopUp("Warning! The acidity of the Fishpond is not in optimal level.")
                            }
                        }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        myRefTemperature.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue<Float>()
                if (value != null) {
                temperatureLevel.text = value.toString() + "°C"

                        if (value < 16 || value > 32) {
                            temperatureLevel.setTextColor(Color.RED)
                            temperatureToolTip.setOnClickListener {
                                createPopUp("Danger! The temperature of the Fishpond is in deadly level. ")
                            }

                        }
                        else if (value in 26.0..32.0) {
                            temperatureLevel.setTextColor(resources.getColor(R.color.green))
                            temperatureToolTip.setOnClickListener {
                                createPopUp("The temperature of the Fishpond is in optimal level.")
                            }

                        }
                        else if (value in 16.0..25.9) {
                            temperatureLevel.setTextColor(Color.rgb(255, 165, 0))
                            temperatureToolTip.setOnClickListener {
                                createPopUp("Warning! The acidity of the Fishpond is not in optimal level.")
                            }
                        }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        myRefDepth.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue<Float>()
                if (value != null) {
                depthLevel.text = value.toString() + " cm"
                depthToolTip.setOnClickListener {
                        createPopUp("How deep is the pond")
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        myRefFishCount.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue<Int>()
                if (value != null) {
                fishCount.text = value.toString()
                fishCountToolTip.setOnClickListener {
                        createPopUp("Total Number of Fishes Counted")
                    }
                }
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    fishCountToolTip.tooltipText = "Its harvest season. I'm a big fish, i'm a big fish, i'm a big fish\n-Sza"
//                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        myRefRSSI.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue<Float>()
                if (value != null) {
                    rssi.text = value.toString()
                    rssiToolTip.setOnClickListener {
                        createPopUp("RSSI = Received Signal Strength Indicator\n" +
                                "The closer the RSSI is to zero, the stronger the received signal.")
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        myRefSNR.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue<Float>()
                if (value != null) {
                    snr.text = value.toString()
                    snrToolTip.setOnClickListener {
                        createPopUp("SNR = Signal to Noise Ratio\n" +
                                "Determines the quality of the received signal.")
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

    }
    fun createPopUp(Text: String, Value: Float?=null) {
        val dialogBuilder = AlertDialog.Builder(context)
        val popUpView = layoutInflater.inflate(R.layout.popup, null)

        val text = popUpView.findViewById<TextView>(R.id.textPopUp)


        dialogBuilder.setView(popUpView)
        val dialog = dialogBuilder.create()

        dialog.show()

        text.text = Text
    }
}