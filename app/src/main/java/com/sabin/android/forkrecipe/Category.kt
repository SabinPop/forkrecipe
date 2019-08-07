package com.sabin.android.forkrecipe

data class Categories (
        val categories: List<Category>
)

data class Category (
    val idCategory : Int?,
    val strCategory: String?,
    val strCategoryThumb: String?,
    val strCategoryDescription: String?
)