package com.example.a30days.model

import com.example.a30days.R

object DailyRepository{

    val dailies =listOf(
        Daily(
            index = R.string._1,
            tittle = R.string.title_1,
            image = R.drawable.image_1,
            content = R.string.desc_1
        ),
        Daily(
            index = R.string._2,
            tittle = R.string.title_2,
            image = R.drawable.image_2,
            content = R.string.desc_2
        ),
        Daily(
            index = R.string._3,
            tittle = R.string.title_3,
            image = R.drawable.image_3,
            content = R.string.desc_3
        ),
        Daily(
            index = R.string._4,
            tittle = R.string.title_4,
            image = R.drawable.image_4,
            content = R.string.desc_4
        ),
    )
}