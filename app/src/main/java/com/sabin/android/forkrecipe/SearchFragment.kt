package com.sabin.android.forkrecipe

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SearchView
import com.sabin.android.forkrecipe.R.layout.fragment_search
import com.sabin.android.forkrecipe.Url.Companion.action
import com.sabin.android.forkrecipe.Url.Companion.getUrlText
import com.sabin.android.forkrecipe.Url.Companion.processCategories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchFragment : Fragment() {
    private lateinit var searchView : SearchView
    private lateinit var listView1: ListView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView = view.findViewById(R.id.search_view)
        listView1 = view.findViewById(R.id.category_list_view)
        val categories = action("categories.php", 0, "")

        GlobalScope.launch(Dispatchers.Main) {
            val jsonString = withContext(Dispatchers.Default) {
                getUrlText(categories)
            }
            val result = processCategories(jsonString)
            setupViews(result)
        }
    }
    private fun setupViews(result: Categories) {
        listView1.adapter = CategoryAdapter(this.context, result)

    }

    companion object {
        fun newInstance() : SearchFragment = SearchFragment()
    }

}
