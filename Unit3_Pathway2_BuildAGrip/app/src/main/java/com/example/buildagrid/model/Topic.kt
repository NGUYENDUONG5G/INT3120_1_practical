package com.example.buildagrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val nameCourse: Int,
    val amountCourse: Int,
    @DrawableRes val imageCourse: Int
)
