/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package com.sabin.android.forkrecipe

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL


class MainActivity : AppCompatActivity() {

    val mainURL : String = "https://www.themealdb.com/api/json/v1/1/"
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.recipe_list_view)


        //val recipeList = Recipe.getRecipesFromFile("recipes.json", this)

        //val adapter = ResultAdapter(this, recipeList)
        //listView.adapter = adapter

        val latest = action(mainURL, "latest.php", 0, "")
        val filterByCategory = action(mainURL, "filter.php", 1, "c=")
        val filterByIngredient= action(mainURL, "filter.php", 1, "i=")
        GlobalScope.launch(Dispatchers.Main) {
            val jsonString = withContext(Dispatchers.Default) {
                getUrlText(latest)
            }
            val result = processResponse(jsonString)
            setupViews(result)
        }
    }

    private fun setupViews(result: Result) {
        listView.adapter = ResultAdapter(this, result)

        listView.setOnItemClickListener {_, _, position, _ ->
            val selectedMeal = result.meals[position]
            val detailIntent = RecipeDetailActivity.newIntent(this@MainActivity, selectedMeal)
            startActivity(detailIntent)
        }
    }

    private fun getUrlText(url: String) : String {
        return URL(url).readText()
    }
    private fun processResponse(jsonString: String) : Result =
            ObjectMapper().registerModule(KotlinModule()).readValue(jsonString)

    private fun action(mainURL: String, action: String, argumentID: Int, argument: String) : String =
            mainURL  + (if (argumentID != 0) "$action?$argument" else action)

}