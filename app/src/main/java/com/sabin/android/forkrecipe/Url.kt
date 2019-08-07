package com.sabin.android.forkrecipe

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URL

class Url (action: String, argumentID: Int, argument: String) {

    companion object{
        const val mainURL: String = "https://www.themealdb.com/api/json/v1/1/"

        fun getUrlText(url: String) : String {
            return URL(url).readText()
        }
        fun processResponse(jsonString: String) : Result =
                ObjectMapper().registerModule(KotlinModule()).readValue(jsonString)
        fun processCategories(jsonString: String) : Categories =
                ObjectMapper().registerModule(KotlinModule()).readValue(jsonString)

        fun action(action: String, argumentID: Int, argument: String) : String =
               mainURL  + (if (argumentID != 0) "$action?$argument" else action)
    }
}