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
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import com.sabin.android.forkrecipe.R.layout.*


class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar
    lateinit var bottomNavigation: BottomNavigationView

    val fragment1 = NewFragment.newInstance()
    val fragment2 = SearchFragment.newInstance()
    val fragment3 = FavoritesFragment.newInstance()
    val fm: FragmentManager = supportFragmentManager
    var active : Fragment = fragment1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        toolbar = supportActionBar!!
        setContentView(activity_main)

        bottomNavigation = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        fm.beginTransaction().add(R.id.container, fragment3, "3").hide(fragment3).commit()
        fm.beginTransaction().add(R.id.container, fragment2, "2").hide(fragment2).commit()
        fm.beginTransaction().add(R.id.container,fragment1, "1").commit()


        //val recipeList = Recipe.getRecipesFromFile("recipes.json", this)

        //val adapter = ResultAdapter(this, recipeList)
        //listView.adapter = adapter


    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        item -> when (item.itemId){
            R.id.navigation_new -> {
                toolbar.title = getString(R.string.toolbar_title_new)
                fragment1.activate()
                fm.beginTransaction().hide(active).show(fragment1).commit()
                active = fragment1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                toolbar.title = getString(R.string.toolbar_title_search)
                fragment2.activate()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                toolbar.title = getString(R.string.toolbar_title_favorites)
                fragment3.activate()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    private fun Fragment.activate() {
        fm.beginTransaction().hide(active).show(this).commit()
        active = this
    }


}