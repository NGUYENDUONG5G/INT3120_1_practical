package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.data.StatisticPlace.getListCategory
import com.example.mycity.model.Place

object StatisticPlace {

    val getListCategory = listOf<String>(
        "Cafe",
        "Park",
        "Restaurant",
        "Shopping"
    )
    val categoryDefault = getListCategory.firstOrNull() ?: "Unknown"

    val getListPlace = mapOf<String, List<Place>>(
        getListCategory[0] to listOf(
            Place(
                R.string.place_cafe_43factory_name,
                R.string.place_cafe_43factory_desc,
                R.drawable.cafe
            ),
            Place(R.string.place_cafe_nia_name, R.string.place_cafe_nia_desc, R.drawable.cafe)
        ),
        getListCategory[2] to listOf(
            Place(
                R.string.place_rest_buncha_name,
                R.string.place_rest_buncha_desc,
                R.drawable.restaurant
            ),
            Place(
                R.string.place_rest_miquang_name,
                R.string.place_rest_miquang_desc,
                R.drawable.restaurant
            ),
            Place(
                R.string.place_rest_hai_san_name,
                R.string.place_rest_hai_san_desc,
                R.drawable.restaurant
            )
        ),
        getListCategory[1] to listOf(
            Place(
                R.string.place_park_sontra_name,
                R.string.place_park_sontra_desc,
                R.drawable.park
            ),
            Place(
                R.string.place_park_asiapark_name,
                R.string.place_park_asiapark_desc,
                R.drawable.park
            ),
            Place(
                R.string.place_park_hoaxuan_name,
                R.string.place_park_hoaxuan_desc,
                R.drawable.park
            )
        ),
        getListCategory[3] to listOf(
            Place(
                R.string.place_shop_vincom_name,
                R.string.place_shop_vincom_desc,
                R.drawable.shopping
            ),
            Place(
                R.string.place_shop_lotte_name,
                R.string.place_shop_lotte_desc,
                R.drawable.shopping
            ),
            Place(R.string.place_shop_con_name, R.string.place_shop_con_desc, R.drawable.shopping)
        )
    )
    val defaultPlace: Place = Place(
        R.string.place_cafe_43factory_name,
        R.string.place_cafe_43factory_desc,
        R.drawable.cafe
    )
}