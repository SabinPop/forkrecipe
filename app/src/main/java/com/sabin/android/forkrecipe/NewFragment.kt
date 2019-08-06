package com.sabin.android.forkrecipe

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.sabin.android.forkrecipe.R.layout.fragment_new
import com.sabin.android.forkrecipe.Url.*
import com.sabin.android.forkrecipe.Url.Companion.action
import com.sabin.android.forkrecipe.Url.Companion.getUrlText
import com.sabin.android.forkrecipe.Url.Companion.processResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewFragment : Fragment() {

    private lateinit var listView: ListView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = view.findViewById(R.id.recipe_list_view)

        val latest = action("latest.php", 0, "")
        GlobalScope.launch(Dispatchers.Main) {
            val jsonString = withContext(Dispatchers.Default) {
                getUrlText(latest)
            }
            val result = processResponse(jsonString)
            setupViews(result)
        }
    }

    private fun setupViews(result: Result) {
        listView.adapter = ResultAdapter(this.context, result)

        listView.setOnItemClickListener {_, _, position, _ ->
            val selectedMeal = result.meals[position]
            val detailIntent = RecipeDetailActivity.newIntent(this.context, selectedMeal)
            startActivity(detailIntent)
        }
    }



    companion object {
        fun newInstance(): NewFragment = NewFragment()
    }
}