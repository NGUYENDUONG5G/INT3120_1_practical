package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    @StringRes val name: Int,
    @StringRes val describe: Int,
    @DrawableRes val image: Int
)
