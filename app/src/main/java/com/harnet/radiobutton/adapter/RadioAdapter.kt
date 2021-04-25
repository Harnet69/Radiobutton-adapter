package com.harnet.radiobutton.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.harnet.radiobutton.R
import com.harnet.radiobutton.model.ReportType
import xdroid.enumformat.EnumFormat


class RadioAdapter : RecyclerView.Adapter<RadioAdapter.RadioHolder>() {
    private var lastChecked: RadioButton? = null
    private var lastCheckedPos = 0

    private val diffUtil = object : DiffUtil.ItemCallback<ReportType>(){
        override fun areItemsTheSame(oldItem: ReportType, newItem: ReportType): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ReportType, newItem: ReportType): Boolean {
            return oldItem.type == newItem.type
        }
    }

    private val recyclerViewDiffer = AsyncListDiffer(this, diffUtil)

    var reportTypes: List<ReportType>
        get() = recyclerViewDiffer.currentList
        set(value) = recyclerViewDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_radio_btn,
            parent,
            false
        )
        return RadioHolder(view)
    }

    override fun onBindViewHolder(holder: RadioHolder, position: Int) {
        //TODO implement dataBinding
        val reportName = holder.itemView.findViewById<RadioButton>(R.id.reportNameBtn)

        reportName.apply {
            this.text = EnumFormat.getInstance().format(reportTypes[position].type)
            this.isChecked = reportTypes[position].isSelected
            this.tag = Integer.valueOf(position)
        }

        //for default check in first item
        if(position == 0 && reportTypes[0].isSelected && reportName.isChecked)
        {
            lastChecked = reportName
            lastCheckedPos = 0
        }

        reportName.setOnClickListener {
            val cb: RadioButton = it as RadioButton
            val clickedPos: Int = cb.tag as Int

            if(cb.isChecked)
            {
                if(lastChecked != null)
                {
                    lastChecked?.isChecked = false
                    reportTypes[lastCheckedPos].isSelected = false
                }

                lastChecked = cb
                lastCheckedPos = clickedPos
            }
            else
                lastChecked = null

            reportTypes[clickedPos].isSelected = cb.isChecked
        }

    }

    class RadioHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return reportTypes.size
    }
}