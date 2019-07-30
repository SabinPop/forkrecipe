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

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.*
import java.net.URL
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


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



        val mapper = jacksonObjectMapper()

        var r : Result = mapper.readValue<Result>(get())
        print(r)


        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        //val listType = Types.newParameterizedType(List::class.java, Result::class.java)



        val jsonAdapter: JsonAdapter<Result> = moshi.adapter(Result::class.java)
        val result = jsonAdapter.fromJson(get())
        val meals = arrayListOf<Meal>()

        meals.addAll(result!!.meals)

        listView.adapter = ResultAdapter(this, result)

        val context = this
        listView.setOnItemClickListener {_, _, position, _ ->
            val selectedMeal = meals[position]
            val detailIntent = RecipeDetailActivity.newIntent(context, selectedMeal)

            startActivity(detailIntent)
        }
    }
    fun get() : String {
        var result = ""
        GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) {
            result = fetchResponse(pool)
        }
        return result
    }

    private fun action(mainURL: String, action: String, argumentID: Int, argument: String) : String =
            mainURL  + (if (argumentID != 0) action + argument else action)

    private fun getResponse(action : String): String {
        val text = URL(action).readText()
        return text
    }
    private val pool = ThreadPoolExecutor(1, 1, 3L, TimeUnit.SECONDS, LinkedBlockingQueue())

    private suspend fun fetchResponse(threadPoolExecutor: ThreadPoolExecutor): String = coroutineScope {
        withContext(threadPoolExecutor.asCoroutineDispatcher())
        {
            val result = async { getResponse(action(mainURL, "latest.php", 0, "")) }
            result.await()
        }

    }


}