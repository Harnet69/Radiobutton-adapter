package com.harnet.radiobutton.model

import android.annotation.SuppressLint
import com.harnet.radiobutton.R
import xdroid.enumformat.EnumString

class Report(
    val reportType: ReportType,
    var isSelected: Boolean = false

) {
    @SuppressLint("NonConstantResourceId")
    enum class ReportType {
        @EnumString(R.string.wrong_poi)
        WRONG_POI,

        @EnumString(R.string.new_speed_camera)
        NEW_CAMERA;
    }
}