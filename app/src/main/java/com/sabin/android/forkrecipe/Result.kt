package com.sabin.android.forkrecipe

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    val meals: List<Meal>
){
    companion object
}