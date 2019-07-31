package com.sabin.android.forkrecipe

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class Meal(
    val dateModified: Any?,
    @JsonProperty("idMeal")
    val idMeal: String?,
    @JsonProperty("strArea")
    val strArea: String?,
    @JsonProperty("strCategory")
    val strCategory: String?,
    @JsonProperty("strDrinkAlternate")
    val strDrinkAlternate: Any?,
    @JsonProperty("strIngredient1")
    val strIngredient1: String?,
    @JsonProperty("strIngredient2")
    val strIngredient2: String?,
    @JsonProperty("strIngredient3")
    val strIngredient3: String?,
    @JsonProperty("strIngredient4")
    val strIngredient4: String?,
    @JsonProperty("strIngredient5")
    val strIngredient5: String?,
    @JsonProperty("strIngredient6")
    val strIngredient6: String?,
    @JsonProperty("strIngredient7")
    val strIngredient7: String?,
    @JsonProperty("strIngredient8")
    val strIngredient8: String?,
    @JsonProperty("strIngredient9")
    val strIngredient9: String?,
    @JsonProperty("strIngredient10")
    val strIngredient10: String?,
    @JsonProperty("strIngredient11")
    val strIngredient11: String?,
    @JsonProperty("strIngredient12")
    val strIngredient12: String?,
    @JsonProperty("strIngredient13")
    val strIngredient13: String?,
    @JsonProperty("strIngredient14")
    val strIngredient14: String?,
    @JsonProperty("strIngredient15")
    val strIngredient15: String?,
    @JsonProperty("strIngredient16")
    val strIngredient16: String?,
    @JsonProperty("strIngredient17")
    val strIngredient17: String?,
    @JsonProperty("strIngredient18")
    val strIngredient18: String?,
    @JsonProperty("strIngredient19")
    val strIngredient19: String?,
    @JsonProperty("strIngredient20")
    val strIngredient20: String?,
    @JsonProperty("strInstructions")
    val strInstructions: String?,
    @JsonProperty("strMeal")
    val strMeal: String?,
    @JsonProperty("strMealThumb")
    val strMealThumb: String?,
    @JsonProperty("strMeasure1")
    val strMeasure1: String?,
    @JsonProperty("strMeasure2")
    val strMeasure2: String?,
    @JsonProperty("strMeasure3")
    val strMeasure3: String?,
    @JsonProperty("strMeasure4")
    val strMeasure4: String?,
    @JsonProperty("strMeasure5")
    val strMeasure5: String?,
    @JsonProperty("strMeasure6")
    val strMeasure6: String?,
    @JsonProperty("strMeasure7")
    val strMeasure7: String?,
    @JsonProperty("strMeasure8")
    val strMeasure8: String?,
    @JsonProperty("strMeasure9")
    val strMeasure9: String?,
    @JsonProperty("strMeasure10")
    val strMeasure10: String?,
    @JsonProperty("strMeasure11")
    val strMeasure11: String?,
    @JsonProperty("strMeasure12")
    val strMeasure12: String?,
    @JsonProperty("strMeasure13")
    val strMeasure13: String?,
    @JsonProperty("strMeasure14")
    val strMeasure14: String?,
    @JsonProperty("strMeasure15")
    val strMeasure15: String?,
    @JsonProperty("strMeasure16")
    val strMeasure16: String?,
    @JsonProperty("strMeasure17")
    val strMeasure17: String?,
    @JsonProperty("strMeasure18")
    val strMeasure18: String?,
    @JsonProperty("strMeasure19")
    val strMeasure19: String?,
    @JsonProperty("strMeasure20")
    val strMeasure20: String?,
    @JsonProperty("strSource")
    val strSource: String?,
    @JsonProperty("strTags")
    val strTags: String?,
    @JsonProperty("strYoutube")
    val strYoutube: String?
)