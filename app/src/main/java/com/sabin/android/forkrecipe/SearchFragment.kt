package com.sabin.android.forkrecipe

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sabin.android.forkrecipe.R.layout.fragment_search
import com.sabin.android.forkrecipe.Url.Companion.action
import com.sabin.android.forkrecipe.Url.Companion.getUrlText
import com.sabin.android.forkrecipe.Url.Companion.mainURL
import com.sabin.android.forkrecipe.Url.Companion.processResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val latest = action("latest.php", 0, "")
    }

    companion object {
        fun newInstance() : SearchFragment = SearchFragment()
    }

}
