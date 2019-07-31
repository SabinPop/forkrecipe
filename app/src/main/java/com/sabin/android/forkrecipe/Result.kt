package com.sabin.android.forkrecipe

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonUnwrapped

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class Result {
    @JsonUnwrapped
    lateinit var meals: List<Meal>
}