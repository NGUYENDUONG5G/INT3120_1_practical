package com.example.a30days.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Daily(
    @StringRes val index:Int,
    @StringRes val tittle:Int,
    @DrawableRes val image:Int,
    @StringRes val content:Int
)
