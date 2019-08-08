package com.sabin.android.forkrecipe

import android.app.ActionBar
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.Toolbar
import android.view.*
import android.view.inputmethod.InputMethodManager
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
import android.view.Menu
import android.view.MenuItem


class SearchFragment : Fragment() {
    private lateinit var searchView : SearchView
    private lateinit var searchToolbar : Toolbar
    private lateinit var listView1: ListView

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.searchview_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //searchView = view.findViewById(R.id.search_view)

        searchToolbar = view.findViewById(R.id.search_toolbar)
        searchToolbar.visibility = View.VISIBLE

        /*
        val actionBar = activity.actionBar
        actionBar.customView = searchView
        actionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
         */
        /*

        searchView.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                closeKeyboard(v)
                Toast.makeText(context, "on focus", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(context, "lost focus", Toast.LENGTH_LONG).show()
            }
        }
        */

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

    private fun closeKeyboard(view: View?) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        if (view != null) {
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun setupViews(result: Categories) {
        listView1.adapter = CategoryAdapter(this.context, result)
    }

    companion object {
        fun newInstance() : SearchFragment = SearchFragment()
    }

}
