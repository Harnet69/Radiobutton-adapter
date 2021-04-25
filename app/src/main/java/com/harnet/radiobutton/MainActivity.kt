package com.harnet.radiobutton

import com.harnet.radiobutton.adapter.ReportTypeAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harnet.radiobutton.model.ReportType

class MainActivity : AppCompatActivity() {
    private val reportTypeAdapter: ReportTypeAdapter = ReportTypeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val reports = arrayListOf(ReportType(ReportType.Type.WRONG_POI),
                                  ReportType(ReportType.Type.NEW_CAMERA))

        val recyclerView = findViewById<RecyclerView>(R.id.radioGroup)
        recyclerView.layoutManager = LinearLayoutManager(this)
        reportTypeAdapter.reportTypes = reports
        recyclerView.adapter = reportTypeAdapter

        findViewById<Button>(R.id.sendBtn).setOnClickListener {
            val checkedReportType = reportTypeAdapter.reportTypes.first { it.isSelected }

            Toast.makeText(this, checkedReportType.type.toString(), Toast.LENGTH_LONG).show()
        }
    }
}