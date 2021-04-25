package com.harnet.radiobutton

import com.harnet.radiobutton.adapter.RadioAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harnet.radiobutton.model.Report

class MainActivity : AppCompatActivity() {
    private val radioAdapter: RadioAdapter = RadioAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val reports = arrayListOf(Report(Report.ReportType.WRONG_POI),
                                  Report(Report.ReportType.NEW_CAMERA))

        val recyclerView = findViewById<RecyclerView>(R.id.radioGroup)
        recyclerView.layoutManager = LinearLayoutManager(this)
        radioAdapter.reports = reports
        recyclerView.adapter = radioAdapter
    }
}