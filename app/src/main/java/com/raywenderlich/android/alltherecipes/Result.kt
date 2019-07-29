package com.raywenderlich.android.alltherecipes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    val meals: List<Meal>
)