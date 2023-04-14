package com.example.thesis

import android.content.ContentValues
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

//    private lateinit var newRecyclerViewPH : RecyclerView
//    private lateinit var newArrayList : ArrayList<History>
//    private lateinit var newArrayListValue : ArrayList<HistoryValue>

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


//        newRecyclerViewPH = view.findViewById(R.id.recyclerViewPH)
//        newRecyclerViewPH.layoutManager = LinearLayoutManager(context)
//        newRecyclerViewPH.setHasFixedSize(true)
//        newArrayList = arrayListOf<History>()
//        newArrayListValue = arrayListOf<HistoryValue>()


        phHistory.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var count = 0
                var tv = arrayOf(
                    binding.phField0, binding.phField1, binding.phField2, binding.phField3,
                    binding.phField4, binding.phField5, binding.phField6, binding.phField7,
                    binding.phField8, binding.phField9, binding.phField10, binding.phField11
                )

                for (ds in dataSnapshot.children) {

                    tv[count].text = ds.key.toString()
                    count += 1
                    tv[count].text = ds.getValue<Float>().toString()
                    count += 2

                }

//                newRecyclerViewPH.adapter = RecyclerAdapter(newArrayList, newArrayListValue)

            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
        return view
    }


}